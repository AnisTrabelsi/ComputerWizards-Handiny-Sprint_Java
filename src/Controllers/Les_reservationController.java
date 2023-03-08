/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Covoiturage;
import Entite.reservation_covoiturage;
import Entite.Utilisateur;
import Services.ServiceCovoiturage;
import Services.Service_reservation_cov;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Les_reservationController implements Initializable {

    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //   vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<reservation_covoiturage> l = new ArrayList<reservation_covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
                Service_reservation_cov serv = new Service_reservation_cov() ; 

        try {
            l = (List<reservation_covoiturage>) serv.readAll_reservation_cov();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (reservation_covoiturage d : l) {

        
            HBox hbox = new HBox();
       
Label label4 = new Label(String.valueOf(d.getId_reserv_cov()));
Label label5 = new Label(String.valueOf(d.getId_utilisateur()));
Label label6 = new Label(String.valueOf(d.getPrix_covoiturage()) +"dt");
Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
             Label label7 = new Label(d.getNom());
            Label label8 = new Label(d.getTelephone());

    Button button2 = new Button("annuler !");
                                     button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     serv.supprime_reservation_cov(d.getId_reserv_cov(),d.getId_cov());
                     JOptionPane.showMessageDialog(null, "covoiturage supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     actualiser(); 

});                 
       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label6,label1,label2,label7,label8,button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

    @FXML
    private void actualiser() {
              vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<reservation_covoiturage> l = new ArrayList<reservation_covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
                Service_reservation_cov serv = new Service_reservation_cov() ; 

        try {
            l = (List<reservation_covoiturage>) serv.readAll_reservation_cov();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (reservation_covoiturage d : l) {

        
            HBox hbox = new HBox();
       
Label label4 = new Label(String.valueOf(d.getId_reserv_cov()));
Label label5 = new Label(String.valueOf(d.getId_utilisateur()));
Label label6 = new Label(String.valueOf(d.getPrix_covoiturage()));
Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
             Label label7 = new Label(d.getNom());
            Label label8 = new Label(d.getTelephone());

    Button button2 = new Button("annuler !");
                                     button2.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     serv.supprime_reservation_cov(d.getId_reserv_cov(),d.getId_cov());
                     
                     JOptionPane.showMessageDialog(null, "covoiturage supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     

});                 
       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label6,label1,label2,label7,label8,button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

    @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goaffi(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


     @FXML
    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   
    @FXML
    private void goresv(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
    
        
    

  
}
