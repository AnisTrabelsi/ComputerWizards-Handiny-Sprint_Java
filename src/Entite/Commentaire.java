/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author bengh
 */
public class Commentaire {

    private int id_commentaire;
    private String contenu_commentaire;
    private Date date_publication;
    private int nb_mentions;
    private Sujet sujet;
    private Utilisateur user;
    
    public Commentaire(String contenu_commentaire, Date date_publication, int nb_mentions, Sujet sujet, Utilisateur user) {
        this.contenu_commentaire = contenu_commentaire;
        this.date_publication = date_publication;
        this.nb_mentions = nb_mentions;
        this.sujet = sujet;
        this.user = user;
    }

    public Commentaire(int id_commentaire, String contenu_commentaire, Date date_publication, int nb_mentions, Sujet sujet, Utilisateur user) {
        this.id_commentaire = id_commentaire;
        this.contenu_commentaire = contenu_commentaire;
        this.date_publication = date_publication;
        this.nb_mentions = nb_mentions;
        this.sujet = sujet;
        this.user = user;
    }

    public Commentaire() {
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public int getNb_mentions() {
        return nb_mentions;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public void setNb_mentions(int nb_mentions) {
        this.nb_mentions = nb_mentions;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", contenu_commentaire=" + contenu_commentaire + ", date_publication=" + date_publication + ", nb_mentions=" + nb_mentions + '}';
    }

}
