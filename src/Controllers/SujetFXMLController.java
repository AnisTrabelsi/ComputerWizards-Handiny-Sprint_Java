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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList liste;
        try {
            liste = FXCollections.observableArrayList(ser.readNoms());
            id_categorie.setItems(liste);

        } catch (SQLException ex) {
            Logger.getLogger(SujetFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private String getRbEtat(ActionEvent event) {
        ServiceSujet sujetdao = new ServiceSujet();
        Sujet suj = new Sujet();

        if (ouvert.isSelected()) {
            suj.setEtat("ouvert");
            return "Ouvert";
        }
        if (ferme.isSelected()) {
            suj.setEtat("fermé");
            return "Fermé";
        }
        if (bloque.isSelected()) {
            suj.setEtat("bloqué");
            return "Bloqué";
        }
        return "null";
    }

    @FXML
    private void submitsujet(ActionEvent event) throws SQLException {

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

    @FXML
    private void cancelsujet(ActionEvent event) {
        titre_sujet.setText("");
        tags.setText("");
        contenu_sujet.setText("");
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
