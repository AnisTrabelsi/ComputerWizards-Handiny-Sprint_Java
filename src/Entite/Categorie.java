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

/**
 *
 * @author bengh
 */
public class Categorie {

    private int id_categorie;
    private String nom_categorie;
    private Date date_creation_categorie;
    private int nb_sujets;

    public Categorie(String nom_categorie, int nb_sujets) {
        this.nom_categorie = nom_categorie;
        this.nb_sujets = nb_sujets;
    }

    public Categorie(int id_categorie, String nom_categorie, Date date_creation_categorie, int nb_sujets) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.date_creation_categorie = date_creation_categorie;
        this.nb_sujets = nb_sujets;
    }

    public Categorie(int id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    public Categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public Categorie() {
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public Date getDate_creation_categorie() {
        return date_creation_categorie;
    }

    public int getNb_sujets() {
        return nb_sujets;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setDate_creation_categorie(Date date_creation_categorie) {
        this.date_creation_categorie = date_creation_categorie;
    }

    public void setNb_sujets(int nb_sujets) {
        this.nb_sujets = nb_sujets;
    }

    @Override
    public String toString() {
        return nom_categorie + "         " + date_creation_categorie + "         " + nb_sujets;
    }

}