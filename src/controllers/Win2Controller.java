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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        // TODO
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
    private void afficherVoitures(ActionEvent event) throws SQLException {
      ServiceVoiture sv= new ServiceVoiture();
      List<Voiture> voitures= sv.readAll();
      ObservableList ObList = FXCollections.observableList(voitures);
      
      
      listv.setItems(ObList);
      // Ajout des boutons "Supprimer" et "Editer" pour chaque élément de la ListView
      listv.setCellFactory(param -> new ListCell<Voiture>() {
        private final HBox cellBox = new HBox();
        private final Label label = new Label();
        private final Button editButton = new Button("Editer");
        private final Button deleteButton = new Button("Supprimer");

        {
          cellBox.getChildren().addAll(label, editButton, deleteButton);
          HBox.setHgrow(label, Priority.ALWAYS);
          editButton.setOnAction(event -> {
            // Logique pour éditer la voiture ici
            Voiture voiture = getItem();
            System.out.println("Editer: " + voiture);
          });
          deleteButton.setOnAction(event -> {
            // Logique pour supprimer la voiture ici
           
          });
        }

        @Override
        protected void updateItem(Voiture voiture, boolean empty) {
          super.updateItem(voiture, empty);
          if (empty || voiture == null) {
            setText(null);
            setGraphic(null);
          } else {
            label.setText(voiture.getMarque() + " " + voiture.getModele());
            setGraphic(cellBox);
          }
        }
      });
    }}
      
      
