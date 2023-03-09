/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import entities.utilisateur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceChauffeur;

/**                                   
 * FXML Controller class         /////////////////////////////////////////////////////            
 *
 * @author Mehdi
 */
public class Ajout_chauffeurController implements Initializable {

    private TextField id;
    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField dispo;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Afficher_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Modifier_chauffeur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

     @FXML
    private void dd(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Ajout_chauffeur_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /*
    public boolean containsBadWords(String text) throws Exception {
    String url = "https://api.example.com/badwords?text=" + text;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    con.setRequestMethod("GET");

    int responseCode = con.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {         StringBuffer response;
        try ( // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return Boolean.parseBoolean(response.toString());
    } else {
        throw new Exception("Bad words detection API call failed with response code: " + responseCode);
    }
}  */
    public boolean containsBadWords(String text) throws Exception {
    ArrayList<String> badWords = new ArrayList<>();
    badWords.add("bad");
    badWords.add("sad");
    badWords.add("no");

    return badWords.stream().anyMatch((badWord) -> (text.toLowerCase().contains(badWord)));
}

    @FXML
    private void ajouterch(ActionEvent event) throws Exception {
        
     //   utilisateur u = new utilisateur(1, "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

     
        
        String ttcin = cin.getText();
 String ttnom = nom.getText();
  String ttadresse = adresse.getText();
  String ttdispo = dispo.getText();
        ServiceChauffeur ser = new ServiceChauffeur();
        
     if (containsBadWords(nom.getText())) {
   Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Baad Wooord");
            alert.showAndWait();
     
     }
      
       else if (ttcin.isEmpty() || ttnom.isEmpty() || ttdispo.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } 
                
                
                else{
          Chauffeur c;
            c = new Chauffeur(ttcin,ttnom,ttadresse,ttdispo );
            try {
                ser.ajouter(c);
                JOptionPane.showMessageDialog(null, "chauffeur ajouté");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
               
            vider();
            JOptionPane.showMessageDialog(null, "chauffeur ajouté");
            

        }
    }
    
     private void vider() {

        id.setText(null);
        cin.setText(null);

        nom.setText(null);
      
        adresse.setText(null);
        dispo.setText(null);

    }
    
}
