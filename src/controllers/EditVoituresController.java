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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TextField idV;
    @FXML
    private ImageView imageV;
    @FXML
    private TextField idDatee;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    String img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filechooser=new FileChooser();
        
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new FileChooser.ExtensionFilter("JPG", new String[]{"*.jpg"}), new FileChooser.ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new FileChooser.ExtensionFilter("BMP", new String[]{"*.bmp"}), new FileChooser.ExtensionFilter("PNG", new String[]{"*.png"}), new FileChooser.ExtensionFilter("GIF", new String[]{"*.gif"})});
    }   

    @FXML
    private void éditer(ActionEvent event) {
        try {
            int id=Integer.parseInt(idV.getText());
            System.out.println(id);
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
        if (immat.isEmpty() || marque.isEmpty() || modele.isEmpty()
                || boite.isEmpty() || kilometrage.trim().isEmpty()
                || carburant.isEmpty() || desc.isEmpty()
                || idPrixLocation.getText().isEmpty() || date == null || img.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Tous les champs sont obligatoires.");
            alert.showAndWait();
            return;
        }

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

            Voiture v= new Voiture(id,immat,marque,modele,boite,kilometrage,carburant,img,desc,prixLocation,java.sql.Date.valueOf(date));
            //System.out.println(v);
            ServiceVoiture sv=new ServiceVoiture();
            
           
            if (sv.update(v)) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Voiture modifiéé avec succés ! ");
                alert.showAndWait();
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
    private void vider(ActionEvent event) {
    }

    @FXML
    private void loadImage(ActionEvent event) {
         file = filechooser.showOpenDialog(stage);
        if (file != null) {
            String s = file.getName();
           img="C:/xampp/htdocs/img/"+s;
           //img=s;

            System.out.println(img);
            
        }
    }

    @FXML
    private void findID(ActionEvent event) {
        try {
            int id=Integer.parseInt(idV.getText());
            ServiceVoiture sv =new ServiceVoiture();
            Voiture v= sv.findById(id);
            //System.out.println(v);
            idImmatriculation.setText(v.getImmatriculation());
            idMarque.setText(v.getMarque());
            idModele.setText(v.getModele());
            idDatee.setText((v.getDate_validation_technique()).toString());
            idBoiteV.setText(v.getBoite_vitesse());
            idKilometrage.setText(v.getKilometrage());
            idCarburant.setText(v.getCarburant());
            idDesc.setText(v.getDescription());
            Double  prix=v.getPrix_location();
            idPrixLocation.setText(prix.toString());
            
            String path=v.getImage_voiture();
            System.out.println(path);
            Image img=new Image((path));
            
            imageV.setImage(img);
            
        } catch (SQLException ex) {
            Logger.getLogger(EditVoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
