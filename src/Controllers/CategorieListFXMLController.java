/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.CategorieUpdateFXMLController;
import Entite.Categorie;
import Services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CategorieListFXMLController implements Initializable {

    private TextArea ListCategories;
    @FXML
    private Label categoriesLabel;
    @FXML
    private Button modifCategorieBtn;
    @FXML
    private Button supprimerCategorieBtn;
    private List<Categorie> items = null;
    ServiceCategorie catdao = new ServiceCategorie();
    @FXML
    private ListView<Categorie> listView;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            items = catdao.readAll();
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (Categorie c : items) {
            listView.getItems().add(c);
        }

    }

    @FXML
    private void goToModifCategorie(MouseEvent event) {
                try {
                    int selectedID = listView.getSelectionModel().getSelectedIndex();
                    Categorie c = listView.getSelectionModel().getSelectedItem();
                    System.out.println(c.getId_categorie());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategorieUpdateFXML.fxml"));
                    Parent root = loader.load();
                    CategorieUpdateFXMLController controller = loader.getController();
                    controller.initData(listView.getSelectionModel().getSelectedItem());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Modifier une catégorie");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException ex) {
                    System.out.println(ex);
        }
    }

    @FXML
    private void supprimerCategorie(MouseEvent event) {
        try {
            int selectedID = listView.getSelectionModel().getSelectedIndex();
            Categorie c = listView.getSelectionModel().getSelectedItem();
            listView.getItems().remove(selectedID);
            catdao.supprime(c);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
