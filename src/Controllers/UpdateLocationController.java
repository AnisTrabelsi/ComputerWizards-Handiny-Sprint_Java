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
import Services.ServiceUtilisateur;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.DialogPane;
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
    public void setIDVoiture(int id_reservation) throws SQLException {
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
        
        datedeb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDateSelection(event);
            }
        });
        datefin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDateSelection(event);
            }
        });
    } 
    @FXML
    private void handleDateSelection(ActionEvent event) {
        LocalDate debut = datedeb.getValue();
        LocalDate fin = datefin.getValue();
       
        if (debut != null && fin != null) {
            if (fin.isBefore(debut)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates incorrectes");
        alert.setContentText("La date de fin ne peut pas être antérieure à la date de début.");
        alert.showAndWait();}
            else{
            try {
                long daysBetween = Duration.between(debut.atStartOfDay(), fin.atStartOfDay()).toDays();
                ServiceVoiture sv = new ServiceVoiture();
                Voiture v = sv.findById(getIDVoiture());
                ServiceReservation_Voiture sr = new ServiceReservation_Voiture();
                double prixTotal = sr.calculerPrixTotal(v, debut,fin); // Calcul du prix total de la location (10 € par jour)
                System.out.println("prix : "+prixTotal);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prix de location");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Le prix total de la location est de %.2f DT pour une durée de %d jours.", prixTotal, daysBetween));
        // Définir le style inline de l'alerte
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color:  #f2f2ff; "
            + "-fx-font-size: 18pt; "
            + "-fx-text-fill: #444444; "
            + "-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif;");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(InsertLocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
    }




    @FXML
    private void insert(ActionEvent event) {
        
        LocalDate date1 = datedeb.getValue();
        LocalDate date2 = datefin.getValue();
        String descrip=desc.getText();

        // Vérification de la date
     // Vérification de la date
    if (date1 == null) {
        datedeb.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datedeb).play();
    }else
        datedeb.setStyle("");
        
      
    if (date2 == null) {
        datefin.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datefin).play();
        
    }else
        datefin.setStyle("");
        
        if (descrip.isEmpty()) {
        desc.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(desc).play();
        }
        else
       desc.setStyle("");
    if (datedeb == null ||datefin == null ||descrip.isEmpty()){return;}
    if (date2.isBefore(date1)) {
        datefin.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datefin).play();
        datedeb.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datedeb).play();
        
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
