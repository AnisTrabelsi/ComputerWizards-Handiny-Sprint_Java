/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import entities.Reservation_Chauffeur;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceChauffeur;

/**                                   
 * FXML Controller class         /////////////////////////////////////////////////////            
 *
 * @author Mehdi
 */
public class Ajout_chauffeurController implements Initializable {

    private TextField id;
    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField dispo;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Modifier_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

     @FXML
    private void dd(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Ajout_reservationchauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ajouterch(ActionEvent event) {
        
     //   utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

     
        
        String ttcin = cin.getText();
 String ttnom = nom.getText();
  String ttadresse = adresse.getText();
  String ttdispo = dispo.getText();
        ServiceChauffeur ser = new ServiceChauffeur();
        
        
        
        if (ttcin.isEmpty() || ttnom.isEmpty() || ttdispo.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } 
                
                
                else{
          Reservation_Chauffeur c;
            c = new Reservation_Chauffeur(ttcin,ttnom,ttadresse,ttdispo );
            ser.ajouter(c);
            JOptionPane.showMessageDialog(null, "chauffeur ajouté");
               
            vider();
            JOptionPane.showMessageDialog(null, "chauffeur ajouté");
            

        }
    }
    
     private void vider() {

        id.setText(null);
        cin.setText(null);

        nom.setText(null);
      
        adresse.setText(null);
        dispo.setText(null);

    }
    
}
