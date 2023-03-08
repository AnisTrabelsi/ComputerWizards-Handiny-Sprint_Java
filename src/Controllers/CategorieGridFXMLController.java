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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CategorieGridFXMLController implements Initializable {

    @FXML
    private Label namecategorie;
    @FXML
    private Label nbsujets;
    @FXML
    private Button modifCategorieBtn;
    @FXML
    private Button supprimerCategorieBtn;

    ServiceCategorie ser = new ServiceCategorie();
    CategorieListGridPaneFXMLController cc = new CategorieListGridPaneFXMLController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Categorie cat) {
        namecategorie.setText(cat.getNom_categorie());
        nbsujets.setText(cat.getNb_sujets() + " Sujets");
    }

    @FXML
    private void goToModifCategorie(MouseEvent event) {
        try {
            Categorie cat = ser.findById(ser.getIdByName(namecategorie.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui_handiny/CategorieUpdateFXML.fxml"));
            Parent root = loader.load();
            CategorieUpdateFXMLController controller = loader.getController();
            controller.initData(cat);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier une catégorie");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cc.loadData();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CategorieGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void supprimerCategorie(MouseEvent event) {
        try {
            Categorie cat = ser.findById(ser.getIdByName(namecategorie.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Voulez-vous vraiment supprimer cette catégorie ?");
            alert.setContentText("Cette action est irréversible.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ser.supprime(cat);
                cc.loadData(); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieGridFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
