/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.demande_don;
import Entite.don;
import Services.Service_demande_don;
import Services.Service_don;
import Services.ServiceUtilisateur;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Modifier_demande_don_adminController implements Initializable {

    @FXML
    private Button btnupdate;
    @FXML
    private TextField mod_URLImage;
    @FXML
    private TextField mod_desc;
    private ListView<demande_don> table;
    @FXML
    private ImageView Image;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private TextField id_user;
    @FXML
    private VBox vb;

    private static int id,idd,uu;
     private static String pr;
       private static Date da;
    /**
     * Initializes the controller class.
     */
        ServiceUtilisateur u=new ServiceUtilisateur();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
            ObservableList<String> list = FXCollections.observableArrayList("En cours", "validé", "refusé");
        etat.setItems(list);
        
      vb.getChildren().clear();
        List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
              String path = "C:\\xampp4\\htdocs\\Handiny\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           ServiceUtilisateur u=new ServiceUtilisateur();
              Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
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
  Button button = new Button("Click me!");
                 button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

            idd=d.getId_demande_don();
            uu=d.getId_utilisateur();
            id=d.getId_don();
             da=d.getDate_demande();
               pr=d.getType_produit_demande();
        
                // alert.setContentText("Description : "+evenement.getDescription());
                //don d = new don();
            mod_desc.setText(d.getRemarques());
        mod_URLImage.setText(d.getJustificatif_handicap());
          etat.setValue(d.getEtat());
        id_user.setText(Integer.toString(d.getId_utilisateur()));
 JOptionPane.showMessageDialog(null, "demande selectionée");
            });
                
                   hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
        // button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px;-fx-padding: 10;  -fx-spacing: 10 0; ");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label0.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
          button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");
                 
            hbox.getChildren().addAll(button,label0,label1,label2,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
    }    

    
    
    
       private boolean descvalide() {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(mod_desc.getText());
        if (m.find() && m.group().equals(mod_desc.getText())) {
            return true;
        } else {

            return false;
        }
    }
    
    
    @FXML
    private void modifierdemandedon(ActionEvent event) {
        Service_demande_don ser = new Service_demande_don();
 Service_don ser1 = new Service_don();
        String r = (String) mod_desc.getText();
       
        String i = mod_URLImage.getText();
        //String u=id_user.getText();
         String e=(String) etat.getValue();
       
 
  if (r.isEmpty() || i.isEmpty() || e.isEmpty() )  {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } 
  else if ((!descvalide()))
        {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type validé !");
                alert.showAndWait();
        }   
  else 
 
  {
        demande_don p = new demande_don(idd,uu,id,da ,i,pr ,r,e);

        try { 
              if (e == "validé")
                {ser.update_demande_don_admin(p);
                /*ser1.supprimer_don(table.getSelectionModel().getSelectedItem().getId_don());
                JOptionPane.showMessageDialog(null, "don "+ table.getSelectionModel().getSelectedItem().getId_don()+ " est supprimé ");*/
                }
                else 
                {ser.update_demande_don_admin(p);
                }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
Actualiser();
JOptionPane.showMessageDialog(null, "Demande modifie");
        mod_desc.clear();
        mod_URLImage.clear();
        Image.setImage(null);
        id_user.setText(null);
         etat.setValue("etat");
        
  }
        
    }

    @FXML
    private void modImage(ActionEvent event) throws FileNotFoundException, IOException{
    
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
            String res;
            int len;
            len = path.length();

            res = path.substring(47, len);
            System.out.println(res);
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
            mod_URLImage.setText(res);
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
    
     private void Actualiser() {
          vb.getChildren().clear();
        List<demande_don> l = new ArrayList<demande_don>();
        Service_demande_don ser = new Service_demande_don();
        try {
            l = ser.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (demande_don d : l) {
   
              String path = "C:\\xampp4\\htdocs\\Handiny\\"+d.getJustificatif_handicap();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType_produit_demande());
                  Label label2 =new Label(d.getDate_demande().toString());
                  Label label3 =new Label(d.getRemarques());
                  Label label4 =new Label(d.getEtat());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
               Label label0  = null;
           try {
               label0  = new Label(""+ u.rechercherparcid_cin(d.getId_utilisateur()) );
           } catch (SQLException ex) {
               Logger.getLogger(Affichage_don_adminController.class.getName()).log(Level.SEVERE, null, ex);
           }
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
  Button button = new Button("Click me!");
                 button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

            idd=d.getId_demande_don();
            id=d.getId_don();
             
               pr=d.getType_produit_demande();
        
                // alert.setContentText("Description : "+evenement.getDescription());
                //don d = new don();
            mod_desc.setText(d.getRemarques());
        mod_URLImage.setText(d.getJustificatif_handicap());
          etat.setValue(d.getEtat());
        id_user.setText(Integer.toString(d.getId_utilisateur()));
 JOptionPane.showMessageDialog(null, "demande selectionée");
            });
                  hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
        // button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px;-fx-padding: 10;  -fx-spacing: 10 0; ");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label0.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 9px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
          button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");
                 
            hbox.getChildren().addAll(button,label0,label1,label2,label4);
          //  hbox.getChildren().addAll(button,label1, vbox2,label2,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
         
     }
 
    private void chercher(ActionEvent event) {
          demande_don d = new demande_don();
        d = table.getSelectionModel().getSelectedItem();
      
        mod_desc.setText(d.getRemarques());
        mod_URLImage.setText(d.getJustificatif_handicap());
          etat.setValue(d.getEtat());
        id_user.setText(Integer.toString(d.getId_utilisateur()));
    }
    
}
