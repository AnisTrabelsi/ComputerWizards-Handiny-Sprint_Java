/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Chayma
 */
public class Add_user_by_adminController {

    @FXML
    private TextField nom_added_by_admin;
    @FXML
    private TextField prenom_added_by_admin;
    @FXML
    private TextField cin_added_by_admin;
    @FXML
    private TextField email_added_by_admin;
    @FXML
    private TextField telephone_added_by_admin;
    @FXML
    private TextField login_added_by_admin;
    @FXML
    private TextField region_added_by_admin;
    @FXML
    private PasswordField mot_de_passe_added_by_admin;
    @FXML
    private TextField adresse_added_by_admin;
    @FXML
    private TextField code_postal_added_by_admin;
    @FXML
    private DatePicker date_naiss_added_by_admin;
    @FXML
    private Button add_user;
    @FXML
    private RadioButton locatire_added_by_admin;
    @FXML
    private ToggleGroup Role;
    @FXML
    private RadioButton proprietaire_added_by_admin;
  String role ="";
  Stage dialogStage =new Stage ();
    Scene scene ;
    @FXML
    private void add_user_by_admin(ActionEvent event) throws SQLException {
        String nom = nom_added_by_admin.getText();
     String prenom = prenom_added_by_admin.getText();
     //DatePicker date_naiss_inscri = new DatePicker ();
     LocalDate ds = date_naiss_added_by_admin.getValue();
     String cin =cin_added_by_admin.getText();
     String email = email_added_by_admin.getText();
     String telephone =telephone_added_by_admin.getText();
     String login =login_added_by_admin.getText();
     String region =region_added_by_admin.getText();
     String mot_de_passe =mot_de_passe_added_by_admin.getText();
     String adresse =adresse_added_by_admin.getText();
     int code_postal =Integer.parseInt(code_postal_added_by_admin.getText());
   
     Utilisateur user = new Utilisateur( nom, prenom,  cin,  email,  telephone,  login,  mot_de_passe, java.sql.Date.valueOf( ds ),  region,  adresse,  code_postal,role );
     ServiceUtilisateur su = new ServiceUtilisateur ();
     su.ajouter(user);
        infoBox("Utilisateur ajouté avec succés","Success",null);
        Node source =(Node) event.getSource();
       dialogStage =(Stage) source.getScene().getWindow();
       dialogStage.close();

    }

    @FXML
    private void locataire(ActionEvent event) {
         if (locatire_added_by_admin.isSelected()){
            System.out.println("Vous connecté en tant que locataire");
           role= "Locataire";
        }
    }

    @FXML
    private void proprietaire(ActionEvent event) {
        if (proprietaire_added_by_admin.isSelected()){
            System.out.println("Vous etes connecte en tanat que proprietaire");
           role="Proprietaire";
    }
    
}

 private void infoBox(String infoMessage, String titre_de_bar, String message_de_header) {
Alert alert = new Alert (Alert.AlertType.INFORMATION);
alert.setTitle(titre_de_bar);
alert.setHeaderText(message_de_header);
alert.setContentText(infoMessage);
alert.showAndWait();
    }    
}
