/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import entities.utilisateur;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Ajout_chauffeur_adminController implements Initializable {

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aff(ActionEvent event) {
    }

    @FXML
    private void suppr(ActionEvent event) {
    }

    @FXML
    private void md(ActionEvent event) {
    }

    @FXML
    private void dd(ActionEvent event) {
    }

    @FXML
    private void ajouterch(ActionEvent event) {
        
     //   utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

     
        
        String tcin = cin.getText();
 String tnom = nom.getText();
  String tadresse = adresse.getText();
  String tdispo = dispo.getText();
        ServiceChauffeur ser = new ServiceChauffeur();
        
        
        
        if (tcin.isEmpty() || tnom.isEmpty() || tdispo.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } 
                
                
                else{
          Chauffeur c=new Chauffeur(tcin,tnom,tadresse,tdispo );
            try {
                ser.ajouter(c);
                JOptionPane.showMessageDialog(null, "chauffeur ajouté");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
               
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
