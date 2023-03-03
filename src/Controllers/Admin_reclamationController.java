/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Reclamation;
import Services.ServiceReclamation;
import java.net.URL;
import java.sql.SQLException;
//import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Admin_reclamationController implements Initializable {

    @FXML
    private ListView<Reclamation> listview;
    @FXML
    private Label description;

    private String etat_reclam[] = {"En attente", "En cours de traitement ", "En attente de réponse ", "Résolue ", "Fermée", "Rejetée "};
    @FXML
    private ComboBox<String> etat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etat.setItems(FXCollections.observableArrayList(etat_reclam));

    }

    public void list_reclamation() throws SQLException {
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = new Reclamation();
        List<Reclamation> reclam;
        try {

            reclam = sr.readAll();

            ObservableList<Reclamation> obs = FXCollections.observableList(reclam);
            listview.setItems(obs);
            listview.setCellFactory(param -> new ReclamationCell());
        } catch (SQLException ex) {
            Logger.getLogger(Read_user_by_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void read_all_reclamation(ActionEvent event) {
        Reclamation selectedReclamation = listview.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            description.setText(selectedReclamation.getDescription());
        }

    }

}
