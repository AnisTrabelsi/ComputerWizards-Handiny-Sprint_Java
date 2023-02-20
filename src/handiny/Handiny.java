/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package handiny;
import Entite.Reclamation;
import Entite.Utilisateur;
import Services.ServiceReclamation;
import Services.ServiceUtilisateur;
import Utils.DataSource;
import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *  
      }
    }
 * @author Chayma
 */
public class Handiny {


    public static void main(String[] args) {
  
        Connection con =DataSource.getInstance().getConnection();
        Date d1= new Date(21/12/2000);
        
    ServiceReclamation serr = new ServiceReclamation();
    ServiceUtilisateur seru = new ServiceUtilisateur();

 Utilisateur u = new Utilisateur(1, "Chayma", "Ben Saad", "1436274", "chayma.bensaad@gmail.com", "25964725", "cha", "chgrtyul", d1, "Tunis", "Bardo", 2000, "Utilisateur");  
 u.setId_utilisateur(1);
   
 Reclamation r = new Reclamation ( 1,"kygyg", "en cours", "hvhj",u);
 r.setId_reclamation(1);

// try {
//            seru.ajouter(u);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }

//    try {
//            seru.supprime(u);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//      }
//try {            seru.readAll();
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
//  try {    seru.update(u);
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
//       } 
//    
//    try {   serr.supprime(r) ;
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
//       
//    try {   serr.update(r); 
//       } catch (SQLException ex) {
//           System.out.println(ex);
//       }
//    
//   try {   serr.readAll(); 
//      } catch (SQLException ex) {
//         System.out.println(ex);
//      }

   try {    
       System.out.println(serr.findById(2));
       } catch (SQLException ex) {
           System.out.println(ex);
       } 
   
    }
    
}


    


