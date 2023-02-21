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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private TableColumn<Voiture, String> immat;
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
   //public ObservableList<Voiture> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Voiture, String> editCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void afficherVoitures(ActionEvent event) throws IOException, SQLException {
 
           
       ServiceVoiture sv= new ServiceVoiture();
      List<Voiture> voitures= sv.readAll();
      ObservableList ObList = FXCollections.observableList(voitures);
      tableVoitures.setItems(ObList);
      immat.setCellValueFactory(new PropertyValueFactory<Voiture, String>("immatriculation"));
       modele.setCellValueFactory(new PropertyValueFactory<Voiture, String>("marque"));
       marque.setCellValueFactory(new PropertyValueFactory<Voiture, String>("marque"));
       boiteV.setCellValueFactory(new PropertyValueFactory<Voiture, String>("boite_vitesse"));
       dateV.setCellValueFactory(new PropertyValueFactory<Voiture, String>("date_validation_technique"));
       kilomet.setCellValueFactory(new PropertyValueFactory<Voiture, String>("kilometrage"));
       carburant.setCellValueFactory(new PropertyValueFactory<Voiture, String>("carburant"));
       prixLoc.setCellValueFactory(new PropertyValueFactory<Voiture, Double>("prix_location"));
       desc.setCellValueFactory(new PropertyValueFactory<Voiture, String>("description"));
        tableVoitures.setItems(ObList);
        //add cell of button edit 
         Callback<TableColumn<Voiture, String>, TableCell<Voiture, String>> cellFoctory = (TableColumn<Voiture, String> param) -> {
            // make cell containing buttons
            final TableCell<Voiture, String> cell = new TableCell<Voiture, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button();
                        Button editIcon = new Button();

                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Voiture v = tableVoitures.getSelectionModel().getSelectedItem();
                            ServiceVoiture sc= new ServiceVoiture();
                            try {
                                sv.supprime(v);
                            } catch (SQLException ex) {
                                Logger.getLogger(Win1Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Voiture student = tableVoitures.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/gui/InsertVoitures.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                            
                            /*InsertVoituresController addStudentController = loader.getController();
                            InsertVoituresController.setUpdate(true);
                           InsertVoituresController.setTextField(student.getId(), student.getName(), 
                                    student.getBirth().toLocalDate(),student.getAdress(), student.getEmail());*/
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                      

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         //tableVoitures.setItems(obList);
         
         
    }}
       
       
        
   
   
    
    
   
    

           
