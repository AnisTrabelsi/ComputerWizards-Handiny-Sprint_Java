/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.SessionManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.ServiceNote_voitures;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class BackController implements Initializable {

    @FXML
    private AnchorPane mesDons;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
           mesDons.getChildren().setAll(pane);
        
      
    }    

    @FXML
    private void Liste_des_dons(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Liste_des_demandes_dons(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/affichage_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Ajouter_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Modifier_demande_don(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_demande_don_admin.fxml"));
           mesDons.getChildren().setAll(pane);
    }

  

    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    
    @FXML
    private void accueil(ActionEvent event) throws IOException {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
           mesDons.getChildren().setAll(pane);
        
    }

    @FXML
    private void Accueil2(ActionEvent event) throws IOException {
      AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/HomeBack.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void statdd(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/DemandesDonStats.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
             SessionManager.CleanUserSession(); 
      Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Authentification.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Forum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ForumSideBarFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void golesreserv(ActionEvent event) throws IOException  {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Les_reservation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void goreserv(ActionEvent event) throws IOException  {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void goajout(ActionEvent event) throws IOException  {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void golescov(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_covoiturage.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Liste_utilisateurs(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Admin_affichage_user.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Supprimer_utilisateurs(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Admin_supprime_user.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void Traiter_reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/admin_reclamation.fxml"));
           mesDons.getChildren().setAll(pane);
    }

   

    @FXML
    private void ajcha(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_chauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void modcha(ActionEvent event) throws IOException {
             AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/supprimer_chauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void suppchau(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_chauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void affchaauf(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Afficher_chauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void ajchar(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Ajout_reservationchauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void suppreserch(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Supprimer_reservationchauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void affchaaufr(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Afficher_reservationchauffeur.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void voitures(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_VoituresBackEnd.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void statistiques(ActionEvent event) {
          try {
            // Créer la liste des données pour le Pie Chart
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            Map<String, Double> dataMap = getDataMap();
            
            for (String key : dataMap.keySet()) {
                pieChartData.add(new PieChart.Data(key, dataMap.get(key)));
            }
            
            // Créer le Pie Chart
            PieChart pieChart = new PieChart(pieChartData);
            
            Label label = new Label("Statistiques de l'évaluation de voitures par marque");
            
// Définir la couleur du texte en bleu



// Définir la taille de la police à 32
label.setFont(new Font(32));


// Créer la VBox
VBox vbox = new VBox();
vbox.getChildren().addAll(label, pieChart);

// Créer la scène
Scene scene = new Scene(vbox, 800, 600);

// Créer et afficher la fenêtre
Stage stage = new Stage();
stage.setScene(scene);
stage.show();
        } catch (SQLException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
}




   private Map<String, Double> getDataMap() throws SQLException {
    ServiceNote_voitures sn = new ServiceNote_voitures();
    Map<String, Double> noteMoyenneParMarque = sn.calculerNoteMoyenneParMarque();
    System.out.println(noteMoyenneParMarque);

    Map<String, Double> dataMap = noteMoyenneParMarque;

    return dataMap;
    }

    @FXML
    private void reservations(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/AffichageLocationBackEnd.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    @FXML
    private void tousLesvoitures(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_Voitures_frontOffice.fxml"));
           mesDons.getChildren().setAll(pane);
    }

    
}
