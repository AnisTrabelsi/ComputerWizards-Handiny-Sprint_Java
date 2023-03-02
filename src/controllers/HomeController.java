/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.ServiceNote_voitures;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class HomeController implements Initializable {

   
    @FXML
    private AnchorPane rootpane;

   

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
           rootpane.getChildren().setAll(pane);
    }

      

    @FXML
    private void mesVoitures(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/win2.fxml"));
           rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void aff2(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/AFFfinale.fxml"));
           rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void affichageVoitures(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/AFFfinale.fxml"));
           rootpane.getChildren().setAll(pane);
    }

  @FXML
private void dashbord(ActionEvent event) throws IOException, SQLException {
    // Créer la liste des données pour le Pie Chart
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    Map<String, Double> dataMap = getDataMap();

    for (String key : dataMap.keySet()) {
        pieChartData.add(new PieChart.Data(key, dataMap.get(key)));
    }

    // Créer le Pie Chart
    PieChart pieChart = new PieChart(pieChartData);

    Label label = new Label("Statistiques de l'évaluation de voitures par marque");

// Définir la couleur du texte en bleu



// Définir la taille de la police à 32
label.setFont(new Font(32));


    // Créer la VBox
    VBox vbox = new VBox();
    vbox.getChildren().addAll(label, pieChart);

    // Créer la scène
    Scene scene = new Scene(vbox, 800, 600);

    // Créer et afficher la fenêtre
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
}




   private Map<String, Double> getDataMap() throws SQLException {
    ServiceNote_voitures sn = new ServiceNote_voitures();
    Map<String, Double> noteMoyenneParMarque = sn.calculerNoteMoyenneParMarque();
    System.out.println(noteMoyenneParMarque);

    Map<String, Double> dataMap = noteMoyenneParMarque;

    return dataMap;
}
}

       
       

    

