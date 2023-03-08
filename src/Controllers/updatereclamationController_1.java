/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Reclamation;
import Services.ServiceReclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class updatereclamationController_1 implements Initializable {

    private TextArea description;
    @FXML
    private Label etat_reclamation_label;
    @FXML
    private Label type_reclamation_label;
    @FXML
    private Button update_reclamation;

    @FXML
    private ComboBox<Reclamation> mes_reclams;

    @FXML
    private Label description1;
    @FXML
    private Button delete_reclamation1;
    
    Reclamation r;

    ServiceReclamation sr = new ServiceReclamation();

    private List<Reclamation> reclamationList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            int id_utilisateur = 3;
            reclamationList = sr.findById_Utilisateur(id_utilisateur);
            System.out.println(reclamationList);

            mes_reclams.setItems(FXCollections.observableArrayList(reclamationList));

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        mes_reclams.setOnAction((event) -> {
            Reclamation selectedReclamation = mes_reclams.getValue();
            if (selectedReclamation != null) {
                description1.setText(selectedReclamation.getDescription());
                type_reclamation_label.setText(selectedReclamation.getType_reclamation());
                etat_reclamation_label.setText(selectedReclamation.getEtat_reclamation());
            }


        });
    }

    @FXML
    private void update_reclamation(ActionEvent event) throws SQLException {
    Reclamation selectedReclamation = mes_reclams.getValue();

    if (selectedReclamation == null) {
        return;
    }

    // je vais créer une boite de dialogue personnalisé
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Modifier la description");
    dialog.setHeaderText("Modifier la description de la réclamation sélectionnée :");

    TextField descriptionField = new TextField();
    descriptionField.setPromptText("Nouvelle description");
    descriptionField.setText(selectedReclamation.getDescription().toString());

    dialog.getDialogPane().setContent(descriptionField);

    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

    // Controle de saisie sur le champ
    Node okNode = dialog.getDialogPane().lookupButton(okButton);
    okNode.setDisable(true);
    descriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
        okNode.setDisable(newValue.trim().isEmpty());
    });

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
        String description_new = descriptionField.getText();
        selectedReclamation.setDescription(description_new);
        sr.update(selectedReclamation);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Votre réclamation a été mise à jour.");
        alert.showAndWait();
    }
    }

    
////         String description_new =description2.getText();
////         Reclamation selectedReclamation = mes_reclams.getValue();
////         selectedReclamation.setDescription(description_new);
////        sr.update(selectedReclamation);
////        Alert alert = new Alert(Alert.AlertType.INFORMATION);
////        alert.setHeaderText(null);
////        alert.setContentText("Votre reclamation a été mise a jour");
////        alert.showAndWait();
////    }
////    
        @FXML
    private void delete_reclamation(ActionEvent event) throws SQLException {
Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
    alert.setContentText("La réclamation sera définitivement supprimée.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
      Reclamation selectedReclamation = mes_reclams.getValue();

        sr.supprime(selectedReclamation);
        mes_reclams.getItems().remove(selectedReclamation);

    } 
}
 

}