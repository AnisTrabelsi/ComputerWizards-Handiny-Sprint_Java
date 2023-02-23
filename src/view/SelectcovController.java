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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author abbes
 */
public class SelectcovController implements Initializable {

    @FXML
    private ListView<Covoiturage> mylistview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceCovoiturage sc = new ServiceCovoiturage();
            
            List<Covoiturage> data = sc.readAll() ;
            
        mylistview.getItems().addAll(data) ;    
             
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
    
    
    


