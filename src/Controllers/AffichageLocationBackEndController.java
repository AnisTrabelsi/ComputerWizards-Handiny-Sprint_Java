/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Entite.Reservation_voiture;
import Entite.Utilisateur;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.ServiceReservation_Voiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class AffichageLocationBackEndController implements Initializable {
       @FXML
    private ListView<Reservation_voiture> listv;
    @FXML
    private TextField searchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
             Utilisateur u=Utilisateur.getCurrent_user();
            List<Reservation_voiture> locations= sv.readAll();
            System.out.println(locations);
            ObservableList ObList = FXCollections.observableList(locations);
            listv.setItems(ObList);
        } catch (SQLException ex) {
            Logger.getLogger(controllers.AffichageLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
   

    

    @FXML
    private void supprimer(ActionEvent event) {
         Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
    if (r != null) {
        //int id = r.getId_reservation_voiture();
        ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
        try {
            sv.supprime(r);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("");
            alert.setContentText("Réservation effacée avec succés ! ");
            alert.showAndWait();
            listv.getItems().remove(listv.getSelectionModel().getSelectedIndex());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une réservation à supprimer.");
        alert.showAndWait();
    }
    }

    @FXML
    private void exporter(ActionEvent event) { 
        try {
            String filename = "C:\\xampp4\\htdocs\\Handiny\\reservations.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Date début demande");
            rowhead.createCell((short) 1).setCellValue("Date Fin demande");
            rowhead.createCell((short) 2).setCellValue("Description");
            rowhead.createCell((short) 3).setCellValue("état");
            rowhead.createCell((short) 4).setCellValue("date demande");
           
            
            
            
            
            
            ServiceReservation_Voiture SC = new ServiceReservation_Voiture ();
            List<Reservation_voiture> voit = SC.readAll();
            
            for (int i = 0; i < voit.size(); i++) {
                HSSFRow row = sheet.createRow((short) (i + 1));
                row.createCell((short) 0).setCellValue(""+voit.get(i).getDate_debut_reservation());
                row.createCell((short) 2).setCellValue(""+voit.get(i).getDate_fin_reservation());
                row.createCell((short) 3).setCellValue(voit.get(i).getDescription_reservation());
                row.createCell((short) 4).setCellValue(voit.get(i).getEtat_demande_reservation());
                row.createCell((short) 0).setCellValue(""+voit.get(i).getDate_demande_reservation());
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
                alert.setContentText("La liste de réservations a été bien téléchargé");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(controllers.AffichageLocationController.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    @FXML
    private void updateReservation(ActionEvent event) throws IOException, SQLException {
        Reservation_voiture r = listv.getSelectionModel().getSelectedItem();
           
        if (r != null) {
          
               int id_reservation = r.getId_reservation_voiture(); 
               System.out.println("fff"+id_reservation);
                ServiceReservation_Voiture sv= new ServiceReservation_Voiture();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/UpdateLocation.fxml"));
               Parent root = loader.load();
               controllers.UpdateLocationController controller = loader.getController();
               System.out.println(controller);
               controller.setIDVoiture(id_reservation);
               Scene scene = new Scene(root);
               Stage stage = new Stage();
               stage.setTitle("Edit page");
               stage.setScene(scene);
               stage.show();
        }     
     else {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText("");
        alert.setContentText("Veuillez sélectionner une voiture à modifier.");
        alert.showAndWait();
    }
    }

    private void ajouterReservation(ActionEvent event) {
         try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui_handiny/InsertLocation.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }

}
