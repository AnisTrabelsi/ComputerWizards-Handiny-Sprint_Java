/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.io.File;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Rayen
 */
public class MailerAPI {

    static Session sesh;
    static Properties prop = new Properties();

    public static void Mail(String UN, String PW, String to, String sub, String cont, String path) /*throws IOException*/ {

        try /*throws IOException*/ {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(UN, PW);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(UN));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);

            // Crée une partie de message pour la pièce jointe
            MimeBodyPart attachmentPart = new MimeBodyPart();
            try {
                attachmentPart.attachFile(new File("C:\\xampp4\\htdocs\\Handiny\\" + path));
            } catch (IOException ex) {
                Logger.getLogger(MailerAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
           // création de la partie de message pour le contenu HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(cont, "text/html");

            // création du message multipart
            Multipart multipart = new MimeMultipart();
             multipart.addBodyPart(attachmentPart);
            multipart.addBodyPart(htmlPart);
           

// Ajoute le conteneur multipart au message
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
