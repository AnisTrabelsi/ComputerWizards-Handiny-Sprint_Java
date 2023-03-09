/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Utilisateur;
import Entite.Voiture;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class EditVoituresController implements Initializable {
 private ObservableList<Voiture> ObList;
    @FXML
    private AnchorPane anchlorPaneVoiture;
    @FXML
    private TextField idImmatriculation;
    @FXML
    private TextField idMarque;
    @FXML
    private TextField idModele;
    @FXML
    private DatePicker idDate;
    @FXML
    private TextField idBoiteV;
    @FXML
    private TextField idKilometrage;
    @FXML
    private TextField idCarburant;
    @FXML
    private TextField idDesc;
    @FXML
    private TextField idPrixLocation;
    @FXML
    private Button idImage;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    String img;
    int carId;
    private TextField urlImg;
    @FXML
    private ImageView imgg;
    @FXML
    private TextField URLImage;

    /**
     * Initializes the controller class.
     */
     public void setIDVoiture(int id_voiture) throws SQLException {
         carId = id_voiture;
         System.out.println("methode set "+carId);
           ServiceVoiture sv =new ServiceVoiture();
            Voiture v= sv.findById2(carId);
            System.out.println(v);
            idImmatriculation.setText(v.getImmatriculation());
            idMarque.setText(v.getMarque());
            idModele.setText(v.getModele());
           
            idBoiteV.setText(v.getBoite_vitesse());
            idKilometrage.setText(v.getKilometrage());
            idCarburant.setText(v.getCarburant());
            idDesc.setText(v.getDescription());
            Double  prix=v.getPrix_location();
            idPrixLocation.setText(prix.toString());
            URLImage.setText(v.getImage_voiture());
            
            
           // Image img1=new Image(getClass().getResourceAsStream(path));
            
          //  imageV.setImage(img1);
         java.sql.Date sqlDate2 = (java.sql.Date) v.getDate_validation_technique();
         java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
         LocalDate localDate2 = utilDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

         idDate.setValue(localDate2);
    }
    public int getIDVoiture() {
        return this.carId;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            
           
          
      
            
       
    }   

   /* private void éditer(ActionEvent event) {
       
    }*/

   

    @FXML
    private void loadImage(ActionEvent event) throws FileNotFoundException, IOException {
         /*file = filechooser.showOpenDialog(stage);
        if (file != null) {
            String s = file.getName();
            img="../images/"+s;  
           //img=s;

//            System.out.println(img);
            
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
            
            URLImage.setText(DBPath);
          Image img = new Image(file.toURI().toString());
            
            imgg.setImage(img);
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
    

  
     
   public void setObList(ObservableList ObList) {
       this.ObList = ObList;
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
    idBoiteV.clear();
    URLImage.clear();
    imgg.setImage(null);
    }

    @FXML
    private void edit(ActionEvent event) {
         try {
             
          
        String immat=idImmatriculation.getText();
        String marque=idMarque.getText();
        String modele=idModele.getText();
        LocalDate date=idDate.getValue();
        String kilometrage=idKilometrage.getText();
        String carburant=idCarburant.getText();
        String desc=idDesc.getText();
        String prixLocationStr=idPrixLocation.getText();
        String BoiteVitesse= idBoiteV.getText();
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
     if (idBoiteV.getText().isEmpty()){
    idBoiteV.setStyle("-fx-border-color:red ; -fx-border-width:2px");
    new animatefx.animation.Shake(idBoiteV).play();
    }
     else {
   idBoiteV.setStyle("");}
     
      if (i.isEmpty()) {
          idImage.setStyle("-fx-border-color:red ; -fx-border-width:2px");
          new animatefx.animation.Shake(idImage).play();   idImage.setStyle("");
    } else {
             idImage.setStyle("");
        
        
    }
   
  

    
        
    if (immat.isEmpty() || marque.isEmpty() || modele.isEmpty() || date == null || idBoiteV.getText().isEmpty()||
        kilometrage.isEmpty() || carburant.isEmpty() || desc.isEmpty() || prixLocationStr.isEmpty()||URLImage.getText().isEmpty()) {
        
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
    
    
     
        

        

         
           Voiture v= new Voiture(immat,marque,modele,BoiteVitesse,kilometrage,carburant,URLImage.getText(),desc,prixLocation,java.sql.Date.valueOf(date));
            System.out.println(v);
            ServiceVoiture sv=new ServiceVoiture();
            
          
            if (sv.update(v)) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Voiture modifiéé avec succés ! ");
                alert.showAndWait();
                 List<Voiture> voitures= sv.readAll();
                ObList.clear();
            
                ObList.addAll(voitures);
            }
            else {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Alerte");
                alert.setContentText("Impossible de modifier cette voiture");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

   
    
}
