/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Entite.Covoiturage;
import Services.ServiceCovoiturage;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Entite.utilisateur;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Ajout_covoiturageController implements Initializable {

    @FXML
    private ImageView Image;
    @FXML
    private TextField tfdepart;
    @FXML
    private TextField tfdestination;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnbr;
    @FXML
    private DatePicker tfdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 tfprix.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfprix.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });   
    
    tfnbr.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfnbr.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });
    
    }    

    @FXML
    private void ajouterCov(ActionEvent event) {
                utilisateur u = new utilisateur(1, "mohamed", "benabbes", "09638420", "abbes525@gmail.com", "24076282", "aaa", "aaaa", new Date(2020, 25, 01), "tounes", 2086, "user", "sdsdsd");
        ServiceCovoiturage ser = new ServiceCovoiturage();

         String depart = tfdepart.getText() ; 
                String destination = tfdestination.getText() ; 
        LocalDate date = tfdate.getValue() ; 

            //    int nbrplace =Integer.parseInt(tfnbr.getText()) ; 
              //  int prix =Integer.parseInt(tfprix.getText()) ; 

        
      String nbrText = tfnbr.getText();
String prixText = tfprix.getText();

        if (depart.isEmpty() || destination.isEmpty() || nbrText.isEmpty()  || nbrText.isEmpty()  || date == null ||prixText.isEmpty()   ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("complter remplire");
            alert.showAndWait();
        } else  {
            int nbr = Integer.parseInt(nbrText);
    int prix = Integer.parseInt(prixText);
    
    if (nbr > 8 || nbr <= 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("nombre de place doit etre positif et moins de 8");
            alert.showAndWait();
        
    }else if (prix < 0 ) { 
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("prix lezmo ikoun positif ");
            alert.showAndWait();
    }else if ( date.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez saisir une date valide.");
        alert.showAndWait();
        
   
        
    }else {
            Covoiturage p = new Covoiturage(u.getId_utilisateur(), depart, destination, java.sql.Date.valueOf(date), prix,nbr,u.getNom(),u.getTelephone());

            try {
                ser.ajouter(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            JOptionPane.showMessageDialog(null, "covoiturage ajouter avec succes");
            tfdepart.clear();
                        tfdestination.clear();
            tfprix.clear();
            tfnbr.clear();
tfdate.setValue(null) ; 

        }
        } }

    @FXML
    private void goaffi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("affichage_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("modifier_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mesreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("mes_reservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public static boolean isNumberWithinLengthLimit(String input) {
    try {
        if (input == null || input.isEmpty()) {
        return false; // input cannot be empty or null
    }
        Integer.parseInt(input); // check if input can be parsed as an integer
        return true; // input is a valid number within the length limit
    } catch (NumberFormatException e) {
        return false; // input is not a valid number
    }
}
    
    }
    
 
