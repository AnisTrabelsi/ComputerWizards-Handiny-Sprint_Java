/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Sujet;
import Entite.Utilisateur;
import Entite.PostsSauvegardés;
import Services.ServiceCommentaire;
import Services.ServiceSauvegarde;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class PostHomeFXMLController implements Initializable {

    @FXML
    private Label titre_sujet;
    @FXML
    private Label etat;
    @FXML
    private TextArea post;
    @FXML
    private Button addCommentBtn;
    @FXML
    private Label nom_categorie;
    private Sujet sujet;
    ServiceSujet ss = new ServiceSujet();
    ServiceCommentaire ser = new ServiceCommentaire();
    @FXML
    private Button affichcommentsBtn;
    @FXML
    private Button sauvegardeBtn;
    Utilisateur u = Utilisateur.getCurrent_user();
    @FXML
    private Label username;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void addComment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/CommentaireFXML.fxml"));
            Parent root = loader.load();
            CommentaireFXMLController controller = loader.getController();
            controller.setSujet(sujet);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SujetGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setData(Sujet s) {
        sujet = s;
        titre_sujet.setText(s.getTitre_sujet());
        etat.setText(s.getEtat());
        post.setText(s.getContenu_sujet());
        nom_categorie.setText(s.getCat().getNom_categorie());
        username.setText(s.getUser().getNom());
    }

    @FXML
    private void affichcomments(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/CommentListBySujetFXML.fxml"));
            Parent root = loader.load();

            CommentListBySujetFXMLController cc = loader.getController();
            cc.setSujet(sujet);
            cc.loadData();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SujetGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void sauvegardePost(ActionEvent event) {
        ServiceSauvegarde ss1 = new ServiceSauvegarde();
        PostsSauvegardés p = new PostsSauvegardés(u, sujet);
        System.out.println(p.toString());

        // Check if there is already a saved post with the same user ID and subject ID
        try {
            if (ss1.verifierExistenceSauvegarde(p)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avertissement");
                alert.setHeaderText("");
                alert.setContentText("Ce sujet a déjà été sauvegardé !");
                alert.showAndWait();
                return; // Exit the method without saving the post
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        // Save the post
        try {
            ss1.ajouter(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("");
            alert.setContentText("Votre sujet a été sauvegardé ! ");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
