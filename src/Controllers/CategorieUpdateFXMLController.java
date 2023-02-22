/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Categorie;
import Services.ServiceCategorie;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void ModifCategorie(ActionEvent event) {
       ServiceCategorie pdao = new ServiceCategorie();
        Categorie cat = new Categorie(nom_categorie.getText());
        
        ModifCategorieBtn.setOnAction((ActionEvent e) -> {
            try {
               
                
                pdao.update(cat);

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

    void initData(Categorie selectedItem) {
        nom_categorie.setText(selectedItem.getNom_categorie());
    }
    
}
