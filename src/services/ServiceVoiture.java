/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Entite.Voiture;
import utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chaima
 */
public class ServiceVoiture implements IService<Voiture> {
Connection con=DataSource.getInstance().getConnection();

    
    private Statement ste;

    public ServiceVoiture() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public boolean ajouter(Voiture t) throws SQLException {
        boolean verif=true;
  java.util.Date date_util = new java.util.Date();
  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
    try { String req = "INSERT INTO `voiture` (`immatriculation`,`marque`,`modele`,`boite_vitesse`,`kilometrage`,`carburant`,`image_voiture`,`description`,`prix_location`,`date_validation_technique`,`id_utilisateur`) VALUES ( ?,?,?,?,?,?,?,?,?,?,?);";
        
        PreparedStatement pre=con.prepareStatement(req);
        
     
        pre.setString(1,t.getImmatriculation());
        pre.setString(2, t.getMarque());
        pre.setString(3, t.getModele());
        pre.setString(4, t.getBoite_vitesse());
        pre.setString(5, t.getKilometrage());
        pre.setString(6, t.getCarburant());
        pre.setString(7, t.getImage_voiture());
        pre.setString(8, t.getDescription());
        pre.setDouble(9, t.getPrix_location());
        pre.setDate(10,new java.sql.Date(t.getDate_validation_technique().getTime()));
        //pre.setInt(11,t.getUser().getId_utilisateur());
        pre.setInt(11,1);
     

     int rowsInserted = pre.executeUpdate();
     if (rowsInserted > 0) {
         
                           }
            
     }catch(SQLException e){
            System.out.println(e);
            verif=false;
        }
    return verif;
    }
    

    @Override
    public void update(Voiture t) throws SQLException {
    
     
        
        try {
            
          String req = "UPDATE voiture SET immatriculation=?, marque=?,modele=?,boite_vitesse=?, kilometrage=?,carburant=?, image_voiture=?, description=?, prix_location=?, date_validation_technique=? WHERE id_voiture=?";
          PreparedStatement pre = con.prepareStatement(req);
        
        pre.setString(1,t.getImmatriculation());
        pre.setString(2, t.getMarque());
        pre.setString(3, t.getModele());
        pre.setString(4, t.getBoite_vitesse());
        pre.setString(5, t.getKilometrage());
        pre.setString(6, t.getCarburant());
        pre.setString(7, t.getImage_voiture());
        pre.setString(8, t.getDescription());
        pre.setDouble(9, t.getPrix_location());
        pre.setDate(10,new java.sql.Date(t.getDate_validation_technique().getTime()));
        pre.setInt(11,t.getId_voiture());
        

        int rowsUpdated = pre.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing car was updated successfully!");
        }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }


    @Override
    public void supprime(Voiture t) throws SQLException {
       
        try {
            String req = "DELETE FROM `voiture` WHERE id_voiture = ?";
            PreparedStatement statement = con.prepareStatement(req);
            statement.setInt(1,t.getId_voiture());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A car was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }
        
    }
    public void delete(int id) throws SQLException {
       
        try {
            String req = "DELETE FROM `voiture` WHERE id_voiture = " + id;
           

            int rowsDeleted = ste.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("A car was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }
        
    }
    


    @Override
    public List<Voiture> readAll() throws SQLException {
    
        List<Voiture> list = new ArrayList<>();
        try {
            String req = "Select * from voiture";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
               Voiture v = new Voiture(rs.getString("immatriculation"), rs.getString("marque"), rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("kilometrage"),rs.getString("carburant"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
               //Voiture v = new Voiture(rs.getString("immatriculation"), rs.getString("marque"),rs.getString("boite_vitesse"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Voiture findById(int id) throws SQLException {
        Voiture v = null;
        try {
            String req = "Select * from voiture";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                 //v = new Voiture(rs.getString("immatriculation"), rs.getString("marque"), rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("kilometrage"),rs.getString("carburant"),rs.getString("image_voiture"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return v;
    }

    }
    

