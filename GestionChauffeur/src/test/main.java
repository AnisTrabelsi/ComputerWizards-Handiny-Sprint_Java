/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import modules.Chauffeur;
import java.sql.SQLException;
import modules.ReservationChauffeur;
import Services.IServiceChauffeur;
import Services.ServiceChauffeur;
import services.ServiceReservationChauffeur;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.time.Instant;
import java.util.*;
/**
 *
 * @author anis
 */
public class main {

    public static void main(String[] args) {
        Chauffeur p1 = new Chauffeur(1, "aaa", "bbb", "ccc", 0);

        ServiceChauffeur ser = new ServiceChauffeur();
        
        try {
            
            ser.ajouterChauffeur(p1);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        } }
        
         
