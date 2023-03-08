/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import Entite.Utilisateur;
import Entite.Voiture;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import Services.ServiceUtilisateur;
import java.awt.Desktop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import javafx.scene.image.Image;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class InsertVoituresController implements Initializable {
    @FXML
    private AnchorPane anchlorPaneVoiture;
    private final Desktop deskTop=Desktop.getDesktop();
    Image image;
    @FXML
    private TextField idImmatriculation;
    @FXML
    private TextField idMarque;
    @FXML
    private TextField idModele;
    @FXML
    private DatePicker idDate;
    @FXML
    private TextField idKilometrage;
    @FXML
    private TextField idCarburant;
    @FXML
    private TextArea idDesc;
    @FXML
    private TextField idPrixLocation;
    @FXML
    private Button idImage;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    @FXML private ComboBox<String> idBoiteV;
    String img;
    private ObservableList<Voiture> ObList;
    @FXML
    private ImageView imgg;
    @FXML
    private TextField URLImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idBoiteV.setItems(FXCollections.observableArrayList("Automatique","manuelle"));
        //filechooser=new FileChooser();
        
        //filechooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new ExtensionFilter("JPG", new String[]{"*.jpg"}), new ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new ExtensionFilter("BMP", new String[]{"*.bmp"}), new ExtensionFilter("PNG", new String[]{"*.png"}), new ExtensionFilter("GIF", new String[]{"*.gif"})});
    }

    private boolean checkFields() {
    // Vérifier que chaque champ ne contient pas de caractères spéciaux
    Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
    Matcher matcher;
    if (pattern.matcher(idImmatriculation.getText()).find()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("L'immatriculation ne doit pas contenir de caractères spéciaux");
        return false;
    }
    if (pattern.matcher(idMarque.getText()).find()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("La marque ne doit pas contenir de caractères spéciaux");
        return false;
    }
    if (pattern.matcher(idModele.getText()).find()) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Le modèle ne doit pas contenir de caractères spéciaux");
        return false;
    }
    if (pattern.matcher(idKilometrage.getText()).find()) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Le kilométrage ne doit pas contenir de caractères spéciaux");
        return false;
    }
    if (pattern.matcher(idCarburant.getText()).find()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Le carburant ne doit pas contenir de caractères spéciaux");
        return false;
    }
    if (pattern.matcher(idDesc.getText()).find()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("La description ne doit pas contenir de caractères spéciaux");
        return false;
    }
    
    return true;
}   
    @FXML
    private void insert(ActionEvent event) {
       //gridpane
        String immat=idImmatriculation.getText();
        
        String marque=idMarque.getText();
        String modele=idModele.getText();
        LocalDate date=idDate.getValue();
        String kilometrage=idKilometrage.getText();
        String carburant=idCarburant.getText();
        String desc=idDesc.getText();
        String prixLocationStr=idPrixLocation.getText();
        String BoiteVitesse= idBoiteV.getValue();
        Double prixLocation;
        String i = URLImage.getText();
        
    /*conditions de saisies pour l'immatriculation */    
    if (immat.isEmpty()){
    idImmatriculation.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idImmatriculation).play();
    //Génération d'une alerte
    }
     else {
    idImmatriculation.setStyle("");}
    
    
    /* conditions de saisies pour la marque */ 
     if (marque.isEmpty()){
    idMarque.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idMarque).play();
    }
     else {
    idMarque.setStyle("");}
     
  /* conditions de saisies pour le modèle */ 
     if (modele.isEmpty()){
    idModele.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idModele).play();
    }
     else {
    idModele.setStyle("");}
     
     /* conditions de saisies pour la date de validation technique */ 
     if (date == null){
    idDate.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idDate).play();
    }
     else {
    idDate.setStyle("");}
     
      /* conditions de saisies pour le kilométrage */ 
     if (kilometrage.isEmpty()){
    idKilometrage.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idKilometrage).play();
    }
     else {
   idKilometrage.setStyle("");}
     
       /* conditions de saisies pour le carburant */ 
     if (carburant.isEmpty()){
    idCarburant.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idCarburant).play();
    }
     else {
   idCarburant.setStyle("");}
     
         /* conditions de saisies pour la description */ 
     if (desc.isEmpty()){
    idDesc.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idDesc).play();
    }
     else {
   idDesc.setStyle("");}
     
     
         /* conditions de saisies pour le prix de location */ 
     if (prixLocationStr.isEmpty()){
    idPrixLocation.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idPrixLocation).play();
    }
     else {
   idPrixLocation.setStyle("");}
     
         /* conditions de saisies pour le boite de vitesse*/ 
     if (idBoiteV.getValue()==null){
    idBoiteV.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idBoiteV).play();
    }
     else {
   idBoiteV.setStyle("");}
     
    /*  if (file != null) {
        idImage.setStyle("");
    } else {
        
        idImage.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(idImage).play();
    }*/
   
  

    
        
    /*if (immat.isEmpty() || marque.isEmpty() || modele.isEmpty() || date == null || 
        kilometrage.isEmpty() || carburant.isEmpty() || desc.isEmpty() || idPrixLocation.isEmpty()||img.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir tous les champs");
    DialogPane dialogPane = alert.getDialogPane();
    // Ajouter le style CSS inline
    dialogPane.setStyle("-fx-background-color: #F8D7DA; -fx-border-color: #F5C6CB; -fx-border-width: 2px;");
    alert.showAndWait();
        return;
    }
   /* if (!immat.matches("^[a-zA-Z0-9]+$") || !marque.matches("^[a-zA-Z]+$")
                || !modele.matches("^[a-zA-Z0-9]+$")
                || !kilometrage.matches("^[0-9]+$") || !carburant.matches("^[a-zA-Z]+$")
                || !desc.matches("^[a-zA-Z0-9\\s]+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Certains champs contiennent des caractères spéciaux.");
            alert.showAndWait();
            return;
        }*/
     

    // Vérification de la date : elle doit être inférieure ou égale à la date d'aujourd'hui !
    
    LocalDate today = LocalDate.now();
    if (date.isAfter(today)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Date invalide");
        alert.setContentText("La date saisie doit être inférieure ou égale à la date d'aujourd'hui !");
        alert.showAndWait();
        idDate.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(idDate).play();
        return;
    }
    
   
    
    // Vérifier si le champ Prix Location est un nombre valide
    
     try {
        Double kilometrage_double = Double.parseDouble(kilometrage);
    } catch (NumberFormatException e) {
        idKilometrage.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(idKilometrage).play();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Prix Location invalide");
        alert.setContentText("Veuillez entrer un nombre valide pour le kilométrage");
        alert.showAndWait();
        
        return;
    }
    try {
        prixLocation = Double.parseDouble(prixLocationStr);
    } catch (NumberFormatException e) {
        idPrixLocation.setStyle("-fx-border-color:red ; -fx-border-width:2px");
        new animatefx.animation.Shake(idPrixLocation).play();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Prix Location invalide");
        alert.setContentText("Veuillez entrer un nombre valide pour le Prix Location");
        alert.showAndWait();
        
        return;
    }
     // Vérifier si le champ Kilométrage est un nombre valide
    
   
    
    
          Utilisateur u=Utilisateur.getCurrent_user();
            ServiceUtilisateur su= new ServiceUtilisateur();
        try {
            su.ajouter(u);
        } catch (SQLException ex) {
            Logger.getLogger(InsertVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
           Voiture v= new Voiture(immat,marque,modele,BoiteVitesse,kilometrage,carburant,i,desc,prixLocation,java.sql.Date.valueOf(date),u);
        // Vérifier si tous les champs sont remplis
            ServiceVoiture sv=new ServiceVoiture();
            try { 
            if (sv.ajouter(v)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Voiture ajoutée avec succés ! ");
                alert.showAndWait();
                 ObList.add(v);
            
                stage.close();
            }
            else {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Alerte");
                alert.setContentText("Impossible d'ajouter cette voiture");
                alert.showAndWait();
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
        
        
        
    
   
    }@FXML
    public void loadImage(ActionEvent event) throws FileNotFoundException, IOException {
       /*file = filechooser.showOpenDialog(stage);
        if (file != null) {
            String s = file.getName();
          // img="C:/xampp/htdocs/img/"+s;
            img="../images/"+s; 
          
           //img=s;

            System.out.println(img);
            
        }*/
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
            Image img = new Image(file.toURI().toString());
            imgg.setImage(img);
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
    

    @FXML
    private void vider(ActionEvent event) {
    idImmatriculation.clear();
    idMarque.clear();
    idModele.clear();
    idDate.setValue(null);
    idKilometrage.clear();
    idCarburant.clear();
    idDesc.clear();
    idPrixLocation.clear();
    
    idBoiteV.setValue(null);
    }

    void initData(Voiture car) {
    idImmatriculation.setText(car.getImmatriculation());
    idMarque.setText(car.getMarque());
    idModele.setText(car.getModele());
    idDate.setValue(new java.sql.Date(car.getDate_validation_technique().getTime()).toLocalDate());

    idKilometrage.setText(car.getKilometrage());
    idCarburant.setText(car.getCarburant());
    idDesc.setText(car.getDescription());
    idPrixLocation.setText(Double.toString(car.getPrix_location()));
    idBoiteV.setValue(car.getBoite_vitesse());
    
}


    void setObList(ObservableList ObList) {
       this.ObList = ObList;
    }


    
}
