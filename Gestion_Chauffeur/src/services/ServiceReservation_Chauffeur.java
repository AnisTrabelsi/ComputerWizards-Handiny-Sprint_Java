/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chauffeur;
import entities.Reservation_Chauffeur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

                    @Override
     public void ajouter(Reservation_Chauffeur c) throws SQLException {
    String query = "INSERT INTO reservation_chauffeur (id_chauffeur, duree_service, date_prise_en_charge, description_demande) VALUES (?, ?, ? ,?)";
    try{
    PreparedStatement statement = cnx.prepareStatement(query);
    statement.setInt(1, c.getId_chauffeur());
    statement.setInt(2, c.getDuree_service());
    statement.setDate(3, c.getDate_prise_en_charge());
    statement.setString(4, c.getDescription_demande());
    
   /* String selectReq = "SELECT * FROM chauffeur WHERE id_chauffeur = ?";
        PreparedStatement selectPs = cnx.prepareStatement(selectReq);
        selectPs.setInt(1, c.getId_chauffeur());
        ResultSet rt = selectPs.executeQuery();
        if (!rt.next()) {
            // Si le résultat est vide, le troc n'existe pas, afficher une erreur
            System.err.println("L'expertise correspondant à id_expertise n'existe pas.");
            return;
        }*/
    
    statement.executeUpdate();
    //System.out.println("Rapport ajouté avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation_Chauffeur.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    
                    @Override
    public void modifier(Reservation_Chauffeur c) throws SQLException {
        String req = " UPDATE reservation_chauffeur SET  duree_service = ? , date_prise_en_charge = ? , description_demande = ?   where id_reservation_chauffeur  = ?    ";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, c.getDuree_service());
        ps.setDate(2, (Date) c.getDate_prise_en_charge());
        ps.setString(3, c.getDescription_demande());
             ps.setInt(4, c.getId_reservation_chauffeur());
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
            c.setDate_prise_en_charge(rs.getDate("date_prise_en_charge"));
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

