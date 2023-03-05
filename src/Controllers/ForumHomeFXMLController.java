/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.PostHomeFXMLController;
import Entite.Sujet;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class ForumHomeFXMLController implements Initializable {

    private List<Sujet> sujets;

    @FXML
    private TextField toResearch;
    @FXML
    private Button rechercheBtn;
    @FXML
    private GridPane gridpane;
    ServiceSujet ser = new ServiceSujet();
    int column = 0;
    int row = 1;

    public void loadData() {
        try {
            sujets = new ArrayList<>(ser.readAll());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        sujets.forEach((c) -> {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/GUI/PostHomeFXML.fxml"));
            Pane pane;

            try {
                pane = fxmlloader.load();
                PostHomeFXMLController Controller = fxmlloader.getController();
                Controller.setData(c);

                if (column == 3) {
                    column = 0;
                }
                ++row;
                gridpane.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void Rechercher(ActionEvent event) {
         // Récupération de la chaîne de caractères de recherche
    String searchQuery = toResearch.getText();

    // Si la chaîne de recherche est vide, on recharge les données initiales
    if (searchQuery == null || searchQuery.isEmpty()) {
        gridpane.getChildren().clear();
        loadData();
        return;
    }

    // Sinon, on filtre les sujets qui contiennent la chaîne de recherche dans leur titre ou contenu
    List<Sujet> filteredSujets = new ArrayList<>();
    for (Sujet sujet : sujets) {
        if (sujet.getTitre_sujet().contains(searchQuery)) {
            filteredSujets.add(sujet);
        }
    }

    // On efface les éléments actuels de la grille de sujets
    gridpane.getChildren().clear();
    row = 1;
    column = 0;

    // On ajoute les sujets filtrés à la grille
    filteredSujets.forEach((c) -> {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/GUI/PostHomeFXML.fxml"));
        Pane pane;

        try {
            pane = fxmlloader.load();
            PostHomeFXMLController Controller = fxmlloader.getController();
            Controller.setData(c);

            if (column == 3) {
                column = 0;
            }
            ++row;
            gridpane.add(pane, column, row);
            GridPane.setMargin(pane, new Insets(20));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    });
    }

}
