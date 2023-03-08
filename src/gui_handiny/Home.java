/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Chaima
 */
public class Home extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
        primaryStage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
