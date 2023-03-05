/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.PostHomeFXMLController;
import Entite.Sujet;
import Services.ServiceCategorie;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class FiltresFXMLController implements Initializable {

    private List<Sujet> sujets;
    @FXML
    private ComboBox<String> combocat;
    @FXML
    private ComboBox<String> comboEtat;
    @FXML
    private Button TrierBtn;
    @FXML
    private GridPane gridpane;
    ServiceCategorie sc = new ServiceCategorie();
    ServiceSujet ss = new ServiceSujet();

    int column = 0;
    int row = 1;
    @FXML
    private Button rechercherBtn;

    /**
     * Initializes the controller class.
     */
    private void loadData() {
        try {
            sujets = new ArrayList<>(ss.readAll());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        // Récupération de la chaîne de caractères de recherche
        String cat = combocat.getValue();
        String etat = comboEtat.getValue();

        // Si la chaîne de recherche est vide, on recharge les données initiales
        if (cat == null || etat.isEmpty()) {
            return;
        }

        // Sinon, on filtre les sujets qui contiennent la chaîne de recherche dans leur titre ou contenu
        List<Sujet> filteredSujets = sujets.stream()
                .filter(sujet -> sujet.getCat().getNom_categorie().equalsIgnoreCase(cat) && sujet.getEtat().equalsIgnoreCase(etat))
                .collect(Collectors.toList());

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList liste, liste2;
        try {
            liste = FXCollections.observableArrayList(sc.readNoms());
            combocat.setItems(liste);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        liste2 = FXCollections.observableArrayList("ouvert", "bloqué", "fermé");
        comboEtat.setItems(liste2);
    }

    @FXML
    private void Trier(ActionEvent event) {
        String cat = combocat.getValue();
        String etat = comboEtat.getValue();

        gridpane.getChildren().clear();
        if (combocat.getValue() == null || comboEtat.getValue() == null) {
            return;
        }
        try {
            sujets = new ArrayList<>(ss.readAll());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        Comparator<Sujet> comparator = (s1, s2) -> s1.getTitre_sujet().compareToIgnoreCase(s2.getTitre_sujet());
        sujets.sort(comparator);
        List<Sujet> filteredSujets = sujets.stream()
                .filter(sujet -> sujet.getCat().getNom_categorie().equalsIgnoreCase(cat) && sujet.getEtat().equalsIgnoreCase(etat))
                .collect(Collectors.toList());
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

    @FXML
    private void rechercher(ActionEvent event) {
        gridpane.getChildren().clear();
        loadData();
    }

}
