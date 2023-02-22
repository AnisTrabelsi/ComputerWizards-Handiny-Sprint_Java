/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.don;
import Entite.utilisateur;
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
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Ajout_don_adminController implements Initializable {

    @FXML
    private TextField tfdescription;
    private DatePicker tfdate;
    @FXML
    private ComboBox<String> tfype;
    @FXML
    private TextField URLImage;
    @FXML
    private ImageView Image;
    @FXML
    private TextField id_user;
  
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Fauteuils roulants", "Prothèses", "Appareils auditifs", "Lunettes et lentilles de contact", "Béquilles",
                "Équipement de soins à domicile", "Rampes d'accès", "Appareils de levage ", "Sièges de bain", "autre");
        tfype.setItems(list);
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
   private boolean user_valide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(id_user.getText());
        if(m.find() && m.group().equals(id_user.getText())){
            return true;
        }else{
               
            return false;            
        }}
   
   
     private boolean descvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(tfdescription.getText());
        if(m.find() && m.group().equals(tfdescription.getText())){
            return true;
        }else{
               
           
            return false;            
        }}
     
    @FXML
    private void ajouterdon(ActionEvent event) {

       // utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

        String restype = (String) tfype.getValue();
        String resdescription = tfdescription.getText();
        String i = URLImage.getText();
         String a =id_user.getText();
        Service_don ser = new Service_don();
        
        
        
        if (restype.isEmpty() || resdescription.isEmpty() || i.isEmpty() || a.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } else if (!user_valide() || (!descvalide()))
        {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Type validé !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un type validé !");
                alert.showAndWait();
        }
        
       else 
        {
            don p = new don(Integer.parseInt(a), restype, i, resdescription);
            try {
                ser.ajouter_don(p);
                JOptionPane.showMessageDialog(null, "don ajouté");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
               tfype.setValue("Type");
            Image.setImage(null);
            vider();
            
            

        }

    }

    @FXML
    private void tfype(ActionEvent event) {
    }

    private void vider() {

        tfdescription.setText(null);
        URLImage.setText(null);
id_user.setText(null);
        tfdate.setValue(null);
        tfype.setValue("Type");
        URLImage.setText(null);
        Image.setImage(null);

    }

    @FXML
    private void aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affichage_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifier_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dd(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("affichage_demande_don_admin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

}
