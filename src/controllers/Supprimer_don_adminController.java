/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.don;
import Services.Service_don;
import Services.Service_utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Supprimer_don_adminController implements Initializable {

    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType());
                  Label label2 =new Label(d.getDescription());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
             Service_utilisateur u=new Service_utilisateur();
              Label label6  = null;
           try {
               label6  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label3 =new Label(d.getImage_don());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label3);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
             Label label4 =new Label(d.getDate_ajout().toString());
             
            hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label6.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

             Button button = new Button("Supprimer !");
               button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");
               
             button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

               // alert.setContentText("Description : "+evenement.getDescription());
                 try {
                     ser.supprimer_don(d.getId_don());
                     JOptionPane.showMessageDialog(null, "don supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_donController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);

               actualiser();

            });
                
            hbox.getChildren().addAll(label6,label1, vbox2,label2,label4,button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }    



    private void actualiser() {
        
             vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType());
                  Label label2 =new Label(d.getDescription());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
      Service_utilisateur u=new Service_utilisateur();
              Label label6  = null;
           try {
               label6  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label3 =new Label(d.getImage_don());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label3);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
             Label label4 =new Label(d.getDate_ajout().toString());
             
            hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label6.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

             Button button = new Button("Supprimer !");
               button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");
               
             button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

               // alert.setContentText("Description : "+evenement.getDescription());
                 try {
                     ser.supprimer_don(d.getId_don());
                     JOptionPane.showMessageDialog(null, "don supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_donController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);


            });
                
            hbox.getChildren().addAll(label6,label1, vbox2,label2,label4,button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }

  
    
}