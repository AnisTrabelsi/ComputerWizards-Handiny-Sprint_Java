/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Commentaire;
import Entite.Sujet;
import Services.ServiceCommentaire;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
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
        comm = selectedItem;
    }

    @FXML
    private void gettext(MouseEvent event) throws FileNotFoundException, IOException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Attach File");
//        File selectedFile = fileChooser.showOpenDialog(new Stage());
//        if (selectedFile != null) {
//            attachments.add(selectedFile);
//            messageTextArea.setText(selectedFile.getName() + "\n");
//            Image image;
//            image = new Image(selectedFile.toURI().toString());
//            imageview.setImage(image);
//        }
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Image Files", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"}), new FileChooser.ExtensionFilter("JPG", new String[]{"*.jpg"}), new FileChooser.ExtensionFilter("JPEG", new String[]{"*.jpeg"}), new FileChooser.ExtensionFilter("BMP", new String[]{"*.bmp"}), new FileChooser.ExtensionFilter("PNG", new String[]{"*.png"}), new FileChooser.ExtensionFilter("GIF", new String[]{"*.gif"})});

        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp8.1\\htdocs\\Handiny\\src\\images\\" + x + ".jpg";

        //String DBPath = "" + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            messageTextArea.setText(path);
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
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

    @FXML
    private void modifCommentaire(ActionEvent event) {
        modifCommentBtn.setOnAction((ActionEvent e) -> {
            try {
                System.out.println(comm.getId_commentaire());
                System.out.println(comm.getSujet().getId_sujet());
                comm.setContenu_commentaire(commentaire.getText());
                comm.setSujet(sujet);
                comm.setPiecejointe(messageTextArea.getText());
                Date utilDate = new Date();

                // Conversion en java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                comm.setDate_publication(sqlDate);
                System.out.println(comm.toString() + comm.getSujet().getId_sujet());
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
