/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Entite.Sujet;
import Entite.Utilisateur;
import Services.ServiceCommentaire;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private Sujet sujet;
    Utilisateur u = Utilisateur.getCurrent_user();
    /**
     * Initializes the controller class.
     */
    @FXML
    private void gettext(MouseEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new ExtensionFilter("JPG", new String[]{"*.jpg"}), new ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new ExtensionFilter("BMP", new String[]{"*.bmp"}), new ExtensionFilter("PNG", new String[]{"*.png"}), new ExtensionFilter("GIF", new String[]{"*.gif"})});

        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp8.1\\htdocs\\Handiny\\src\\images\\" + x + ".jpg";

        //String DBPath = "" + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            messageTextArea.setText(path);
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();

        } else {
            System.out.println("error");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new ExtensionFilter("JPG", new String[]{"*.jpg"}), new ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new ExtensionFilter("BMP", new String[]{"*.bmp"}), new ExtensionFilter("PNG", new String[]{"*.png"}), new ExtensionFilter("GIF", new String[]{"*.gif"})});
    }

    public void showNotification() {
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Nouveau commentaire");
        tray.setMessage("Vous avez reçu un nouveau commentaire du sujet " + sujet.getTitre_sujet());
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.seconds(5)); // affiche la notification pendant 5 secondes
    }

    @FXML
    private void EnvoyerCommentaire(ActionEvent event) {
        try {
            ServiceCommentaire serc = new ServiceCommentaire();
             String commentText = commentaire.getText();
            String messageText = messageTextArea.getText();
            // Vérifier si le commentaire contient des mots interdits
            List<String> motsInterdits = Arrays.asList("bad", "mot2", "mot3");
            boolean contientMotInterdit = motsInterdits.stream().anyMatch(commentText::contains);
            if (contientMotInterdit) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le commentaire contient des mots interdits.");
                alert.showAndWait();
                return;
            }
            Commentaire comment = new Commentaire(commentText, messageText, sujet, u);
            serc.ajouter(comment);
            showNotification();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Commentaire inséré avec succés!");
            alert.show();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EffacerCommentaire(ActionEvent event) {
        commentaire.setText("");
        messageTextArea.setText("");
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }
}
