/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Affichage_tableController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> departCol;
    @FXML
    private TableColumn<?, ?> destinationCol;
    @FXML
    private TableColumn<?, ?> nbPlaceCol;
    @FXML
    private TableColumn<?, ?> dateAjoutCol;
    @FXML
    private TableColumn<?, ?> prixCol;
    @FXML
    private TableColumn<?, ?> conducteurCol;
    @FXML
    private TextField rechercheField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
