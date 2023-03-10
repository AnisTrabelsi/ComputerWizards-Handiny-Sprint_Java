/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//import utils.MyConnection;
import Entite.Chauffeur;
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
//import modules.Chauffeur;

/**
 *
 * @author MSI
 */
public class ServiceChauffeur implements Services.IService<Chauffeur> {
    
       Connection con = DataSource.getInstance().getConnection();
    private Statement cnx;

    public ServiceChauffeur() {

        try {
            cnx = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       @Override
    public void ajouter(Chauffeur c) throws SQLException {
        
        String req = "INSERT INTO `chauffeur` ( `CIN`, `Nom`, `Adresse`,`Statut_disponibilite`) VALUES (?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        
        pre.setString(1, c.getCIN());
        pre.setString(2, c.getNom());
        pre.setString(3, c.getAdresse());
        pre.setString(4, c.getStatut_disponibilite());
        pre.executeUpdate();
       
        }
    
    /**
     *
     * @param c
     * @throws SQLException
     */
    @Override
    public void modifier(Chauffeur c) throws SQLException {
         String req = " UPDATE chauffeur SET CIN = ? , Nom = ? , Adresse = ? , Statut_disponibilite = ?  where id_chauffeur = ?  ";
            PreparedStatement ps = con.prepareStatement(req);

        ps.setString(1, c.getCIN());
        ps.setString(2, c.getNom());
        ps.setString(3, c.getAdresse());
        ps.setString(4, c.getStatut_disponibilite());
               ps.setInt(5, c.getId_chauffeur());
        ps.executeUpdate();
    }

       @Override
    public void supprimer(int id) throws SQLException {
         String req = " DELETE FROM Chauffeur where id_chauffeur =  " +id ;
       
               PreparedStatement ps = con.prepareStatement(req);
          
             ps.executeUpdate();

    }

   @Override
    public List<Chauffeur> recuperer(Chauffeur c) throws SQLException {
        List<Chauffeur> Chauffeur = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM Chauffeur";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
          //      Chauffeur c = new Chauffeur();
                c.setId_chauffeur(1);
                c.setCIN(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setAdresse(rs.getString(4));
                c.setStatut_disponibilite(rs.getString(5));
                
                
                Chauffeur.add(c);
            }
            
        } catch (SQLException ex) {
           // ex.printStackTrace();
        }
        
        return Chauffeur;
    }
    
       @Override
    public List<Chauffeur> readAll() throws SQLException {
        ArrayList<Chauffeur> listper = new ArrayList<>();

        String req = "select * from Chauffeur";

        ResultSet res = cnx.executeQuery(req);

        while (res.next()) {
            int a = res.getInt(1);
            String b = res.getString(2);
            String c = res.getString(3);
            String d = res.getString(4);
            String f = res.getString(5);
             
            Chauffeur p = new Chauffeur(a, b, c, d, f);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

  public int countChauffeursByType(String selectedServiceType) {
    int count = 0;
    try {
        String req = "SELECT COUNT(*) AS count FROM Chauffeur WHERE Adresse = ?";
        PreparedStatement ps;
        ps = con.prepareStatement(req);
        ps.setString(1, selectedServiceType);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException ex) {
    }
    return count;
}
  
  
  public List<Chauffeur>trierChauffeurParNom() throws SQLException {
           List<Chauffeur> list = new ArrayList<>();
        try {
            String req = "Select * from chauffeur order by Nom ";
            
            ResultSet rs = cnx.executeQuery(req);
            while (rs.next()) {
             Chauffeur p = new Chauffeur(rs.getString("CIN"),rs.getString("Adresse"),rs.getString("Statut_disponibilite"),rs.getString("Nom"));
               
               list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
  
  
  

    @Override
    public void ajouter_don(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chauffeur findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chauffeur findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> sort_date_demande_don_of_user(int id) throws SQLException {
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
    public void update(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chauffeur findById(int id) throws SQLException {
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




       



