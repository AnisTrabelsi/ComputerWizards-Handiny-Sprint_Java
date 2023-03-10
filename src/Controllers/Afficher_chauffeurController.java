/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import Entite.Chauffeur;
import Entite.Pdf;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import Entite.Pdf;
import Entite.PdfM;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class Afficher_chauffeurController implements Initializable {

    @FXML
    private TableView<Chauffeur> Reclamation;
    @FXML
    private TableColumn<Chauffeur, String> cin;
    @FXML
    private TableColumn<Chauffeur, String> nom;
    @FXML
    private TableColumn<Chauffeur, String> adresse;
    @FXML
    private TableColumn<Chauffeur, String> statut;
    @FXML
    private Button excel;
    
    ServiceChauffeur SC = new ServiceChauffeur();
    
    
    

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           List<Chauffeur> l = new ArrayList<Chauffeur>();
        ServiceChauffeur ser = new ServiceChauffeur();
       
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
           
            cin.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("CIN"));
            nom.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Nom"));
            adresse.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("Statut_disponibilite"));
          
     
       
          Reclamation.setItems(olc);
    }  
    
   
    
   
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Modifier_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void aj(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajout_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void afficher(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Afficher_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
     
              
            }

    
@FXML
private void excelbutton(ActionEvent event) {
    try {
        String filename = "C:\\Users\\anis\\Desktop\\dataChauffeur.xls";
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("new sheet");
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue("CIN");
        rowhead.createCell((short) 1).setCellValue("Adresse");
        rowhead.createCell((short) 2).setCellValue("Statut Disponibilite ");

        ServiceChauffeur SC = new ServiceChauffeur();
        List<Chauffeur> chauffeurs = SC.readAll();

        for (int i = 0; i < chauffeurs.size(); i++) {
            HSSFRow row = sheet.createRow((short) (i + 1));
            row.createCell((short) 0).setCellValue(chauffeurs.get(i).getCIN());
            row.createCell((short) 1).setCellValue(chauffeurs.get(i).getAdresse());
            row.createCell((short) 2).setCellValue(chauffeurs.get(i).getStatut_disponibilite());
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

    } catch (SQLException ex) {
        Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  
     private void statitistiquebutton(ActionEvent event) throws IOException {
       Parent root;
        root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
     
              
            }
/*
    @FXML
    private void p(ActionEvent event) throws DocumentException, IOException, FileNotFoundException, BadElementException, InterruptedException, SQLException {
                           List<Chauffeur> l = new ArrayList<>();
        ServiceChauffeur ser = new ServiceChauffeur();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user

     

        Pdf pd=new Pdf();
        pd.GeneratePdf("MesInformations de Chauffeur",l);

            System.out.println("impression Chauffeur");
        
    }*/

 
   @FXML
private void p(ActionEvent event) throws DocumentException, IOException, FileNotFoundException, BadElementException, InterruptedException, SQLException {
List<Chauffeur> l = new ArrayList<>();
ServiceChauffeur ser = new ServiceChauffeur();

l = ser.readAll();
PdfM pd = new PdfM();
pd.GeneratePdf("MesInformations de Chauffeur", l);
System.out.println("impression Chauffeur");
 {
            boolean ex = false;
System.out.println(ex);
}
}
    
        
       
@FXML
    private void trier(ActionEvent event) throws SQLException {
    Reclamation.getItems().clear();
    List<Chauffeur> l = new ArrayList<Chauffeur>();
    ServiceChauffeur ser = new ServiceChauffeur();
    l = ser.trierChauffeurParNom();
    ObservableList<Chauffeur> olc = FXCollections.observableArrayList(l);
    Reclamation.setItems(olc);
    }
    
    
    }
    

