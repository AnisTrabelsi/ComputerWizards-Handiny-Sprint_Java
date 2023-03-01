/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Modifier_chauffeur_adminController implements Initializable {

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
    
    @FXML
    private TextField cin1;
    @FXML
    private TextField nom1;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField dispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           refresh();
       } catch (SQLException ex) {
           Logger.getLogger(Modifier_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    

    @FXML
    private void aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Afficher_chauffeur_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_chauffeur_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aj(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajout_chauffeur_admin.fxml"));
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
       
        ServiceChauffeur sv = new ServiceChauffeur();
        

         String tcin = cin1.getText();
 String tnom = nom1.getText();
  String tadresse = adresse1.getText();
  String tdispo = dispo.getText();
  int id=Reclamation.getSelectionModel().getSelectedItem().getId_chauffeur();
    Chauffeur c=new Chauffeur(id,tcin,tnom,tadresse,tdispo );
         
         sv.modifier(c);
        
           JOptionPane.showMessageDialog(null, "chauffeur modifie");
            refresh();
              vider();
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

    @FXML
    private void selectionner(ActionEvent event) {
       cin1.setText( Reclamation.getSelectionModel().getSelectedItem().getCIN());
       nom1.setText( Reclamation.getSelectionModel().getSelectedItem().getNom());
        adresse1.setText( Reclamation.getSelectionModel().getSelectedItem().getAdresse());
         dispo.setText( Reclamation.getSelectionModel().getSelectedItem().getStatut_disponibilite());
    }
    
      private void vider() {

      
        cin1.setText(null);
        nom1.setText(null);
    
        adresse1.setText(null);
        dispo.setText(null);

    }
    
}
