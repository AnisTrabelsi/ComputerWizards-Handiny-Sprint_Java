/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Chauffeur;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Chauffeur> l = new ArrayList<>();
        ServiceChauffeur ser = new ServiceChauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
           
            cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<>("Statut_disponibilite"));
          
     
       
          Reclamation.setItems(olc);
    }    

    @FXML
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifier_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void aj(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajout_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Afficher_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
        List<Chauffeur> l = new ArrayList<>();
        ServiceChauffeur ser = new ServiceChauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
           
            cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
            nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<>("Statut_disponibilite"));
          
     
       
          Reclamation.setItems(olc);
    }

}
