/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Voiture;
import java.io.File;
import javafx.scene.image.Image;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AffichageVoituresFrontOfficeController implements Initializable {

   
    @FXML
    private VBox vb;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
            List<Voiture> voitures = new ArrayList<Voiture>();
            ServiceVoiture sv = new ServiceVoiture();
          
        try {
            voitures=sv.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageVoituresFrontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
           
            
            vb.setSpacing(20);
        for (Voiture v : voitures) {

            String path = v.getImage_voiture();
            
            System.out.println(path);
            
            //imageV.setImage(img1);
            HBox hbox = new HBox();
            
            Label label1 = new Label(v.getMarque());
            Label label2 = new Label(v.getModele());
            Image img1=new Image(getClass().getResourceAsStream(path));
            ImageView i;
            
            i = new ImageView(img1);
             i.setFitWidth(150);
            i.setFitHeight(150);
             

/////////////////////////////////////////////////
           
                   
           
             

           

          
          
         
            /////////////////////////////////////////////////////////  
           
            /////////////////////////
   hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            //label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            i.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //////////////////////////////
            hbox.getChildren().addAll(label1, label2,i);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
// vb.setStyle("fx-spacing: -1; fx-padding:-1; fx-alignment: center; ");
            vb.getChildren().add(hbox);
        }
       

    }
           
    }
        

