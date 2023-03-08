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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceReservation_Voiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AffichageLocations_Proprietaire_voitureController implements Initializable {

    @FXML
    private ListView<Reservation_voiture> listv;
    @FXML
    private TextField searchBar;
    @FXML
    private Button ReponseID;
    private  ObservableList ObList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
            Utilisateur u=Utilisateur.getCurrent_user();
            List<Reservation_voiture> locations= sv.readAll_Of_prop(u.getId_utilisateur());
            System.out.println(locations);
            ObList = FXCollections.observableList(locations);
            listv.setItems(ObList);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageLocations_Proprietaire_voitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

   


   /* @FXML
    private void Reponse_demande_Reservation(ActionEvent event) throws IOException, SQLException {
        Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
           
        if (r != null) {
          
               int id_reservation = r.getId_reservation_voiture(); 
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/UpdateLocation.fxml"));
            Parent root = loader.load();
            //Reponse_Demande_ReservationController controller = loader.getController();
            //System.out.println(controller);
            //controller.setIDReservation(id_reservation);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
        }     
     else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une réservation !");
        alert.showAndWait();
    }
    }*/

    @FXML
    private void Reponse(ActionEvent event) {
        Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
           
        if (r != null) {
          
            try {
                int id_reservation = r.getId_reservation_voiture();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/Reponse_Demande_Reservation.fxml"));
                Parent root = loader.load();
                Reponse_Demande_ReservationController controller = loader.getController();
                System.out.println(controller);
                controller.setIDReservation(id_reservation);
                controller.setObList(ObList);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AffichageLocations_Proprietaire_voitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
     else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une réservation !");
        alert.showAndWait();
    }
    }
    

    

   
    
}
