/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import services.ServiceNote_voitures;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class DashbordAdminController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private TableView<?> userActivityTable;
    @FXML
    private TableView<?> reportsTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleUsersButton(ActionEvent event) {
    }

    @FXML
    private void handleReportsButton(ActionEvent event) {
    }

    @FXML
    private void handleSettingsButton(ActionEvent event) {
    }
     private Map<Integer, Double> getDataMap() throws SQLException {
        // Remplacer cette méthode par votre propre méthode pour récupérer les données de la map
       ServiceNote_voitures sn = new ServiceNote_voitures();
Map<Integer, Double> noteMoyenneParVoiture = sn.calculerNoteMoyenneParVoiture();
System.out.println(noteMoyenneParVoiture);

        Map<Integer, Double> dataMap = noteMoyenneParVoiture;
       
        return dataMap;
    }
    
}
