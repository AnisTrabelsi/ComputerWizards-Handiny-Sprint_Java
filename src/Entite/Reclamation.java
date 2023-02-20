/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

/**
 *
 * @author Chayma
 */
public class Reclamation {
private int id_utilisateur ;
private int id_reclamation ;
private String type_reclamation ;
private String etat_reclamation ; 
private String description ;
private Utilisateur user;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Reclamation() {
    }


    public Reclamation(int id_utilisateur , int id_reclamation, String type_reclamation, String etat_reclamation, String description , Utilisateur user) {
        this.id_utilisateur =id_utilisateur ;
        this.id_reclamation = id_reclamation;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description = description;
        this.user = user ;
    }

    public Reclamation( int id_reclamation, String type_reclamation, String etat_reclamation, String description) {
        
        this.id_reclamation = id_reclamation;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description = description;
    }

    public Reclamation( String type_reclamation, String etat_reclamation, String description, Utilisateur user ) {
        this.id_utilisateur= id_utilisateur ;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description = description;
        this.user = user ;
    }
     public Reclamation( String type_reclamation, String etat_reclamation, String description) {
        this.id_utilisateur= id_utilisateur ;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description = description;
        
    }

    public Reclamation(int id_utilisateur, String type_reclamation, String etat_reclamation, String description, Utilisateur user) {
        this.id_utilisateur = id_utilisateur;
        this.type_reclamation = type_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.description = description;
        this.user = user;
    }


    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur ;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_utilisateur=" + id_utilisateur + ", id_reclamation=" + id_reclamation + ", type_reclamation=" + type_reclamation + ", etat_reclamation=" + etat_reclamation + ", description=" + description + '}';
    }


}