/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Voiture;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AffichageDetailVoitureController implements Initializable {
public int carId;
    @FXML
    private Label marqueLabel;
    @FXML
    private Label modeleLabel;
    @FXML
    private Label anneeLabel;
    @FXML
    private Label couleurLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private ImageView imageV;
    @FXML
    private VBox reserverBTN;
    @FXML
    private Button reserverboutton;
   public void setIDVoiture(int id_voiture) throws SQLException {
         carId = id_voiture;
         System.out.println("methode set "+carId);
         ServiceVoiture sv=new ServiceVoiture();
          System.out.println("ii"+carId);
        Voiture voiture=sv.findById2(getIDVoiture());
        System.out.println("les infos "+voiture);
        String path = "C:\\xampp4\\htdocs\\Handiny\\" + voiture.getImage_voiture();
        Image img1=new Image(getClass().getResourceAsStream(path));
        imageV.setImage(img1);
        
         marqueLabel.setText(voiture.getMarque());
        modeleLabel.setText(voiture.getModele());
        anneeLabel.setText(voiture.getBoite_vitesse());
        couleurLabel.setText(voiture.getCarburant());
        prixLabel.setText(voiture.getDate_validation_technique().toString());
    
    reserverboutton.setOnAction(event -> {
       try {
            // création de la nouvelle fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InsertLocation.fxml"));
            Parent root = loader.load();
            InsertLocationController controller = loader.getController();
            System.out.println(controller);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
           
            // passage de l'ID de la voiture au contrôleur de la nouvelle page
            controller.setIDVoiture(id_voiture);
            System.out.println(voiture.getId_voiture());
            // affichage de la nouvelle fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    
});
    }
    public int getIDVoiture() {
        return this.carId;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
   

  
    }
    
    
    

