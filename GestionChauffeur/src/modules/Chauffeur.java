/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

/**
 *
 * @author Mehdi
 */
public class Chauffeur {

   int id_chauffeur , Statut_disponibilite;
   String CIN ,Nom , Adresse;
 
    public Chauffeur() {
    }

    public Chauffeur( String CIN, int Statut_disponibilite , String Nom, String Adresse) {
        this.Statut_disponibilite = Statut_disponibilite;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
    }

    public Chauffeur(int id_chauffeur, String CIN, String Nom, String Adresse, int Statut_disponibilite) {
        
        this.id_chauffeur = id_chauffeur;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
        this.Statut_disponibilite = Statut_disponibilite;
       
    }
  

    public int getid_chauffeur() {
        return id_chauffeur;
    }

    public void setIid_chauffeur(int id_chauffeur) {
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

    public int getStatut_disponibilite() {
        return Statut_disponibilite;
    }

    public void setStatut_disponibilite(int Statut_disponibilite) {
        this.Statut_disponibilite = Statut_disponibilite;
    }

    

    @Override
    public String toString() {
        return "Chauffeur{" + "id_chauffeur=" + id_chauffeur + ", CIN=" + CIN + ", Nom=" + Nom + ", Adresse=" + Adresse + ", Statut_disponibilite=" + Statut_disponibilite + "\n" + '}';
    }

    public String getId_chauffeur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String Adresse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setid_chauffeur(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    








    
}
