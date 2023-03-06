/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
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
public class FrontController implements Initializable {

    @FXML
    private AnchorPane mesDons;
    @FXML
    private MediaView mediaview;
    @FXML
    private Slider volume;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button stop;
 private MediaPlayer mediaplayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try{
           String videoname = null;
           String path = new File("src/" + videoname + ".mp4").getAbsolutePath();
           File mediaFile = new File("C:\\xampp4\\htdocs\\Gestion don\\src\\images\\projet_final.mp4");
           Media media = new Media(mediaFile.toURI().toURL().toString());
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            volume.setValue(mediaplayer.getVolume() * 50);
            
             volume.valueProperty().addListener(new InvalidationListener() {
            

               @Override
               public void invalidated(Observable observable) {
                 mediaplayer.setVolume(volume.getValue() / 100);

               }

        });
      } catch (MalformedURLException ex) {
           System.out.println(ex.getMessage());
      }
        //mediaplayer.play();
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
    private void click(MouseEvent event) {
    }

    @FXML
    private void play(ActionEvent event) {
        mediaplayer.play();
    }

    @FXML
    private void pause(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    private void stop(ActionEvent event) {
         mediaplayer.seek(mediaplayer.getStartTime());
        mediaplayer.stop();
    }


    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void youtube(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.youtube.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void facebook(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.facebook.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void instagram(ActionEvent event) {
         try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://www.instagram.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void accueil(ActionEvent event) throws IOException {
     /*  Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();*/
        
    }

    @FXML
    private void Accueil2(ActionEvent event) throws IOException {
       /*   Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    private void statdd(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DemandesDonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Consulter_mes_reclamations(ActionEvent event) throws IOException {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/updatereclamation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_une_reclamation(ActionEvent event) throws IOException {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/updatereclamation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_une_reclamation(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/updatereclamation.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void Ajouter_une_reclamation(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Ajouter_reclamationController.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void Modifier_mon_profil(ActionEvent event) {
    }
    
}