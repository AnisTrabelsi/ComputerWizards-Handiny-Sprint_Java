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
  

 
  
  
    public reservation_covoiturage(int id_reserv_cov, int id_cov, int id_utilisateur, int prix_covoiturage) {
        this.id_reserv_cov = id_reserv_cov;
        this.id_cov = id_cov;
        this.id_utilisateur = id_utilisateur;
        this.prix_covoiturage = prix_covoiturage;
     
    }
   public reservation_covoiturage( int id_cov, int id_utilisateur, int prix_covoiturage) {
       this.id_cov = id_cov;
      this.id_utilisateur = id_utilisateur;
       
        this.prix_covoiturage = prix_covoiturage;
    
    }
    public reservation_covoiturage() {
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

 

}
