/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Services.ServiceCommentaire;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CommentaireListFXMLController implements Initializable {

    @FXML
    private Label categoriesLabel;
    @FXML
    private Button modifCommentBtn;
    @FXML
    private Button supprimerCommentBtn;
    @FXML
    private ListView<Commentaire> listView;
    private List<Commentaire> items = null;
    ServiceCommentaire catdao = new ServiceCommentaire();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            items = catdao.readAll();
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (Commentaire c : items) {
            String path = "C:\\Users\\bengh\\OneDrive\\Documents\\NetBeansProjects\\Handiny\\src\\Assets\\" + c.getPiecejointe();
            File file = new File(path);
            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(100);
            i.setFitHeight(100);
            listView.getItems().add(c);
        }
    }    

    @FXML
    private void goToModifCommentaire(MouseEvent event) {
    }

    @FXML
    private void supprimerCommentaire(MouseEvent event) {
    }
    
}
