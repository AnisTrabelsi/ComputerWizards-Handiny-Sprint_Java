/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Chauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import services.ServiceChauffeur;
import utils.DataSource;
/**
 *
 * @author Mehdi
 */
public class ServiceChauffeur implements IService<Chauffeur> {

                    Connection cnx;
             public ServiceChauffeur(){
             cnx = DataSource.getInstance().getCnx();
             }

    @Override
    public void ajouter(Chauffeur t) throws SQLException {
        String req = "INSERT INTO  chauffeur (CIN,Nom,Adresse,Statut_disponibilite) VALUES("
         + "'" + t.getCIN() + "','" + t.getNom()+ "','" + t.getAdresse()+ "','" + t.getStatut_disponibilite()+ "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    
    @Override
    public void modifier(Chauffeur t) throws SQLException {
        String req = " UPDATE chauffeur SET CIN = ? , Nom = ? , Adresse = ? , Statut_disponibilite = ?   where id_chauffeur = ?    ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getCIN());
        ps.setString(2, t.getNom());
        ps.setString(3, t.getAdresse());
        ps.setString(4, t.getStatut_disponibilite());
        ps.setInt(5, t.getId_chauffeur());
        ps.executeUpdate();
    }

    
    @Override
    public void supprimer(Chauffeur t) throws SQLException {
        String req = " DELETE FROM chauffeur where id_chauffeur = ?   ";
             PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, t.getId_chauffeur());
             ps.executeUpdate();
    }

    
    @Override
    public List<Chauffeur> readAll (Chauffeur t) throws SQLException {
        List<Chauffeur> Chauffeur = new ArrayList<>();
        String s = "select * from chauffeur";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Chauffeur c = new Chauffeur();
            c.setId_chauffeur(rs.getInt("id_chauffeur"));
            c.setCIN(rs.getString("CIN"));
            c.setNom(rs.getString("Nom"));
            c.setAdresse(rs.getString("Adresse"));
            c.setStatut_disponibilite(rs.getString("Statut_disponibilite"));
    
            Chauffeur.add(c);
            
        }
        return Chauffeur;   
    }
    
    
    
}
