/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Sujet;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class SujetListFXMLController implements Initializable {

    private ObservableList<Sujet> objects = FXCollections.observableArrayList();
    private List<Sujet> sujets;
    @FXML
    private BorderPane bp;
    @FXML
    private Label nbposts;
    @FXML
    private Button commentsReadAllBtn;
    @FXML
    private GridPane TopicGrid;

    ServiceSujet ser = new ServiceSujet();
    int column = 0;
    int row = 1;
    @FXML
    private Button addsujetBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button returnlistcategoriesBtn;

    /**
     * Initializes the controller class.
     */
     public void loadData(){
         try {
            sujets = new ArrayList<>(ser.readAll());
            nbposts.setText(String.valueOf(sujets.size() + " Posts"));
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        sujets.forEach((c) -> {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/GUI/SujetGridFXML.fxml"));
            Pane pane;

            try {
                pane = fxmlloader.load();
                SujetGridFXMLController sujController = fxmlloader.getController();
                sujController.setData(c);

                if (column == 3) {
                    column = 0;

                }
                ++row;
                TopicGrid.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ex) {
                Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();

    }

//    private void handleEditButton(Sujet get) {
////        System.out.println("Edit button clicked for object " + get.getTitre_sujet());
//
//        try {
//            ServiceSujet service = new ServiceSujet();
//            ObservableList liste, liste2;
//            liste = FXCollections.observableArrayList(service.readNoms());
//            id_categorie.setItems(liste);
//            liste2 = FXCollections.observableArrayList(service.getAllTags());
//            tags.setItems(liste2);
//            try {
//                objects.addAll(service.readAll());
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            // Add the objects to the GridPane
//            for (int i = 0; i < objects.size(); i++) {
//                final int index = i; // create a final copy of i
//                Label nameLabel = new Label(objects.get(index).getTitre_sujet());
//                Label descLabel = new Label(dateFormatter.format((TemporalAccessor) objects.get(index).getDate_creation_sujet()));
//                Label descLabe2 = new Label(objects.get(index).getContenu_sujet());
//                Label descLabe3 = new Label(String.valueOf(objects.get(index).getNb_commentaires()));
//                Label descLabe4 = new Label(objects.get(index).getEtat());
//                gridpane.addRow(index, nameLabel, descLabel, descLabe2, descLabe3, descLabe4);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SujetListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }                
//        
//
//    }
//    private void update(ActionEvent event) throws SQLException {
//        updateBtn.setOnAction((ActionEvent e) -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Sujet modifié avec succés!");
//            alert.show();
//        });
//    }

    @FXML
    private void addsujet(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/SujetFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
        TopicGrid.getChildren().clear();
        loadData();
    }

    @FXML
    private void commentsReadAll(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CommentaireListFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieListGridPaneFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void returnlistcategories(MouseEvent event) {
        returnlistcategoriesBtn.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumSideBarFXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.initModality(Modality.APPLICATION_MODAL); 
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(CategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
