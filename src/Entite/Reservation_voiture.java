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

    public Reservation_voiture(int id_reservation_voiture, int id_utilisateur, int id_voiture,Date date_debut_reservation, Date date_fin_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.date_debut_reservation=date_debut_reservation;
        this.date_fin_reservation=date_fin_reservation;
    }
    public Reservation_voiture(Date date_debut_reservation, Date date_fin_reservation) {
       
        this.date_debut_reservation=date_debut_reservation;
        this.date_fin_reservation=date_fin_reservation;
    }
    public Reservation_voiture(int id_reservation_voiture,Date date_debut_reservation, Date date_fin_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.date_debut_reservation=date_debut_reservation;
        this.date_fin_reservation=date_fin_reservation;
    }
    
   
    
    
    

    public int getId_reservation_voiture() {
        return id_reservation_voiture;
    }

    public void setId_reservation_voiture(int id_reservation_voiture) {
        this.id_reservation_voiture = id_reservation_voiture;
    }

    @Override
    public String toString() {
        return date_debut_reservation +"\t \t \t \t"+date_fin_reservation ;
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

   

   

    

   

 
         
}
