/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Chayma
 */
public class Utilisateur {
        private int id_utilisateur;
        private String nom ;
        private String prenom ;
        private String cin ;
        private String email ;
        private String telephone ;
        private String login ;
        private String mot_de_passe ;
        private Date date_de_naissance ;
        private String pays ;
        private String adresse ;
        private int code_postal ;
        private String role ;
        public Utilisateur(){}

    public Utilisateur(int id_utilisateur, String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, Date date_de_naissance, String pays, String adresse, int code_postal, String role) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naissance = date_de_naissance;
        this.pays = pays;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, Date date_de_naissance, String pays, String adresse, int code_postal, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naissance = date_de_naissance;
        this.pays = pays;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.role = role;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email=" + email + ", telephone=" + telephone + ", login=" + login + ", mot_de_passe=" + mot_de_passe + ", date_de_naissance=" + date_de_naissance + ", pays=" + pays + ", adresse=" + adresse + ", code_postal=" + code_postal + ", role=" + role + '}';
    }
        
     

}
