/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.HelloPageController;
import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import Utils.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nom_inscri;
    @FXML
    private TextField prenom_inscri;
    @FXML
    private DatePicker date_naiss_inscri;
    @FXML
    private TextField cin_inscri;
    @FXML
    private TextField email_inscri;
    @FXML
    private TextField telephone_inscri;
    @FXML
    private TextField login_inscri;
    //@FXML
    // private ChoiceBox<String> region_inscri;
     @FXML
      private PasswordField mdp_inscri;
    @FXML
      private CheckBox checkbox;
    @FXML
    private TextField password_text;
    @FXML
    private TextField adresse_inscri;
    @FXML
    private TextField code_postal_inscri;
    @FXML
    private Button inscription;
    @FXML
    private RadioButton loataire_inscri;
    @FXML
    private RadioButton proprietaire_inscri;
    @FXML
    private ToggleGroup Role;
    @FXML
    private ComboBox<String> region;
   

    ServiceUtilisateur su = new ServiceUtilisateur();

    AESEncryptor encryptor = new AESEncryptor();
    String role = "";
    String mot_de_passe;
    String type;
    String selectedRegion;
    private String regions[]
            = {"Ariana",
                "Béja",
                "Ben Arous",
                "Bizerte",
                "Gabès",
                "Gafsa",
                " Jendouba",
                "Kairouan",
                "Kasserine",
                "Kébili",
                "Kef",
                "Mahdia",
                "Mannouba",
                "Médenine",
                "Monastir",
                "Nabeul",
                "Sfax",
                "Sidi Bouzid",
                "Siliana",
                "Sousse",
                "Tataouine",
                "Tozeur",
                "Tunis",
                "Zaghouan"};
 


//    public void get_region(ActionEvent event) {
//        int index = Arrays.asList(regions).indexOf(selectedRegion); 
//        if (index >= 0) {
//            u.setRegion(regions[index]); 
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        region_inscri.getItems().addAll(regions);
//        region_inscri.setOnAction((event) -> {
//        String selectedRegion = region_inscri.getValue();
//        });

        region.setItems(FXCollections.observableArrayList(regions));
//        Date d=new Date(2023,14,14);
////
// Utilisateur v = new Utilisateur("nom", "prenom", "cin", "email@gmail.fr", "202014", "login","nn",d,"region"," adresse",2045, "role");
//        try {            su.ajouter(v);
//        } catch (SQLException ex) {
//            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void ajouter_utilisateur(ActionEvent event) throws SQLException, IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, AddressException, MessagingException, Exception {
        String nom = nom_inscri.getText();
        //***********************Controle de saisie nom
        if (!isValidNom(nom)) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom est invalide.");
            alert.showAndWait();
            return;
        }
        //***************Controle de saisie prenom

        String prenom = prenom_inscri.getText();
        if (!isValidPrenom(prenom)) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prenom est invalide.");
            alert.showAndWait();

        }

        LocalDate ds = date_naiss_inscri.getValue();
        //***************Controle de saisie CIN

        String cin = cin_inscri.getText();
        if (!isValidCIN(cin)) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le cin est invalide.");
            alert.showAndWait();
            return;
        }
        String email = email_inscri.getText();
        //*************************Controle de saisie nom

        if (!isValidEmailAddress(email)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("L'email est invalide.");
            alert.showAndWait();
            return;
        }
        if (su.emailExists(email)) {

            Alert alert = new Alert(AlertType.ERROR);
            
            alert.setTitle("L'email est invalide.");
            alert.setHeaderText(null);
            alert.setContentText("Ce mail est déja utilisé Veuillez saisir un nouveau mail");
            alert.showAndWait();
            return;

        }

        String telephone = telephone_inscri.getText();
        //**************************Controle de saisie numero de telephone 
        if (!isValidTelephone(telephone)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le numero de telephone est invalide.");
            alert.showAndWait();
            return;
        }
        String login = login_inscri.getText();
        if (su.loginExists(login)) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login invalide ");
            alert.setHeaderText(null);
            alert.setContentText("Ce login est déja utilisé . Veuillez saisir un nouveau login.");
            alert.showAndWait();
            return;
        }
//        String region = region_inscri.getValue();
        String adresse = adresse_inscri.getText();
        String mot_de_passe = mdp_inscri.getText();
        String mdp_chiffré = AESEncryptor.encrypt(mot_de_passe);
//        //MessageDigest md = MessageDigest.getInstance("SHA-256");
        System.out.println(mdp_chiffré);

        //***********************Mot de passe Controle de saisie 
