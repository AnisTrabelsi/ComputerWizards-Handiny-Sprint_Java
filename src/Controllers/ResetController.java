/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import entities.CurrentUser;

import Entite.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import org.mindrot.jbcrypt.BCrypt;
import Services.ServiceUtilisateur;
import java.sql.SQLException;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Chayma
 */
public class ResetController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField nv_pass;
    @FXML
    private TextField conv_pass;
    Stage app_stage;
    @FXML
    private Label labelmdp;
    //  ForgotMdpController fr= new ForgotMdpController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean test() {
        int x = 0;
        if (nv_pass.getText().length() < 8) {
            labelmdp.setText("veuillez saisir un mdp long");
            labelmdp.setTextFill(Color.rgb(210, 39, 30));
            x++;
        } else if (!nv_pass.getText().matches("(?=.*[0-9]).*")) {
            labelmdp.setText("au moins un chiffre");
            labelmdp.setTextFill(Color.rgb(210, 39, 30));
            x++;
        } else if (!nv_pass.getText().matches("(?=.*[a-zA-Z]).*")) {
            labelmdp.setText("au moins une lettre");
            labelmdp.setTextFill(Color.rgb(210, 39, 30));
            x++;
        } else {
            labelmdp.setText("");
        }
        if (x == 0) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void resetpass(ActionEvent event) throws SQLException, Exception {
        // Utilisateur u = Utilisateur.getCurrent_user();
        if (!nv_pass.getText().isEmpty() && !conv_pass.getText().isEmpty()) {
            if (test()) {
                System.out.println(ForgotMdpController.id);
                int ii = ForgotMdpController.id;
                if (nv_pass.getText().equals(conv_pass.getText())) {
                    ServiceUtilisateur su = new ServiceUtilisateur();
                    Utilisateur user = su.findById(ii);
                    System.out.println(user);
                    String nv_passwd = nv_pass.getText();
                    String crypted = AESEncryptor.encrypt(nv_passwd);
                    user.setMot_de_passe(crypted);
                    su.mettreAJourMotDePasse(user);

                    Notifications.create()
                            .title("Succès")
                            .text("Votre mot de passe a été modifié.")
                            .showInformation();
                    Parent loader;
                    try {
                        loader = FXMLLoader.load(getClass().getResource("/gui_handiny/Authentification.fxml"));
                        loader.setOnMousePressed(pressEvent -> {
                            loader.setOnMouseDragged(dragEvent -> {
                                app_stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                                app_stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                            });
                        });

                        Scene scene = new Scene(loader);

                        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    } catch (IOException ex) {
                    }
                } else {
                    labelmdp.setText("Les deux mots de passe ne sont pas identiques");
                    labelmdp.setTextFill(Color.RED);
                }
            }
        } else {
            labelmdp.setText("Les deux mots de passe ne sont pas identiques");
            labelmdp.setTextFill(Color.rgb(210, 39, 30));
        }
    }

}