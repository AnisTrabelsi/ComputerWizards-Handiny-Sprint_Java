/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
public class FrontPropietaireController implements Initializable {

    @FXML
    private AnchorPane mesDons;
    @FXML
    private Label nom_actuel;
 
    Utilisateur user = Utilisateur.getCurrent_user();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Homefront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FrontPropietaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean setAll = mesDons.getChildren().setAll(pane);
        nom_actuel.setText(user.getNom());
       
    }    
  @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }
    @FXML
    private void Liste_des_dons(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Liste_des_demandes_dons(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_demande_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_demande_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_demande_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_demande_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }


    @FXML
    private void accueil(ActionEvent event) throws IOException {
    AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Homefront.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void Accueil2(ActionEvent event) throws IOException {
      AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Homefront.fxml"));
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

    private void affichageVoitures(ActionEvent event) throws IOException {
        // AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_Voitures_frontOffice.fxml"));
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_don.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void voitures(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_Voitures_frontOffice.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void mesVoituresProp(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_Voitures.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void listeDemandesProp(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/AffichageLocations_Proprietaire_voiture.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Forum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ForumSideBarFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goajout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void gomescov(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void goreserver(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void gomesreservv(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Mes_reservation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void ajout_reclam(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajouter_reclamation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void update_reclam(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/updatereclamation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_profil(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Modifier_profil_user.fxml"));
           mesDons.getChildren().setAll(pane);  
    }
    
}
