/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.scene.control.TextField;

/**
 *
 * @author Mehdi
 */
public class Reservation_Chauffeur {
    
    private int id_reservation_chauffeur,id_chauffeur,duree_service;
     private String description_demande;
     private Date date_prise_en_charge; 
     private Chauffeur chauffeur;

    public Reservation_Chauffeur() {
    }

    public Reservation_Chauffeur(int id_reservation_chauffeur, int id_chauffeur,int duree_service, Date date_prise_en_charge, String description_demande,Chauffeur chauffeur) {
        this.id_reservation_chauffeur = id_reservation_chauffeur;
        this.id_chauffeur = id_chauffeur;
        this.duree_service= duree_service;
        this.date_prise_en_charge = date_prise_en_charge;
        this.description_demande = description_demande;
        this.chauffeur =chauffeur ;
    }
     public Reservation_Chauffeur( int id_chauffeur,int duree_service, Date date_prise_en_charge, String description_demande) {
        
        this.id_chauffeur = id_chauffeur;
        this.duree_service= duree_service;
        this.date_prise_en_charge = date_prise_en_charge;
        this.description_demande = description_demande;
        
    }

    public Reservation_Chauffeur(int id_chauffeur, int duree_service, String description_demande, Date date_prise_en_charge) {
        this.id_chauffeur = id_chauffeur;
        this.duree_service = duree_service;
        this.description_demande = description_demande;
        this.date_prise_en_charge = date_prise_en_charge;
    }

    public Reservation_Chauffeur(int id_reservation_chauffeur, int id_chauffeur, int duree_service, String description_demande, Date date_prise_en_charge) {
        this.id_reservation_chauffeur = id_reservation_chauffeur;
        this.id_chauffeur = id_chauffeur;
        this.duree_service = duree_service;
        this.description_demande = description_demande;
        this.date_prise_en_charge = date_prise_en_charge;
    }

    public Reservation_Chauffeur(int id, String tcin, String tnom, String tadresse, String tdispo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



     
/*
    public Reservation_Chauffeur(String ttcin, String ttnom, String ttadresse, String ttdispo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }
 
    public int getId_reservation_chauffeur() {
        return id_reservation_chauffeur;
    }

    public void setId_reservation_chauffeur(int id_reservation_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }
    public int getDuree_service() {
        return duree_service;
    }

    public void setDuree_service(int duree_service) {
        this.duree_service = duree_service;
    }

    public Date getDate_prise_en_charge() {
        return date_prise_en_charge;
    }

    public void setDate_prise_en_charge(Date date_prise_en_charge) {
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
