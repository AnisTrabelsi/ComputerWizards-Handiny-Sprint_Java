/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.Chauffeur;
import java.sql.SQLException;
import services.ServiceChauffeur;
/**
 *
 * @author Mehdi
 */
public class test {
    public static void main(String[] args) {
    
         
          
         
     try {
            ///////////////////////////////////// CHAUFFEUR ///////////////////////////////////////////////////////// 
           
            Chauffeur c1 = new Chauffeur(1, "109999", "Maamouri", "TUNIS", "OUI");
            ServiceChauffeur SC = new ServiceChauffeur();
            //SC.ajouter(c1);
            //SC.modifier(c1);
            SC.supprimer(c1);
              System.out.println(SC.readAll(c1));
            
            
            
           
            
            
             
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }
    
}
