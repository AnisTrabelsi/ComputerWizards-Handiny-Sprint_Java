/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Covoiturage;
import Entite.reservation_covoiturage;
import Entite.utilisateur;
import Services.ServiceCovoiturage;
import Services.Service_reservation_cov;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author achrafzribi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
          //      utilisateur u = new utilisateur(1, "mohamed", "benabbes", "09638420", "abbes525@gmail.com", "24076282", "aaa", "aaaa", new Date(2020, 25, 01), "tounes", 2086, "user", "sdsdsd");

         Covoiturage c3 = new Covoiturage(3,1 ,"rades ", "gahzela","2222-02-02",5,7);
       //  reservation_covoiturage c8 = new reservation_covoiturage(c3.getId_cov(),1 ,c3.getPrix(), c3.getDepart(),c3.getDestination());

        ServiceCovoiturage ser = new ServiceCovoiturage();
        Service_reservation_cov serv = new Service_reservation_cov();

        try {
            ser.ajouter(c3);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
     /*     try {
        serv.ajouter_reservation_cov(c8);
 } catch (SQLException ex) {
            System.out.println(ex);
                        System.out.println("letsgoooo");

        } */

         //Covoiturage c2 = new Covoiturage(7,1 ,"rades ", "gahzela","22-02-2021"  ,5,5);

        
     /*   try {
            ser.ajouter(c2);
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        try{
                    ser.update(c2);
        }catch (SQLException ex) {
            System.out.println(ex);}
       */ 
    //   try{
      //            ser.supprime(c2);
       // }catch (SQLException ex) {
         //   System.out.println(ex);}
       
        
        List<Covoiturage> l1 = null;

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> {
            System.out.println(e);
        });
    }

}
