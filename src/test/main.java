/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Entite.Utilisateur;
import Entite.Voiture;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceUtilisateur;
import services.ServiceVoiture;

/**
 *
 * @author Chaima
 */
public class main {
    
    public static void main(String[] args) {
        Utilisateur u1=new Utilisateur(5,"1","2","3","4","5","6","7","2023/02/16","9","10",11,"12");
        Voiture v1 = new Voiture("78","3","4","5","6","7","8","10",11,new Date(21/12/2000),u1);
        Voiture v2 = new Voiture("88","33","44","55","66","77","88","100",771,new Date(21/12/2000),10);
        
        
        //ServiceUtilisateur sp1 = new ServiceUtilisateur();
        ServiceVoiture sp2 = new ServiceVoiture();
       
        
         try {
            List<Voiture> l = sp2.readAll();
            l.stream().forEach(v->System.out.println(v));
           
             System.out.println("************************"+sp2.findById(7));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
}
