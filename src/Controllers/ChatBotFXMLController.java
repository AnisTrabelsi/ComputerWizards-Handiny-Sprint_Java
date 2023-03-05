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
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ChatBotFXMLController implements Initializable{

    @FXML
    private VBox chatBox;
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private TextField inputField;

    @FXML
    public void handleInput() {
        String input = inputField.getText();
        if (!input.isEmpty()) {
            displayMessage("User", input, "user-message");
            String response = chatbotAPI(input);
            displayMessage("Chatbot Handiny", response, "chatbot-message");
            inputField.clear();
        }
    }

    private void displayMessage(String sender, String message, String style) {
         Label label = new Label(sender + ": " + message);
        label.setStyle("-fx-background-color: #f8f8f8; -fx-text-fill: #555; -fx-padding: 10px; -fx-border-radius: 10px;");
        if (style.equals("user-message")) {
            label.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 10px;");
        }
        chatBox.getChildren().add(label);
        chatScrollPane.setVvalue(1.0);
    }

    private String chatbotAPI(String input) {
        // Add your chatbot API logic here
        if("Bonjour".equals(input)){
        return "Bonjour";}
        else{
            return "I'm sorry, I don't understand.";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayMessage("Chatbot Handiny", "Bienvenue à ChatBot Handiny, \nComment nous pouvons vous aider ?", "chatbot-message");
    }
}

