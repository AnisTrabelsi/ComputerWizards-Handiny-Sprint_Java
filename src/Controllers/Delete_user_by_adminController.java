/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Chayma
 */
public class Delete_user_by_adminController implements Initializable {

    @FXML
    private TextField id_to_delete;
    @FXML
    private Button btn_delete;
    ServiceUtilisateur su = new ServiceUtilisateur ();

    Utilisateur u = new Utilisateur ();
    
        Connection con =DataSource.getInstance().getConnection();

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event)  {
        int id_choisit = Integer.parseInt(id_to_delete.getText());

        try {
            su.supprime(su.findById(id_choisit));
            
            // infoBox("Utilisateur supprimé", "Success", null);
        } catch (SQLException ex) {
           System.out.println(ex);        }
                
          
            }

       
    
    

 private void infoBox(String infoMessage, String titre_de_bar, String message_de_header) {
Alert alert = new Alert (Alert.AlertType.INFORMATION);
alert.setTitle(titre_de_bar);
alert.setHeaderText(message_de_header);
alert.setContentText(infoMessage);
alert.showAndWait();
    }    
    
}
