/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Favoris_voitures;


import Entite.Note_voitures;
import Entite.Utilisateur;
import Entite.Voiture;
import com.jfoenix.controls.JFXToggleNode;
import controllers.AffichageDetailVoitureController;
import controllers.Affichage_Voitures_frontOfficeController;
import controllers.InsertLocationController;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import org.controlsfx.control.Rating;
import services.ServiceFavorisVoitures;
import services.ServiceNote_voitures;
import services.ServiceVoiture;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Affichage_Voitures_frontOfficeController implements Initializable {

    @FXML
    private GridPane gridPane;
    private List<Voiture> voitures = new ArrayList<Voiture>();
    @FXML
    private TextField searchBar;
   // HashMap<Integer, List<Double>> notesParVoiture = new HashMap<>();
@FXML
private ComboBox<String> comboBoxTri;



    
    




    /**
     * Initializes the controller class.
     */
   
   @Override
public void initialize(URL url, ResourceBundle rb) {
       
    comboBoxTri.setItems(FXCollections.observableArrayList(
    "Tri par marque",
    "Tri par modèle",
    "Tri par prix de location"
));
    comboBoxTri.setValue("Tri par marque"); // Set default value to "Tri par marque"
    ServiceVoiture sv = new ServiceVoiture();

    try {
        voitures=sv.readAll();
    } catch (SQLException ex) {
        Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    gridPaneVoitures(voitures);
    
     // ajout du gestionnaire d'événements à la barre de recherche
    searchBar.setOnKeyReleased(event -> {
       
        try {
            String searchText = searchBar.getText().toLowerCase();
            List<Voiture> filteredVoitures = sv.filtre(searchText);
            System.out.println(filteredVoitures);
            gridPane.getChildren().clear();
            gridPaneVoitures(filteredVoitures);
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    });
}

private void trierParModele() throws SQLException {
    ServiceVoiture sv = new ServiceVoiture();
    List<Voiture> voitures = sv.trierParModele();// appel de la fonction de tri
    gridPane.getChildren().clear(); // suppression des anciens éléments de la grille
    
      gridPaneVoitures(voitures);
    
    
    
}


    private void trierParPrix() throws SQLException {
        ServiceVoiture sv = new ServiceVoiture();
    List<Voiture> voitures = sv.trierParPrix();// appel de la fonction de tri
    gridPane.getChildren().clear(); // suppression des anciens éléments de la grille
    
    gridPaneVoitures(voitures);
    
    }

    private void trierParMarque() throws SQLException {
        ServiceVoiture sv = new ServiceVoiture();
    List<Voiture> voitures = sv.trierParMarque();// appel de la fonction de tri
    gridPane.getChildren().clear(); // suppression des anciens éléments de la grille
    gridPaneVoitures(voitures);
    

   

    
}

    private void gridPaneVoitures(List<Voiture> voitures) {
        // initialisation de la grille
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setStyle("-fx-background-color: #ffffff; ");

    // ajout des voitures à la grille
    int row = 0;
    int col = 0;
    for (Voiture voiture : voitures) {
        // création de la vue de la voiture 
        System.out.println(voiture.getImage_voiture());
        String path = "C:\\xampp4\\htdocs\\Handiny\\" + voiture.getImage_voiture();
        System.out.println(path);
        File file = new File(path);
       // Image img1=new Image(getClass().getResourceAsStream(path));
        //ImageView i = new ImageView(img1);
        ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
         
        i.setFitWidth(170);
        i.setFitHeight(170);
       
        // création des boutons "Réserver" et "Voir détails"
        Button reserverBtn = new Button("Réserver");
         int id_voiture=voiture.getId_voiture();
        
        reserverBtn.getStyleClass().add("btn-reserver");
        // Ajout du gestionnaire d'événements pour le bouton "Réserver"
      

        Button detailsBtn = new Button("Voir détails");
       detailsBtn.getStyleClass().add("button-detail");

        // création du VBox pour les informations de la voiture et les boutons
        Label marqueLabel = new Label(voiture.getMarque());
        marqueLabel.setFont(Font.font("System", 18));
        marqueLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label modeleLabel = new Label(voiture.getModele());
        modeleLabel.setFont(Font.font("System", 16));
       modeleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label anneeLabel = new Label(Double.toString(voiture.getPrix_location()));
        anneeLabel.setFont(Font.font("System", 14));
       anneeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox vbox = new VBox(marqueLabel, modeleLabel, anneeLabel, reserverBtn, detailsBtn);
        vbox.setSpacing(5);
        

        // création du HBox contenant l'image et le VBox des informations et boutons
        HBox hbox = new HBox(i, vbox);
       hbox.getStyleClass().add("hbox"); // ajout de la classe CSS "hbox"d
       hbox.setSpacing(10);
       // création du Rating avec une note de 3
Rating rating = new Rating(5);
rating.setRating(3);
// Ajout de la zone d'évaluation à la vue de la voiture

rating.setOnMouseClicked(event -> {
   double note = rating.getRating();
     Utilisateur u=Utilisateur.getCurrent_user();
     System.out.println("le user connecté "+u.getNom());
   // variable voiture définie dans la boucle for
   Note_voitures noteVoiture = new Note_voitures(u, voiture, note);
   ServiceNote_voitures sn = new ServiceNote_voitures();
            try {
                sn.ajouter(noteVoiture);
                 Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Votre évaluation est enregistrée avec succés ! ");
                alert.showAndWait();
                System.out.println("la note est enregistrée");
            } catch (SQLException ex) {
                Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
});

        vbox.getChildren().add(rating);
        // création du bouton avec l'icône "heart"
Button favorisBtn = new Button("Ajouter au favoris");
favorisBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 50");

/*
FontAwesomeIconView heartIcon;
        heartIcon = new FontAwesomeIconView(FontAwesomeIcon.Heart);
heartIcon.setSize("2em"); // taille de l'icône
heartIcon.setStyleClass("heart-icon"); // classe CSS personnalisée
favorisBtn.setGraphic(heartIcon);
*/




// Créer l'icône de suppression
       Button removeFavButton = new Button("Annuler");
       removeFavButton.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-background-radius: 50;");

/*FontAwesomeIconView removeIcon = new FontAwesomeIconView(FontAwesomeIcon.HEART);
removeIcon.setSize("2em"); // taille de l'icône
removeIcon.setStyle("-fx-fill: gray;"); // couleur de l'icône
removeFavButton.setGraphic(removeIcon);
//removeFavButton.setStyle("-fx-background-color: transparent;"); // fond du bouton transparent*/
HBox hbox3 = new HBox();
HBox hbox4 = new HBox();


hbox3.getChildren().addAll(favorisBtn);




hbox4.getChildren().addAll(removeFavButton );
// ajout du gestionnaire d'événements pour le bouton "Favoris"
favorisBtn.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Utilisateur u=Utilisateur.getCurrent_user();
        int id_utilisateur = u.getId_utilisateur();
        int id_voiture = voiture.getId_voiture();
        ServiceFavorisVoitures serviceFavoris = new ServiceFavorisVoitures();
        Favoris_voitures f= new Favoris_voitures(u,voiture);
        try {
            // Check if the record already exists in the "notes_voitures" table
            if (!serviceFavoris.existe(id_utilisateur, id_voiture)) {
                // Insert the new record if it doesn't exist
                serviceFavoris.ajouter(f);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Votre voiture a été ajoutée aux favoris ! ");
                alert.showAndWait();
            } else {
                // Show an error message if the record already exists
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("");
                alert.setContentText("La voiture est déjà dans vos favoris !");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
removeFavButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        Utilisateur u = Utilisateur.getCurrent_user();
        int id_utilisateur = u.getId_utilisateur();
        int id_voiture = voiture.getId_voiture();
        ServiceFavorisVoitures serviceFavoris = new ServiceFavorisVoitures();
        Favoris_voitures f = new Favoris_voitures(u, voiture);
        try {
            // Check if the record exists in the "favoris_voitures" table
            if (serviceFavoris.existe(id_utilisateur, id_voiture)) {
                // Delete the record if it exists
                serviceFavoris.supprime(f);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("");
                alert.setContentText("Votre voiture a été supprimée des favoris ! ");
                alert.showAndWait();
            } else {
                // Show an error message if the record doesn't exist
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("");
                alert.setContentText("La voiture n'est pas dans vos favoris !");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});



// ajout du bouton à la vue de la voiture
vbox.getChildren().add(hbox3 );
vbox.getChildren().add(hbox4);



        // ajout du HBox à la grille
        gridPane.add(hbox, col, row);
         reserverBtn.setOnAction(event -> {
       try {
            // création de la nouvelle fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/InsertLocation.fxml"));
            Parent root = loader.load();
            InsertLocationController controller = loader.getController();
            System.out.println(controller);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
           
            // passage de l'ID de la voiture au contrôleur de la nouvelle page
            controller.setIDVoiture(id_voiture);
            System.out.println(voiture.getId_voiture());
            // affichage de la nouvelle fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    
});
          detailsBtn.setOnAction(event -> {
       try {
            // création de la nouvelle fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/AffichageDetailVoiture.fxml"));
            Parent root = loader.load();
            AffichageDetailVoitureController controller = loader.getController();
            System.out.println(controller);
             controller.setIDVoiture(id_voiture);
            System.out.println(voiture.getId_voiture());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
           
            // passage de l'ID de la voiture au contrôleur de la nouvelle page
           
            // affichage de la nouvelle fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   catch (SQLException ex) {
                Logger.getLogger(Affichage_Voitures_frontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    
});
         

        // incrémentation des indices de la grille
        col++;
        if (col == 2) {
            col = 0;
            row++;
        }
    }
    }

    @FXML
    private void tri(ActionEvent event) throws SQLException {
        String selectedOption = comboBoxTri.getValue();
    // Perform sorting based on the selected option
    switch (selectedOption) {
        case "Tri par marque":
            trierParMarque();
            break;
        case "Tri par modèle":
            trierParModele();
            break;
        case "Tri par prix de location":
            trierParPrix();
            break;
        default:
            System.out.println("Invalid selection");
            break;
    }
    }

    @FXML
    private void favoris(ActionEvent event) throws SQLException {
    ServiceVoiture sv = new ServiceVoiture();
    Utilisateur u=Utilisateur.getCurrent_user();
    List<Voiture> voitures = sv.readFavorites_Of_user(u.getId_utilisateur());// appel de la fonction de tri
    gridPane.getChildren().clear(); // suppression des anciens éléments de la grille
    
    gridPaneVoitures(voitures);
    }

    





    }



   


    

    
    
