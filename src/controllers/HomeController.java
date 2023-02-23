/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane mesVoitures;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void locations(ActionEvent event)throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/AffichageLocation.fxml"));
           mesVoitures.getChildren().setAll(pane);
    }

       private void informationAlert(ActionEvent event)throws IOException {
           Alert alert=new Alert(AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setHeaderText("Alerte");
           alert.setContentText("Cette application est Handiny");
           alert.showAndWait();
           
    }

    @FXML
    private void mesVoitures(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/win2.fxml"));
           mesVoitures.getChildren().setAll(pane);
    }
       
       

    
}
