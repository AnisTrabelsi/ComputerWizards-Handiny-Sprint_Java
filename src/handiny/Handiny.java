/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package handiny;

import Entite.Categorie;
import Entite.Commentaire;
import Entite.Sujet;
import Entite.Utilisateur;
import Services.ServiceCategorie;
import Services.ServiceCommentaire;
import Services.ServiceSujet;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

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
        ServiceCategorie sercat = new ServiceCategorie();
//        ServiceSujet sersuj = new ServiceSujet();

//        Categorie cat = new Categorie("mmmm");
        try {
            int id= sercat.getIdByName("zz");
            System.out.println(id);
        } catch (SQLException ex) {
            Logger.getLogger(Handiny.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Utilisateur u = new Utilisateur();
//        Sujet suj = new Sujet("ee", "eeee", 4, "rr", "ttt", cat, u);

//        suj.setId_sujet(1);
//        u.setId_utilisateur(1);
//        cat.setId_categorie(78);
//        try {
//            sercat.ajouter(cat);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }        try {
//            sersuj.ajouter(suj);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }

//
//        List<Categorie> l1 = null;
//
//        try {
//            l1 = sercat.readAll();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        l1.forEach(e -> System.out.println(e));
//        
//        try {
//            sersuj.ajouter(suj);
//        } catch (SQLException ex1) {
//            System.out.println(ex1);
//        }
//        List<Sujet> l2 = null;
//
//        try {
//            l2 = sersuj.readAll();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        l2.forEach(e -> System.out.println(e));
//        ServiceSujet cc = new ServiceSujet();
//        try {
//            Sujet s= cc.findById(66);
//            System.out.println(s.getId_sujet());
//        } catch (SQLException ex2) {
//            System.out.println(ex2);
//        }
         try {
            Categorie cat1 = sercat.findById(34);
            System.out.println(cat1.getId_categorie());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }

    }
