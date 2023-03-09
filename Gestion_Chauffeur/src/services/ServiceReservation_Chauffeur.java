/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//import utils.MyConnection;
import entities.Reservation_Chauffeur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;
//import modules.Reservation_Chauffeur;

/**
 *
 * @author MSI
 */
public class ServiceReservation_Chauffeur implements IServices <Reservation_Chauffeur> {
    
       Connection con = DataSource.getInstance().getCnx();
    private Statement cnx;

    public ServiceReservation_Chauffeur() {

        try {
            cnx = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       @Override
    public void ajouter(Reservation_Chauffeur c) throws SQLException {
        
        String req = "INSERT INTO `reservation_chauffeur` ( `id_chauffeur`, `duree_service`, `date_prise_en_charge`,`description_demande`) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(req);
        
        ps.setInt(1, c.getId_chauffeur());
        ps.setInt(2,c.getDuree_service());
        ps.setDate(3, new java.sql.Date(c.getDate_prise_en_charge().getTime())); 
        ps.setString(4, c.getDescription_demande());
        ps.executeUpdate();
       
        }
    
    /**
     *
     * @param c
     * @throws SQLException
     */
    @Override
    public void modifier(Reservation_Chauffeur c) throws SQLException {
         String req = " UPDATE reservation_chauffeur SET `duree_service` = ? , `date_prise_en_charge` = ? , `description_demande`= ?  where id_reservation_Chauffeur = ?";
            PreparedStatement ps = con.prepareStatement(req);

       
        ps.setInt(1,c.getDuree_service());
        ps.setDate(2, new java.sql.Date(c.getDate_prise_en_charge().getTime())); 
        ps.setString(3, c.getDescription_demande());
        ps.setInt(4, c.getId_reservation_chauffeur());
              
        ps.executeUpdate();
    }

       @Override
    public void supprimer(int id) throws SQLException {
         String req = " DELETE FROM reservation_chauffeur where id_reservation_Chauffeur =  " +id ;
       
               PreparedStatement ps = con.prepareStatement(req);
          
             ps.executeUpdate();

    }

  /* @Override
    public List<Reservation_Chauffeur> recuperer(Reservation_Chauffeur c) throws SQLException {
        List<Reservation_Chauffeur> Reservation_Chauffeur = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM Reservation_Chauffeur";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
          //      Reservation_Chauffeur c = new Reservation_Chauffeur();
                c.setId_reservation_Chauffeur(1);
                c.setid_chauffeur(rs.getString(2));
                c.setduree_service(rs.getString(3));
                c.setdate_prise_en_charge(rs.getString(4));
                c.setid_reservation_Chauffeur(rs.getString(5));
                
                
                Reservation_Chauffeur.add(c);
            }
            
        } catch (SQLException ex) {
           // ex.printStackTrace();
        }
        
        return Reservation_Chauffeur;
    }*/
    
    
    
    public List<Reservation_Chauffeur> readAll() throws SQLException {
       
    
        List<Reservation_Chauffeur> list = new ArrayList<>();
        try {
            String req = "Select * from reservation_chauffeur ";
            
            ResultSet rs = cnx.executeQuery(req);
            while (rs.next()) {
             Reservation_Chauffeur p = new Reservation_Chauffeur(rs.getInt("id_chauffeur"),rs.getInt("duree_service"),rs.getDate("date_prise_en_charge"),rs.getString("description_demande"));
               
               list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<Reservation_Chauffeur> recuperer(Reservation_Chauffeur c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Reservation_Chauffeur> trierReservationsParDate() throws SQLException {
           List<Reservation_Chauffeur> list = new ArrayList<>();
        try {
            String req = "Select * from reservation_chauffeur order by date_prise_en_charge ";
            
            ResultSet rs = cnx.executeQuery(req);
            while (rs.next()) {
             Reservation_Chauffeur p = new Reservation_Chauffeur(rs.getInt("id_chauffeur"),rs.getInt("duree_service"),rs.getDate("date_prise_en_charge"),rs.getString("description_demande"));
               
               list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }



       
}


