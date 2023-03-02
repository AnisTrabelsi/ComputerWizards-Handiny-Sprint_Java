/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author abbes
 */
public class reservation_covoiturage {
    private int id_reserv_cov;
   private int id_cov;
   private int id_utilisateur;
   private int  prix_covoiturage;
  private String depart ;
  private String destination ; 
  private String nom ;
  private String telephone ;

 
  
  
    public reservation_covoiturage(int id_reserv_cov, int id_cov, int id_utilisateur, int prix_covoiturage, String depart, String destination, String nom, String telephone) {
        this.id_reserv_cov = id_reserv_cov;
        this.id_cov = id_cov;
        this.id_utilisateur = id_utilisateur;
        this.prix_covoiturage = prix_covoiturage;
            this.depart = depart;
        this.destination = destination;
 this.nom = nom;
        this.telephone = telephone;
    }
   public reservation_covoiturage( int id_cov, int id_utilisateur, int prix_covoiturage , String depart, String destination, String nom, String telephone) {
       this.id_cov = id_cov;
      this.id_utilisateur = id_utilisateur;
       
        this.prix_covoiturage = prix_covoiturage;
            this.depart = depart;
        this.destination = destination;
this.nom = nom;
        this.telephone = telephone;
    }
    public reservation_covoiturage() {
    }

    public reservation_covoiturage(int id_cov, int id_utilisateur) {
 this.id_cov = id_cov;
      this.id_utilisateur = id_utilisateur;
           }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getId_reserv_cov() {
        return id_reserv_cov;
    }

    public void setId_reserv_cov(int id_reserv_cov) {
        this.id_reserv_cov = id_reserv_cov;
    }

    public int getId_cov() {
        return id_cov;
    }

    public void setId_cov(int id_cov) {
        this.id_cov = id_cov;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getPrix_covoiturage() {
        return prix_covoiturage;
    }

    public void setPrix_covoiturage(int prix_covoiturage) {
        this.prix_covoiturage = prix_covoiturage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "reservation_covoiturage{" + "id_reserv_cov=" + id_reserv_cov + ", id_cov=" + id_cov + ", id_utilisateur=" + id_utilisateur + ", prix_covoiturage=" + prix_covoiturage + ", depart=" + depart + ", destination=" + destination + ", nom=" + nom + ", telephone=" + telephone + '}';
    }

   
 

}
