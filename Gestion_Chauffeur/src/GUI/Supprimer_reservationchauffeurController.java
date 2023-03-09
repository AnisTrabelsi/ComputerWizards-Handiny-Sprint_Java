/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reservation_Chauffeur;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceChauffeur;
import services.ServiceReservation_Chauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Supprimer_reservationchauffeurController implements Initializable {

    @FXML
    private TableView<Reservation_Chauffeur> Reclamation;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> cin;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> nom;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> adresse;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            List<Reservation_Chauffeur> k = new ArrayList<>();
            ServiceReservation_Chauffeur ser = new ServiceReservation_Chauffeur();
            
            
            k = ser.readAll();
       
            ObservableList<Reservation_Chauffeur> olc = FXCollections.observableArrayList(k);
           
            cin.setCellValueFactory(new PropertyValueFactory<>("id_chauffeur"));
            nom.setCellValueFactory(new PropertyValueFactory<>("duree_service"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("date_prise_en_charge"));
            statut.setCellValueFactory(new PropertyValueFactory<>("description_demande"));
            
           
            Reclamation.setItems(olc);
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_reservationchauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }    

    @FXML
    private void md(ActionEvent event) {
    }

    @FXML
    private void aff(ActionEvent event) {
    }

    @FXML
    private void aj(ActionEvent event) {
    }

    @FXML
    private void dd(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          ServiceReservation_Chauffeur SV = new ServiceReservation_Chauffeur();
        SV.supprimer(Reclamation.getSelectionModel().getSelectedItem().getId_reservation_chauffeur());
        
    }
    public void refresh()
            {
                    try {
            List<Reservation_Chauffeur> k = new ArrayList<>();
            ServiceReservation_Chauffeur ser = new ServiceReservation_Chauffeur();
            
            
            k = ser.readAll();
       
            ObservableList<Reservation_Chauffeur> olc = FXCollections.observableArrayList(k);
           
            cin.setCellValueFactory(new PropertyValueFactory<>("id_chauffeur"));
            nom.setCellValueFactory(new PropertyValueFactory<>("duree_service"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("date_prise_en_charge"));
            statut.setCellValueFactory(new PropertyValueFactory<>("description_demande"));
            
           
            Reclamation.setItems(olc);
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_reservationchauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 }
            
}
