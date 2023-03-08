/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
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
import static jdk.nashorn.internal.runtime.Debug.id;
import services.ServiceChauffeur;

/**                                   
 * FXML Controller class         /////////////////////////////////////////////////////            
 *
 * @author Mehdi
 */
public class Ajout_reservationchauffeurController implements Initializable {

    
    private TextField id;
    @FXML
    private TextField ds;
    @FXML
    private TextField dc;
    @FXML
    private TextField dd;
    @FXML
    private TextField cin;

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
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Modifier_chauffeur_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dd(ActionEvent event) {
    }

    @FXML
    private void ajouterch(ActionEvent event) {
        
     //   utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

     
        
        String rid = id.getText();
 String rds = ds.getText();
  String rdc = dc.getText();
  String rdd = dd.getText();
        ServiceChauffeur ser = new ServiceChauffeur();
        
        
        
        if (rid.isEmpty() || rds.isEmpty() || rdd.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } 
                
                
                else{
          Reservation_Chauffeur c;
                 c = new Reservation_Chauffeur(rid,rds,rdc,rdd );
            ser.ajouter(c);
            JOptionPane.showMessageDialog(null, "Reservation chauffeur ajouté");
               
            vider();
            JOptionPane.showMessageDialog(null, "Reservation chauffeur ajouté");
            

        }
    }
    
     private void vider() {

        id.setText(null);
        ds.setText(null);

        dc.setText(null);
      
        dd.setText(null);
        

    }
    
}
