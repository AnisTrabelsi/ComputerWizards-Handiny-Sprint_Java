/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.HelloPageController;
import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nom_inscri;
    @FXML
    private TextField prenom_inscri;
    @FXML
    private DatePicker date_naiss_inscri;
    @FXML
    private TextField cin_inscri;
    @FXML
    private TextField email_inscri;
    @FXML
    private TextField telephone_inscri;
    @FXML
    private TextField login_inscri;
    @FXML
    private TextField region_inscri;
    @FXML
    private PasswordField mdp_inscri;
    @FXML
    private TextField adresse_inscri;
    @FXML
    private TextField code_postal_inscri;
    private TextField role_inscri;
    @FXML
    private Button inscription;
    @FXML
    private RadioButton loataire_inscri;
    @FXML
    private RadioButton proprietaire_inscri;
    @FXML
    private ToggleGroup Role;
    
//Utilisateur u = new Utilisateur ();
  String role ="";
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_utilisateur(ActionEvent event) throws SQLException, IOException {
     String nom = nom_inscri.getText();
     String prenom = prenom_inscri.getText();
     //DatePicker date_naiss_inscri = new DatePicker ();
     LocalDate ds = date_naiss_inscri.getValue();
     String cin =cin_inscri.getText();
     String email = email_inscri.getText();
     String telephone =telephone_inscri.getText();
     String login =login_inscri.getText();
     String region =region_inscri.getText();
     String mot_de_passe =mdp_inscri.getText();
     String adresse =adresse_inscri.getText();
     int code_postal =Integer.parseInt(code_postal_inscri.getText());
   
     //String role = role_inscri.getText();
     
     Utilisateur user = new Utilisateur( nom, prenom,  cin,  email,  telephone,  login,  mot_de_passe, java.sql.Date.valueOf( ds ),  region,  adresse,  code_postal,role );
     ServiceUtilisateur su = new ServiceUtilisateur ();
     su.ajouter(user);
     //Pour aller d'une page a une autre 
   FXMLLoader loader =new FXMLLoader(getClass().getResource("HelloPage.fxml")) ;
    Parent root =loader.load();
    HelloPageController hpc = loader.getController();
    // pour se rederiger vers une autre scene , je vais prendre la scene acttuelle avec l'un des composants graphiques
   //par suite je vais charger la variable root qui contient l'affichage de la page helloPage.fxml 
    inscription.getScene().setRoot(root);
    }
    
            
    @FXML
    private void locataire(ActionEvent event) {
        if (loataire_inscri.isSelected()){
            System.out.println("Vous connecté en tant que locataire");
           role= "Locataire";
        }
    }

    @FXML
    private void proprietaire(ActionEvent event) {
        if (proprietaire_inscri.isSelected()){
            System.out.println("Vous etes connecte en tanat que proprietaire");
           role="Proprietaire";
        }
    }
}
