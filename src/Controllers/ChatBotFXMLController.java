/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class ChatBotFXMLController implements Initializable {

    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextField inputTextField;
    @FXML
    private Button EnvoieBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleUserInput(ActionEvent event) {
        String userInput = inputTextField.getText();
        chatTextArea.appendText("User: " + userInput + "\n");
        // Code pour traiter la saisie utilisateur et générer une réponse
        inputTextField.clear();
    }
    
}
