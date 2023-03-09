/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.PostsSauvegardés;
import Entite.Sujet;
import Entite.Utilisateur;
import Services.ServiceSauvegarde;
import Services.ServiceSujet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class PostsSauvegardésFXMLController implements Initializable {

    @FXML
    private Label nbposts;
    @FXML
    private Button AjoutSujetBtn;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private GridPane gridpane;
    ServiceSauvegarde ser = new ServiceSauvegarde();
    int column = 0;
    int row = 1;
    private List<PostsSauvegardés> sauvegardes;
    Utilisateur u = Utilisateur.getCurrent_user();
    ServiceSujet ss = new ServiceSujet();
    /**
     * Initializes the controller class.
     */
    public void loadData() throws SQLException {
        try {
            sauvegardes = new ArrayList<>(ser.readAllByUser(u));
            nbposts.setText(String.valueOf(sauvegardes.size() + " Sauvegardes"));
            System.out.println(nbposts.getText());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        for (PostsSauvegardés c : sauvegardes) {
            try {
                System.out.println("hi " + c.toString());
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui_handiny/SujetGridFXML.fxml"));
                Pane pane;

                System.out.println(c.getUser().getId_utilisateur());
                System.out.println(c.getSujet().getId_sujet());

                SujetGridFXMLController sujController = fxmlloader.getController();
                pane = fxmlloader.load();
                Sujet s = ss.findById(c.getSujet().getId_sujet());
                if (s==null){System.out.println("s est null");}
                else {System.out.println("s n'est pas null"+s.getCat().getNom_categorie());}
                sujController.setData2(c);
                if (column == 3) {
                    column = 0;
                }
                ++row;
                gridpane.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(PostsSauvegardésFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjoutSujetBtn(ActionEvent event) {
    }

}
