/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.SessionManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class BackController implements Initializable {

    @FXML
    private AnchorPane mesDons;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
           mesDons.getChildren().setAll(pane);
        
      
    }    

    @FXML
    private void Liste_des_dons(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Liste_des_demandes_dons(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

  

    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    
    @FXML
    private void accueil(ActionEvent event) throws IOException {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void Accueil2(ActionEvent event) throws IOException {
      AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void statdd(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DemandesDonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
             SessionManager.CleanUserSession(); 
      Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Authentification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Forum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ForumSideBarFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
}
