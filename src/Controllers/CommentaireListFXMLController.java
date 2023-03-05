/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Services.ServiceCommentaire;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class CommentaireListFXMLController implements Initializable {

    private List<Commentaire> comments;
    @FXML
    private BorderPane bp;
    @FXML
    private Label nbcomments;
    @FXML
    private Button categoriesReadAllBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private GridPane CommentGrid;
    ServiceCommentaire ser = new ServiceCommentaire();
    int column = 0;
    int row = 1;
    @FXML
    private Button returnlistsujetsBtn;

    /**
     * Initializes the controller class.
     */
    public void loadData() {
        try {
            comments = new ArrayList<>(ser.readAll());
            nbcomments.setText(String.valueOf(comments.size() + " Réponses"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        comments.forEach((c) -> {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/GUI/CommentaireGridFXML.fxml"));
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

    }

    @FXML
    private void categoriesReadAll(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CategorieListGridPaneFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        CommentGrid.getChildren().clear();
        loadData();
    }

    @FXML
    private void returnlistsujets(MouseEvent event) {
        returnlistsujetsBtn.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SujetListFXML.fxml"));
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
}
