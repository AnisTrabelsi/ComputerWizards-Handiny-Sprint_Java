/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Sujet;
import Services.ServiceSujet;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class SujetListFXMLController implements Initializable {

    @FXML
    private ComboBox<String> id_categorie;
    @FXML
    private ComboBox<String> tags;

    private ObservableList<Sujet> objects = FXCollections.observableArrayList();
    @FXML
    private GridPane gridpane;
    @FXML
    private Button updateBtn;
    @FXML
    private ColumnConstraints titre_sujet;
    @FXML
    private ColumnConstraints date_creation_sujet;
    @FXML
    private ColumnConstraints contenu_sujet;
    @FXML
    private ColumnConstraints nb_commentaires;
    @FXML
    private ColumnConstraints etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceSujet service = new ServiceSujet();
            ObservableList liste, liste2;
            liste = FXCollections.observableArrayList(service.readNoms());
            id_categorie.setItems(liste);
            liste2 = FXCollections.observableArrayList(service.getAllTags());
            tags.setItems(liste2);
        } catch (SQLException ex) {
            Logger.getLogger(SujetListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void handleEditButton(Sujet get) {
//        System.out.println("Edit button clicked for object " + get.getTitre_sujet());

        try {
            ServiceSujet service = new ServiceSujet();
            ObservableList liste, liste2;
            liste = FXCollections.observableArrayList(service.readNoms());
            id_categorie.setItems(liste);
            liste2 = FXCollections.observableArrayList(service.getAllTags());
            tags.setItems(liste2);
            try {
                objects.addAll(service.readAll());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Add the objects to the GridPane
            for (int i = 0; i < objects.size(); i++) {
                final int index = i; // create a final copy of i
                Label nameLabel = new Label(objects.get(index).getTitre_sujet());
                Label descLabel = new Label(dateFormatter.format((TemporalAccessor) objects.get(index).getDate_creation_sujet()));
                Label descLabe2 = new Label(objects.get(index).getContenu_sujet());
                Label descLabe3 = new Label(String.valueOf(objects.get(index).getNb_commentaires()));
                Label descLabe4 = new Label(objects.get(index).getEtat());
                gridpane.addRow(index, nameLabel, descLabel, descLabe2, descLabe3, descLabe4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }                
        

    }

    @FXML
    private void update(ActionEvent event) throws SQLException{
        updateBtn.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Sujet modifié avec succés!");
            alert.show();
        });
    }

}
