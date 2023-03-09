/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;
import Entite.Utilisateur;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Entite.Voiture;
import controllers.EditVoituresController;
import controllers.InsertVoituresController;
import java.awt.Desktop;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Affichage_VoituresBackEndController implements Initializable {

    @FXML
    private ListView<Voiture> listv;
    @FXML
    private TextField searchBar;
     private ObservableList ObList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            // TODO
            ServiceVoiture sv= new ServiceVoiture();
            //Utilisateur u=Utilisateur.getCurrent_user();
            List<Voiture> voitures= sv.readAll();
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
        try {
            /* File outputFile = new File("D:/JDBC/output.txt");
            PrintWriter writer = new PrintWriter(outputFile);
            for (Voiture v : listv.getItems()) {
            writer.println(v);}
            writer.close();*/
            String filename =  "C:\\xampp4\\htdocs\\Handiny\\voitures.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Immatriculation");
            rowhead.createCell((short) 1).setCellValue("Marque");
            rowhead.createCell((short) 2).setCellValue("Modèle");
            rowhead.createCell((short) 3).setCellValue("BoiteVitesse");
            rowhead.createCell((short) 4).setCellValue("Description");
            rowhead.createCell((short) 5).setCellValue("Prix de location");
            rowhead.createCell((short) 6).setCellValue("Date de validation technique");
            
            
            
            
            
            ServiceVoiture SC = new ServiceVoiture();
            List<Voiture> voit = SC.readAll();
            
            for (int i = 0; i < voit.size(); i++) {
                HSSFRow row = sheet.createRow((short) (i + 1));
                row.createCell((short) 0).setCellValue(voit.get(i).getImmatriculation());
                row.createCell((short) 1).setCellValue(voit.get(i).getMarque());
                row.createCell((short) 2).setCellValue(voit.get(i).getModele());
                row.createCell((short) 3).setCellValue(voit.get(i).getBoite_vitesse());
                row.createCell((short) 4).setCellValue(voit.get(i).getDescription());
                row.createCell((short) 5).setCellValue(voit.get(i).getPrix_location());
                row.createCell((short) 6).setCellValue(""+voit.get(i).getDate_validation_technique());
            }
            
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                hwb.write(fileOut);
                System.out.println("Your excel file has been generated!");
                File file = new File(filename);
                if (file.exists() && Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
            finally {
                
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("excel");
                alert.setHeaderText(null);
                alert.setContentText("La liste de voiture a été bien téléchargé");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_VoituresBackEndController.class.getName()).log(Level.SEVERE, null,ex);
        }
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
      
