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
public class SujetUpdateFXMLController implements Initializable {

    @FXML
    private TextField titre_sujet;
    @FXML
    private ChoiceBox<String> id_categorie;
    @FXML
    private TextField contenu_sujet;
    @FXML
    private RadioButton ouvert;
    @FXML
    private RadioButton ferme;
    @FXML
    private RadioButton bloque;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button ModifiersujetBtn;
    @FXML
    private TextField tags;
    ServiceSujet sujetdao = new ServiceSujet();
    ServiceCategorie ser = new ServiceCategorie();
    Sujet sujet = new Sujet();
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
    private void cancelsujet(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    void initData(Sujet selectedItem) {
        System.out.println(selectedItem.toString());
        titre_sujet.setText(selectedItem.getTitre_sujet());
        contenu_sujet.setText(selectedItem.getContenu_sujet());
        tags.setText(selectedItem.getTags());
        // Initialisation de la valeur de la ChoiceBox id_categorie
        id_categorie.setValue(selectedItem.getCat().getNom_categorie());

        // Initialisation de la valeur du bouton radio
        if (selectedItem.getEtat().equals("Ouvert")) {
            ouvert.setSelected(true);
        } else if (selectedItem.getEtat().equals("Fermé")) {
            ferme.setSelected(true);
        } else if (selectedItem.getEtat().equals("Bloqué")) {
            bloque.setSelected(true);
        }
        sujet.setId_sujet(selectedItem.getId_sujet());
//        sujet.setTitre_sujet(selectedItem.getTitre_sujet());
//        sujet.setContenu_sujet(selectedItem.getContenu_sujet());
//        sujet.setTags(selectedItem.getTags());
//        sujet.setCat(selectedItem.getCat());
//        sujet.setEtat(selectedItem.getEtat());
    }

    @FXML
    private void Modifiersujet(ActionEvent event) {
        ModifiersujetBtn.setOnAction((ActionEvent e) -> {
            try {
                Categorie cat = ser.findById(ser.getIdByName(id_categorie.getValue()));
                sujet.setTitre_sujet(titre_sujet.getText());
                sujet.setCat(cat);
                System.out.println(cat.toString());
                sujet.setContenu_sujet(contenu_sujet.getText());
                sujet.setEtat(getRbEtat(event));
                sujet.setTags(tags.getText());
                sujetdao.update(sujet);
                System.out.println(sujet.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Sujet modifié avec succés!");

                alert.show();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        );
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SujetListFXML.fxml"));
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
