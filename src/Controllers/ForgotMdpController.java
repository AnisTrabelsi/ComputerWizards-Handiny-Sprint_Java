package Controllers;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ForgotMdpController implements Initializable {
     Utilisateur u = new Utilisateur();

    Stage Stage2;
    @FXML
    private TextField email_entred;
    @FXML
    private TextField code_entred;
    @FXML
    private Button Reset;
    @FXML
    private Label labelemail;
  //  Utilisateur current_user;
   // Utilisateur user = new Utilisateur ();
    ServiceUtilisateur us = new ServiceUtilisateur();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        code_entred.setDisable(true);
        Reset.setDisable(true);
    }

    @FXML
    private void sendcode(ActionEvent event) throws SQLException {
      //user=  us.findById(3);
        email_entred.setStyle("-fx-border-color:WHITE");
        if (us.verifierEmailBd(email_entred.getText())) {
            String e =email_entred.getText();
            code_entred.setDisable(false);
            Reset.setDisable(false);
            labelemail.setText("");
            labelemail.setText("un code a été envoyé a votre Email, retapez-le");
            labelemail.setTextFill(Color.BLUE);
            String code = us.getAlphaNumericString(8);
           
            u.setTargetid(ServiceUtilisateur.getIdByEmail(e));
            u.setCode(code);
            System.out.println(code);
           
     //Utilisateur currentUser = Utilisateur.getCurrent_user();

            if (u != null) {
             
                String email = email_entred.getText();

                us.updateCode(u.getCode(), u.getTargetid());
                System.out.println(u.getCode());
                ///String cn = "Saissisez ce code pour réinitialiser votre mot de passe : " + Utilisateur.getCurrent_user().getCode();
                String cn = "Saissisez ce code pour réinitialiser votre mot de passe : " + u.getCode();
                String sb = "Mot de passe oublié";
                SendMail.sendMail(email, sb, cn);
            } else {
                email_entred.setStyle("-fx-border-color:RED");
        labelemail.setText("email inexistant");
        labelemail.setTextFill(Color.RED);
            }
                    //user = us.findById(user.getTargetid());

        }
    }

    @FXML
    private void Reset_onclick(ActionEvent event) throws SQLException {

        System.out.println(u);
        if (u.getCode().equals(code_entred.getText())) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/RESET.fxml"));
                Stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                root.setOnMousePressed(pressEvent -> {
                    root.setOnMouseDragged(dragEvent -> {
                        Stage2.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                        Stage2.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                    });
                });
                Scene scene = new Scene(root);
                Stage2.setScene(scene);
                Stage2.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            labelemail.setText("code incorrect");
            labelemail.setTextFill(Color.RED);
        }

    }
}
