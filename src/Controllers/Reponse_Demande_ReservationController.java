/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import javax.mail.PasswordAuthentication;
import Entite.Reservation_voiture;
import Entite.Utilisateur;
import Entite.Voiture;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
//import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import services.ServiceReservation_Voiture;
import Services.ServiceUtilisateur;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Reponse_Demande_ReservationController implements Initializable {


    int reservation_Id;
    @FXML
    private Label desc;
    @FXML
    private Label deb;
    @FXML
    private Label fin;
    @FXML
    private Label datedem;
    @FXML
    private Button saveButton;
    @FXML
    private VBox detailReservationId;
    @FXML
    private RadioButton accepterButton;
    @FXML
    private RadioButton reffuserButton;
    @FXML
    private ToggleGroup toggleReponse;
     private ObservableList<Reservation_voiture> ObList;

    /**
     * Initializes the controller class.
     */
    void setIDReservation(int id_reservation) {
       
       
            reservation_Id = id_reservation;
            System.out.println("idR"+reservation_Id);
            ServiceReservation_Voiture sr=new ServiceReservation_Voiture();
         try {    Reservation_voiture r=sr.findById(reservation_Id);
             System.out.println("bb"+r);
            
            
            deb.setText(r.getDate_debut_reservation().toString());
            fin.setText(r.getDate_fin_reservation().toString());
            desc.setText(r.getDescription_reservation());
            datedem.setText(r.getDate_demande_reservation().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Reponse_Demande_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  

    
    public int getIDReservation() {
        return this.reservation_Id;
    }

    @FXML
    private void save(ActionEvent event) throws WriterException, MessagingException, IOException {
    
    // Récupérer la valeur sélectionnée dans le groupe de boutons radio
    RadioButton selectedRadioButton = (RadioButton) toggleReponse.getSelectedToggle();
    String reponse = selectedRadioButton.getText();
    
    // Vérifier si l'utilisateur a sélectionné le bouton "Accepter"
    if (reponse.equals("Accepter")) {
        
        try {
            
            try {
                // Faire quelque chose si le bouton "Accepter" a été sélectionné
                System.out.println("L'utilisateur a cliqué sur Accepter.");
                ServiceReservation_Voiture sr= new ServiceReservation_Voiture();
                Reservation_voiture r= new Reservation_voiture(getIDReservation(),"Acceptée");
                sr.update2(r);
                
                
                
                List<Reservation_voiture> reservations=sr.readAll();
                ObList.clear();;
                ObList.addAll(reservations);
                
            } catch (SQLException ex) {
                Logger.getLogger(Reponse_Demande_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            // Obtenir l'adresse e-mail et le mot de passe de l'utilisateur
            String from = JOptionPane.showInputDialog("Adresse e-mail : ");
            String password = JOptionPane.showInputDialog("Mot de passe : ");
            
            // Configurer la session SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            
            
            
// Créer le contenu du mail
//String from = "chaima.lotfi@esen.tn";
ServiceReservation_Voiture sr= new ServiceReservation_Voiture();
Reservation_voiture resv = sr.findById2(getIDReservation());

// Informations de la réservation
String immatriculation = resv.getVoit().getImmatriculation();
Date dateDebut = resv.getDate_debut_reservation();
Date dateFin = resv.getDate_fin_reservation();
String descrip=resv.getDescription_reservation();
String etat=resv.getEtat_demande_reservation();
Date datedem=resv.getDate_demande_reservation();

// Informations de l'utilisateur
String email = resv.getUser().getEmail();
String nom = resv.getUser().getNom();
String prenom = resv.getUser().getPrenom();
String prenprop=resv.getVoit().getUser().getNom();
String emailprop = resv.getVoit().getUser().getEmail();
String to = email;
String subject = "Réservation de voiture acceptée";
String body = "Bonjour " + nom +prenom+ ",\n\nVotre demande de voiture ayant l'immatriculation " + immatriculation + " a été acceptée.\n\nVeuillez trouver ci-joint le code QR contenant les informations de votre réservation.\n\nCordialement,\nL'équipe de Handiny";

QRCodeWriter qrCodeWriter = new QRCodeWriter();
            LocalDate currentDate = LocalDate.now();

/// Créer une chaîne contenant les informations de la réservation
String reservationInfo = "Etat de la demande de reservation : " + etat + "\n" +
                         "Date de la demande de reservation : " + datedem + "\n" +
                         "Date de reponse de demande : " + currentDate.toString() + "\n" +
                         "Proprietaire de voiture : " + prenprop + " (" + emailprop + ")\n" +
                         "Immatriculation : " + immatriculation + "\n" +
                         "Locataire : " + prenom + " " + nom  + "\n" +
                         "Date de debut de reservation : " + dateDebut.toString() + "\n" +
                         "Date de fin de reservation: " + dateFin.toString() + "\n" +
                         "Description : " + descrip;

BitMatrix bitMatrix = qrCodeWriter.encode(reservationInfo, BarcodeFormat.QR_CODE, 400, 400);

BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
for (int x = 0; x < bitMatrix.getWidth(); x++) {
    for (int y = 0; y < bitMatrix.getHeight(); y++) {
        int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
        image.setRGB(x, y, color);
    }
}

ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
ImageIO.write(image, "PNG", outputStream);

byte[] qrCodeBytes = outputStream.toByteArray();

// Ajouter le code QR en pièce jointe
MimeBodyPart qrCodeAttachment = new MimeBodyPart();

qrCodeAttachment.setFileName("reservation_qr_code.png");
qrCodeAttachment.setContent(qrCodeBytes, "image/png");

// Créer le message et ajouter les pièces jointes
MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(from));
message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
message.setSubject(subject);
MimeMultipart multipart = new MimeMultipart();
MimeBodyPart textPart = new MimeBodyPart();
textPart.setText(body);
multipart.addBodyPart(textPart);
multipart.addBodyPart(qrCodeAttachment);
message.setContent(multipart);

// Envoyer le message
Transport.send(message);



        } catch (SQLException ex) {
            Logger.getLogger(Reponse_Demande_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    } else {
        // Faire quelque chose si le bouton "Refuser" a été sélectionné
        System.out.println("L'utilisateur a cliqué sur Refuser.");
        try {
            // Faire quelque chose si le bouton "Accepter" a été sélectionné
           
            ServiceReservation_Voiture sr= new ServiceReservation_Voiture();
            Reservation_voiture r= new Reservation_voiture(getIDReservation(),"Refusée");
            sr.update2(r);
            
            
            List<Reservation_voiture> reservations=sr.readAll();
                    ObList.clear();

                    ObList.addAll(reservations);
                    // Obtenir l'adresse e-mail et le mot de passe de l'utilisateur
            String from = JOptionPane.showInputDialog("Adresse e-mail : ");
            String password = JOptionPane.showInputDialog("Mot de passe : ");
            
            // Configurer la session SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            
            
            
// Créer le contenu du mail
//String from = "chaima.lotfi@esen.tn";

Reservation_voiture resv = sr.findById2(getIDReservation());

// Informations de la réservation
String immatriculation = resv.getVoit().getImmatriculation();
Date dateDebut = resv.getDate_debut_reservation();
Date dateFin = resv.getDate_fin_reservation();
String descrip=resv.getDescription_reservation();
String etat=resv.getEtat_demande_reservation();
Date datedem=resv.getDate_demande_reservation();

// Informations de l'utilisateur
String email = resv.getUser().getEmail();
String nom = resv.getUser().getNom();
String prenom = resv.getUser().getPrenom();
String prenprop=resv.getVoit().getUser().getNom();
String emailprop = resv.getVoit().getUser().getEmail();
String to = email;
String subject = "Réservation de voiture acceptée";
String body = "Bonjour " + nom +prenom+ ",\n\nVotre demande de voiture ayant l'immatriculation " + immatriculation + " a été refusée .\n\nCordialement,\nL'équipe de Handiny";
MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(from));
message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
message.setSubject(subject);
MimeMultipart multipart = new MimeMultipart();
MimeBodyPart textPart = new MimeBodyPart();
textPart.setText(body);
multipart.addBodyPart(textPart);
message.setContent(multipart);

// Envoyer le message
Transport.send(message);
        } catch (SQLException ex) {
            Logger.getLogger(Reponse_Demande_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    }

    void setObList(ObservableList ObList) {
         this.ObList = ObList;
    }

   
    
}
