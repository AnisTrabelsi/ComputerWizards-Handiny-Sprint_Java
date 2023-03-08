/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author Chaima
 */
public class Favoris_voitures {
 private int id_favoris_voitures;
 private Utilisateur user;
 private Voiture voit; 
 private Date date_ajout_favoris;

    public Favoris_voitures(int id_favoris_voitures, Utilisateur user, Voiture voit, Date date_ajout_favoris) {
        this.id_favoris_voitures = id_favoris_voitures;
        this.user = user;
        this.voit = voit;
        this.date_ajout_favoris = date_ajout_favoris;
    }

    public Favoris_voitures(Utilisateur user, Voiture voit, Date date_ajout_favoris) {
        this.user = user;
        this.voit = voit;
        this.date_ajout_favoris = date_ajout_favoris;
    }

    public Favoris_voitures(Utilisateur user, Voiture voit) {
        this.user = user;
        this.voit = voit;
    }

    public int getId_favoris_voitures() {
        return id_favoris_voitures;
    }

    public void setId_favoris_voitures(int id_favoris_voitures) {
        this.id_favoris_voitures = id_favoris_voitures;
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

    public Date getDate_ajout_favoris() {
        return date_ajout_favoris;
    }

    public void setDate_ajout_favoris(Date date_ajout_favoris) {
        this.date_ajout_favoris = date_ajout_favoris;
    }

    @Override
    public String toString() {
        return "Favoris_voitures{" + "id_favoris_voitures=" + id_favoris_voitures + ", user=" + user + ", voit=" + voit + ", date_ajout_favoris=" + date_ajout_favoris + '}';
    }
 
 
    
}
