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
 Date date= new Date();
 SimpleDateFormat formatter= new SimpleDateFormat("yyyy/mm/dd");
 private String date_debut_reservation= formatter.format(date);
 private String  date_fin_reservation= formatter.format(date);

    public Reservation_voiture(int id_reservation_voiture, int id_utilisateur, int id_voiture,String date_debut_reservation, String date_fin_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.id_utilisateur = id_utilisateur;
        this.id_voiture = id_voiture;
        this.date_debut_reservation=date_debut_reservation;
        this.date_fin_reservation=date_fin_reservation;
    }
    public Reservation_voiture(String date_debut_reservation, String date_fin_reservation) {
       
        this.date_debut_reservation=date_debut_reservation;
        this.date_fin_reservation=date_fin_reservation;
    }
    
    public Reservation_voiture( int id_utilisateur, int id_voiture,String date_debut_reservation, String date_fin_reservation) {
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

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getDate_debut_reservation() {
        return date_debut_reservation;
    }

    public void setDate_debut_reservation(String date_debut_reservation) {
        this.date_debut_reservation = date_debut_reservation;
    }

    public String getDate_fin_reservation() {
        return date_fin_reservation;
    }

    public void setDate_fin_reservation(String date_fin_reservation) {
        this.date_fin_reservation = date_fin_reservation;
    }

 
         
}
