/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chauffeur;
import entities.Reservation_Chauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *                     int id_reservation_chauffeur, int id_chauffeur,int duree_service, String duree_service, String description_demande                
 * @author Mehdi
 */
public class ServiceReservation_Chauffeur implements IServices <Reservation_Chauffeur>{
  
                    Connection cnx;
             public ServiceReservation_Chauffeur(){
             cnx = DataSource.getInstance().getCnx();
             }

public void ajouter(Reservation_Chauffeur c) throws SQLException {
    String query = "INSERT INTO reservation_chauffeur (id_reservation_chauffeur, duree_service, date_prise_en_charge, description_demande) VALUES (?, ?, ?, ?)";
    PreparedStatement statement = cnx.prepareStatement(query);
    statement.setInt(1, c.getId_reservation_chauffeur());
    statement.setInt(2, c.getDuree_service());
    statement.setString(3, c.getDate_prise_en_charge());
    statement.setString(4, c.getDescription_demande());
    statement.executeUpdate();
}


    
    public void modifier(Reservation_Chauffeur c) throws SQLException {
        String req = " UPDATE reservation_chauffeur SET id_reservation_chauffeur  = ? , duree_service = ? , date_prise_en_charge = ? , description_demande = ?   where id_reservation_chauffeur  = ?    ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, c.getId_reservation_chauffeur ());
        ps.setInt(2, c.getDuree_service());
        ps.setString(3, c.getDate_prise_en_charge());
        ps.setString(4, c.getDescription_demande());
        ps.executeUpdate();
    }

    
    public void supprimer(Reservation_Chauffeur c) throws SQLException {
        String req = " DELETE FROM reservation_chauffeur where id_reservation_chauffeur = ?   ";
             PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, c.getId_reservation_chauffeur());
             ps.executeUpdate();
    }

    
    public List<Reservation_Chauffeur> recuperer (Reservation_Chauffeur t) throws SQLException {
        List<Reservation_Chauffeur> ReservationChauffeur = new ArrayList<>();
        String s = "select * from reservation_chauffeur";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reservation_Chauffeur c = new Reservation_Chauffeur();
            c.setId_reservation_chauffeur(rs.getInt("id_reservation_chauffeur"));
            c.setDuree_service(rs.getInt("duree_service"));
            c.setDate_prise_en_charge(rs.getString("date_prise_en_charge"));
            c.setDescription_demande(rs.getString("description_demande"));
           
    
            ReservationChauffeur.add(c);
            
        }
        return ReservationChauffeur;   
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  


  
 

}

