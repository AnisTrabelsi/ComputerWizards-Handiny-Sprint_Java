/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Reclamation;
import Entite.Utilisateur;
import Services.ServiceReclamation;
import Services.ServiceUtilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author Chayma
 */
public class Ajouter_reclamationController implements Initializable {

    @FXML
    private TextArea description_ajout_reclamation;
    String description;
    @FXML
    private Button envoyer_reclamation;
    @FXML
    private ChoiceBox<String> choice_box;
    // On a initilaisé les elements de choicebox aux differents types de reclamation
    private String[] Type_reclamations = {"Couvoiturage", "Site", "Location de voiture", "Don"};
    Reclamation r = new Reclamation();
    Utilisateur u;
    ServiceReclamation sr = new ServiceReclamation();
    ServiceUtilisateur su = new ServiceUtilisateur();

    public void get_type(ActionEvent event) {
        String type = choice_box.getValue();
        int index = Arrays.asList(Type_reclamations).indexOf(Type_reclamations); // rechercher l'index de la région dans le tableau regions[]
        if (index >= 0) {
           r.setType_reclamation(Type_reclamations[index]);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice_box.getItems().addAll(Type_reclamations);
    }

    @FXML
    private void Envoyer_reclamation(ActionEvent event) throws SQLException {
        String selectedType = choice_box.getValue();
        String description = description_ajout_reclamation.getText();
       // u = su.findById(7);
        r.setType_reclamation(selectedType);
        r.setDescription(description);
        sr.ajouter(r);

    }

}
