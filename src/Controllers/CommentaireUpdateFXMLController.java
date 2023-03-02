/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Entite.Sujet;
import Services.ServiceCommentaire;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bengh
 */
public class CommentaireUpdateFXMLController implements Initializable {
    
    FileChooser fileChooser = new FileChooser();
    private Sujet sujet = new Sujet();
    
    @FXML
    private TextField commentaire;
    @FXML
    private ImageView imageview;
    @FXML
    private Button file;
    @FXML
    private Button modifCommentBtn;
    @FXML
    private Button EffacerBtn;
    @FXML
    private Label messageTextArea;
    
    ServiceCommentaire sc = new ServiceCommentaire();
    private ArrayList<File> attachments = new ArrayList<>();
    Commentaire comm = new Commentaire();

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new FileChooser.ExtensionFilter("JPG", new String[]{"*.jpg"}), new FileChooser.ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new FileChooser.ExtensionFilter("BMP", new String[]{"*.bmp"}), new FileChooser.ExtensionFilter("PNG", new String[]{"*.png"}), new FileChooser.ExtensionFilter("GIF", new String[]{"*.gif"})});
        
    }
    
    void initData(Commentaire selectedItem) {
        commentaire.setText(selectedItem.getContenu_commentaire());
        messageTextArea.setText(selectedItem.getPiecejointe());
      String path = selectedItem.getPiecejointe();

        File file = new File(path);
        if (path != null) {
            // ajustez la taille de l'image ici

            ImageView image = new ImageView(new Image(file.toURI().toString()));
            imageview.setImage(image.getImage());
        }
//        sujet.setId_sujet(selectedItem.getSujet().getId_sujet());
//        System.out.println(sujet.getId_sujet());
    }
    
    @FXML
    private void gettext(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Attach File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            attachments.add(selectedFile);
            messageTextArea.setText(selectedFile.getName() + "\n");
            Image image;
            image = new Image(selectedFile.toURI().toString());
            imageview.setImage(image);
        }
    }
    
    @FXML
    private void modifCommentaire(ActionEvent event) {
        modifCommentBtn.setOnAction((ActionEvent e) -> {
            try {
                System.out.println(sujet.toString());
                comm.setContenu_commentaire(commentaire.getText());
                comm.setSujet(sujet);
                comm.setPiecejointe(messageTextArea.getText());
                Date utilDate = new Date();

                // Conversion en java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                comm.setDate_publication(sqlDate);
                System.out.println(comm.toString()+comm.getSujet().getId_sujet());
                sc.update(comm);
                System.out.println(comm.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                
                alert.setTitle("Information Dialog");
                
                alert.setHeaderText(null);
                
                alert.setContentText("Commentaire modifié avec succés!");
                
                alert.show();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        );
    }
    
    @FXML
    private void clearCommentaire(ActionEvent event) {
        commentaire.setText("");
        Stage stage = (Stage) EffacerBtn.getScene().getWindow();
        stage.close();
    }
    
}
