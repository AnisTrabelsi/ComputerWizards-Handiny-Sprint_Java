/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entities.Reservation_Chauffeur;
import entities.Chauffeur;
import java.sql.SQLException;
import services.ServiceChauffeur;
import services.ServiceReservation_Chauffeur;
/**
 *
 * @author Mehdi
 */
public class Test {
    public static void main(String[] args) throws SQLException {
    
         
          
         
        ///////////////////////////////////// CHAUFFEUR /////////////////////////////////////////////////////////
        
        Chauffeur c1 = new Chauffeur(18, "881144", "AAAA", "AABBB", "OUI");
        ServiceChauffeur SC = new ServiceChauffeur();
        //SC.ajouter(c1);
        //SC.modifier(c1);
        //SC.supprimer(c1);
        System.out.println(SC.recuperer(c1));
        
        ///////////////////////////////////// Reservation CHAUFFEUR /////////////////////////////////////////////////////////
        
        Reservation_Chauffeur E1 = new Reservation_Chauffeur(2,"7/07/2023", "Tunis");
        ServiceReservation_Chauffeur ES = new ServiceReservation_Chauffeur();
        ES.ajouter(E1);
        //ES.modifier(E1);
        //ES.supprimer(E1);
        System.out.println(ES.recuperer(E1));
        
        
    
    
    }
    
}
