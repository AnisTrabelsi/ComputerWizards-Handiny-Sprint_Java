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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CategorieListGridPaneFXMLController implements Initializable {

   
    private List<Categorie> categories;
    ServiceCategorie ser = new ServiceCategorie();
    int column = 0;
    int row = 1;
    @FXML
    private GridPane CategoriesGrid;


    @FXML
    private BorderPane bp;

    @FXML
    private Label nbposts;

    @FXML
    private Button sujetReadAllBtn;

    @FXML
    private Button addCategorieBtn;
    @FXML
    private Button refreshBtn;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadData();
    }
    public void loadData(){
        try {
            CategoriesGrid.getChildren().clear();
            categories = new ArrayList<>(ser.readAll());
            nbposts.setText(String.valueOf(categories.size()+" Posts"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        categories.forEach((c) -> {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/GUI/CategorieGridFXML.fxml"));
            Pane pane;
            
            try {
                pane = fxmlloader.load();
                CategorieGridFXMLController catController = fxmlloader.getController();
                catController.setData(c);
                
                if (column == 3) {
                    column = 0;
                  
                }  ++row;
                CategoriesGrid.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ex) {
                Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void sujetReadAll(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/SujetListFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCategorie(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CategorieFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
        loadData();
    }






}
