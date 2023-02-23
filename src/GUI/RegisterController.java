/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nom_inscri;
    @FXML
    private TextField email_inscri;
    @FXML
    private TextField prenom_inscri;
    @FXML
    private TextField adresse_inscri;
    @FXML
    private TextField cin_inscri;
    @FXML
    private TextField region_inscri;
    @FXML
    private TextField num_telph_inscri;
    @FXML
    private TextField login_inscri;
    @FXML
    private TextField code_pos_inscri;
    @FXML
    private PasswordField mdp_inscri;
    @FXML
    private Button inscri_butt;
//    @FXML
//    private RadioButton rb_roprietaire_de_voiture;
//    @FXML
//    private ToggleGroup role;
//    @FXML
//    private RadioButton rb_locataire;
    @FXML 
    private Label label_role ;
    

              ServiceUtilisateur su =new ServiceUtilisateur ();

int code_postal ;
 String code_postal_string ;
    @FXML
    private TextField role_inscri;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//public void getDate (ActionEvent event){

    @FXML
    private void Inscription(ActionEvent event) {
         String nom = nom_inscri.getText();
         String prenom = prenom_inscri.getText() ;
         String cin =cin_inscri.getText() ;
         String email=email_inscri.getText() ;
         String telephone = num_telph_inscri.getText() ;
         String login =login_inscri.getText () ;
         String mot_de_passe = mdp_inscri.getText();
        DatePicker date_naiss_inscri= new DatePicker ();
        LocalDate date_de_naissance = date_naiss_inscri.getValue();
         String region =region_inscri.getText() ;
         String adresse = adresse_inscri.getText() ;
         String role= role_inscri.getText();
          
          int code_postall= Integer.parseInt(code_pos_inscri.getText()) ;
          
         
        ServiceUtilisateur su =new ServiceUtilisateur ();
        Utilisateur u =new Utilisateur(  nom, prenom,  cin,  email,telephone, login, mot_de_passe,java.sql.Date.valueOf(date_de_naissance),region, adresse, code_postall,role);

        try {
            su.ajouter(u);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    

//    @FXML
//    private void get_rb_roprietaire_de_voiture(ActionEvent event) {
//        if(rb_roprietaire_de_voiture.isSelected())
//        {
//        label_role.setText("Vous etes connecté en tant que proprietaire");
//            try {
//                su.setroleproprietaire();
//            } catch (SQLException ex) {
//                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
////ajouter un bouton  qui lui permet de proposer sa voiture a louer 
//    @FXML
//    private void get_rb_locatire(ActionEvent event) {
//        if (rb_locataire.isSelected()){
//                label_role.setText("Vous etes connecté en tant que locataire ");
//            try {
//                su.setrolelocataire();
//            } catch (SQLException ex) {
//                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//                
//        }
//    }

   /* private void setDate_de_naissance(TextField date_naiss_inscri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

  
}

    

   


