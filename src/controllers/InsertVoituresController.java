/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Voiture;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
    private TextField imgtest;
    @FXML
    private Button idImage;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    @FXML private ComboBox<String> idBoiteV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idBoiteV.setItems(FXCollections.observableArrayList("Automatique","manuelle"));
        filechooser=new FileChooser();
        
        filechooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new ExtensionFilter("JPG", new String[]{"*.jpg"}), new ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new ExtensionFilter("BMP", new String[]{"*.bmp"}), new ExtensionFilter("PNG", new String[]{"*.png"}), new ExtensionFilter("GIF", new String[]{"*.gif"})});
    }

       

    @FXML
    private void insert(ActionEvent event) {
       
        String immat=idImmatriculation.getText();
        
        String marque=idMarque.getText();
        String modele=idModele.getText();
        LocalDate date=idDate.getValue();
        String kilometrage=idKilometrage.getText();
        String carburant=idCarburant.getText();
        String desc=idDesc.getText();
        String img=imgtest.getText();
        Double prixLocation=Double.parseDouble(idPrixLocation.getText());
        
             
            Voiture v= new Voiture(immat,marque,modele,idBoiteV.getValue(),kilometrage,carburant,img,desc,prixLocation,java.sql.Date.valueOf(date),1);
            ServiceVoiture sv=new ServiceVoiture();
            try { 
            if (sv.ajouter(v)){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Voiture ajoutée avec succés ! ");
                alert.showAndWait();
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
    public void loadImage(ActionEvent event) {
       
        file=filechooser.showOpenDialog(anchlorPaneVoiture.getScene().getWindow());
        try {
            deskTop.open(file);
            
        } catch (IOException ex) {
            Logger.getLogger(InsertVoituresController.class.getName()).log(Level.SEVERE, null, ex);
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
    imgtest.clear();
    idBoiteV.setValue(null);
    }
    
}
