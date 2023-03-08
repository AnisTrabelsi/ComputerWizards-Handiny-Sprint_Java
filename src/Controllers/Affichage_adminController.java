/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Controllers.Read_user_by_adminController;
import Controllers.Modifier_profil_userController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Affichage_adminController implements Initializable {

    private Button admin_add;
    @FXML
    private Button admin_read;
       
    Read_user_by_adminController ru= new Read_user_by_adminController();
    @FXML
    private Button admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    


    @FXML
    private void admin_afficher_liste_utilisateur(ActionEvent event) throws IOException, SQLException {

    FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/read_user_by_admin.fxml")) ;
    
    Parent root = loader.load();
    Read_user_by_adminController ruc = loader.getController();
    // ruc.list_tilisateur();

    admin_read.getScene().setRoot(root);
    }


    @FXML
    private void afficher_liste_reclamation(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/admin_reclamation.fxml")) ;
    
    Parent root = loader.load();
    Admin_reclamationController ar = loader.getController();
    // ar.list_reclamation();

    admin_read.getScene().setRoot(root);
    }
    
}
