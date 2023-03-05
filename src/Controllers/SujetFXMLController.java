/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Categorie;
import Entite.Sujet;
import Services.ServiceCategorie;
import Services.ServiceSujet;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class SujetFXMLController implements Initializable {

    @FXML
    private RadioButton ouvert;
    @FXML
    private RadioButton ferme;
    @FXML
    private RadioButton bloque;
    @FXML
    private TextField tags;
    @FXML
    private TextField titre_sujet;
    @FXML
    private ChoiceBox<String> id_categorie;
    @FXML
    private TextField contenu_sujet;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    private Statement ste;
    
    ServiceCategorie ser = new ServiceCategorie();
    ServiceSujet sersujet = new ServiceSujet();
    @FXML
    private Button linkedInBtn;
    @FXML
    private Button FacebookBtn;
    @FXML
    private Button GmailBtn;
    @FXML
    private Button returnlistSujetsBtn;

    /**
     * Initializes the controller class.
     */
    ToggleGroup etatToggleGroup = new ToggleGroup();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList liste;
        try {
            liste = FXCollections.observableArrayList(ser.readNoms());
            id_categorie.setItems(liste);

        } catch (SQLException ex) {
            Logger.getLogger(SujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ouvert.setToggleGroup(etatToggleGroup);
        ferme.setToggleGroup(etatToggleGroup);
        bloque.setToggleGroup(etatToggleGroup);

    }

    @FXML
    private String getRbEtat(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) etatToggleGroup.getSelectedToggle();
        return selectedRadioButton.getText();
    }

    @FXML
    private void submitsujet(ActionEvent event) throws SQLException {
        if (isEmptyFields()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires!");
            alert.showAndWait();
        } else {
            if (titre_sujet.getText().matches(".*\\d.*")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le titre ne doit pas contenir de chiffres.");
                alert.showAndWait();
                return;
            }

            if (getRbEtat(event) == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez choisir l'état du sujet.");
                alert.showAndWait();
                return;
            }

            if (id_categorie == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez choisir une catégorie.");
                alert.showAndWait();
                return;
            }

            if (contenu_sujet.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le contenu du sujet ne doit pas être vide.");
                alert.showAndWait();
                return;
            }

            submitBtn.setOnAction((ActionEvent e) -> {
                try {
                    Categorie cat = ser.findById(ser.getIdByName(id_categorie.getValue()));
                    Sujet sujet = new Sujet(titre_sujet.getText(), contenu_sujet.getText(), getRbEtat(e), tags.getText(), cat);
                    sersujet.ajouter(sujet);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Sujet inséré avec succés!");
                    alert.show();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            });
        }
    }

    private boolean isEmptyFields() {
        return titre_sujet.getText().isEmpty()
                || contenu_sujet.getText().isEmpty()
                || tags.getText().isEmpty()
                || id_categorie.getSelectionModel().isEmpty()
                || (!ouvert.isSelected() && !ferme.isSelected() && !bloque.isSelected());
    }

    @FXML
    private void cancelsujet(ActionEvent event) {
        titre_sujet.setText("");
        tags.setText("");
        contenu_sujet.setText("");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void linkedIn(ActionEvent event) {
        linkedInBtn.setOnAction((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/oumaima-benghanem-26021420b/"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void Facebook(ActionEvent event) {
        FacebookBtn.setOnAction((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.facebook.com"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void Gmail(ActionEvent event) {
        GmailBtn.setOnAction((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://mail.google.com/mail/u/0/#inbox"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void returnlistsujets(MouseEvent event) {
        returnlistSujetsBtn.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumHomeFXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
