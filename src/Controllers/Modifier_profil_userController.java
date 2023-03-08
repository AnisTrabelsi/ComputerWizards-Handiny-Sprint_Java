/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import Services.ServiceAuthentification;
import Services.ServiceUtilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Modifier_profil_userController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField numero_telph;
    
    ServiceUtilisateur su = new ServiceUtilisateur ();
    ServiceAuthentification sa = new ServiceAuthentification();
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
       Utilisateur user = su.findById(31) ;

        String login_new =login.getText();
        String email_new =email.getText();
        String adresse_new = adresse.getText();
        String telph_new =numero_telph.getText();
        //Utilisateur user ;
        user= new Utilisateur(31,email_new,telph_new,login_new,adresse_new);
        su.update(user);
        System.out.println(user);
      
        
          
       Notifications.create()
            .title("Succès")
            .text("Votre compte a été modifié.")
            .showInformation();
    }
     private boolean isValidTelephone(String tel) {
        String regex = "^[0-9]{1,8}$";
        return tel.matches(regex);
     }
    
  
    
}
