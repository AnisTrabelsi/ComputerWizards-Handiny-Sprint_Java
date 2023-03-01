/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Pdfdd;
import Entite.demande_don;
import Services.Service_demande_don;
import Services.Service_utilisateur;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Affichage_demande_don_adminController implements Initializable {

    @FXML
    private TextField idu;
    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          vb.getChildren().clear();
        List<demande_don> l = new ArrayList<>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
              String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();       
              Service_utilisateur u=new Service_utilisateur();
              Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
      hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label0.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
      
              
                
            hbox.getChildren().addAll(label0,label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

       
    }    

   
    @FXML
    private void afficher(ActionEvent event) {
          ///////////////////////////////////////////////user
  vb.getChildren().clear();
        List<demande_don> l = new ArrayList<>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
              String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();       
              Service_utilisateur u=new Service_utilisateur();
              Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
      hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label0.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
      
              
                
            hbox.getChildren().addAll(label0,label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

       
        
    }

    @FXML
    private void sort(ActionEvent event) {
    ///////////////////////////////////////////////user
vb.getChildren().clear();
     List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.sort_date_demande_don_of_users();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
            Service_utilisateur u=new Service_utilisateur();
              Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
                  hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label0.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
      
              
      
                
            hbox.getChildren().addAll(label0,label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
    }

    @FXML
    private void cherch(ActionEvent event) {
        vb.getChildren().clear();
     List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        Service_utilisateur se=new Service_utilisateur();
        String id=idu.getText();
        try {
            l = ser.find_demande_don_of_user(se.rechercherid_parrrcin(id));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();      
             Service_utilisateur u=new Service_utilisateur();
              Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label5 =new Label(d.getJustificatif_handicap());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label5);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
                    hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
         
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                          label0.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
      
              
      
                
            hbox.getChildren().addAll(label0,label1, vbox2,label2,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
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
    private void imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
           printNode(vb);
    }

    @FXML
    private void ex(ActionEvent event) throws IOException, SQLException {
         Writer writer = null;
                  Service_demande_don ser = new Service_demande_don();
                   Service_utilisateur se= new Service_utilisateur();
               List<demande_don> l = new ArrayList<demande_don>();
               l = ser.readAll_demande_don();
               
         try {
            //badel path fichier excel
            File file = new File("C:\\Users\\anis\\Desktop\\Demandes_Dons.csv");
            writer = new BufferedWriter(new FileWriter(file));
            
            for (demande_don ev : l) {

                String text = se.rechercherparcid_prenom(ev.getId_utilisateur())+" | "+ se.rechercherparid_nom(ev.getId_utilisateur()) + " | "+ev.getType_produit_demande()+" | " +ev.getRemarques()+ "|" +ev.getDate_demande()  +" | "+ ev.getEtat()+ "\n";
                System.out.println(text);
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
             writer.close();
             Alert alert= new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("!!!Excel exported!!!");
        alert.showAndWait();
        }
    }
    

    @FXML
    private void p(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
            List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     

        Pdfdd pd=new Pdfdd();
        pd.GeneratePdf("MesInformations de demandes de dons",l);

            System.out.println("impression done");
    }

  

    }
    

