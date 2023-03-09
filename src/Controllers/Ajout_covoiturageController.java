/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Covoiturage;
import Services.ServiceCovoiturage;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Entite.Utilisateur;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Ajout_covoiturageController implements Initializable {
Utilisateur u=Utilisateur.getCurrent_user();

    @FXML
    private ImageView Image;
    @FXML
    private TextField tfdepart;
    @FXML
    private TextField tfdestination;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnbr;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Button idqr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 tfprix.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfprix.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });   
    
    tfnbr.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) { // Only allow digits
            tfnbr.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digits
        }
    });
    
    }    

    @FXML
    private void ajouterCov(ActionEvent event) {
            
//    Utilisateur u = new Utilisateur(3, "mohamed", "benabbes", "09638420", "abbes525525@gmail.com", "24076282", "aaa", "aaaa", new Date(2020, 25, 01), "tounes", 2086, "user", "sdsdsd");
        ServiceCovoiturage ser = new ServiceCovoiturage();

         String depart = tfdepart.getText() ; 
                String destination = tfdestination.getText() ; 
        LocalDate date = tfdate.getValue() ; 

            //    int nbrplace =Integer.parseInt(tfnbr.getText()) ; 
              //  int prix =Integer.parseInt(tfprix.getText()) ; 

        
      String nbrText = tfnbr.getText();
String prixText = tfprix.getText();

        if (depart.isEmpty() || destination.isEmpty() || nbrText.isEmpty()  || nbrText.isEmpty()  || date == null ||prixText.isEmpty()   ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît remplir tous les champs !");
            alert.showAndWait();
        } else  {
            int nbr = Integer.parseInt(nbrText);
    int prix = Integer.parseInt(prixText);
    
    if (nbr > 8 || nbr <= 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("nombre de place doit etre positif et moins de 8");
            alert.showAndWait();
        
    }else if (prix < 0 ) { 
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prix doit etre positif ");
            alert.showAndWait();
    }else if ( date.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez saisir une date valide.");
        alert.showAndWait();
        
   
        
    }else {
            Covoiturage p = new Covoiturage(u.getId_utilisateur(), depart, destination, java.sql.Date.valueOf(date), prix,nbr,u.getNom(),u.getTelephone());

            try {
                ser.ajouter(p);
                String subject = "";
              String body = "" ; 
           //     String body = "Hello " + u.getNom() + ",\n\nYour carpool from " + depart + " to " + destination + " on " + date.toString() + " has been successfully added to the system.\n\nThank you for using our service!\n\nBest regards,\nThe Carpool Team";
                sendEmail(u.getEmail(), subject, body);
                
              
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            JOptionPane.showMessageDialog(null, "covoiturage ajouter avec succes");
            tfdepart.clear();
                        tfdestination.clear();
            tfprix.clear();
            tfnbr.clear();
tfdate.setValue(null) ; 
 String subject = "Réservation de votre voiture";
String message = "Bonjour Mr/Madame  "+ u.getNom()+ "  ,\n\n Votre covoiturage est ajouter avec success ";
String from = "mohamed.benabbes@esprit.tn";
String password = "223JMT3660";
String to = u.getEmail();
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");
Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Envoyer l'e-mail
        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(from));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            email.setSubject(subject);
            email.setText(message);
            Transport.send(email);
            System.out.println("E-mail envoyé à " + to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        }
        } 
   
    
    
    
    }

    private void goaffi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void mesreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/mes_reservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public static boolean isNumberWithinLengthLimit(String input) {
    try {
        if (input == null || input.isEmpty()) {
        return false; // input cannot be empty or null
    }
        Integer.parseInt(input); // check if input can be parsed as an integer
        return true; // input is a valid number within the length limit
    } catch (NumberFormatException e) {
        return false; // input is not a valid number
    }
}
    
    private void sendEmail(String recipient, String subject, String body) {
    String host = "smtp.gmail.com";
    String username = "mohamed.benabbes@esprit.tn"; // Replace with your Gmail address
    String password = "223JMT3660"; // Replace with your Gmail password
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
 
        Message message = new MimeMessage(session);
 
    }

    private void gomesreserv(ActionEvent event) throws IOException {
    
       Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/mes_reservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
      @FXML
   private void generateQR(ActionEvent event) {
    // Création de l'objet QRCodeWriter
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

    // Paramètres de l'encodeur
    int width = 300;
    int height = 300;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer le QR code");
    File file = fileChooser.showSaveDialog(null);

    // Configuration des paramètres de l'encodeur
    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    try {
        String data = "https://youtu.be/nZ1JwKRxiLs";
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        String fileName = file.getName().concat(".png");
        try (OutputStream out = new FileOutputStream(new File(file.getParent(), fileName))) {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch (WriterException e) {
        e.printStackTrace();
    }
}
    }
    
 
