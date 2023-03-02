/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Chaima
 */
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TableView;
import services.ServiceNote_voitures;
import services.ServiceReservation_Voiture;

public class NewClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Créer la liste des données pour le Pie Chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<Integer, Double> dataMap = getDataMap();

        for (Integer key : dataMap.keySet()) {
            pieChartData.add(new PieChart.Data(key.toString(), dataMap.get(key)));
             
        }

        // Créer le Pie Chart
        PieChart pieChart = new PieChart(pieChartData);

        // Créer la scène
        StackPane root = new StackPane();
        root.getChildren().add(pieChart);
        Scene scene = new Scene(root, 800, 600);

        // Afficher la scène
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Map<Integer, Double> getDataMap() throws SQLException {
        // Remplacer cette méthode par votre propre méthode pour récupérer les données de la map
       ServiceNote_voitures sn = new ServiceNote_voitures();
Map<Integer, Double> noteMoyenneParVoiture = sn.calculerNoteMoyenneParVoiture();
System.out.println(noteMoyenneParVoiture);

        Map<Integer, Double> dataMap = noteMoyenneParVoiture;
       
        return dataMap;
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }
}

