/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
import Entite.Utilisateur;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Entite.Voiture;
import controllers.EditVoituresController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Affichage_VoituresController implements Initializable {

    @FXML
    private ListView<Voiture> listv;
    @FXML
    private TextField searchBar;
     ObservableList ObList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            // TODO
            ServiceVoiture sv= new ServiceVoiture();
            Utilisateur u=Utilisateur.getCurrent_user();
            List<Voiture> voitures= sv.readAll_Of_user(u.getId_utilisateur());
            ObList = FXCollections.observableList(voitures);
            listv.setItems(ObList);
            searchBar.setOnKeyReleased(event -> {
                
                
                try {
                    String searchText = searchBar.getText().toLowerCase();
                    List<Voiture> filteredVoitures = sv.filtre(searchText);
                    System.out.println(filteredVoitures);
                    ObList.clear();
                    
                    ObList = FXCollections.observableList(filteredVoitures);
                    
                    
                    listv.setItems(ObList);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Affichage_VoituresController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            });
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_VoituresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  @FXML
    private void ajouterVoitures(ActionEvent event) throws SQLException {
     try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui_handiny/InsertVoitures.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       InsertVoituresController controller = fxmloader.<InsertVoituresController>getController();
       controller.setObList(ObList);
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
     
    }


    @FXML
    private void supprimer(ActionEvent event) {
    Voiture voiture = listv.getSelectionModel().getSelectedItem();
    if (voiture != null) {
        int id = voiture.getId_voiture();
        ServiceVoiture sv = new ServiceVoiture();
        try {
            sv.delete(id);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("");
            alert.setContentText("Voiture effacée avec succés ! ");
            alert.showAndWait();
            listv.getItems().remove(listv.getSelectionModel().getSelectedIndex());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une voiture à supprimer.");
        alert.showAndWait();
    }
}     
      @FXML
    private void updateVoiture(ActionEvent event) throws IOException {
       Voiture voiture = listv.getSelectionModel().getSelectedItem();
          int id_voiture;
        if (voiture != null) {
           try {
               id_voiture = voiture.getId_voiture();
               ServiceVoiture sv = new ServiceVoiture();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/EditVoitures.fxml"));
               Parent root = loader.load();
               EditVoituresController controller = loader.getController();
               System.out.println(controller);
               controller.setIDVoiture(id_voiture);
               System.out.println(voiture.getId_voiture());
               Scene scene = new Scene(root);
               Stage stage = new Stage();
               
                controller.setObList(ObList);
               stage.setTitle("Edit page");
               stage.setScene(scene);
               stage.show();
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_VoituresController.class.getName()).log(Level.SEVERE, null, ex);
           }}
     else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une voiture à modifier.");
        alert.showAndWait();
    }
    }
     @FXML
    private void exporter(ActionEvent event) throws FileNotFoundException, IOException {
      /* File outputFile = new File("D:/JDBC/output.txt");
       PrintWriter writer = new PrintWriter(outputFile);
       for (Voiture v : listv.getItems()) {
       writer.println(v);}
       writer.close();*/
    Workbook workbook = new XSSFWorkbook();
Sheet sheet = (Sheet) workbook.createSheet("Voitures");

Row headerRow = sheet.createRow(0);
headerRow.createCell(0).setCellValue("Immatriculation");
headerRow.createCell(1).setCellValue("Marque");
headerRow.createCell(2).setCellValue("Modèle");
headerRow.createCell(3).setCellValue("BoiteVitesse");
headerRow.createCell(4).setCellValue("Description");
headerRow.createCell(5).setCellValue("Prix de location");

       

        int rowIndex = 1;
        for (Voiture v : listv.getItems()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(v.getImmatriculation());
            row.createCell(1).setCellValue(v.getMarque());
            row.createCell(2).setCellValue(v.getModele());
            row.createCell(3).setCellValue(v.getBoite_vitesse());
            row.createCell(4).setCellValue(v.getDescription());
            row.createCell(5).setCellValue(v.getPrix_location());
           
        }

        FileOutputStream outputStream = new FileOutputStream("D:/JDBC/voitures.xlsx");
        workbook.write(outputStream);
        
        workbook.close();
         Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("La liste de voitures est téléchargée avec succès ");
        alert.showAndWait();






    }

   

    @FXML
    private void filtreParMarque(ActionEvent event) throws SQLException {
        ServiceVoiture sv = new ServiceVoiture();
        List<Voiture> voitures = sv.trierParMarque();// appel de la fonction de tri
        System.out.println(voitures);
        ObList.clear(); // suppression des anciens éléments de la grille
        ObList = FXCollections.observableList(voitures);
        listv.setItems(ObList);
      
    }

    @FXML
    private void filtreParModele(ActionEvent event) throws SQLException {
        ServiceVoiture sv = new ServiceVoiture();
        List<Voiture> voitures = sv.trierParModele();// appel de la fonction de tri
        ObList.clear(); // suppression des anciens éléments de la grille
        ObList = FXCollections.observableList(voitures);
        listv.setItems(ObList);
    }

    @FXML
    private void filtreParPrix(ActionEvent event) throws SQLException {
        ServiceVoiture sv = new ServiceVoiture();
        List<Voiture> voitures = sv.trierParPrix();// appel de la fonction de tri
        ObList.clear(); // suppression des anciens éléments de la grille
        ObList = FXCollections.observableList(voitures);
        listv.setItems(ObList);
    }

    
}    
      
