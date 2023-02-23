/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author abbes
 */
public class Covoiturage {
    
   
    private int id_cov,id_utilisateur,Prix,nbrplace ;
    private String depart,destination ;
   Date date= new Date();
 SimpleDateFormat formatter= new SimpleDateFormat("yyyy/mm/dd");
 private String date_covoiturage= formatter.format(date);

    public Covoiturage(int id_cov, int id_utilisateur, String depart, String destination , String date_covoiturage ,int Prix ,int nbrplace ) {
        this.id_cov = id_cov;
        this.id_utilisateur = id_utilisateur;
        this.depart=depart;
        this.destination = destination;
        this.Prix=Prix;
        this.nbrplace = nbrplace ; 
        this.date_covoiturage = date_covoiturage ; 
    }
    
     public Covoiturage( int id_utilisateur , String depart, String destination , String date_covoiturage ,int Prix ,int nbrplace ) {
              this.id_utilisateur = id_utilisateur;

        this.depart=depart;
        this.destination = destination;
        this.Prix=Prix;
        this.nbrplace = nbrplace ; 
        this.date_covoiturage = date_covoiturage ; 
    }
    

    public Covoiturage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Covoiturage(int id_cov, String depart, String destination, LocalDate date, int prix, int nbrplace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
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

    public String getDate_covoiturage() {
        return date_covoiturage;
    }

    public void setDate_covoiturage(String date_covoiturage) {
        this.date_covoiturage = date_covoiturage;
    }

    @Override
    public String toString() {
        return  "id_cov=" + id_cov + ", id_utilisateur=" + id_utilisateur + ", Prix=" + Prix + ", nbrplace=" + nbrplace + ", depart=" + depart + ", destination=" + destination + ", date_covoiturage=" + date_covoiturage + '}';
    }

   

 
    
    
}
