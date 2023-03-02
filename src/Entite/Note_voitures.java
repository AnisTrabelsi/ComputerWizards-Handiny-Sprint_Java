/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Chaima
 */
public class Note_voitures {
    private int id_note_voitures;
    private Utilisateur user;
    private Voiture voit; 
    private Double note;

    public Note_voitures(int id_note_voitures, Utilisateur user, Voiture voit, Double note) {
        this.id_note_voitures = id_note_voitures;
        this.user = user;
        this.voit = voit;
        this.note = note;
    }

    public Note_voitures(Utilisateur user, Voiture voit, Double note) {
        this.user = user;
        this.voit = voit;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note_voitures{" + "id_note_voitures=" + id_note_voitures + ", user=" + user + ", voit=" + voit + ", note=" + note + '}';
    }

    public int getId_note_voitures() {
        return id_note_voitures;
    }

    public void setId_note_voitures(int id_note_voitures) {
        this.id_note_voitures = id_note_voitures;
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

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
    
    
}
