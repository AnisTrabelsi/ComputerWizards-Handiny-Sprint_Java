/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import API.MailerAPI;
import Entite.don;
import Entite.utilisateur;
import Services.Service_don;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.util.Properties;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Ajout_donController implements Initializable {

    @FXML
    private TextField tfdescription;
    private DatePicker tfdate;
    @FXML
    private ComboBox<String> tfype;
    @FXML
    private TextField URLImage;
    @FXML
    private ImageView Image;
    utilisateur u = new utilisateur(1, "Anis", "Trabelsi", "kbikjb", "tunisietendances@gmail.com", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Fauteuils roulants", "Prothèses", "Appareils auditifs", "Lunettes et lentilles de contact", "Béquilles",
                "Équipement de soins à domicile", "Rampes d'accès", "Appareils de levage ", "Sièges de bain", "autre");
        tfype.setItems(list);
        
    
    }

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        //String DBPath = "C:\\\\xampp\\\\htdocs\\\\Version-Integre\\\\public\\\\uploads\\\\" + x + ".jpg";
        String DBPath = "" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
            URLImage.setText(DBPath);
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

    private boolean descvalide() {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(tfdescription.getText());
        if (m.find() && m.group().equals(tfdescription.getText())) {
            return true;
        } else {

            return false;
        }
    }

    @FXML
    private void ajouterdon(ActionEvent event) throws MessagingException {

        String restype = (String) tfype.getValue();
        String resdescription = tfdescription.getText();
        String i = URLImage.getText();

        Service_don ser = new Service_don();

        if (restype.isEmpty() || resdescription.isEmpty() || i.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } else if (!descvalide()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Type validé !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un type validé !");
            alert.showAndWait();

        } else {
            don p = new don(u.getId_utilisateur(), restype, i, resdescription);
            try {
                ser.ajouter_don(p);

               

                JOptionPane.showMessageDialog(null, "don ajouté");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            String UN = "tunisietendances@gmail.com";
            String PW = "cndzsmkffcnrpuer";
            String mto = u.getEmail();
            String msub = "Votre don est ajouté avec succés ";
            //String heure_str = (p.getDate_ajout()).strftime('%H:%M:%S');
            //String cTEXT = "Monsieur,Madame '"+u.getNom()+" "+u.getPrenom()+"'\nVotre don de "+ p.getType()+" est ajouté avec succés"+"\nvoici vos données: \n"+"Description: " +p.getDescription()+"\n" ;
                
String html = "<div class='container'>" +
                 "<p class='greeting'>Monsieur,Madame ''" + u.getNom() + " " + u.getPrenom() + "''</p>" +
                 "<p class='success-msg'>Votre don de ''" + p.getType() + "'' est ajouté avec succés</p>" +
                 "<p class='data'>Voici vos données:</p>" +
                 "<p class='description'>Description du don: " + p.getDescription() + "</p>" +
              "</div>";

String css = ".container {" +
               "font-family: Arial, sans-serif;" +
               "background-color: #f2f2f2;" +
               "border: 1px solid #ccc;" +
               "padding: 20px;" +
               "border-radius: 10px;" +
             "}" +
             ".greeting {" +
               "font-size: 18px;" +
               "font-weight: bold;" +
             "}" +
             ".success-msg {" +
               "color: green;" +
               "font-weight: bold;" +
             "}" +
             ".data {" +
               "font-weight: bold;" +
               "margin-top: 20px;" +
             "}" +
             ".description {" +
               "margin-top: 10px;" +
             "}";

String cTEXT = "<html><head><style>" + css + "</style></head><body>" + html + "</body></html>";
            MailerAPI.Mail(UN, PW, mto, msub, cTEXT, p.getImage_don());

            tfype.setValue("Type");
            Image.setImage(null);
             notiff();
            vider();
            JOptionPane.showMessageDialog(null, "don ajouté");
           

        }

    }

   
    private void vider() {

        tfdescription.setText(null);
        URLImage.setText(null);
        tfdate.setValue(null);
        tfype.setValue("Type");
        URLImage.setText(null);
        Image.setImage(null);

    }

    private void notiff() {
        Service_don ser = new Service_don();
        String restype = (String) tfype.getValue();
        don d = new don();

        //int y = sv.calculnb((Destination.getText()));
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("un don a été ajouté");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }

}
