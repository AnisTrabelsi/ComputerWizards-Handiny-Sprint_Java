/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.awt.Desktop;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class ForumRulesFXMLController implements Initializable {

    @FXML
    private Button gotofileBtn;
    @FXML
    private CheckBox acceptrulesCheckbox;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    Utilisateur u = Utilisateur.getCurrent_user();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotofile(ActionEvent event) {
        generatePDF();
    }

    private void mailconfirm() {

        String user_name = u.getNom();
        String subject = "Confirmation avec Handiny";
        String message = "Bonjour Mr/Madame," + user_name + "\nVous êtes le bienvenu à notre communauté Handiny, \nMaintenant, vous pouvez accéder à notre forum et bien évidemment créer vos postes \n cordialement";
        String from = "oumaima.benghanem@esen.tn";
        String password = "nwanwntbigehgjkd";
        String to = u.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String s = "E-mail envoyé";
            alert.setTitle(s);
            alert.setHeaderText(null);
            alert.setContentText("L'e-mail a été envoyé avec succès à " + to);
            alert.showAndWait();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
        if (acceptrulesCheckbox.isSelected()) {
            // Send the email
            mailconfirm();
        } else {
            // Show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez accepter les règles du forum.");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    public void generatePDF() {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Add a blank page to the document
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);

            // Add some text to the page
            PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Voici le règlement du forum !");
            contentStream.endText();
            contentStream.close();

            // Save the document
            document.save("RèglementsForum.pdf");
            document.close();

            // Download the PDF file
            File pdfFile = new File("RèglementsForum.pdf");
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
