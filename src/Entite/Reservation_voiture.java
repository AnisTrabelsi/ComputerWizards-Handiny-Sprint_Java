/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chaima
 */
public class Reservation_voiture {
 private int id_reservation_voiture;
 private Utilisateur user;
 private Voiture voit; 
 private Date date_debut_reservation;
 private Date  date_fin_reservation;
 private String etat_demande_reservation;
 private String description_reservation;
 private Date date_demande_reservation;

    public Reservation_voiture(int id_reservation_voiture, Utilisateur user, Voiture voit, Date date_debut_reservation, Date date_fin_reservation, String etat_demande_reservation, String description_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.user = user;
        this.voit = voit;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
       
    }
    public Reservation_voiture(int id_reservation_voiture, String etat_demande_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.etat_demande_reservation = etat_demande_reservation;

       
    }

    public Reservation_voiture(Utilisateur user, Voiture voit, Date date_debut_reservation, Date date_fin_reservation, String description_reservation) {
        this.user = user;
        this.voit = voit;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
       //this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
      
    }
    

    public Reservation_voiture(int id_reservation_voiture, Date date_debut_reservation, Date date_fin_reservation,String description_reservation, String etat_demande_reservation, Date date_demande_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
        this.date_demande_reservation = date_demande_reservation;
    }
    
    public Reservation_voiture(int id_reservation_voiture, Date date_debut_reservation, Date date_fin_reservation,String description_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        
        this.description_reservation = description_reservation;
     
    }

    public Reservation_voiture(Date date_debut_reservation, Date date_fin_reservation, String description_reservation, String etat_demande_reservation, Date date_demande_reservation) {
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
        this.date_demande_reservation = date_demande_reservation;
    }

   public Reservation_voiture(int id, Utilisateur u, Voiture v, java.sql.Date dateDebut, java.sql.Date dateFin, String description, String etat, java.sql.Date dateDemande) {
    this.id_reservation_voiture= id;
    this.user = u;
    this.voit = v;
    this.date_debut_reservation= new java.util.Date(dateDebut.getTime());
    this.date_fin_reservation = new java.util.Date(dateFin.getTime());
    this.description_reservation= description;
    this.etat_demande_reservation= etat;
    this.date_demande_reservation= new java.util.Date(dateDemande.getTime());
}


   
     
    

    public int getId_reservation_voiture() {
        return id_reservation_voiture;
    }

    @Override
    public String toString() {
        return date_debut_reservation + "\t \t \t \t" + date_fin_reservation + "\t  \t \t \t" + etat_demande_reservation + "\t \t \t \t" + description_reservation + " \t \t \t \t" + date_demande_reservation ;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Voiture getVoit() {
        return voit;
    }

    public void setVoit(Voiture voit) {
        this.voit = voit;
    }

    public Date getDate_debut_reservation() {
        return date_debut_reservation;
    }

    public void setDate_debut_reservation(Date date_debut_reservation) {
        this.date_debut_reservation = date_debut_reservation;
    }

    public Date getDate_fin_reservation() {
        return date_fin_reservation;
    }

    public void setDate_fin_reservation(Date date_fin_reservation) {
        this.date_fin_reservation = date_fin_reservation;
    }

    public String getEtat_demande_reservation() {
        return etat_demande_reservation;
    }

    public void setEtat_demande_reservation(String etat_demande_reservation) {
        this.etat_demande_reservation = etat_demande_reservation;
    }

    public String getDescription_reservation() {
        return description_reservation;
    }

    public void setDescription_reservation(String description_reservation) {
        this.description_reservation = description_reservation;
    }

    public Date getDate_demande_reservation() {
        return date_demande_reservation;
    }

    public void setDate_demande_reservation(Date date_demande_reservation) {
        this.date_demande_reservation = date_demande_reservation;
    }
    

    
         
}
