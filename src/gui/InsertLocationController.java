/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Reservation_voiture;
import Entite.Voiture;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.ServiceReservation_Voiture;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class InsertLocationController implements Initializable {

    @FXML
    private AnchorPane anchlorPaneVoiture;
    @FXML
    private DatePicker datedeb;
   
    @FXML
    private DatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insert(ActionEvent event) {
      
        LocalDate date1=datedeb.getValue();
        LocalDate date2=datefin.getValue();
        
     

    // Vérification de la date
    
    LocalDate today = LocalDate.now();
    if (date1.isAfter(today)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Date invalide");
        alert.setContentText("La date saisie doit être inférieure ou égale à la date d'aujourd'hui.");
        alert.showAndWait();
        return;
    }
    if (date2.isAfter(today)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Date invalide");
        alert.setContentText("La date saisie doit être inférieure ou égale à la date d'aujourd'hui.");
        alert.showAndWait();
        return;
    }
    
    
    // Vérifier si le champ Prix Location est un nombre valide
    
    
             
            Reservation_voiture r=new Reservation_voiture(java.sql.Date.valueOf(date1),java.sql.Date.valueOf(date2));
            ServiceReservation_Voiture rv= new ServiceReservation_Voiture();
            try { 
            if (rv.ajouter(r)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Alerte");
                alert.setContentText("Impossible d'ajouter cette Location");
                alert.showAndWait();
            }
            else {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Location ajoutée avec succés ! ");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
                System.out.println(ex);
            
    }

    
    
}
}