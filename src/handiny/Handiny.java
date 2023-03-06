/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package handiny;

import Entite.Reclamation;
import Entite.Utilisateur;
//import GUI.handinyFX;
import Services.ServiceReclamation;
import Services.ServiceUtilisateur;
import Utils.DataSource;
import java.awt.Image;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Handiny extends Application {

    public static void main(String[] args) {

        Connection con = DataSource.getInstance().getConnection();
        Date d1 = new Date(21 / 12 / 2000);
//
      ServiceReclamation serr = new ServiceReclamation();
    ServiceUtilisateur seru = new ServiceUtilisateur();
//
  Utilisateur u = new Utilisateur( 8,"Chayma", "Ben Saad", "1436274", "chayma.bensaad@gmail.com", "25964725", "cha", "chgrtyul", d1, "Tunis", "Bardo", 2000, "Locataire");
//   u.setId_utilisateur(3);
//////
        Reclamation r = new Reclamation(1,3, "Couvoiturage", "En cours de traitement ", "Ce n'etait pas ponctuel", "rrrrr");
////        Reclamation r1 = new Reclamation(19, "hdhdhdudj", "traité", "kkdkkdll ", u);
//
//        //r.setId_reclamation(1);
//
//        try {
//            seru.ajouter(u);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    

//    try {
//            seru.supprime(u);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//      }
//    try {            
//           seru.readAll();
//       }   catch (SQLException ex) {
//           System.out.println(ex);
//       }
//   try {    seru.update(u);
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }  
//   try {    
//       System.out.println(seru.findById(1));
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       } 
//  
//  
//  try {   serr.ajouter(r);
//       } catch (SQLException ex) {
//           System.out.println(ex);
//           System.out.println(r);
//       } 
//        try {   serr.supprime(r) ;
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
////       
//    try {   serr.update(r); 
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
//    
//   try {   serr.readAll(); 
//      } catch (SQLException ex) {
//         System.out.println(ex);
//      }
//   try {    
//       System.out.println(serr.findById(2));
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
//   try {    
//       System.out.println(serr.findById_Utilisateur(3));
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root;
//     try {
//
//     root=FXMLLoader.load(getClass().getResource("/gui_handiny/ajouter_reclamation.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Ajouter reclamation");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//
// try {
//            Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/front.fxml"));
//            Scene scene = new Scene(root);    
//            primaryStage.setTitle("Handiny");
//            primaryStage.setScene(scene);
//            //  Image icon = new Image("/images/logo.png") {};
//
//        // Ajouter l'icône au Primary Stage
//     //   primaryStage.getIcons().add();
//        
//            primaryStage.show(); 
//            
//            
//           
//        } catch (IOException ex) {
//            Logger.getLogger(Handiny.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    try {
//
//     root=FXMLLoader.load(getClass().getResource("/gui_handiny/updatereclamation.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Mettre a jour  reclamation");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//     catch (IOException ex) {
//      System.out.println(ex.getMessage());  }
//        try {
//            root = FXMLLoader.load(getClass().getResource("/gui_handiny/Inscription.fxml"));
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("Inscription Handiny");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        
//   try {
//            root = FXMLLoader.load(getClass().getResource("/gui_handiny/espace_user.fxml"));
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("Inscription Handiny");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }


//   try {
//            root = FXMLLoader.load(getClass().getResource("/gui_handiny/admin_reclamation.fxml"));
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("Inscription Handiny");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

  try {

    root=FXMLLoader.load(getClass().getResource("/gui_handiny/Authentification.fxml"));        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Authentification Handiny ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        catch (IOException ex) {
            System.out.println(ex.getMessage());  }


//  try {
//
//    root=FXMLLoader.load(getClass().getResource("/gui_handiny/Recuperer_mdp.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Recuperation mot de passe Handiny ");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//
//


//
//try {
//
//     root=FXMLLoader.load(getClass().getResource("/gui_handiny/Affichage_admin.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Affichage admin Handiny ");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//    

//try {
//
//     root=FXMLLoader.load(getClass().getResource("/gui_handiny/Modifier_profil_user.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Paramétres de profil Handiny ");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//

//try {
//
//     root=FXMLLoader.load(getClass().getResource("/gui_handiny/anis.fxml"));        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("home ");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());  }
//

  }


}
}







