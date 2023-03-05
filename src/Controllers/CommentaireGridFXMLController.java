/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Services.ServiceCommentaire;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CommentaireGridFXMLController implements Initializable {

    @FXML
    private Label mentions;
    @FXML
    private Label comment_contenu;
    @FXML
    private ImageView Attachement;
    @FXML
    private Button likeBtn;
    @FXML
    private Button modifCommentBtn;
    @FXML
    private Button supprimerCommentBtn;

    private Commentaire comment = new Commentaire();
    ServiceCommentaire ss = new ServiceCommentaire();
    private int nbLikes = 0;
    private boolean isLiked = false;

    /**
     * Initializes the controller class.
     *
     * @param c
     */
    @FXML
    private void like(ActionEvent event) {
        if (!isLiked) {
            nbLikes++;
            comment.setNb_mentions(nbLikes);
            mentions.setText(String.valueOf(nbLikes));
            likeBtn.setStyle("-fx-background-color: blue;");
            isLiked = true;
        } else {
            nbLikes--;
            if (nbLikes >= 0) {
                comment.setNb_mentions(nbLikes);
                mentions.setText(String.valueOf(nbLikes));
            } else {
                nbLikes = 0;
                comment.setNb_mentions(nbLikes);
                mentions.setText(String.valueOf(nbLikes));
            }
            likeBtn.setStyle("-fx-background-color: white;");
            isLiked = false;
        }
        try {
            ss.update(comment);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setData(Commentaire c) throws FileNotFoundException, IOException {
        System.out.println(c.toString());
        comment = c;
        comment_contenu.setText(c.getContenu_commentaire());
        String path = c.getPiecejointe();
        System.out.println(path);
        File file = new File(path);
        if (path != null) {
            ImageView image = new ImageView(new Image(file.toURI().toString()));
            Attachement.setImage(image.getImage());
        }
        comment.setNb_mentions(comment.getNb_mentions());
        mentions.setText(String.valueOf(comment.getNb_mentions()));
        isLiked = false;
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(nbLikes>0){
            likeBtn.setStyle("-fx-background-color: blue;");
        }
        else{
            likeBtn.setStyle("-fx-background-color: white;");
        }
    }

    @FXML
    private void goToModifComment(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CommentaireUpdateFXML.fxml"));
            Parent root = loader.load();
            CommentaireUpdateFXMLController controller = loader.getController();
            controller.initData(comment);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un commentaire");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(CategorieGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerComment(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce commentaire ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                ss.supprime(comment);
            } catch (SQLException ex) {
                Logger.getLogger(CommentaireGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
