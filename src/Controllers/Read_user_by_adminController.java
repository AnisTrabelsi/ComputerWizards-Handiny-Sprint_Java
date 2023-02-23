/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Chayma
 */

//      ServiceVoiture sv= new ServiceVoiture();
//      List<Voiture> voitures= sv.readAll();
//      ObservableList ObList = FXCollections.observableList(voitures);
//      
//      
//      listv.setItems(ObList);
//      

public class Read_user_by_adminController implements Initializable {

    @FXML
    private ListView<Utilisateur> listu;

          

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void list_tilisateur() throws SQLException{
        
        ServiceUtilisateur su= new ServiceUtilisateur();
        Utilisateur user = new Utilisateur ();
        List<Utilisateur> users = su.readAll();
        ObservableList obs=FXCollections.observableList(users);
        listu.setItems(obs);
        

    }


 
    }
        
    

