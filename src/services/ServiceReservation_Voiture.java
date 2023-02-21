/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entite.Reservation_voiture;
import Entite.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Chaima
 */
public class ServiceReservation_Voiture implements IService <Reservation_voiture> {
Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceReservation_Voiture() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } {
    
}

    @Override
    public void ajouter(Reservation_voiture t) throws SQLException {
      
      
      String req = "INSERT INTO `reservation_voiture` (`id_utilisateur`,`id_voiture`,`date_debut_reservation`,`date_fin_reservation`prix_location) VALUES ( ?,?,?,?);";
        
        PreparedStatement pre=con.prepareStatement(req);
        
     
     pre.setInt(1,t.getId_utilisateur());
     pre.setInt(1,t.getId_voiture());
     pre.setString(2, t.getDate_debut_reservation());
     pre.setString(2, t.getDate_fin_reservation());
    
     
     pre.executeUpdate();
    }
    

    @Override
    public void update(Reservation_voiture t) throws SQLException {
        try {
            String req = "UPDATE `reservation_voiture` SET `date_debut_reservation` = '" + t.getDate_debut_reservation() + "', `marque` = '" + t.getDate_fin_reservation()  "' WHERE `reservation_voiture`.`id_reservation_voiture` = " + t.getId_reservation_voiture()";
            
            ste.executeUpdate(req);
            System.out.println("reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprime(Reservation_voiture t) throws SQLException {
       
       
        try {
            String req = "DELETE FROM `reservation_voiture` WHERE id_reservation_voiture = " + t.getId_reservation_voiture();
            
            ste.executeUpdate(req);
            
            System.out.println("réservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation_voiture> readAll() throws SQLException {
       
    
        List<Reservation_voiture> list = new ArrayList<>();
        try {
            String req = "Select * from reservation_voiture ";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
               Reservation_voiture p = new Reservation_voiture(rs.getString("date_debut_reservation"), rs.getString("date_fin_reservation"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Reservation_voiture findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}