/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.sql.*;

/**
 *
 * @author anis
 */
public class demande_don {
       private int id_demande_don;
   private int id_utilisateur;
   private int id_don;
   private Date date_demande;
   private String justificatif_handicap;
  private String etat;

 
  
  
    public demande_don(int id_demande_don, int id_utilisateur, int id_don, Date date_demande, String justificatif_handicap, String etat) {
        this.id_demande_don = id_demande_don;
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.date_demande = date_demande;
        this.justificatif_handicap = justificatif_handicap;
        this.etat = etat;
    }
   public demande_don( int id_utilisateur, int id_don, Date date_demande, String justificatif_handicap, String etat) {
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.date_demande = date_demande;
        this.justificatif_handicap = justificatif_handicap;
        this.etat = etat;
    }
    public demande_don() {
    }

 

    public int getId_demande_don() {
        return id_demande_don;
    }

    public void setId_demande_don(int id_demande_don) {
        this.id_demande_don = id_demande_don;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
    }

    public String getJustificatif_handicap() {
        return justificatif_handicap;
    }

    public void setJustificatif_handicap(String justificatif_handicap) {
        this.justificatif_handicap = justificatif_handicap;
    }
      public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}

