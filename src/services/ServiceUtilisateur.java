/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entite.Utilisateur;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Chaima
 */
public class ServiceUtilisateur implements IService <Utilisateur>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceUtilisateur() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    
    
  public void ajouter(Utilisateur u) throws SQLException {
        
     
String req = "INSERT INTO `utilisateur`(`id_utilisateur`, `nom`, `prenom`, `cin`, `email`, `telephone`, `login`, `mot_de_passe`, `date_de_naissance`, `pays`, `adresse`,`code_postal`, `role`)"
        +"VALUES ( '"+u.getId_utilisateur()+"','"+u.getNom()+"', '"+u.getPrenom()+"', '"+u.getCin()+"', '"+u.getEmail()+"', '"+u.getTelephone()+"', '"+u.getLogin()+"', '"+u.getMot_de_passe()+"', '"+u.getDate_de_naissance()+"', '"+u.getPays()+"', '"+u.getAdresse()+"', '"+u.getCode_postal()+"', '"+u.getRole()+"');";
      
        ste.executeUpdate(req);
   
    }  
    @Override
    public void update(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprime(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Utilisateur> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Utilisateur findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}