/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;

import Entite.don;
import Services.Service_don;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author anis
 */
public class main {

    public static void main(String[] args) {
    
        don p1 = new don(1, "t", "t", "t");

        Service_don ser = new Service_don();
      /*  
        try {
            ser.ajouter_don(p1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
*/
         
        /////////////////////////////////////////////
        List<don> l1 = null;
        try {
            l1 = ser.readAll_don();
       
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println((e.toString())));
//////////////////////////////////////////////////////////////////
        try {
            ser.supprimer_don(43);
        } catch (SQLException exx) {
            System.out.println(exx);
            System.out.println("supp no");
        }

////////////////////////////////////////////////////////////////
        don p3 = new don(43, 1, "xxx", "xxxx", "xxxxxx");
        try {
            ser.modifier_don(p3);
        } catch (SQLException exx) {
            System.out.println(exx);
            System.out.println("mod no xxx");
        }
////////////////////////////////////////////////////////////
        don d2 = new don();
        try {
            d2 = ser.findById_don(43);
        } catch (SQLException exx) {
            System.out.println(exx);
        }
        System.out.println("don de id 5 :" + "description :" + d2.getDescription());
       
        /*****************************************************************************************/
         
      
      /* demande_don d4;
        d4 = new demande_don();


 Date date_demande = new Date(2020,01,16);
d4.setId_utilisateur(1);
d4.setId_demande_don(88);
d4.setId_don(15);
d4.setJustificatif_handicap("jvjhv");
d4.setDate_demande(date_demande);

        Service_demande_don ser1 = new Service_demande_don();
      /*  try {
            ser1.ajouter_demande_don(d4);
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("ajd");
        }

        /////////////////////////////////////////////
        List<demande_don> l7 = null;
        try {
            l7 = ser1.readAll_demande_don();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l7.forEach(e -> System.out.println(e));
//////////////////////////////////////////////////////////////////
        try {
            ser1.supprime_demande_don(1);
        } catch (SQLException exx) {
            System.out.println(exx);
            System.out.println("supp no");
        }

////////////////////////////////////////////////////////////////
       demande_don d8;
        d8 = new demande_don(2,
                 1,
                16,
                new Date(2020,01,17),
                "ggggggggg",
                "ggggggggggg");
        try {
            ser1.update_demande_don(d8);
        } catch (SQLException exx) {
            System.out.println(exx);
            System.out.println("mod no xxx");
        }
////////////////////////////////////////////////////////////
    /*    demande_don d666 = new demande_don();
        try {
            d666 = ser1.findById_demande_don(2);
        } catch (SQLException exx) {
            System.out.println(exx);
        }
        System.out.println("don de id 2 :" + "jutif :" + d666.getDate_demande());
*/
}
}
