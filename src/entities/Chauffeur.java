/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mehdi
 */
public class Chauffeur {
    
    int id_chauffeur;
    String CIN,Nom,Adresse,Statut_disponibilite;

    public Chauffeur() {
    }

    public Chauffeur(int id_chauffeur, String CIN, String Nom, String Adresse, String Statut_disponibilite) {
        this.id_chauffeur = id_chauffeur;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
        this.Statut_disponibilite = Statut_disponibilite;
    }
     public Chauffeur( String CIN, String Nom, String Adresse, String Statut_disponibilite) {

        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
        this.Statut_disponibilite = Statut_disponibilite;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getStatut_disponibilite() {
        return Statut_disponibilite;
    }

    public void setStatut_disponibilite(String Statut_disponibilite) {
        this.Statut_disponibilite = Statut_disponibilite;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "id_chauffeur=" + id_chauffeur + ", CIN=" + CIN + ", Nom=" + Nom + ", Adresse=" + Adresse + ", Statut_disponibilite=" + Statut_disponibilite + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}
