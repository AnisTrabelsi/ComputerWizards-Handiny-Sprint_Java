/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Chaima
 */
public class Voiture {
    private int id_voiture;
    private String immatriculation,marque,modele,boite_vitesse,kilometrage,carburant,image_voiture,description;
    private double prix_location;
    private Date date_validation_technique;
    private Utilisateur user;
public Voiture(String string, String string1, String string2, double aDouble, Date date){} 
    
  public Voiture(String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, Double prix_location, Date date_validation_technique, int user) {
       
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique=date_validation_technique;
       // this.user = user;
        
    }
    public Voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }
    
    public Voiture(int id_voiture, String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, double prix_location, Date date_validation_technique, Utilisateur user) {
        this.id_voiture = id_voiture;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
        this.user = user;
    }
      public Voiture(int id_voiture, String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, double prix_location, Date date_validation_technique) {
        this.id_voiture = id_voiture;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
        
    }
       public Voiture(String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, double prix_location, Date date_validation_technique) {
        
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
        
    }


   public Voiture( int id,String marque,String modele, String boite_vitesse,String description,double prix_location, Date date_validation_technique) {
        this.id_voiture=id;
    
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
       // this.kilometrage = kilometrage;
        //this.carburant = carburant;
        //this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
       
    }
   public Voiture( String immatriculation, String marque, String boite_vitesse,double prix_location, Date date_validation_technique,int id_voiture) {
        
        this.immatriculation = immatriculation;
        this.marque = marque;
        //this.modele = modele;
        this.boite_vitesse = boite_vitesse;
       // this.kilometrage = kilometrage;
        //this.carburant = carburant;
        //this.image_voiture = image_voiture;
       // this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
       
    }
   public Voiture( String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String description, double prix_location, Date date_validation_technique) {
       
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
       
    }

    

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }
    
    
    

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getBoite_vitesse() {
        return boite_vitesse;
    }

    public void setBoite_vitesse(String boite_vitesse) {
        this.boite_vitesse = boite_vitesse;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Date getDate_validation_technique() {
        return date_validation_technique;
    }

    public void setDate_validation_technique(Date date_validation_technique) {
        this.date_validation_technique = date_validation_technique;
    }
     

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getImage_voiture() {
        return image_voiture;
    }

    public void setImage_voiture(String image_voiture) {
        this.image_voiture = image_voiture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(double prix_location) {
        this.prix_location = prix_location;
    }

    
    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return   marque+"\t \t"+"\t \t"+modele+"\t \t \t \t"+ description+"\t \t \t"+ boite_vitesse +"\t \t \t"+ prix_location +"\t \t"+ date_validation_technique;

    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voiture other = (Voiture) obj;
        return Objects.equals(this.immatriculation, other.immatriculation);
    }
    
   

}