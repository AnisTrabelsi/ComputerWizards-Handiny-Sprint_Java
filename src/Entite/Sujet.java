/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author bengh
 */
import java.sql.Date;
import java.util.ArrayList;
import Entite.Commentaire;

/**
 *
 * @author bengh
 */
public class Sujet {

    private int id_sujet;
    private String titre_sujet;
    private Date date_creation_sujet;
    private String contenu_sujet;
    private int nb_commentaires;
    private String etat;
    private String tags;
    private Categorie cat;
    private Utilisateur user;

    public Sujet(String titre_sujet, String contenu_sujet, int nb_commentaires, String etat, String tags, Categorie cat, Utilisateur user) {
        this.titre_sujet = titre_sujet;
        this.contenu_sujet = contenu_sujet;
        this.nb_commentaires = nb_commentaires;
        this.etat = etat;
        this.tags = tags;
        this.cat = cat;
        this.user = user;
    }

    public Sujet(String titre_sujet, String contenu_sujet, String etat, String tags, Categorie cat, Utilisateur user) {
        this.titre_sujet = titre_sujet;
        this.contenu_sujet = contenu_sujet;
        this.etat = etat;
        this.tags = tags;
        this.cat = cat;
        this.user = user;
    }

    public Sujet(int id_sujet, String titre_sujet, String contenu_sujet, String etat, String tags) {
        this.id_sujet = id_sujet;
        this.titre_sujet = titre_sujet;
        this.contenu_sujet = contenu_sujet;
        this.etat = etat;
        this.tags = tags;
    }

    public Sujet(int id_sujet, String titre_sujet, Date date_creation_sujet, String contenu_sujet, int nb_commentaires, String etat, String tags, Categorie cat, Utilisateur user) {
        this.id_sujet = id_sujet;
        this.titre_sujet = titre_sujet;
        this.date_creation_sujet = date_creation_sujet;
        this.contenu_sujet = contenu_sujet;
        this.nb_commentaires = nb_commentaires;
        this.etat = etat;
        this.tags = tags;
        this.cat = cat;
        this.user = user;
    }

    public Sujet() {
    }

    public int getId_sujet() {
        return id_sujet;
    }

    public String getTitre_sujet() {
        return titre_sujet;
    }

    public Date getDate_creation_sujet() {
        return date_creation_sujet;
    }

    public String getContenu_sujet() {
        return contenu_sujet;
    }

    public int getNb_commentaires() {
        return nb_commentaires;
    }

    public String getEtat() {
        return etat;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }

    public void setTitre_sujet(String titre_sujet) {
        this.titre_sujet = titre_sujet;
    }

    public void setDate_creation_sujet(Date date_creation_sujet) {
        this.date_creation_sujet = date_creation_sujet;
    }

    public void setContenu_sujet(String contenu_sujet) {
        this.contenu_sujet = contenu_sujet;
    }

    public void setNb_commentaires(int nb_commentaires) {
        this.nb_commentaires = nb_commentaires;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return titre_sujet + "         " + date_creation_sujet + "         " + contenu_sujet + "         " + nb_commentaires + "         " + etat + "         " + tags;
    }

}
