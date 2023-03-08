/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Chauffeur;
import Entite.don;
import Services.Service_don;
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.lang.*;
import Entite.Pdf;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.transform.Scale;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Affichage_donController implements Initializable {

    private DatePicker date;
    @FXML
    private VBox vb;
    @FXML
    private ComboBox<String> types;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Fauteuils roulants", "Prothèses", "Appareils auditifs", "Lunettes et lentilles de contact", "Béquilles",
                "Équipement de soins à domicile", "Rampes d'accès", "Appareils de levage ", "Sièges de bain", "autre");
        types.setItems(list);
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Handiny\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
             

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                   
            Label label3 = new Label(d.getImage_don());
             

            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
            /////////////////////////
   hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //////////////////////////////
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
// vb.setStyle("fx-spacing: -1; fx-padding:-1; fx-alignment: center; ");
            vb.getChildren().add(hbox);
        }
       

    }

    @FXML
    private void afficher(ActionEvent event) {
       
        
        
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Handiny\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }

    @FXML
    private void trier(ActionEvent event) {
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.sortbydate_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Handiny\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }

 
    @FXML
    private void cherch(ActionEvent event) {
        String restype = (String) types.getValue();
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.findbytype(restype);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Handiny\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
    }

  

    @FXML
    private void p(ActionEvent event) throws DocumentException, IOException, FileNotFoundException, BadElementException, InterruptedException, SQLException {
                           List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     

        Pdf pd=new Pdf();
        pd.GeneratePdf("MesInformations de dons",l);

            System.out.println("impression done");
        
    }

    @FXML
    private void Exporter(ActionEvent event) throws IOException, SQLException {
        
         
         ////////////////////////////////////
         
           try {
        String filename = "C:\\Users\\anis\\Desktop\\DataDons.xls";
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("new sheet");
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue("Type");
        rowhead.createCell((short) 1).setCellValue("Description");
        rowhead.createCell((short) 2).setCellValue("Date d'ajout ");

        Service_don SC = new Service_don();
        List<don> dons = SC.readAll_don();

        for (int i = 0; i < dons.size(); i++) {
            HSSFRow row = sheet.createRow((short) (i + 1));
            row.createCell((short) 0).setCellValue(dons.get(i).getType());
            row.createCell((short) 1).setCellValue(dons.get(i).getDescription());
            row.createCell((short) 2).setCellValue(""+dons.get(i).getDate_ajout());
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
        alert.setContentText("!!!Excel exported!!!");
        alert.showAndWait();
        }

    } catch (SQLException ex) {
        Logger.getLogger(Afficher_chauffeurController.class.getName()).log(Level.SEVERE, null, ex);
    }
         
    }
    
   public static void printNode(final VBox vbox) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX = pageLayout.getPrintableWidth() / vbox.getBoundsInParent().getWidth();
    double scaleY = pageLayout.getPrintableHeight() / vbox.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    vbox.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(vbox.getScene().getWindow())) {
        boolean success = job.printPage(pageLayout, vbox);
        if (success) {
            job.endJob();
        }
    }
    vbox.getTransforms().remove(scale);
}


    @FXML
    private void node(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(vb);
    }

}
