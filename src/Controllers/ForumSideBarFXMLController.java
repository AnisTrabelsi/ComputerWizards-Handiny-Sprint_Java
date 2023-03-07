/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class ForumSideBarFXMLController implements Initializable {

    @FXML
    private AnchorPane side_anchorpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Button forumhomeBtn;
    @FXML
    private Button filtersBtn;
    @FXML
    private Button categoriesBtn;
    @FXML
    private Button forumRulesBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private BorderPane bp;
    @FXML
    private Button openChatbotBtn;
    @FXML
    private Button mesPostesBtn;

    /**
     * Initializes the controller class.
     */
    int s = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        home(null);
        if (s == 0) {
            categoriesBtn.setVisible(false);
            mesPostesBtn.setVisible(true);
        } else {
            categoriesBtn.setVisible(true);
            mesPostesBtn.setVisible(false);
        }
    }

    @FXML
    private void home(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ForumHomeFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void filters(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FiltresFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void categories(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CategorieListGridPaneFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forumRules(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ForumRulesFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void déconnexion(MouseEvent event) {
    }

    @FXML
    private void openChatbot(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ChatBotFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mesPostes(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/MesPostesFXML.fxml"));
            bp.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
