/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Services.Service_don;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class DonStatsController implements Initializable {

    @FXML
    private PieChart don_front;
 ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Service_don ser = new Service_don();
            
            HashMap<String,Integer> mp =  ser.stattype();
            
            
            for( Map.Entry<String,Integer> e : mp.entrySet()){
                 data.add(new PieChart.Data(e.getKey(),e.getValue()));
                 
            }  
        
        } catch (SQLException ex) {    
            Logger.getLogger(DonStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        don_front.setTitle("**Statistiques des types des dons **");
    don_front.lookup(".chart-title").setStyle("-fx-text-fill: Black; -fx-background-color: #dbe8fc;  -fx-font-size: 11pt;   -fx-font-weight: bold;  -fx-font-size: 20pt;");
        don_front.setLegendSide(Side.LEFT);
  don_front.lookup(".chart-legend").setStyle("-fx-text-fill: Black;   -fx-font-size: 11pt;   -fx-font-weight: bold;  -fx-font-size: 10pt;");

        don_front.setData(data);
}
}
