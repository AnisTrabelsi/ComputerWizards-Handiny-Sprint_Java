/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Voiture;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Win2Controller implements Initializable {

    @FXML
    private ListView<Voiture> listv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ServiceVoiture sv= new ServiceVoiture();
            List<Voiture> voitures= sv.readAll();
            ObservableList ObList = FXCollections.observableList(voitures);
            
            
            listv.setItems(ObList);
        } catch (SQLException ex) {
            Logger.getLogger(Win2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  @FXML
    private void ajouterVoitures(ActionEvent event) {
     try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui/InsertVoitures.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }


    @FXML
    private void supprimer(ActionEvent event) {
    Voiture voiture = listv.getSelectionModel().getSelectedItem();
    if (voiture != null) {
        int id = voiture.getId_voiture();
        ServiceVoiture sv = new ServiceVoiture();
        try {
            sv.delete(id);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("");
            alert.setContentText("Voiture effacée avec succés ! ");
            alert.showAndWait();
            listv.getItems().remove(listv.getSelectionModel().getSelectedIndex());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une voiture à supprimer.");
        alert.showAndWait();
    }
}     
     @FXML
    private void updateVoiture(ActionEvent event) {
        try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui/EditVoitures.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Edit page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }

    
}    
      
