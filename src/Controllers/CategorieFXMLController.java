/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Categorie;
import Services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CategorieFXMLController implements Initializable {

    @FXML
    private Button CreationCategorieBtn;
    @FXML
    private TextField nom_categorie;
    @FXML
    private Button AnnulerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void CreationCategorie(ActionEvent event) {
        CreationCategorieBtn.setOnAction((ActionEvent e) -> {
            try {
                Categorie cat = new Categorie(nom_categorie.getText());
                ServiceCategorie pdao = new ServiceCategorie();
                pdao.ajouter(cat);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Catégorie insérée avec succés!");

                alert.show();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        );

    }

    @FXML
    private void Annuler(ActionEvent event) {
        nom_categorie.setText("");
        Stage stage = (Stage) AnnulerBtn.getScene().getWindow();
        stage.close();
    }

}
