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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CategorieUpdateFXMLController implements Initializable {

    @FXML
    private TextField nom_categorie;
    @FXML
    private Button ModifCategorieBtn;
    @FXML
    private Button AnnulerBtn;
    ServiceCategorie pdao = new ServiceCategorie();
            Categorie cat = new Categorie();
    @FXML
    private Button returnlistcategoriesBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void initData(Categorie selectedItem) {
        nom_categorie.setText(selectedItem.getNom_categorie());
        System.out.println(selectedItem.getNom_categorie());
        System.out.println(selectedItem.getId_categorie());
        
        cat.setId_categorie(selectedItem.getId_categorie());
        cat.setNom_categorie(selectedItem.getNom_categorie());
    }

    @FXML
    private void ModifCategorie(ActionEvent event) {
        ModifCategorieBtn.setOnAction((ActionEvent e) -> {
            try {
                System.out.println(cat.toString());
                cat.setNom_categorie(nom_categorie.getText());
                pdao.update(cat);
                System.out.println(cat.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                
                alert.setTitle("Information Dialog");
                
                alert.setHeaderText(null);
                
                alert.setContentText("Catégorie modifiée avec succés!");
                
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

    @FXML
    private void returnlistcategories(MouseEvent event) {
        returnlistcategoriesBtn.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/ForumSideBarFXML.fxml"));
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
