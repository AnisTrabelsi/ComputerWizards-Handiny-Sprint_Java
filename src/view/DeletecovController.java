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
public class DeletecovController implements Initializable {

    @FXML
    private Button tfbutton;
    @FXML
    private TextField tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ondelete(ActionEvent event) {
      
        
      
        int idc=Integer.parseInt(tfid.getText());
        Covoiturage c = new Covoiturage(idc,1,"","","",0,0);
        // Delete the payment from the database
        ServiceCovoiturage sc= new ServiceCovoiturage();
        try {
            if (sc.supprime(c)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("covoiturage supprimer ! ");
                alert.showAndWait();
            }
            else {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Alerte");
                alert.setContentText("Impossible efaccer");
                alert.showAndWait();
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCovoiturage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
   
    

