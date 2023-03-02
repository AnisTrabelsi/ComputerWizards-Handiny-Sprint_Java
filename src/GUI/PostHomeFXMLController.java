/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.CommentaireFXMLController;
import Controllers.SujetGridFXMLController;
import Entite.Sujet;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addComment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CommentaireFXML.fxml"));
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
    }
    
}
