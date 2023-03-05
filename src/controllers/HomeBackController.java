/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class HomeBackController implements Initializable {

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

    
}
