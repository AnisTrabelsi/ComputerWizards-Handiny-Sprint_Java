/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Date;

/**
 *
 * @author Chayma
 */
public class SessionManager {

    private static SessionManager instance;
    private static int id_utilisateur;
    private static String nom;
    private static String prenom;
    private static String cin;
    private static String email;
    private static String telephone;
    private static String login;
    private static String mot_de_passe;
    private static Date date_de_naissance;
    private static String region;
    private static String adresse;
    private static int code_postal;
    private static String role;

    private SessionManager(int id_utilisateur, String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, Date date_de_naissance, String region, String adresse, int code_postal, String role) {
        SessionManager.id_utilisateur = id_utilisateur;
        SessionManager.nom = nom;
        SessionManager.prenom = prenom;
        SessionManager.cin = cin;
        SessionManager.email = email;
        SessionManager.telephone = telephone;
        SessionManager.login = login;
        SessionManager.mot_de_passe = mot_de_passe;
        SessionManager.date_de_naissance = date_de_naissance;
        SessionManager.adresse = adresse;
        SessionManager.code_postal = code_postal;
        SessionManager.role = role;
    }

    public static SessionManager getInstance(int id_utilisateur, String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, Date date_de_naissance, String region, String adresse, int code_postal, String role) {
        if (instance == null) {

            instance = new SessionManager(id_utilisateur, nom, prenom, cin, email, telephone, login, mot_de_passe, date_de_naissance, region, adresse, code_postal, role);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;

    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId_utilisateur() {
        return id_utilisateur;
    }

    public static void setId_utilisateur(int id_utilisateur) {
        SessionManager.id_utilisateur = id_utilisateur;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getCin() {
        return cin;
    }

    public static void setCin(String cin) {
        SessionManager.cin = cin;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static void setTelephone(String telephone) {
        SessionManager.telephone = telephone;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        SessionManager.login = login;
    }

    public static String getMot_de_passe() {
        return mot_de_passe;
    }

    public static void setMot_de_passe(String mot_de_passe) {
        SessionManager.mot_de_passe = mot_de_passe;
    }

    public static Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public static void setDate_de_naissance(Date date_de_naissance) {
        SessionManager.date_de_naissance = date_de_naissance;
    }

    public static String getRegion() {
        return region;
    }

    public static void setRegion(String region) {
        SessionManager.region = region;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static int getCode_postal() {
        return code_postal;
    }

    public static void setCode_postal(int code_postal) {
        SessionManager.code_postal = code_postal;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

    public static void CleanUserSession() {
        id_utilisateur = 0;
        nom = "";
        prenom = "";
        cin = "";
        email = "";
        telephone = "";
        login = "";
        mot_de_passe = "";
        Date date = new Date(0);
        date_de_naissance = date;
        region = "";
        adresse = "";
        code_postal = 0;
        role = "";
    }

    @Override
    public String toString() {
        return "SessionManager{" +"id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email=" + email + ", telephone=" + telephone + ", login=" + login + ", mot_de_passe=" + mot_de_passe + ", date_de_naissance=" + date_de_naissance + ", region=" + region + ", adresse=" + adresse + ", code_postal=" + code_postal + ", role=" + role + '}';
        
    }
    
//    public class cleanUserSession{
//        
//         public  cleanUserSession() {
//        id_utilisateur = 0;
//        nom = "";
//        prenom = "";
//        cin = "";
//        email = "";
//        telephone = "";
//        login = "";
//        mot_de_passe = "";
//        Date date = new Date(0);
//        date_de_naissance = date;
//        region = "";
//        adresse = "";
//        code_postal = 0;
//        role = "";
//    }
    
 //   }

  
    

}
