/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.don;
import Services.Service_don;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author anis
 */
public class Modifier_don_adminController implements Initializable {

    @FXML
    private TextField mod_tfdescription;
    private DatePicker mod_tfdate;
    @FXML
    private TextField mod_URLImage;
    @FXML
    private ImageView Image;
    @FXML
    private ComboBox<String> mod_tfype;

    private ListView<don> table;
    @FXML
    private VBox vb;

    private static int a, b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Fauteuils roulants", "Prothèses", "Appareils auditifs", "Lunettes et lentilles de contact", "Béquilles",
                "Équipement de soins à domicile", "Rampes d'accès", "Appareils de levage ", "Sièges de bain", "autre");
        mod_tfype.setItems(list);
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
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            Button button = new Button("Select!");
button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

            button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
JOptionPane.showMessageDialog(null, "Don sélectionné");
                // alert.setContentText("Description : "+evenement.getDescription());
                //don d = new don();
                a = d.getId_don();
                b = d.getId_utilisateur();
                /*Instant instant = d.getDate_ajout().toInstant();
LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
mod_tfdate.setValue(localDate);*/
                mod_tfdescription.setText(d.getDescription());
                mod_tfype.setValue(d.getType());
                mod_URLImage.setText(d.getImage_don());

            });

            hbox.getChildren().addAll(label1, label3, label2, label4, button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
    }

    @FXML
    private void modifierdon(ActionEvent event) {

        Service_don ser = new Service_don();

        String restype = (String) mod_tfype.getValue();

        String resdescription = mod_tfdescription.getText();
        String i = mod_URLImage.getText();

        if (restype.isEmpty() || resdescription.isEmpty() || i.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("il y a des attributs vides");
            alert.showAndWait();
        } else {
            don p = new don(a, b, restype, mod_URLImage.getText(), resdescription);

            try {
                ser.modifier_don(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            Actualiser();
            JOptionPane.showMessageDialog(null, "don modifie");
            mod_tfdescription.clear();
            mod_URLImage.clear();
            mod_tfype.setValue("Type");
            LocalDate initialDate = LocalDate.of(1970, 01, 01);
            mod_tfdate.setValue(initialDate);
            Image.setImage(null);
        }
    }

    @FXML
    private void modImage(ActionEvent event) throws FileNotFoundException, IOException {
        mod_URLImage.clear();
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        //String DBPath = "C:\\\\xampp\\\\htdocs\\\\Version-Integre\\\\public\\\\uploads\\\\" + x + ".jpg";
        String DBPath = "" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            String res;
            int len;
            len = path.length();

            res = path.substring(47, len);
            System.out.println(res);
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
            mod_URLImage.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();

        } else {
            System.out.println("error");

        }
    }

    private void Actualiser() {
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
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            vbox2.setStyle("-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 0;");
            label3.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-padding: 10;  -fx-spacing: 10 -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0; ");
            label1.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 12px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            Button button = new Button("Select!");
button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

            button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);

                // alert.setContentText("Description : "+evenement.getDescription());
                //don d = new don();
                a = d.getId_don();
                b = d.getId_utilisateur();
                /*Instant instant = d.getDate_ajout().toInstant();
LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
mod_tfdate.setValue(localDate);*/
                mod_tfdescription.setText(d.getDescription());
                mod_tfype.setValue(d.getType());
                mod_URLImage.setText(d.getImage_don());

            });

            hbox.getChildren().addAll(label1, label3, label2, label4, button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
        vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");
    }

    @FXML
    private void aff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affichage_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("supprimer_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aj(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajout_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("affichage_demande_don_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
