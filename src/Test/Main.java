/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Covoiturage;
import Services.ServiceCovoiturage;
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
    public static void main(String[] args) {
        // TODO code application logic here

         Covoiturage c3 = new Covoiturage(7,1 ,"rades ", "gahzela","22-02-2020",5,7);

        ServiceCovoiturage ser = new ServiceCovoiturage();

       /* try {
            ser.ajouter(c3);
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/


         Covoiturage c2 = new Covoiturage(7,1 ,"rades ", "gahzela","22-02-2021"  ,5,5);

        
        try {
            ser.ajouter(c2);
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        try{
                    ser.update(c2);
        }catch (SQLException ex) {
            System.out.println(ex);}
        
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
