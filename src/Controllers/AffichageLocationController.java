/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Reservation_voiture;
import Entite.Utilisateur;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField searchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
             Utilisateur u=Utilisateur.getCurrent_user();
            List<Reservation_voiture> locations= sv.readAll_Of_user(u.getId_utilisateur());
            System.out.println(locations);
            ObservableList ObList = FXCollections.observableList(locations);
            listv.setItems(ObList);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
   

    

    @FXML
    private void supprimer(ActionEvent event) {
         Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
    if (r != null) {
        //int id = r.getId_reservation_voiture();
        ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
        try {
            sv.supprime(r);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("");
            alert.setContentText("Réservation effacée avec succés ! ");
            alert.showAndWait();
            listv.getItems().remove(listv.getSelectionModel().getSelectedIndex());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une réservation à supprimer.");
        alert.showAndWait();
    }
    }

    @FXML
    private void exporter(ActionEvent event) {
    }

    @FXML
    private void updateReservation(ActionEvent event) throws IOException, SQLException {
        Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
           
        if (r != null) {
          
               int id_reservation = r.getId_reservation_voiture(); 
               System.out.println("fff"+id_reservation);
                ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/UpdateLocation.fxml"));
               Parent root = loader.load();
               UpdateLocationController controller = loader.getController();
               System.out.println(controller);
               controller.setIDVoiture(id_reservation);
               Scene scene = new Scene(root);
               Stage stage = new Stage();
               stage.setTitle("Edit page");
               stage.setScene(scene);
               stage.show();
        }     
     else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une voiture à modifier.");
        alert.showAndWait();
    }
    }

    private void ajouterReservation(ActionEvent event) {
         try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui_handiny/InsertLocation.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }
    
}
