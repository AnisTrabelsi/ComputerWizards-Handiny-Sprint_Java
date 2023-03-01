/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.demande_don;
import Entite.don;
import Entite.utilisateur;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anis
 */
public class Service_utilisateur implements IService_utilisateur<utilisateur> {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public Service_utilisateur() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    

    @Override
    public int rechercherparcin(String cin) throws SQLException {
   
    int id=0;
        String req = "select * FROM `utilisateur` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         id = res.getInt(1);
      
    }
        
        return id;
    }

    @Override
    public String rechercherparcin_nom(String cin) throws SQLException {
          String s="";
        String req = "select * FROM `utilisateur` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("nom");
      
    }
        
        return s;
    }

    @Override
    public String rechercherparcin_prenom(String cin) throws SQLException {
          String s="";
        String req = "select * FROM `utilisateur` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("prenom");
      
    }
        
        return s;
    }

    @Override
    public String rechercherparcin_email(String cin) throws SQLException {
          String s="";
        String req = "select * FROM `utilisateur` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("email");
      
    }
        
        return s;
    }

    @Override
    public int rechercherparid(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_nom(int id) throws SQLException {
             String s="";
        String req = "select * FROM `utilisateur` where id_utilisateur = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("nom");
      
    }
        
        return s;
    }

    
    
    
    @Override
    public String rechercherparcid_cin(int id) throws SQLException {
       String s="";
        String req = "select * FROM `utilisateur` where id_utilisateur = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("cin");
      
    }
        
        return s;
    }

    @Override
    public String rechercherparid_email(int id) throws SQLException {
            String s="";
        String req = "select * FROM `utilisateur` where id_utilisateur = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("email");
      
    }
        
        return s;
    }

    @Override
    public String rechercherparcid_prenom(int id) throws SQLException {
 String s="";
        String req = "select * FROM `utilisateur` where id_utilisateur = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         s = res.getString("prenom");
      
    }
        
        return s;    }

    @Override
    public int rechercherid_parrrcin(String id) throws SQLException {
          int a = 0;
        String req = "select * FROM `utilisateur` where cin = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
         a = res.getInt("id_utilisateur");
      
    }
        
        return a;
    }
}
