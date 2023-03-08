package Controllers;

import Entite.Covoiturage;
import Services.ServiceCovoiturage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewClass implements Initializable {

    @FXML
    private Button button;

    private VBox vb;


    HBox column1 = new HBox();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        List<Covoiturage> l = new ArrayList<Covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        for (Covoiturage d : l) {
            HBox hbox = new HBox();
            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
            Label label4 = new Label(String.valueOf(d.getPrix()));
            Label label5 = new Label(d.getDate_covoiturage().toString());
            hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            hbox.getChildren().addAll(label1, label2, label3, label4, label5);
            vb.getChildren().add(hbox);
        }
    }

   
}
