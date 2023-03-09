package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceChauffeur;

/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class RapportserviceController implements Initializable {

    @FXML
    private ChoiceBox<String> type_service;
    @FXML
    private Button generate_report_button;
    @FXML
    private TextField service_count_textfield;
    
    private final ServiceChauffeur serviceChauffeur = new ServiceChauffeur();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialiser les choix disponibles dans la ChoiceBox
        type_service.getItems().addAll("TUNIS", "NABEUL", " SOUSSE", " HAMMAMET");
    }

    @FXML
    public void generateReport(ActionEvent event) {
       
        String selectedServiceType = type_service.getSelectionModel().getSelectedItem();

        // get count of services of the selected type
        int ChauffeurCount = serviceChauffeur.countChauffeursByType(selectedServiceType);

        // update the label to display the service count
        service_count_textfield.setText("Number of " + selectedServiceType + " chauffeurs: " + ChauffeurCount);
    } 
}
