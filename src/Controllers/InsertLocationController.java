package controllers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.event.ActionEvent;


import Entite.Reservation_voiture;
import Entite.Utilisateur;
import Entite.Voiture;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.mail.PasswordAuthentication;

import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.mail.*;
import javax.mail.internet.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.ServiceReservation_Voiture;
import Services.ServiceUtilisateur;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.swing.JOptionPane;
import services.ServiceVoiture;

public class InsertLocationController implements Initializable {

    @FXML
    private AnchorPane anchlorPaneVoiture;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField desc;
   public int carId;
   
   
public void setIDVoiture(int id_voiture) {
    try {
        carId = id_voiture;
        System.out.println("methode set " + carId);

        ServiceVoiture sv = new ServiceVoiture();
        Voiture v = sv.findById(carId);
        System.out.println(v);
        ServiceReservation_Voiture sr = new ServiceReservation_Voiture();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}



    public int getIDVoiture() {
        return this.carId;
    }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
    datedeb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDateSelection(event);
            }
        });
        datefin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDateSelection(event);
            }
        });
    }
    @FXML
    private void handleDateSelection(ActionEvent event) {
        LocalDate debut = datedeb.getValue();
        LocalDate fin = datefin.getValue();
       
        if (debut != null && fin != null) {
            if (fin.isBefore(debut)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates incorrectes");
        alert.setContentText("La date de fin ne peut pas être antérieure à la date de début.");
        alert.showAndWait();}
            else{
            try {
                long daysBetween = Duration.between(debut.atStartOfDay(), fin.atStartOfDay()).toDays();
                ServiceVoiture sv = new ServiceVoiture();
                Voiture v = sv.findById(getIDVoiture());
                ServiceReservation_Voiture sr = new ServiceReservation_Voiture();
                double prixTotal = sr.calculerPrixTotal(v, debut,fin); // Calcul du prix total de la location (10 € par jour)
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prix de location");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Le prix total de la location est de %.2f DT pour une durée de %d jours.", prixTotal, daysBetween));
        // Définir le style inline de l'alerte
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color:  #f2f2ff; "
            + "-fx-font-size: 18pt; "
            + "-fx-text-fill: #444444; "
            + "-fx-font-family: 'Segoe UI', Helvetica, Arial, sans-serif;");
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(InsertLocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
    }



    @FXML
    private void insert(ActionEvent event) throws SQLException, WriterException, IOException {
        System.out.println("init"+getIDVoiture());
        LocalDate date1 = datedeb.getValue();
        LocalDate date2 = datefin.getValue();
        String descrip=desc.getText();

        // Vérification de la date
    if (date1 == null) {
        datedeb.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datedeb).play();
    }else
        datedeb.setStyle("");
        
      
    if (date2 == null) {
        datefin.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datefin).play();
        
    }else
        datefin.setStyle("");
        
        if (descrip.isEmpty()) {
        desc.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(desc).play();
        }
        else
       desc.setStyle("");
    if (datedeb == null ||datefin == null ||descrip.isEmpty()){return;}
    if (date2.isBefore(date1)) {
        datefin.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datefin).play();
        datedeb.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(datedeb).play();
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates incorrectes");
        alert.setContentText("La date de fin ne peut pas être antérieure à la date de début.");
        alert.showAndWait();
        return;
    }

       

 

        // Vérifier si le champ Prix Location est un nombre valide
        ServiceVoiture sv = new ServiceVoiture();
        Voiture v=sv.findById(getIDVoiture());
        
        
        String emailprop = v.getUser().getEmail();
        String prenprop=v.getUser().getPrenom();

        String voitureimmatric=v.getImmatriculation();
        String pathimg=v.getImage_voiture();
      
           Utilisateur u=Utilisateur.getCurrent_user();
            ServiceUtilisateur su= new ServiceUtilisateur();
            String locatairePrenom=u.getPrenom();
        Reservation_voiture r = new Reservation_voiture(u,v,java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2),descrip);
        ServiceReservation_Voiture rv = new ServiceReservation_Voiture();
        try {
            if (rv.ajouter(r)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alerte");
                alert.setContentText("Location ajoutée avec succés ! ");
                alert.showAndWait();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("");
                alert.setContentText("Impossible d'ajouter cette Location");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println(ex);}
        

String from = "chaima.lotfi@esen.tn";
        //JOptionPane.showInputDialog("Adresse e-mail : ");
String password = "deutsch123LAMMA.";
        //JOptionPane.showInputDialog("Mot de passe : ");    
String subject = "Réservation de votre voiture";
String message = "Bonjour Mr/Madame  "+prenprop+ "  ,\n\n Votre voiture ayant l\'immatriculation "+voitureimmatric+"  a été réservée par  "+locatairePrenom+"\n\n cordialement.";

String to =emailprop;
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
    
    // Créer le contenu du message (texte + image)
    MimeMultipart multipart = new MimeMultipart();
    
    // Ajouter le texte
    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText(message);
    multipart.addBodyPart(textPart);
    
    // Ajouter l'image
    MimeBodyPart imagePart = new MimeBodyPart();
    DataSource fds = new FileDataSource(pathimg);
    imagePart.setDataHandler(new DataHandler(fds));
    imagePart.setHeader("Content-ID", "<image>");
    multipart.addBodyPart(imagePart);
    
    email.setContent(multipart);
    
    Transport.send(email);
    System.out.println("E-mail envoyé à " + to);
} catch (MessagingException e) {
    throw new RuntimeException(e);
}

//// Obtenir la date actuelle du système
//LocalDate currentDate = LocalDate.now();
//
//// Créer une chaîne contenant les informations de la réservation
//String reservationInfo = "Date de réponse de demande : " + currentDate.toString() + "\n" +
//                         "Propriétaire : " + prenprop + " (" + emailprop + ")\n" +
//                         "Immatriculation : " + voitureimmatric + "\n" +
//                         "Locataire : " + locatairePrenom + "\n" +
//                         "Date de début : " + date1.toString() + "\n" +
//                         "Date de fin : " + date2.toString() + "\n" +
//                         "Description : " + descrip;
//
//// Créer un objet QRCodeWriter pour générer le code QR
//QRCodeWriter qrCodeWriter = new QRCodeWriter();
//
//
//// Générer le code QR à partir des informations de la réservation
//BitMatrix bitMatrix = qrCodeWriter.encode(reservationInfo, BarcodeFormat.QR_CODE, 200, 200);
//
//// Créer une image BufferedImage à partir du BitMatrix
//int width = bitMatrix.getWidth();
//int height = bitMatrix.getHeight();
//BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//for (int x = 0; x < width; x++) {
//    for (int y = 0; y < height; y++) {
//        qrImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
//    }
//}
//
//// Enregistrer l'image du code QR dans un fichier
//File qrFile = new File("D:/JDBC/reservation_qr.png");
//ImageIO.write(qrImage, "png", qrFile);
//
//    }

    

   

  
}}
