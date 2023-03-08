/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.PostsSauvegardés;
import Entite.Sujet;
import Entite.Utilisateur;
import Services.ServiceCommentaire;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class SujetGridFXMLController implements Initializable {

    @FXML
    private Label category;

    @FXML
    private Label contenu_sujet;

    @FXML
    private Label etat;

    @FXML
    private Button modifSujetBtn;

    @FXML
    private Label nb_comments;

    @FXML
    private Button supprimerSujetBtn;

    ServiceSujet ss = new ServiceSujet();
    private Sujet sujet;
    private Utilisateur user;
    @FXML
    private Button commentsByTopicBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Sujet suj) {
        sujet = suj;
        contenu_sujet.setText(suj.getContenu_sujet());
        category.setText(suj.getCat().getNom_categorie());
        nb_comments.setText(String.valueOf(suj.getNb_commentaires()));
        etat.setText(suj.getEtat());
    }

    @FXML
    private void goToModifSujet(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/SujetUpdateFXML.fxml"));
            Parent root = loader.load();
            SujetUpdateFXMLController controller = loader.getController();
            controller.initData(sujet);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un sujet");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(CategorieGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerSujet(MouseEvent event) throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce sujet ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ss.supprime(sujet);
        }

    }

    @FXML
    private void commentsByTopic(MouseEvent event) {
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

    void setData2(PostsSauvegardés c) {
        System.out.println(c.toString());
        sujet = c.getSujet();
        user = c.getUser();
        contenu_sujet.setText(c.getSujet().getContenu_sujet());
        category.setText(c.getSujet().getCat().getNom_categorie());
        nb_comments.setText(String.valueOf(c.getSujet().getNb_commentaires()));
        etat.setText(c.getSujet().getEtat());
    }
}
