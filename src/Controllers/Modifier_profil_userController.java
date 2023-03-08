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

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Modifier_profil_userController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
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
        String nom_new =nom.getText();
        String prenom_new =prenom.getText();
        String adresse_new = adresse.getText();
        String telph_new =numero_telph.getText();
        
         if (!isValidTelephone(telph_new)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le numero de telephone est invalide.");
            alert.showAndWait();
            return;
         }
         
//          if (sa.auth() == true) {
//              int id_user_authetifie = sa.getId_auth() ;
              Utilisateur user = su.findById(3) ;
              user.setNom(nom_new);
              user.setPrenom(prenom_new);
              user.setAdresse(adresse_new);
              user.setTelephone(telph_new);
              su.update(user);
              
              
        }
          
    
     private boolean isValidTelephone(String tel) {
        String regex = "^[0-9]{1,8}$";
        return tel.matches(regex);
    }
    
}
