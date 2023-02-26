/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Supprimer_chauffeurController implements Initializable {

    @FXML
    private TableView<Chauffeur> Reclamation;
    @FXML
    private TableColumn<Chauffeur, String> cin;
    @FXML
    private TableColumn<Chauffeur, String> nom;
    @FXML
    private TableColumn<Chauffeur, String> adresse;
    @FXML
    private TableColumn<Chauffeur, String> statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Chauffeur> l = new ArrayList<Chauffeur>();
        ServiceChauffeur ser = new ServiceChauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
           
            cin.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("CIN"));
            nom.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Statut_disponibilite"));
          
     
       
          Reclamation.setItems(olc);
    }    

    @FXML
    private void md(ActionEvent event) {
    }

    @FXML
    private void aff(ActionEvent event) {
    }

    @FXML
    private void dd(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
          ServiceChauffeur SV = new ServiceChauffeur();
        try {
            SV.supprimer(Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur());
        } catch (SQLException ex) {
            Logger.getLogger(Supprimer_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur());
      // Reclamation.getItems().removeAll(Reclamation.getSelectionModel().getSelectedItem());
        System.out.println(Reclamation);
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(Supprimer_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   public void delete() throws SQLException {
        ServiceChauffeur SV = new ServiceChauffeur();
        SV.supprimer(Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur());
        System.out.println(Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur());
    }
   
      public void refresh() throws SQLException {
        List<Chauffeur> l = new ArrayList<Chauffeur>();
        ServiceChauffeur ser = new ServiceChauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
           
            cin.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("CIN"));
            nom.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Statut_disponibilite"));
          
     
       
          Reclamation.setItems(olc);
    }

    @FXML
    private void SupprimerVoyage(ActionEvent event) {
    
      
  
    
}}
