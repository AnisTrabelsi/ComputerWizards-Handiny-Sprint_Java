/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Reservation_voiture;
import Entite.Utilisateur;
import Entite.Voiture;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServiceReservation_Voiture;
import services.ServiceUtilisateur;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class UpdateLocationController implements Initializable {

    @FXML
    private AnchorPane anchlorPaneVoiture;
    @FXML
    private DatePicker datedeb;
    @FXML
    private TextField desc;
    @FXML
    private DatePicker datefin;
    int reservation_Id;

    /**
     * Initializes the controller class.
     */
    void setIDVoiture(int id_reservation) throws SQLException {
         reservation_Id = id_reservation;
         System.out.println("rrr"+reservation_Id);
         ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
            Reservation_voiture r= sv.findById(getIDVoiture());
            System.out.println("testt "+r);
            desc.setText(r.getDescription_reservation());
          
          
        

 //Définir la valeur du DatePicker en utilisant l'objet java.time.LocalDate


java.sql.Date sqlDate = (java.sql.Date) r.getDate_debut_reservation();
java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
System.out.println(localDate);
datedeb.setValue(localDate);


java.sql.Date sqlDate2 = (java.sql.Date) r.getDate_fin_reservation();
java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
LocalDate localDate2 = utilDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
System.out.println(localDate2);
datefin.setValue(localDate2);
   



            
         
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insert(ActionEvent event) {
        
        LocalDate date1 = datedeb.getValue();
        LocalDate date2 = datefin.getValue();
        String descrip=desc.getText();

        // Vérification de la date
    if (date1 == null || date2 == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates manquantes");
        alert.setContentText("Veuillez saisir une date de début et une date de fin.");
        alert.showAndWait();
        return;
    }
    if (date2.isBefore(date1)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates incorrectes");
        alert.setContentText("La date de fin ne peut pas être antérieure à la date de début.");
        alert.showAndWait();
        return;
    }

       

        Reservation_voiture r = new Reservation_voiture(getIDVoiture(),java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2),descrip);
        ServiceReservation_Voiture rv = new ServiceReservation_Voiture();
        try {
            if (rv.update(r)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alerte");
                alert.setContentText("Location modifiée avec succés ! ");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("");
                alert.setContentText("Impossible de modifier cette Location");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
    }

    
    public int getIDVoiture() {
        return this.reservation_Id;
    }
    
}
