/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//import utils.MyConnection;
import Entite.Covoiturage;
import Entite.Reservation_Chauffeur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DataSource;
import java.util.HashMap;
//import modules.Reservation_Chauffeur;

/**
 *
 * @author MSI
 */
public class ServiceReservation_Chauffeur implements Services.IService<Reservation_Chauffeur> {
    
       Connection con = DataSource.getInstance().getConnection();
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

    @Override
    public void ajouter_don(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation_Chauffeur findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation_Chauffeur findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation_Chauffeur> sort_date_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherparcin(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_nom(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_prenom(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_email(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherparid(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_nom(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcid_prenom(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_email(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcid_cin(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherid_parrrcin(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(Reservation_Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation_Chauffeur findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ajouter(Covoiturage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Covoiturage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Covoiturage> sortbydate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



       
}


