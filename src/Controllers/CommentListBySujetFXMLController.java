/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Entite.Sujet;
import Services.ServiceCommentaire;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CommentListBySujetFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button returnlistsujetsBtn;
    @FXML
    private Label nbcomments;
    @FXML
    private Button refreshBtn;
    @FXML
    private GridPane CommentGrid;
    ServiceCommentaire ser = new ServiceCommentaire();
    int column = 0;
    int row = 1;
    private List<Commentaire> comments;
    private Sujet sujet = new Sujet();
    ServiceSujet ss = new ServiceSujet();
    @FXML
    private Button MostLikedBtn;

    /**
     * Initializes the controller class.
     */
    public void setSujet(Sujet s) {
        try {
            sujet = s;
            System.out.println(sujet.getId_sujet());
            ss.update(sujet);
        } catch (SQLException ex) {
            Logger.getLogger(CommentListBySujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() {
        try {
            System.out.println("l'id est" + ss.findById(sujet.getId_sujet()));

            comments = new ArrayList<>(ser.CommentsBySujet(sujet));
            nbcomments.setText(String.valueOf(comments.size() + " Réponses"));

            comments.forEach((c) -> {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui_handiny/CommentaireGridFXML.fxml"));
                Pane pane;

                try {
                    pane = fxmlloader.load();
                    CommentaireGridFXMLController Controller = fxmlloader.getController();
                    Controller.setData(c);

                    System.out.println(c.getSujet().getId_sujet());

                    if (column == 3) {
                        column = 0;
                    }
                    ++row;
                    CommentGrid.add(pane, column, row);
                    GridPane.setMargin(pane, new Insets(20));
                } catch (IOException ex) {
                    Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(CommentListBySujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void returnlistsujets(MouseEvent event) {
        returnlistsujetsBtn.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/ForumHomeFXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void refresh(ActionEvent event) {
        CommentGrid.getChildren().clear();
        loadData();
    }

    @FXML
    private void TriParMentions(ActionEvent event) {
        Collections.sort(comments, new Comparator<Commentaire>() {
        @Override
        public int compare(Commentaire c1, Commentaire c2) {
            return Integer.compare(c2.getNb_mentions(), c1.getNb_mentions());
        }
    });

    // Effacez les commentaires existants de la grille
    CommentGrid.getChildren().clear();

    // Réinitialiser la position de la colonne et de la rangée
    column = 0;
    row = 1;

    // Ajouter les commentaires triés à la grille
    for (Commentaire c : comments) {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/gui_handiny/CommentaireGridFXML.fxml"));
        Pane pane;
        try {
            pane = fxmlloader.load();
            CommentaireGridFXMLController Controller = fxmlloader.getController();
            Controller.setData(c);

            if (column == 3) {
                column = 0;
            }
            ++row;
            CommentGrid.add(pane, column, row);
            GridPane.setMargin(pane, new Insets(20));
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

}
