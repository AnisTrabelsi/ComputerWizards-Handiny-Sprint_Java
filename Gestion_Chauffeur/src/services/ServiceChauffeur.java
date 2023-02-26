/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
//import utils.MyConnection;
import entities.Chauffeur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;
//import modules.Chauffeur;

/**
 *
 * @author MSI
 */
public class ServiceChauffeur implements IServices <Chauffeur> {
    
       Connection con = DataSource.getInstance().getCnx();
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
        
        String req = "INSERT INTO `chauffeur` ( `CIN`, `Nom`, `Adresse`,`Statut_disponibilite`) VALUES (?, ?, ?, ?) ; ";
        PreparedStatement pre = con.prepareStatement(req);
        
        pre.setString(1, c.getCIN());
        pre.setString(2, c.getNom());
        pre.setString(3, c.getAdresse());
        pre.setString(4, c.getStatut_disponibilite());
        pre.executeUpdate();
       
        }
    
    
    

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

       
}


