/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ChatBotFXMLController implements Initializable {

    @FXML
    private VBox chatBox;
    @FXML
    private ScrollPane chatScrollPane;
    private TextField inputField;
    @FXML
    private ComboBox<String> questionComboBox;
    private ObservableList<String> questions = FXCollections.observableArrayList(
            "Quel est votre nom?",
            "Quel est votre âge?",
            "Où habitez-vous?",
            "Quel est l'objectif de l'application?"
    );

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
        switch (input) {
            case "Quel est votre nom?":
                return "Je suis ChatBot Handiny.";
            case "Quel est votre âge?":
                return "Je suis un programme informatique, donc je n'ai pas d'âge.";
            case "Où habitez-vous?":
                return "Je n'ai pas de maison, je suis toujours en ligne.";
            case "Quel est l'objectif de l'application?":
                return "Handiny est une application de location de voitures spéciales aux handicapés";
            default:
                return "Je suis désolé, je ne comprends pas.";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayMessage("Chatbot Handiny", "Bienvenue à ChatBot Handiny, \nComment nous pouvons vous aider ?", "chatbot-message");
        questionComboBox.setItems(questions);
    }

    @FXML
    private void handleQuestionSelection(ActionEvent event) {
        String selectedQuestion = questionComboBox.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            displayMessage("User", selectedQuestion, "user-message");
            String response = chatbotAPI(selectedQuestion);
            displayMessage("Chatbot Handiny", response, "chatbot-message");
        }
    }
}
