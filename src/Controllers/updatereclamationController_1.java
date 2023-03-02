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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Chayma
 */
public class updatereclamationController_1 implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Label etat_reclamation_label;
    @FXML
    private Label type_reclamation_label;
    @FXML
    private Button update_reclamation;
    @FXML
    private Button delete_reclamation;

    private ChoiceBox<String> reclamation_choice_box;

    Reclamation r = new Reclamation();
    
    
    ServiceReclamation sr = new ServiceReclamation();

    //String type_reclam = type_reclamation_label.getText();
    private List<Reclamation> reclamationList = new ArrayList<>();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            r.setId_utilisateur(3) ;
          reclamationList = (List<Reclamation>) sr.findById_Utilisateur(3);
        } catch (SQLException ex) {
            System.out.println(ex);

        reclamation_choice_box.setItems(FXCollections.observableArrayList(reclamationList));
        reclamation_choice_box.setOnAction((event) -> {
            String selectedReclamation = reclamation_choice_box.getValue();
            for (Reclamation r : reclamationList) {
                if (r.toString().equals(selectedReclamation)) {
                    description.setText(r.getDescription());
                    type_reclamation_label.setText(r.getType_reclamation());
                    etat_reclamation_label.setText(r.getEtat_reclamation());
                    break;
                }
            }
        });
    };
    }


    @FXML
     private void update_reclamation(ActionEvent event) throws SQLException {
        r.setDescription(description.getText());
        r.setType_reclamation(type_reclamation_label.getText());
        r.setEtat_reclamation(etat_reclamation_label.getText());
        sr.update(r);
    }


    @FXML
    private void delete_reclamation(ActionEvent event) throws SQLException {
        sr.supprime(r);
    }

}
