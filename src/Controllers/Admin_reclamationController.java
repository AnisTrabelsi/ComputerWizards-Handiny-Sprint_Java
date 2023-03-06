/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Reclamation;
import Services.ServiceReclamation;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class Admin_reclamationController implements Initializable {

    @FXML
    private ListView<Reclamation> listview;
    @FXML
    private Label description;
    @FXML
    private ComboBox<String> comboo;
    @FXML
    private TextArea response;

    private String selectedReclamation = "";
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    //Admin_reclamationController arc;
    ServiceReclamation sr = new ServiceReclamation();
    String responsee = "";
    Reclamation r = new Reclamation();

    private String etat_reclam[] = {"En attente", "En cours de traitement ", "En attente de réponse ", "Résolue ", "Fermée", "Rejetée "};
    @FXML
    private Button Traiter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = new Reclamation();
        List<Reclamation> reclam;
        try {

            reclam = sr.readAll();
            System.out.println(reclam);

            ObservableList<Reclamation> obs = FXCollections.observableList(reclam);
            listview.setItems(obs);
            listview.setCellFactory(param -> new ReclamationCell());
        } catch (SQLException ex) {
            Logger.getLogger(Read_user_by_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboo.setItems(FXCollections.observableArrayList(etat_reclam));

    }

    @FXML
    private Reclamation read_all_reclamation(ActionEvent event) {
        Reclamation selectedReclamation = listview.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            description.setText(selectedReclamation.getDescription());
        }
        return selectedReclamation;

    }

    @FXML
    private void Traiter(ActionEvent event) throws SQLException {
        String reponse = response.getText();
        String selectedEtat = comboo.getValue();
        Reclamation selectedReclamation = listview.getSelectionModel().getSelectedItem();

    if (selectedReclamation == null) {
        return;
    }

    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Modifier la réclamation");
    dialog.setHeaderText("Modifier la réclamation sélectionnée :");

    TextField descriptionField = new TextField();
    descriptionField.setPromptText("Nouvelle description");
    descriptionField.setText(selectedReclamation.getDescription());

    ComboBox<String> etatCombo = new ComboBox<>();
    etatCombo.getItems().addAll("En attente", "En cours de traitement ", "En attente de réponse ", "Résolue ", "Fermée", "Rejetée ");
    etatCombo.setValue(selectedReclamation.getEtat_reclamation());

    TextArea responseArea = new TextArea();
    responseArea.setPromptText("Nouvelle réponse");
    responseArea.setText(selectedReclamation.getReponse());

    GridPane grid = new GridPane();
    grid.add(new Label("Description :"), 1, 1);
    grid.add(descriptionField, 2, 1);
    grid.add(new Label("État :"), 1, 2);
    grid.add(etatCombo, 2, 2);
    grid.add(new Label("Réponse :"), 1, 3);
    grid.add(responseArea, 2, 3);
    dialog.getDialogPane().setContent(grid);

    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

    Node okNode = dialog.getDialogPane().lookupButton(okButton);
    okNode.setDisable(true);
    descriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
        okNode.setDisable(newValue.trim().isEmpty());
    });
    etatCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
        okNode.setDisable(newValue == null);
    });
    responseArea.textProperty().addListener((observable, oldValue, newValue) -> {
        okNode.setDisable(newValue.trim().isEmpty());
    });

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String descriptionNew = descriptionField.getText();
        String etatNew = etatCombo.getValue();
        String responseNew = responseArea.getText();

        selectedReclamation.setDescription(descriptionNew);
        selectedReclamation.setEtat_reclamation(etatNew);
        selectedReclamation.setReponse(responseNew);

        sr.update(selectedReclamation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("La réclamation a été mise à jour.");
        alert.showAndWait();
    }
}
}

