/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
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
 * @author Chayma
 */
public class Recuperer_mdpController implements Initializable {

    @FXML
    private TextField email;
     Connection con = DataSource.getInstance().getConnection();


    private String myAccountEmail = "Chayma.bensaad@esprit.tn"; // mettre votre email ici
    private String password = "223JFT2127"; // mettre votre mot de passe ici

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void recuperer(ActionEvent event) {
        String req = "SELECT * FROM user WHERE email=?";
        String email1 = null;
        try {
            PreparedStatement smt = con.prepareStatement(req);
            smt.setString(1, email.getText());
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                email1 = rs.getString("email");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sendMail(email1);
    }

    private Message preparedMessage(Session session, String recepient) {
        String req = "SELECT * FROM user WHERE email=?";
        String pass = null;
        try {
            PreparedStatement smt = con.prepareStatement(req);
            smt.setString(1, email.getText());
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
              String  passwrd = rs.getString("mot_de_passe");
                      pass =AESEncryptor.decrypt(passwrd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String text = "Votre mot de passe est : " + pass;
        String object = "Récupération de mot de passe";
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(object);
            String htmlcode = "<h1>" + text + "</h1><h2></h2>";
            message.setContent(htmlcode, "text/html");
            return message;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void sendMail(String recepient) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = preparedMessage(session, recepient);
        try {
            Transport.send(message);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Handiny");
            alert.setHeaderText(null);
            alert.setContentText("Consultez votre boîte mail !");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}