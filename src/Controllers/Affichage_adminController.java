/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Delete_user_by_adminController;
import Controllers.Read_user_by_adminController;
import Controllers.Update_user_by_adminController;

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

    @FXML
    private Button admin_add;
    @FXML
    private Button admin_delete;
    @FXML
    private Button admin_read;
    @FXML
    private Button admin_update;
       
    Read_user_by_adminController ru= new Read_user_by_adminController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    

    @FXML
    private void admin_ajouter_utilisateur(ActionEvent event) throws IOException {
    FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/add_user_by_admin.fxml"));
    Parent root =loader.load();
    Add_user_by_adminController auc = loader.getController();
    admin_add.getScene().setRoot(root);
    }

    @FXML
    private void admin_supprimer_un_utilisateur(ActionEvent event) throws IOException, SQLException {
   
     FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/delete_user_by_admin.fxml"));
    Parent root =loader.load();
    //Delete_user_by_adminController duc = loader.getController();
    admin_delete.getScene().setRoot(root);    
    }

    @FXML
    private void admin_afficher_liste_utilisateur(ActionEvent event) throws IOException, SQLException {
    FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/read_user_by_admin.fxml")) ;
    Parent root = loader.load();
    Read_user_by_adminController ruc = loader.getController();
             ru.list_tilisateur();

    admin_read.getScene().setRoot(root);
    }

    @FXML
    private void admin_mettre_a_jour_utilisateur(ActionEvent event) throws IOException {
      FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/Update_user_by_admin.fxml")) ;
    Parent root =loader.load();
    Update_user_by_adminController uuc = loader.getController();
    admin_update.getScene().setRoot(root);
        
    }
    
}
