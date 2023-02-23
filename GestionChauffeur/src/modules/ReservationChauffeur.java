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
public class ReservationChauffeur {
    
    int id_reservation_chauffeur,id_chauffeur,duree_service;
    String description_demande,date_prise_en_charge;
    
     public ReservationChauffeur(int id_reservation_chauffeur, int id_chauffeur,int duree_service, String date_prise_en_charge, String description_demande) {
        this.id_reservation_chauffeur = id_reservation_chauffeur;
        this.id_chauffeur = id_chauffeur;
        this.duree_service = duree_service;
        this.date_prise_en_charge = date_prise_en_charge;
         this.description_demande = description_demande;
    }

    public int getId_reservation_chauffeur() {
        return id_reservation_chauffeur;
    }

    public void setId_reservation_chauffeur(int id_reservation_chauffeur) {
        this.id_reservation_chauffeur = id_reservation_chauffeur;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }
       public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public int getDuree_service() {
        return duree_service;
    }

    public void setDuree_service(int duree_service) {
        this.duree_service = duree_service;
    }

    public String getDate_prise_en_charge() {
        return date_prise_en_charge;
    }

    public void setDate_prise_en_charge(String date_prise_en_charge) {
        this.date_prise_en_charge = date_prise_en_charge;
    }
 public String getDescription_demande() {
        return description_demande;
    }

    public void setDescription_demande(String description_demande) {
        this.description_demande = description_demande;
    }
    @Override
    public String toString() {
        return "ReservationChauffeur{" + "id_reservation_chauffeur=" + id_reservation_chauffeur + ", id_chauffeur=" + id_chauffeur + ", duree_service=" + duree_service + ", date_prise_en_charge=" + date_prise_en_charge + ", description_demande=" + description_demande + "\n" + '}';
    }
 
    
    
}
