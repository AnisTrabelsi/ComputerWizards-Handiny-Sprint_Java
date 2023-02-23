/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField login_auth;
    @FXML
    private Button auth_retour_inscri;
    @FXML
    private Button authentification;
    @FXML
    private TextField mdp_auth;
    Connection con =DataSource.getInstance().getConnection();
    ResultSet res;
    Stage dialogStage =new Stage ();
    Scene scene ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_retour_a_inscri(ActionEvent event) throws SQLException, IOException {
        
    FXMLLoader loader =new FXMLLoader(getClass().getResource("Inscription.fxml")) ;
    Parent root =loader.load();
    Add_user_by_adminController hpc = loader.getController();
    login_auth.getScene().setRoot(root);
        
    }

    @FXML
    private void btn_authentification(ActionEvent event) {
        String login = login_auth.getText ();
        String mot_de_passe =mdp_auth.getText ();
        String req ="SELECT * FROM utilisateur WHERE login=? and mot_de_passe=?";
        try {
        PreparedStatement pre =con.prepareStatement(req);
        pre.setString(1, login);
        pre.setString(2, mot_de_passe);
        ResultSet res = pre.executeQuery();
        if(!res.next()){
            //le type de retour de la la methode next renvoie boolean => si cette methode renvoie falsse cela signifie qu'il n'a pas de ligne dans le resultat
        infoBox("Veuillez resaisir votre login et mot de passe","Success",null);
        }
        else{
        infoBox("Connexion avec succés","succés",null);
        //downcasting
//        Node source =(Node) event.getSource();
//        dialogStage =(Stage) source.getScene().getWindow();
//        dialogStage.close();
        //passage au home
         //Pour aller d'une page a une autre 
   FXMLLoader loader =new FXMLLoader(getClass().getResource("/gui_handiny/home.fxml")) ;
    Parent root =loader.load();
    HomeController hpc = loader.getController();
    // pour se rederger vers une autre scene , je vais prendre la scene acttuelle avec l'un des composants graphiques
   //par suite je vais charger la variable root qui contient l'affichage de la page helloPage.fxml 
    login_auth.getScene().setRoot(root);
    
//////////        scene=new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));
//////////        dialogStage.setScene(scene);
//////////        dialogStage.show();
        }
        }catch (Exception e){
        System.out.println("e");
    }
    }

 private void infoBox(String infoMessage, String titre_de_bar, String message_de_header) {
Alert alert = new Alert (AlertType.INFORMATION);
alert.setTitle(titre_de_bar);
alert.setHeaderText(message_de_header);
alert.setContentText(infoMessage);
alert.showAndWait();
    }
    
}
