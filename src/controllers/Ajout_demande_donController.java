/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import API.MailerAPI;
import Entite.demande_don;
import Entite.don;
import Entite.utilisateur;
import Services.Service_demande_don;
import Services.Service_don;
import Services.Service_utilisateur;
//import com.itextpdf.text.pdf.qrcode.BitMatrix;
//import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.image.BufferedImage;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Ajout_demande_donController implements Initializable {

    @FXML
    private TextField URLImage;
  
    @FXML
    private TextField remarque;
    @FXML
    private ImageView Image;
    @FXML
    private VBox vb;
 private static int a;
 private static String t;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
          vb.getChildren().clear();
     
     
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType());
                  Label label2 =new Label(d.getDescription());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label3 =new Label(d.getImage_don());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label3);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
             Label label4 =new Label(d.getDate_ajout().toString());
      hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                    Button button = new Button("Séletionner don!");
               button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0");
             button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

             a=d.getId_don();
              t=d.getType();
             JOptionPane.showMessageDialog(null, "don selectionné");
               

            });
            hbox.getChildren().addAll(button,label1,vbox2,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
        
        
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
            String res;
           
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
        Matcher m = p.matcher(remarque.getText());
        if (m.find() && m.group().equals(remarque.getText())) {
            return true;
        } else {

            return false;
        }
    }
    
  @FXML
    private void ajouter(ActionEvent event) {
    utilisateur u = new utilisateur(1, "Anis", "Trabelsi", "kbikjb", "tunisietendances@gmail.com", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");
       
        String justif = URLImage.getText();
        String rem = remarque.getText();
        Service_demande_don ser = new Service_demande_don();
        demande_don d = new demande_don(u.getId_utilisateur(), a,justif,t, rem);

        if (justif.isEmpty() || rem.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        }  else if (!descvalide()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Type validé !");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un type validé !");
            alert.showAndWait();

        }
        
        
        else {

            try {
                ser.ajouter_demande_don(d);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            JOptionPane.showMessageDialog(null, "demande ajouté");
            
              Service_utilisateur se=new Service_utilisateur();
                   
                   String UN = "tunisietendances@gmail.com";
                   String PW = "cndzsmkffcnrpuer";
                   String mto=u.getEmail();
               
                   String msub = "Votre demande de don est ajouté avec succés ";
                   //String heure_str = (p.getDate_ajout()).strftime('%H:%M:%S');
                   //String cTEXT = "Monsieur,Madame '"+u.getNom()+" "+u.getPrenom()+"'\nVotre don de "+ p.getType()+" est ajouté avec succés"+"\nvoici vos données: \n"+"Description: " +p.getDescription()+"\n" ;
                   
                   String html = "<div class='container'>" +
                           "<p class='greeting'>Monsieur,Madame ''" + u.getPrenom() + " " + u.getNom() + "''</p>" +
                           "<p class='success-msg'>Votre demande de don de ''" + d.getType_produit_demande() + "'' est ajouté avec succés</p>" +
                           "<p class='data'>Voici vos données:</p>" +
                           "<p class='description'>Remarques : " + d.getRemarques() + "</p>" +
                           "<p class='description'>etat de la demande : en cours" + "</p>" +
                             "<p class='description'> votre justificatif d'handicap est ci dessous: " + "</p>" +
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
                           "margin-top: 30px;" +
                           "}" +
                           ".description {" +
                           "margin-top: 10px;" +
                           "font-weight: bold;"+
                           "}";
                   
                   String cTEXT = "<html><head><style>" + css + "</style></head><body>" + html + "</body></html>";
                   MailerAPI.Mail(UN, PW, mto, msub, cTEXT, d.getJustificatif_handicap());
            notiff();
            vider();
            LocalDate currentDate = LocalDate.now();
            // Créer une chaîne contenant les informations de la réservation
String demande_don =  "Prenom: "+u.getNom()+ "\n"+ "Nom: "+u.getPrenom()+"\n"+"Type de don demande: "+d.getType_produit_demande()+"\n"+"Date de demande : "
        +currentDate.toString()+"\n"+"Remarques: "+d.getRemarques()+"\n"+"Etat: En cours";

// Créer un objet QRCodeWriter pour générer le code QR
QRCodeWriter qrCodeWriter = new QRCodeWriter();


// Générer le code QR à partir des informations de la réservation
BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(demande_don, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException ex) {
            Logger.getLogger(Ajout_demande_donController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Créer une image BufferedImage à partir du BitMatrix
int width = bitMatrix.getWidth();
int height = bitMatrix.getHeight();
BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
        qrImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
    }
}
  Random rand = new Random();
        int x = rand.nextInt(1000);
// Enregistrer l'image du code QR dans un fichier
File qrFile = new File("C:\\xampp4\\htdocs\\Gestion don\\Demande_don_"+x+".png");
        try {
            ImageIO.write(qrImage, "png", qrFile);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_demande_donController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }
    
    private void vider() {

        remarque.setText(null);
        URLImage.setText(null);
        Image.setImage(null);
    }

    /////////////////////////////////////////
  private void notiff() {
        Service_don ser = new Service_don();
    
        don d = new don();

        //int y = sv.calculnb((Destination.getText()));
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("une demande de don a été ajoutée");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }
}
