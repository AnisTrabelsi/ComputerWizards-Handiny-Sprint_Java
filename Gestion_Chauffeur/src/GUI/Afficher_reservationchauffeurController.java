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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import services.ServiceReservation_Chauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Afficher_reservationchauffeurController implements Initializable {

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
    @FXML
    private Button button;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Modifier_chauffeur.fxml"));
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


    private void afficher(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Afficher_reservationchauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
     
              
            }

    @FXML
    private void dd(ActionEvent event) {
    }

@FXML
private void trier(ActionEvent event) throws SQLException {
    Reclamation.getItems().clear();
    List<Reservation_Chauffeur> l = new ArrayList<Reservation_Chauffeur>();
    ServiceReservation_Chauffeur ser = new ServiceReservation_Chauffeur();
    l = ser.trierReservationsParDate();
    ObservableList<Reservation_Chauffeur> olc = FXCollections.observableArrayList(l);
    Reclamation.setItems(olc);
}
    
        
        
    }
    

