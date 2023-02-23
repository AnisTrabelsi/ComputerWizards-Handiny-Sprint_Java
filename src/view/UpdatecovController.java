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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class UpdatecovController implements Initializable {

    @FXML
    private Button tfmodif;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfdepart;
    @FXML
    private TextField tfdest;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onmodif(ActionEvent event) throws SQLException {
         String depart = tfdepart.getText() ; 
                String destination = tfdest.getText() ; 
                                String date = tfdate.getText() ; 

                int nbrplace =Integer.parseInt(tfnbr.getText()) ; 
                int prix =Integer.parseInt(tfprix.getText()) ; 
                                  int id_cov  =Integer.parseInt(tfid.getText()) ; 


        Covoiturage c = new Covoiturage(id_cov ,1, depart, destination, date, prix, nbrplace); 
        ServiceCovoiturage sc = new ServiceCovoiturage(); 
    sc.update(c);
    }
        
        
    }
    

