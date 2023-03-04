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
import Services.Service_utilisateur;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Ajout_don_adminController implements Initializable {

    @FXML
    private TextField tfdescription;
    private DatePicker tfdate;
    @FXML
    private ComboBox<String> tfype;
    @FXML
    private TextField URLImage;
    @FXML
    private ImageView Image;
  
    @FXML
    private TextField cin;
  
  
    

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
            String res;
            int len;
            len = path.length();

            res = path.substring(47, len);
            System.out.println(res);
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
   private boolean user_valide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(cin.getText());
        if(m.find() && m.group().equals(cin.getText())){
            return true;
        }else{
               
            return false;            
        }}
   
   
     private boolean descvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(tfdescription.getText());
        if(m.find() && m.group().equals(tfdescription.getText())){
            return true;
        }else{
               
           
            return false;            
        }}
     
    @FXML
    private void ajouterdon(ActionEvent event) {

       // utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");
Service_utilisateur se=new Service_utilisateur();

        String restype = (String) tfype.getValue();
        String resdescription = tfdescription.getText();
        String i = URLImage.getText();
        String a=cin.getText();
         int b=0;  
        try {
            b = se.rechercherparcin(a);
        } catch (SQLException ex) {
            Logger.getLogger(Ajout_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Service_don ser = new Service_don();
        
        
        
        if (restype.isEmpty() || resdescription.isEmpty() || i.isEmpty() || a.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } else if (!user_valide() || (!descvalide()))
        {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type validé !");
                alert.showAndWait();
        }
        
       else 
        {
            don p = new don(b, restype, i, resdescription);
            try {
                ser.ajouter_don(p);
                JOptionPane.showMessageDialog(null, "don ajouté");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
               String UN = "tunisietendances@gmail.com";
            String PW = "cndzsmkffcnrpuer";
            String mto="";
    try {
        mto = se.rechercherparcin_email(a);
    } catch (SQLException ex) {
        Logger.getLogger(Ajout_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
    }
            String msub = "Votre don est ajouté avec succés ";
            //String heure_str = (p.getDate_ajout()).strftime('%H:%M:%S');
            //String cTEXT = "Monsieur,Madame '"+u.getNom()+" "+u.getPrenom()+"'\nVotre don de "+ p.getType()+" est ajouté avec succés"+"\nvoici vos données: \n"+"Description: " +p.getDescription()+"\n" ;
                
String html="";
    try {
        html = "<div class='container'>" +
                "<p class='greeting'>Monsieur,Madame ''" + se.rechercherparcin_nom(a) + " " + se.rechercherparcin_prenom(a) + "''</p>" +
                "<p class='success-msg'>Votre don de ''" + p.getType() + "'' est ajouté avec succés</p>" +
                "<p class='data'>Voici vos données:</p>" +
                "<p class='description'>Description du don: " + p.getDescription() + "</p>" +
                "</div>";
    } catch (SQLException ex) {
        Logger.getLogger(Ajout_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
    }

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
            
            

        }

    }

    @FXML
    private void tfype(ActionEvent event) {
    }

    private void vider() {

        tfdescription.setText(null);
        URLImage.setText(null);
cin.setText(null);
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
