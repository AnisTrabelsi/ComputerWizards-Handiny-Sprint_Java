/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Sujet;
import Controllers.SujetUpdateFXMLController;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class SujetListViewFXMLController implements Initializable {

    @FXML
    private Label categoriesLabel;
    @FXML
    private Button modifSujetBtn;
    @FXML
    private Button supprimerCategorieBtn;
    @FXML
    private ListView<Sujet> listView;

    private List<Sujet> items = null;
    ServiceSujet catdao = new ServiceSujet();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            items = catdao.readAll();
        } catch (SQLException e) {
            System.out.println(e);
        }

        items.forEach((c) -> {
            listView.getItems().add(c);
        });
    }

    @FXML
    private void goToModifSujet(MouseEvent event) {
       try {
                    int selectedID = listView.getSelectionModel().getSelectedIndex();
                    Sujet c = listView.getSelectionModel().getSelectedItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SujetUpdateFXML.fxml"));
                    Parent root = loader.load();
                    SujetUpdateFXMLController controller = loader.getController();
                    controller.initData(listView.getSelectionModel().getSelectedItem());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Modifier un sujet");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (IOException ex) {
                    System.out.println(ex);
        }
    }

    @FXML
    private void supprimerSujet(MouseEvent event) {
        try {
            int selectedID = listView.getSelectionModel().getSelectedIndex();
            Sujet c = listView.getSelectionModel().getSelectedItem();
            listView.getItems().remove(selectedID);
            catdao.supprime(c);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
