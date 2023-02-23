/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.demande_don;
import Entite.don;
import Entite.utilisateur;
import Services.Service_demande_don;
import Services.Service_don;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
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
public class Ajout_demande_don_adminController implements Initializable {

    @FXML
    private TextField URLImage;
    private ListView<don> table;
    @FXML
    private TextField remarque;
    @FXML
    private ImageView Image;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private TextField id_user;
    @FXML
    private VBox vb;
    
    private static int a;
 private static String t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        
            ObservableList<String> list = FXCollections.observableArrayList("En cours", "validé", "refusé");
        etat.setItems(list);
    

         List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     
          vb.getChildren().clear();
     
     
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
     for (don d : l) {
   
            
             String path = "C:\\xampp4\\htdocs\\Gestion don\\"+d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();        
              Label label1 =new Label(d.getType());
                  Label label2 =new Label(d.getDescription());
       label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
           
/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                            Label label3 =new Label(d.getImage_don());
                 HBox hbox3 = new HBox();
                  hbox3.getChildren().addAll(label3);
                  
                    ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
             hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4,hbox3);
          /////////////////////////////////////////////////////////  
             Label label4 =new Label(d.getDate_ajout().toString());
      
                 hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                    Button button = new Button("Séletionner don!");
               button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0");
               
             button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
JOptionPane.showMessageDialog(null, "don selectionner");
             a=d.getId_don();
              t=d.getType();
             
               

            });
              hbox.getChildren().addAll(button,label1,label3,label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
            
            vb.getChildren().add(hbox);
     }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
        
        
    }

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
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
            URLImage.setText(DBPath);
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

    
    /*private void actualiser_don() {
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user

        ObservableList<don> items = FXCollections.observableArrayList(l);

        table.setItems(items);

// Définir un modèle de cellule personnalisé pour afficher les éléments
        table.setCellFactory(param -> new ListCell<don>() {
            @Override
            protected void updateItem(don item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_don() == 0) {
                    setText(null);
                } else {
                    setText(item.getType() + "  /  " + item.getImage_don() + "  /  " + item.getDescription() + "  /  " + item.getDate_ajout());
                    //  setGraphic(new ImageView(new Image(getClass().getResourceAsStream("C:\\xampp4\\htdocs\\" + item.getImage_don()))));
                    setPadding(new Insets(10, 10, 10, 10));
                }
            }
        });

    }
    */
      private boolean user_valide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(id_user.getText());
        if(m.find() && m.group().equals(id_user.getText())){
            return true;
        }else{
               
            return false;            
        }}
      
    @FXML
    private void ajouter(ActionEvent event) {
       String c =id_user.getText();
     
        String justif = URLImage.getText();
        String rem = remarque.getText();
          String resetat = (String) etat.getValue();
          
        Service_demande_don ser = new Service_demande_don();
        Service_don ser1 = new Service_don();
        demande_don d = new demande_don( Integer.parseInt(c),a,justif,t, rem,resetat);

        if (justif.isEmpty() || rem.isEmpty() || c.isEmpty() || resetat.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } else if (!user_valide())
        {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type validé !");
                alert.showAndWait();
        }     
            else    
                {

            try {
                if (resetat == "validé")
                {ser.ajouter_demande_don_admin(d);
              /*  ser1.supprimer_don(id);
                JOptionPane.showMessageDialog(null, "don "+ id+ " est supprimé ");
                actualiser_don();*/
                }
                else 
                {ser.ajouter_demande_don_admin(d);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            JOptionPane.showMessageDialog(null, "demande ajouté");
            vider();
        }
    }

    private void vider() {

        remarque.setText(null);
        URLImage.setText(null);
        id_user.setText(null);
        Image.setImage(null);
         etat.setValue("etat");
    }

    /////////////////////////////////////////
    @FXML
    private void mdd(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("modifier_demande_don_admin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws IOException{
          Parent root = FXMLLoader.load(getClass().getResource("affichage_demande_don_admin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
    }

    @FXML
    private void sdd(ActionEvent event)throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("supprimer_demande_don_admin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void don(ActionEvent event) throws IOException{  
        Parent root = FXMLLoader.load(getClass().getResource("Affichage_don_admin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

}