//        if (!isValidMotdepasse(mot_de_passe)) {
//           
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Erreur de saisie");
//            alert.setHeaderText(null);
//            alert.setContentText("Le mot de passe ne doit pas contenir des caractéres speciaux et ne doit pas depasser 15 caractéres ");
//            alert.showAndWait();
//            return;
//        }
        //***********************Code postal Controle de saisie 
        int code_postal = Integer.parseInt(code_postal_inscri.getText());
        if (!isValidCodePostal(code_postal)) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le code postal est invalide.");
            alert.showAndWait();
            return;

        }

        Utilisateur u = new Utilisateur(nom, prenom, cin, email, telephone, login, mdp_chiffré, java.sql.Date.valueOf(ds), region.getValue(), adresse, code_postal, role);
//     Date d=new Date(2022,02,14);
//     Utilisateur u = new Utilisateur("nom", "prenom", "cin", "email@gmail.fr", "telephone", "login","nn",d,"region"," adresse",2045, "role");
        su.ajouter(u);

        //System.out.println(u.toString());
        System.out.println(Utilisateur.getCurrent_user().getCin());


////       FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/FrontControllerfxml"));
////            Parent root = loader.load();
////            FrontController fc = loader.getController();
////            login_inscri.getScene().setRoot(root);
        
        String subject = "Mail de bienvenue";
        String message = " Mr/Madame  " + nom + " Vous etes la bienvenue chez Handiny . Nous vous tiendrons au courant de toutes  actualité via le mail . Nous vous souhaitons une excellente journnée.";
        String from = "Chayma.bensaad@esprit.tn";
        String password = "223JFT2127";
        String to = email;
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

         //Envoyer l'e-mail
        try {
            Message emaill = new MimeMessage(session);
            emaill.setFrom(new InternetAddress(from));
            emaill.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emaill.setSubject(subject);
            emaill.setText(message);
            Transport.send(emaill);
            System.out.println("E-mail envoyé à " + to);
        } 
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

//    private boolean isValidEmail(String email) {
//        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//        return email.matches(regex);
//    }

    private boolean isValidNom(String nom) {
        String regex = "^[a-zA-Z ]+$";
        return nom.matches(regex);
    }

    private boolean isValidPrenom(String prenom) {
        String regex = "^[a-zA-Z ]+$";
        return prenom.matches(regex);
    }

    private boolean isValidCIN(String cin) {
        String regex = "^[0-9]{8}$";
        return cin.matches(regex);
    }

    private boolean isValidTelephone(String tel) {
        String regex = "^[0-9]{1,8}$";
        return tel.matches(regex);
    }

    private boolean isValidCodePostal(int code_postal) {
        if ((code_postal >= 1000 && code_postal <= 99999)) {
            return true;
        }

        return true;
    }

//    private boolean isValidMotdepasse(String mot_de_passe) {
//        String regex = "^[a-zA-Z][0-9]{1,20}$";
//        
//        return mot_de_passe.matches(regex);
//
//    }
    @FXML
    private void locataire(ActionEvent event) {
        if (loataire_inscri.isSelected()) {
            System.out.println("Vous connecté en tant que locataire");
            role = "Locataire";
        }
    }

    @FXML
    private void proprietaire(ActionEvent event) {
        if (proprietaire_inscri.isSelected()) {
            System.out.println("Vous etes connecte en tant que proprietaire");
            role = "Proprietaire";
        }
    }
    public boolean isValidEmailAddress(String email) {
    // Vérifie si l'adresse email est null ou vide
    if (email == null || email.isEmpty()) {
        return false;
    }
    
    // Vérifie si l'adresse email contient le symbole @ et le domaine
    int atIndex = email.indexOf("@");
    if (atIndex == -1 || atIndex == 0 || atIndex == email.length() - 1) {
        return false;
    }
    
    // Vérifie si l'adresse email est valide selon la norme RFC-5321
    try {
        InternetAddress internetAddress = new InternetAddress(email);
        internetAddress.validate();
    } catch (AddressException e) {
        return false;
    }
    
    return true;
}





    @FXML
    private void changeer_visibilité(ActionEvent event) {
        if (checkbox.isSelected()){
        password_text.setText(mdp_inscri.getText());
        password_text.setVisible(true);
        mdp_inscri.setVisible(false);
        return ;
        }
        mdp_inscri.setText(password_text.getText());
        mdp_inscri.setVisible(true);
        password_text.setVisible(false);
        
        return ;
        
    }

}


   
    
 