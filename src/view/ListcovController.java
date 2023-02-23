/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Entite.Covoiturage;
import Services.ServiceCovoiturage;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class ListcovController implements Initializable {

    @FXML
private Button btnajouter;
    @FXML
    private TextField tfdepart;
    @FXML
    private TextField tfdestination;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnbr;
    @FXML
        private TextField tfdate ;
@FXML
        private TextField tfid ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savecovoiturage(ActionEvent event) throws SQLException {
        String depart = tfdepart.getText() ; 
                String destination = tfdestination.getText() ; 
                                String date = tfdate.getText() ; 

                int nbrplace =Integer.parseInt(tfnbr.getText()) ; 
                int prix =Integer.parseInt(tfprix.getText()) ; 
                                  int id_cov  =Integer.parseInt(tfid.getText()) ; 


        Covoiturage c = new Covoiturage(id_cov , depart, destination, date, prix, nbrplace); 
        ServiceCovoiturage sc = new ServiceCovoiturage(); 
       try { 
            if (sc.ajouter(c)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("covoiturage  ajoutée avec succés ! ");
                alert.showAndWait();
            }
            else {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Alerte");
                alert.setContentText("Impossible d'ajouter cette covoiturage");
                alert.showAndWait();
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
}
