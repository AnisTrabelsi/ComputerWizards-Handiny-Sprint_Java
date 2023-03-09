/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reservation_Chauffeur;
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
import javax.swing.JOptionPane;
import services.ServiceReservation_Chauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Modifier_reservationchauffeurController implements Initializable {

   @FXML
    private TableView<Reservation_Chauffeur> Reclamation;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> cin1;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> nom1;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> adresse;
    @FXML
    private TableColumn<Reservation_Chauffeur, String> dispo;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           refresh();
       } catch (SQLException ex) {
           Logger.getLogger(Modifier_reservationchauffeurController.class.getName()).log(Level.SEVERE, null, ex);
       }
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
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_chauffeur.fxml"));
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
    private void dd(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
       
        ServiceReservation_Chauffeur sv = new ServiceReservation_Chauffeur();
        

         String tcin = cin1.getText();
 String tnom = nom1.getText();
  String tadresse = adresse.getText();
  String tdispo = dispo.getText();
  int id=Reclamation.getSelectionModel().getSelectedItem().getId_reservation_chauffeur ();
    Reservation_Chauffeur c;
       c = new Reservation_Chauffeur(id ,tcin,tnom,tadresse,tdispo );
         
         sv.modifier(c);
        
           JOptionPane.showMessageDialog(null, "Reservation modifie");
            refresh();
              vider();
    }
    
    
      public void refresh() throws SQLException {
     List<Reservation_Chauffeur> l = new ArrayList<>();
        ServiceReservation_Chauffeur ser;
       ser = new ServiceReservation_Chauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Reservation_Chauffeur> olc;
       olc = FXCollections.observableArrayList(l);
           
            
            cin1.setCellValueFactory(new PropertyValueFactory<>("id_chauffeur "));
            nom1.setCellValueFactory(new PropertyValueFactory<>("duree_service"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("date_prise_en_charge"));
            dispo.setCellValueFactory(new PropertyValueFactory<>("description_demande"));
          
     
       
          Reclamation.setItems(olc);
        
    }

    @FXML
    private void selectionner(ActionEvent event) {
       cin1.setText(""+ Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur());
       nom1.setText( ""+Reclamation.getSelectionModel().getSelectedItem().getDuree_service());
        adresse.setText(""+ Reclamation.getSelectionModel().getSelectedItem().getDate_prise_en_charge());
         dispo.setText( Reclamation.getSelectionModel().getSelectedItem().getDescription_demande());
    }
    
      private void vider() {

      
        cin1.setText(null);
        nom1.setText(null);
    
        adresse.setText(null);
        dispo.setText(null);

    }
    
}
