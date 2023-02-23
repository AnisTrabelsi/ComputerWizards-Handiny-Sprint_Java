/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import Entite.Voiture;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceVoiture;


/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class Win1Controller implements Initializable {
    @FXML
    private Button bouttonInsert;
    @FXML
    private TableView<Voiture> tableVoitures;
    @FXML
    private TableColumn<Voiture, String> modele;
    @FXML
    private TableColumn<Voiture, String> marque;
    @FXML
    private TableColumn<Voiture, String> boiteV;
    @FXML
    private TableColumn<Voiture, String> dateV;
    @FXML
    private TableColumn<Voiture, String> kilomet;
    @FXML
    private TableColumn<Voiture, String> carburant;
    @FXML
    private TableColumn<Voiture, Double> prixLoc;
    @FXML
    private TableColumn<Voiture, String> desc;
    public ObservableList<Voiture> VoituresList=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficherVoitures();
        } catch (IOException ex) {
            Logger.getLogger(Win1Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Win1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void insertButton(ActionEvent event) throws IOException {
 
           try{
       FXMLLoader fxmloader= new FXMLLoader(getClass().getResource("/gui/InsertVoitures.fxml"));
       Parent root1= (Parent) fxmloader.load();
       Stage stage=new Stage();
       
        stage.setTitle("Insert page");
        stage.setScene(new Scene(root1));
        stage.show();
       }catch(Exception e){
       e.printStackTrace();
       }
    }
     @FXML
    private void refreshTable() throws SQLException {
         VoituresList.clear();
      ServiceVoiture sv= new ServiceVoiture();
      List<Voiture> voitures= sv.readAll();
      //ObservableList ObList = FXCollections.observableList(voitures);
      VoituresList=FXCollections.observableArrayList(voitures);
      tableVoitures.setItems(VoituresList);   
    }

    
   

    private void afficherVoitures() throws IOException, SQLException {
 
      refreshTable();     
       
     
       modele.setCellValueFactory(new PropertyValueFactory<Voiture, String>("marque"));
       marque.setCellValueFactory(new PropertyValueFactory<Voiture, String>("modele"));
       boiteV.setCellValueFactory(new PropertyValueFactory<Voiture, String>("boite_vitesse"));
       dateV.setCellValueFactory(new PropertyValueFactory<Voiture, String>("date_validation_technique"));
       kilomet.setCellValueFactory(new PropertyValueFactory<Voiture, String>("kilometrage"));
       carburant.setCellValueFactory(new PropertyValueFactory<Voiture, String>("carburant"));
       prixLoc.setCellValueFactory(new PropertyValueFactory<Voiture, Double>("prix_location"));
       desc.setCellValueFactory(new PropertyValueFactory<Voiture, String>("description"));
       
        TableColumn<Voiture, Void> actionsColumn = new TableColumn<>("Actions");
        tableVoitures.getColumns().add(actionsColumn);
        actionsColumn.setMinWidth(100);
        actionsColumn.setCellFactory(new Callback<TableColumn<Voiture, Void>, TableCell<Voiture, Void>>() {
    @Override
    public TableCell<Voiture, Void> call(TableColumn<Voiture, Void> column) {
        final TableCell<Voiture, Void> cell = new TableCell<Voiture, Void>() {
            private final Button editButton = new Button("Editer");
            private final Button deleteButton = new Button("Supprimer");

            {
                editButton.setOnAction((ActionEvent event) -> {
                    Voiture car = getTableView().getItems().get(getIndex());
                    openEditWindow(car);
                });

                deleteButton.setOnAction((ActionEvent event) -> {
                    Voiture car = getTableView().getItems().get(getIndex());
                    deleteCar(car);
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(editButton, deleteButton);
                    hbox.setSpacing(5);
                    setGraphic(hbox);
                }
            }

            private void openEditWindow(Voiture car) {
                
                try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InsertVoitures.fxml"));
        Parent root = (Parent) loader.load();
        InsertVoituresController controller = loader.getController();
        controller.initData(car);
        Stage stage = new Stage();
        stage.setTitle("Editer une voiture");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(Win1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
            }
            @FXML
private void deleteCar(Voiture car) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Voulez-vous vraiment supprimer cette voiture ?");
    alert.setContentText(car.toString());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        ServiceVoiture sv = new ServiceVoiture();
        try {
            sv.supprime(car);
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(Win1Controller.class.getName()).log(Level.SEVERE, null, ex);
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Erreur lors de la suppression de la voiture.");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
    }
}
        };
        return cell;
        
    }
});
         
        

        
    }

}
       
        
   
   
    
    
   
    

           
