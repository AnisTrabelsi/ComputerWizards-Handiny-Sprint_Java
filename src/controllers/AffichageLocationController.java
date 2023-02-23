/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Reservation_voiture;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ServiceReservation_Voiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AffichageLocationController implements Initializable {

    @FXML
    private ListView<Reservation_voiture> listv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
            List<Reservation_voiture> locations= sv.readAll();
            System.out.println(locations);
            ObservableList ObList = FXCollections.observableList(locations);
            listv.setItems(ObList);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterLocation(ActionEvent event) {
        try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui/InsertLocation.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void updateLocation(ActionEvent event) {
    }
    
}
