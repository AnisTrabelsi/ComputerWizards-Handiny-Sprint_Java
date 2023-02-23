/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.don;
import Services.Service_don;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Affichage_donController implements Initializable {

    private DatePicker date;
    @FXML
    private VBox vb;
    @FXML
    private ComboBox<String> types;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Fauteuils roulants", "Prothèses", "Appareils auditifs", "Lunettes et lentilles de contact", "Béquilles",
                "Équipement de soins à domicile", "Rampes d'accès", "Appareils de levage ", "Sièges de bain", "autre");
        types.setItems(list);
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Gestion don\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
             

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
                   
            Label label3 = new Label(d.getImage_don());
             

            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
            /////////////////////////
   hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //////////////////////////////
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));
// vb.setStyle("fx-spacing: -1; fx-padding:-1; fx-alignment: center; ");
            vb.getChildren().add(hbox);
        }
       

    }

    @FXML
    private void afficher(ActionEvent event) {
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.readAll_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Gestion don\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }

    @FXML
    private void trier(ActionEvent event) {
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.sortbydate_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Gestion don\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    }

    @FXML
    private void md(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifier_don.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_don.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aj(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajout_don.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("affichage_demande_don.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cherch(ActionEvent event) {
        String restype = (String) types.getValue();
        vb.getChildren().clear();
        List<don> l = new ArrayList<don>();
        Service_don ser = new Service_don();
        try {
            l = ser.findbytype(restype);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (don d : l) {

            String path = "C:\\xampp4\\htdocs\\Gestion don\\" + d.getImage_don();
            File file = new File(path);
            HBox hbox = new HBox();
            Label label1 = new Label(d.getType());
            Label label2 = new Label(d.getDescription());
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
            VBox vbox2 = new VBox();
            Label label3 = new Label(d.getImage_don());
            HBox hbox3 = new HBox();
            hbox3.getChildren().addAll(label3);

            ImageView i;
            i = new ImageView(new Image(file.toURI().toString()));
            i.setFitWidth(150);
            i.setFitHeight(150);
            HBox hbox4 = new HBox();
            hbox4.getChildren().addAll(i);
            vbox2.getChildren().addAll(hbox4, hbox3);
            /////////////////////////////////////////////////////////  
            Label label4 = new Label(d.getDate_ajout().toString());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10  ");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, vbox2, label2, label4);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       // vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
    }

}
