/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Voiture;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
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
    private TextArea idDesc;
    @FXML
    private TextField idPrixLocation;
    @FXML
    private Button idImage;
    @FXML
    private ImageView imageV;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    String img;
    int carId;
    @FXML
    private TextField urlImg;

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
            
            String path=v.getImage_voiture();
            urlImg.setText(path);
            System.out.println(path);
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
      
            // TODO
            filechooser=new FileChooser();
            
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new FileChooser.ExtensionFilter("JPG", new String[]{"*.jpg"}), new FileChooser.ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new FileChooser.ExtensionFilter("BMP", new String[]{"*.bmp"}), new FileChooser.ExtensionFilter("PNG", new String[]{"*.png"}), new FileChooser.ExtensionFilter("GIF", new String[]{"*.gif"})});
            
           
          
      
            
       
    }   

    @FXML
    private void éditer(ActionEvent event) {
        try {
             
          
            //System.out.println(v);
            String immat= idImmatriculation.getText();
            System.out.println(immat);
            String marque= idMarque.getText();
            System.out.println(marque);
            String modele= idModele.getText();
            System.out.println(modele);
            LocalDate date=idDate.getValue();
            System.out.println(date);
            String boite= idBoiteV.getText();
            System.out.println(boite);
            String kilometrage=idKilometrage.getText();
            System.out.println(kilometrage);
            String carburant=idCarburant.getText();
            String desc= idDesc.getText();
            System.out.println(desc);
            Double prixLocation=Double.parseDouble(idPrixLocation.getText());
            // Vérification des saisies
        
        

        if (!immat.matches("^[a-zA-Z0-9]+$") || !marque.matches("^[a-zA-Z]+$")
                || !modele.matches("^[a-zA-Z0-9]+$") || !boite.matches("^[a-zA-Z]+$")
                || !kilometrage.matches("^[0-9]+$") || !carburant.matches("^[a-zA-Z]+$")
                || !desc.matches("^[a-zA-Z0-9\\s]+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Certains champs contiennent des caractères spéciaux.");
            alert.showAndWait();
            return;
        }

        if (prixLocation <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le prix de location doit être supérieur à zéro.");
            alert.showAndWait();
            return;
        }

        if (date.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("La date de validation technique doit être antérieure ou égale à aujourd'hui.");
            alert.showAndWait();
            return;
        }

            Voiture v= new Voiture(getIDVoiture(),immat,marque,modele,boite,kilometrage,carburant,img,desc,prixLocation,java.sql.Date.valueOf(date));
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

   

    @FXML
    private void loadImage(ActionEvent event) {
         file = filechooser.showOpenDialog(stage);
        if (file != null) {
            String s = file.getName();
            img="../images/"+s;  
           //img=s;

//            System.out.println(img);
            
        }
    }

  
     
    void setObList(ObservableList ObList) {
       this.ObList = ObList;
    }

   
    
}
