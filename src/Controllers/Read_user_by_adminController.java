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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
   
public class Read_user_by_adminController implements Initializable {

    @FXML
    private ListView<Utilisateur> listu;
    @FXML
    private Button affichage;
    @FXML
    private Label nom_vue_admin;
    @FXML
    private Label prenom_vue_admin;
    @FXML
    private Label telephone_vue_admin;
    @FXML
    private Label date_naiss_vue_admin;
    @FXML
    private Label CIN_vue_admin;
    @FXML
    private Label email_vue_admin;
    @FXML
    private Label login_vue_admin;
    @FXML
    private Label mdp_vue_admin;
    @FXML
    private Label adresse_vue_admin;
    @FXML
    private Label region;
    @FXML
    private Label code_postal_vue_admin;
    @FXML
    private Label role_vue_admin;
    @FXML
    private Label id_user_vue_admin;
    @FXML
    private Button btn_delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void list_tilisateur() throws SQLException {

        ServiceUtilisateur su = new ServiceUtilisateur();
        Utilisateur user = new Utilisateur();
        List<Utilisateur> users;
        try {
            
//            users = su.readAll();
//        ObservableList<Utilisateur> obs = FXCollections.observableList(users);
//        listu.setItems(obs);
//        listu.setCellFactory(param -> new UserListCell());
            users = su.readAll();
            //System.out.println(users);
            // la liste qui contient la liste d'utilisateurs dans la base 
            ObservableList obs = FXCollections.observableList(users);
            listu.setItems(obs);
            listu.setCellFactory(param -> new UserListCell());
        } 
        catch (SQLException ex) {
            Logger.getLogger(Read_user_by_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void affichage(ActionEvent event) {
      nom_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getNom());
      prenom_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getPrenom());
      telephone_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getTelephone());
      date_naiss_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getDate_de_naissance().toString());
      CIN_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getCin());
      email_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getEmail());
      login_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getLogin());
      mdp_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getMot_de_passe());
      adresse_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getAdresse());
      region.setText(listu.getSelectionModel().getSelectedItem().getRegion());
      //String code_postal_string = Integer.toString(listu.getSelectionModel().getSelectedItem().getCode_postal());
      code_postal_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getCode_postal_en_string());
      role_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getRole());
      id_user_vue_admin.setText(listu.getSelectionModel().getSelectedItem().getId_en_string());

 

              
                                   
                                   
       
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
    Utilisateur selectedUser = listu.getSelectionModel().getSelectedItem();
    ServiceUtilisateur su = new ServiceUtilisateur();
    su.supprime(selectedUser);
    listu.getItems().remove(selectedUser);
    }

}
