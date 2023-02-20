/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package handiny;

import Entite.Categorie;
import Entite.Sujet;
import Entite.Utilisateur;
import Services.ServiceCategorie;
import Services.ServiceSujet;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bengh
 */
public class Handiny {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/esprit";
    private static String login = "root";
    private static String pwd = "";
    private static Statement ste;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        java.util.Date dateCreation = null;
        try {
            dateCreation = new SimpleDateFormat("dd-MM-yyyy").parse("10-11-2023");
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        java.sql.Date sqlDateCreation = new java.sql.Date(dateCreation.getTime());
        Categorie c1 = new Categorie(20,"Adoption", sqlDateCreation, 5);
        ServiceCategorie ser = new ServiceCategorie();
//
//        try {
//            ser.ajouter(c1);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
        Categorie c2 = new Categorie(2, "Adoption", sqlDateCreation, 6);
//        try {
//            ser.update(c2);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }

        try {
            ser.supprime(c1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        List<Categorie> l1 = null;

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e));
        System.out.println("****************************************************************************************");
        Utilisateur u1 = new Utilisateur("gg", "ff", "12345678", "kflfe", "45877777", "fffff", "rrrr", sqlDateCreation, "eee", "rrrr", 1002, "Locataire");
        Sujet s1 = new Sujet("Bonjour",sqlDateCreation, sqlDateCreation, "eeeeee", 3, "ouvert", "hashtag", c1, u1);
        
        ServiceSujet ser1 = new ServiceSujet();

        try {
            ser1.ajouter(s1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        List<Sujet> ls = null;

        try {
            ls = ser1.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ls.forEach(e -> System.out.println(e));
        
        
    }

}
