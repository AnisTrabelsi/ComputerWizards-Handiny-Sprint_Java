/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author bengh
 */
public class PostsSauvegardés {
    private int id_post_sauvegarde;
    private Utilisateur user;
    private Sujet sujet;
    private Date date_ajout_sauvegarde;

    public PostsSauvegardés(int id_post_sauvegarde, Utilisateur user, Sujet sujet, Date date_ajout_sauvegarde) {
        this.id_post_sauvegarde = id_post_sauvegarde;
        this.user = user;
        this.sujet = sujet;
        this.date_ajout_sauvegarde = date_ajout_sauvegarde;
    }

    public PostsSauvegardés(Utilisateur user, Sujet sujet, Date date_ajout_sauvegarde) {
        this.user = user;
        this.sujet = sujet;
        this.date_ajout_sauvegarde = date_ajout_sauvegarde;
    }
    public PostsSauvegardés(Utilisateur user, Sujet sujet) {
        this.user = user;
        this.sujet = sujet;
    }

    public PostsSauvegardés() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_post_sauvegarde() {
        return id_post_sauvegarde;
    }

    public Utilisateur getUser() {
        return user;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public Date getDate_ajout_sauvegarde() {
        return date_ajout_sauvegarde;
    }

    public void setId_post_sauvegarde(int id_post_sauvegarde) {
        this.id_post_sauvegarde = id_post_sauvegarde;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public void setDate_ajout_sauvegarde(Date date_ajout_sauvegarde) {
        this.date_ajout_sauvegarde = date_ajout_sauvegarde;
    }

    @Override
    public String toString() {
        return "PostsSauvegard\u00e9s{" + "user=" + user + ", sujet=" + sujet + ", date_ajout_sauvegarde=" + date_ajout_sauvegarde + '}';
    }
    
    

    
}
