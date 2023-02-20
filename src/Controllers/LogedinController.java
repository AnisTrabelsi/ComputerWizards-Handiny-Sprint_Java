/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
 
public class LogedinController implements Initializable {
    @FXML
    private Button logedin_se_deconnecter;
    @FXML
    private Label label_bienvenue;
     @FXML
    private Label label_logedin;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     logedin_se_deconnecter.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
         }
     ){
    
    
         
    }    
    
}
             }
    
