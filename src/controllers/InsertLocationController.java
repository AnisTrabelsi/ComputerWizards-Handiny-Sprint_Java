package controllers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;

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
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import services.ServiceReservation_Voiture;
import services.ServiceUtilisateur;
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
         carId = id_voiture;
         System.out.println("methode set "+carId);
    }
    public int getIDVoiture() {
        return this.carId;
    }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
        
    }

    @FXML
    private void insert(ActionEvent event) throws SQLException, WriterException, IOException {
        System.out.println("init"+getIDVoiture());
        LocalDate date1 = datedeb.getValue();
        LocalDate date2 = datefin.getValue();
        String descrip=desc.getText();

        // Vérification de la date
    if (date1 == null || date2 == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Dates manquantes");
        alert.setContentText("Veuillez saisir une date de début et une date de fin.");
        alert.showAndWait();
        return;
    }
    if (date2.isBefore(date1)) {
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
      
         Utilisateur u=new Utilisateur(1,"Lotfi","Amira","12678","chaima.lotfi@esen.tn","25837256","chaima2703","motdepasse",new Date(2020, 15, 01),"tunis",2045,"locataire","Aouina");
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
            System.out.println(ex);
        } 
        
      
String subject = "Réservation de votre voiture";
String message = "Bonjour Mr/Madame  "+prenprop+ "  ,\n\n Votre voiture ayant l\'immatriculation "+voitureimmatric+"  a été réservée par"+locatairePrenom+"\n\n cordialement.";
String from = "chaima.lotfi@esen.tn";
String password = "";
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
            email.setText(message);
            Transport.send(email);
            System.out.println("E-mail envoyé à " + to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
// Obtenir la date actuelle du système
LocalDate currentDate = LocalDate.now();

// Créer une chaîne contenant les informations de la réservation
String reservationInfo = "Date de réservation : " + currentDate.toString() + "\n" +
                         "Propriétaire : " + prenprop + " (" + emailprop + ")\n" +
                         "Immatriculation : " + voitureimmatric + "\n" +
                         "Locataire : " + locatairePrenom + "\n" +
                         "Date de début : " + date1.toString() + "\n" +
                         "Date de fin : " + date2.toString() + "\n" +
                         "Description : " + descrip;

// Créer un objet QRCodeWriter pour générer le code QR
QRCodeWriter qrCodeWriter = new QRCodeWriter();


// Générer le code QR à partir des informations de la réservation
BitMatrix bitMatrix = qrCodeWriter.encode(reservationInfo, BarcodeFormat.QR_CODE, 200, 200);

// Créer une image BufferedImage à partir du BitMatrix
int width = bitMatrix.getWidth();
int height = bitMatrix.getHeight();
BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
        qrImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
    }
}

// Enregistrer l'image du code QR dans un fichier
File qrFile = new File("D:/JDBC/reservation_qr.png");
ImageIO.write(qrImage, "png", qrFile);

    }

    

   

  
}
