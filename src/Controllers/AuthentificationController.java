/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import Services.ServiceAuthentification;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField login_auth;
    @FXML
    private Button auth_retour_inscri;
    @FXML
    private Button authentification;

    @FXML
    private TextField mdp_auth;

    ServiceAuthentification sa = new ServiceAuthentification();

    Stage dialogStage = new Stage();
    Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_retour_a_inscri(ActionEvent event) throws SQLException, IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/Inscription.fxml"));
        Parent root = loader.load();
        InscriptionController inscri = loader.getController();
        login_auth.getScene().setRoot(root);

    }

    @FXML
    private void btn_authentification(ActionEvent event) throws IOException, SQLException {
        String login_entrée = login_auth.getText();
        String mot_de_passe_entrèe = mdp_auth.getText();
        Utilisateur u = new Utilisateur();
        u.setLogin(login_entrée);
        u.setMot_de_passe(mot_de_passe_entrèe);

        if (sa.auth(u) == true) {
             System.out.println(Utilisateur.getCurrent_user().getEmail());
            infoBox("Connexion avec succés", "succés", null);
          FXMLLoader loader = null;
           if ("Locataire".equals(Utilisateur.getCurrent_user().getRole()))
            {loader = new FXMLLoader(getClass().getResource("/gui_handiny/front.fxml"));
              Parent root = loader.load();
            login_auth.getScene().setRoot(root);
            }
            
            else if("Proprietaire".equals(Utilisateur.getCurrent_user().getRole()))
                  {loader = new FXMLLoader(getClass().getResource("/gui_handiny/FrontPropietaire.fxml"));
              Parent root = loader.load();
            login_auth.getScene().setRoot(root);
            }
            else{
            loader = new FXMLLoader(getClass().getResource("/gui_handiny/back.fxml"));
              Parent root = loader.load();
            login_auth.getScene().setRoot(root);
            }
            
        
          

        } else if (sa.auth(u) == false) {

            infoBox("Veuillez resaisir votre login et mot de passe", "Success", null);

        }
    }

    private void infoBox(String infoMessage, String titre_de_bar, String message_de_header) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre_de_bar);
        alert.setHeaderText(message_de_header);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @FXML
    private void recupererMotDePasse(ActionEvent event) {
    }

}