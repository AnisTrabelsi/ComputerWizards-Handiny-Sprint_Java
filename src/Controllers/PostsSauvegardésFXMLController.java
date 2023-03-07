/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.PostsSauvegardés;
import Services.ServiceSauvegarde;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class PostsSauvegardésFXMLController implements Initializable {

    @FXML
    private Label nbposts;
    @FXML
    private Button AjoutSujetBtn;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private GridPane gridpane;
    ServiceSauvegarde ser = new ServiceSauvegarde();
    int column = 0;
    int row = 1;
    private List<PostsSauvegardés> sauvegardes;
    /**
     * Initializes the controller class.
     */
    public void loadData() {
        try {
            sauvegardes = new ArrayList<>(ser.readAll());
            nbposts.setText(String.valueOf(sauvegardes.size() + " Sauvegardes"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        sauvegardes.forEach((c) -> {
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
    }
    
}
