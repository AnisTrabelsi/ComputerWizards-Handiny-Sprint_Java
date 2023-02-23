/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.demande_don;
import Services.Service_demande_don;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Affichage_demande_donController implements Initializable {

    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          vb.getChildren().clear();
        List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.find_demande_don_of_user(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
       vb.setSpacing(20);
     for (demande_don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
              hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label5.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; ");
      
                
            hbox.getChildren().addAll(label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
    }

        

    @FXML
    private void mdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifier_demande_don.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void ajdd(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Ajout_demande_don.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void sdd(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("supprimer_demande_don.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void don(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("affichage_don.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }


  @FXML
    private void afficher(ActionEvent event) {
     
        ///////////////////////////////////////////////user

         vb.getChildren().clear();
     List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.find_demande_don_of_user(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
       vb.setSpacing(20);
     for (demande_don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
              hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label5.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; ");
      
                
            hbox.getChildren().addAll(label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
    }

    


   
    @FXML
    private void sort(ActionEvent event) {
       
        ///////////////////////////////////////////////user
vb.getChildren().clear();
     List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.sort_date_demande_don_of_user(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
              hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label5.setStyle("-fx-font-size: 19px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; ");
      
                
            hbox.getChildren().addAll(label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
    }

    
}
