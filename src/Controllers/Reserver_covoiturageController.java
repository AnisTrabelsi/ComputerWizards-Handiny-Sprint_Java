/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Covoiturage;
import Entite.reservation_covoiturage;
import Entite.Utilisateur;
import Services.ServiceCovoiturage;
import Services.Service_reservation_cov;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abbes
 */
public class Reserver_covoiturageController implements Initializable {
    Utilisateur u=Utilisateur.getCurrent_user();

   @FXML
    private Button button;
    @FXML
    private VBox vb;
HBox column1 = new HBox();
    private static int a,c,h;
    private static String e,f; 
 List<reservation_covoiturage> l1 = null;
public static final String ACCOUNT_SID = "AC2cf3b48480710e75f659adfb8932f0f2";
  // Your Auth Token from twilio.com/console
  public static final String AUTH_TOKEN = "830a31796d5e9844b56c19cb24d4ea09";
    @FXML
    private TextField rech;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filterCovoiturages(newValue);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    });
        
        
        vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);
      //  Utilisateur u = new Utilisateur(3, "abbes", "benabbes", "24076282", "mail", "+21624076282", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

        List<Covoiturage> l = new ArrayList<Covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
        Service_reservation_cov serv = new Service_reservation_cov() ; 
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Covoiturage d : l) {

        
            HBox hbox = new HBox();

            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
Label label4 = new Label(String.valueOf(d.getPrix()));
           label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label5 = new Label(d.getDate_covoiturage().toString());
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
        Button button2 = new Button("Reserver !");
                                     button2.setStyle("-fx-background-color: #596643 ; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId_cov();
 c =    d.getNbrplace();
e = d.getDepart(); 
 f = d.getDestination();

        h = u.getId_utilisateur() ;
    
try {
    reservation_covoiturage  k = new reservation_covoiturage(a,u.getId_utilisateur(),c , e ,f,d.getNom(),d.getTelephone()) ; 
 l1 =  serv.find_reservation_cov_of_user_rech(d.getId_cov(), u.getId_utilisateur()) ; 
 
 if(l1.size() > 0) { 
     Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous êtes deja inscrit dans ce covoiturage");
alertt.showAndWait(); 

  return;
 } else
     if (u.getId_utilisateur() == d.getId_utilisateur()) { 
         Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous avez pas le droit de reserver dans votre covoiturage !! ");
alertt.showAndWait(); 

  return;
     }
    
if ( d.getNbrplace() <=	0) { 
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText("covoiturage complet");

// Add custom CSS stylesheet to change the color of the alert box
DialogPane dialogPane = alertt.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("reserver_covoiturage.css").toExternalForm());
dialogPane.getStyleClass().add("my-alert");

alertt.showAndWait();                     return;
}else {

                     serv.ajouter_reservation_cov(k);
                   sendSms(d.getTelephone() ,"salut" + d.getNom() +  "vous avez une nouvelle reservation sur votre covoiturage") ;

                     JOptionPane.showMessageDialog(null, "votre réservation est confirmée"); }
    button2.setVisible(false);

                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
            //   actualiser();  
 
});

       hbox.getChildren().addAll(label1, label2, label3, label4 , label5 , button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

     @FXML
    private void trier() {
         vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);
    //   utilisateur u = new utilisateur(3, "abbes", "benabbes", "24076282", "mail", "+21624076282", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

        List<Covoiturage> l = new ArrayList<Covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
        Service_reservation_cov serv = new Service_reservation_cov() ; 
        try {
            l = ser.sortbydate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Covoiturage d : l) {

        
            HBox hbox = new HBox();

            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
Label label4 = new Label(String.valueOf(d.getPrix()));
           label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label5 = new Label(d.getDate_covoiturage().toString());
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
        Button button2 = new Button("Reserver !");
                                     button2.setStyle("-fx-background-color: #596643 ; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId_cov();
 c =    d.getNbrplace();
e = d.getDepart(); 
 f = d.getDestination();

        h = u.getId_utilisateur() ;
    
 
try {
    reservation_covoiturage  k = new reservation_covoiturage(a,u.getId_utilisateur(),c , e ,f,d.getNom(),d.getTelephone()) ; 
 l1 =  serv.find_reservation_cov_of_user_rech(d.getId_cov(), u.getId_utilisateur()) ; 
 
 if(l1.size() > 0) { 
     Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous êtes deja inscrit dans ce covoiturage");
alertt.showAndWait(); 

  return;
 } else
    
if ( d.getNbrplace() <=	0) { 
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText("covoiturage complet");

// Add custom CSS stylesheet to change the color of the alert box
DialogPane dialogPane = alertt.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("reserver_covoiturage.css").toExternalForm());
dialogPane.getStyleClass().add("my-alert");

alertt.showAndWait();                     return;
}else {

                     serv.ajouter_reservation_cov(k);
                                       sendSms(d.getTelephone() ,"salut" + d.getNom() +  "vous avez une nouvelle reservation sur votre covoiturage") ;

                     JOptionPane.showMessageDialog(null, "votre réservation est confirmée"); }
    button2.setVisible(false);

                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
            //   actualiser();  
 
});

       hbox.getChildren().addAll(label1, label2, label3, label4 , label5 , button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }



    
     @FXML
    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/ajout_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/modifier_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goreserv(ActionEvent event) throws IOException {
    
 Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/Mes_reservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
  
}
    
    public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+216"+recipient), // To number
            new PhoneNumber("++15673716202"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }

    private boolean does_reservation_cov_exist(int id_utilisateur, int id_cov) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       private void filterCovoiturages(String searchValue) throws SQLException {
    vb.getChildren().clear(); // Clear the VBox to remove all previous entries
    //    utilisateur u = new utilisateur(5, "abbes", "benabbes", "24076282", "mail", "+21624076282", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

    ServiceCovoiturage ser = new ServiceCovoiturage();
    List<Covoiturage> l = ser.readAll();
        Service_reservation_cov serv = new Service_reservation_cov() ; 
    for (Covoiturage d : l) {
        if (d.getDepart().toLowerCase().contains(searchValue.toLowerCase())
                || d.getDestination().toLowerCase().contains(searchValue.toLowerCase())) {
            HBox hbox = new HBox();
            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
            Label label4 = new Label(String.valueOf(d.getPrix()));
            Label label5 = new Label(d.getDate_covoiturage().toString());

             hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
        Button button2 = new Button("Reserver !");
                                     button2.setStyle("-fx-background-color: #596643 ; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId_cov();
 c =    d.getNbrplace();
e = d.getDepart(); 
 f = d.getDestination();

        h = u.getId_utilisateur() ;
    

try {
    reservation_covoiturage  k = new reservation_covoiturage(a,u.getId_utilisateur(),c , e ,f,d.getNom(),d.getTelephone()) ; 
 l1 =  serv.find_reservation_cov_of_user_rech(d.getId_cov(), u.getId_utilisateur()) ; 
 
 if(l1.size() > 0) { 
     Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous êtes deja inscrit dans ce covoiturage");
alertt.showAndWait(); 

  return;
 } else
    
if ( d.getNbrplace() <=	0) { 
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText("covoiturage complet");

// Add custom CSS stylesheet to change the color of the alert box
DialogPane dialogPane = alertt.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("reserver_covoiturage.css").toExternalForm());
dialogPane.getStyleClass().add("my-alert");

alertt.showAndWait();                     return;
}else {

                     serv.ajouter_reservation_cov(k);
                                        sendSms(d.getTelephone() ,"salut" + d.getNom() +  "vous avez une nouvelle reservation sur votre covoiturage") ;

                     JOptionPane.showMessageDialog(null, "votre réservation est confirmée"); }
    button2.setVisible(false);

                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
            //   actualiser();  
 
});

       hbox.getChildren().addAll(label1, label2, label3, label4 , label5 , button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }
    }
       
   @FXML
     private void Actualiser() {
        vb.getChildren().clear();
                rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filterCovoiturages(newValue);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    });
        
        
        vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);
      //    utilisateur u = new utilisateur(3, "abbes", "benabbes", "24076282", "mail", "+21624076282", "kbikjb", "kbikjb", new Date(2020, 15, 01), "kbikjb", 2086, "kbikjb", "kbikjb");

        List<Covoiturage> l = new ArrayList<Covoiturage>();
        ServiceCovoiturage ser = new ServiceCovoiturage();
        Service_reservation_cov serv = new Service_reservation_cov() ; 
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Covoiturage d : l) {

        
            HBox hbox = new HBox();

            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
Label label4 = new Label(String.valueOf(d.getPrix()));
           label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label5 = new Label(d.getDate_covoiturage().toString());
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
        Button button2 = new Button("Reserver !");
                                     button2.setStyle("-fx-background-color: #596643 ; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button2.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId_cov();
 c =    d.getNbrplace();
e = d.getDepart(); 
 f = d.getDestination();

        h = u.getId_utilisateur() ;
    

try {
    reservation_covoiturage  k = new reservation_covoiturage(a,u.getId_utilisateur(),c , e ,f,d.getNom(),d.getTelephone()) ; 
 l1 =  serv.find_reservation_cov_of_user_rech(d.getId_cov(), u.getId_utilisateur()) ; 
 
 if(l1.size() > 0) { 
     Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText(" vous êtes deja inscrit dans ce covoiturage");
alertt.showAndWait(); 

  return;
 } else
    
if ( d.getNbrplace() <=	0) { 
Alert alertt = new Alert(Alert.AlertType.ERROR);
alertt.setHeaderText(null);
alertt.setContentText("covoiturage complet");

// Add custom CSS stylesheet to change the color of the alert box
DialogPane dialogPane = alertt.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("reserver_covoiturage.css").toExternalForm());
dialogPane.getStyleClass().add("my-alert");

alertt.showAndWait();                     return;
}else {

                     serv.ajouter_reservation_cov(k);
                                        sendSms(d.getTelephone() ,"salut" + d.getNom() +  "vous avez une nouvelle reservation sur votre covoiturage") ;

                     JOptionPane.showMessageDialog(null, "votre réservation est confirmée"); }
    button2.setVisible(false);

                 } catch (SQLException ex) {
                     Logger.getLogger(Affichage_covoiturageController.class.getName()).log(Level.SEVERE, null, ex);
                 } 
            //   actualiser();  
 
});

       hbox.getChildren().addAll(label1, label2, label3, label4 , label5 , button2);
            hbox.setSpacing(60);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       
}
     
}
   
        
   

