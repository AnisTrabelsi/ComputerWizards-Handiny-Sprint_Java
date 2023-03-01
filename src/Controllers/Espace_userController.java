/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Espace_userController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Pane pane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/Modifier_profil_user.fxml"));
        Parent root = loader.load();
        Modifier_profil_userController mpu = loader.getController();
        image.getScene().setRoot(root);
    }

    @FXML
    private void logout_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/Home.fxml"));
        Parent root = loader.load();
        HomeController hc = loader.getController();
        image.getScene().setRoot(root);
    }

    @FXML
    private void consulter_reclam_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/updatereclamation.fxml"));
        Parent root = loader.load();
        updatereclamationController_1 urc = loader.getController();
        image.getScene().setRoot(root);
    }

    @FXML
    private void reclam_add_page(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/ajouter_reclamation.fxml"));
        Parent root = loader.load();
        Ajouter_reclamationController arc = loader.getController();
        image.getScene().setRoot(root);
    }
    
}
