/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.CategorieListGridPaneFXMLController;
import Controllers.SujetGridFXMLController;
import Entite.Sujet;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class MesPostesFXMLController implements Initializable {

    @FXML
    private Label nbposts;
    @FXML
    private Button AjoutSujetBtn;
    @FXML
    private GridPane gridpane;
    ServiceSujet ser = new ServiceSujet();
    int column = 0;
    int row = 1;
    private List<Sujet> sujets;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button Sauvegardes;

    /**
     * Initializes the controller class.
     */
    public void loadData() {
        try {
            sujets = new ArrayList<>(ser.readAll());
            nbposts.setText(String.valueOf(sujets.size() + " Publications"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        sujets.forEach((c) -> {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/GUI/SujetGridFXML.fxml"));
            Pane pane;

            try {
                pane = fxmlloader.load();
                SujetGridFXMLController sujController = fxmlloader.getController();
                sujController.setData(c);

                if (column == 3) {
                    column = 0;

                }
                ++row;
                gridpane.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ex) {
                Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    @FXML
    private void AjoutSujetBtn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/SujetFXML.fxml"));
            AnchorPane pane = fxmlLoader.load();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(pane);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            anchorpane.getChildren().setAll(scrollPane);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        gridpane.getChildren().clear();
        loadData();
    }

    @FXML
    private void PostsSauvegardes(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/PostsSauvegardésFXML.fxml"));
            AnchorPane pane = fxmlLoader.load();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(pane);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            anchorpane.getChildren().setAll(scrollPane);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }

    }

}
