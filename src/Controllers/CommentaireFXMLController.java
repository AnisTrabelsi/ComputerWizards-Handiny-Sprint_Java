/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Entite.Sujet;
import Services.ServiceCommentaire;
import Services.ServiceSujet;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CommentaireFXMLController implements Initializable {

    FileChooser fileChooser = new FileChooser();

    @FXML
    private Button EnvoieCommentBtn;
    @FXML
    private Button EffacerBtn;
    @FXML
    private Button file;
    @FXML
    private TextField commentaire;
    @FXML
    private Label messageTextArea;
    private ArrayList<File> attachments = new ArrayList<>();
    @FXML
    private ImageView imageview;
    @FXML
    private TextField id_sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new ExtensionFilter("JPG", new String[]{"*.jpg"}), new ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new ExtensionFilter("BMP", new String[]{"*.bmp"}), new ExtensionFilter("PNG", new String[]{"*.png"}), new ExtensionFilter("GIF", new String[]{"*.gif"})});
    }

    @FXML
    private void EnvoyerCommentaire(ActionEvent event) {
        ServiceCommentaire serc=new ServiceCommentaire();
        ServiceSujet sers=new ServiceSujet();
        EnvoieCommentBtn.setOnAction((ActionEvent e) -> {
            try {
                
                Sujet suj = sers.findById(Integer.parseInt(id_sujet.getText()));
                Commentaire comment = new Commentaire(commentaire.getText(), messageTextArea.getText(), suj);
                serc.ajouter(comment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire inséré avec succés!");
                alert.show();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
    }

    @FXML
    private void EffacerCommentaire(ActionEvent event) {
        commentaire.setText("");
        messageTextArea.setText("");
    }

    @FXML
    private void gettext(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Attach File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            attachments.add(selectedFile);
            messageTextArea.setText(selectedFile.getName() + "\n");
            Image image;
            image = new Image(selectedFile.toURI().toString());
            imageview.setImage(image);
        }
    }

}
