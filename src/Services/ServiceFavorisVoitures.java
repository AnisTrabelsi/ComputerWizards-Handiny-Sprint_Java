/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entite.Voiture;
import Entite.Utilisateur;
import Entite.Favoris_voitures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Utils.DataSource;

/**
 *
 * @author Chaima
 */
public class ServiceFavorisVoitures implements IService<Favoris_voitures> {
Connection con=DataSource.getInstance().getConnection();

    
    private Statement ste;

    public  ServiceFavorisVoitures(){
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 

    
    public boolean ajouter(Favoris_voitures t) throws SQLException {
         boolean verif=true;
      
      try { String req = "INSERT INTO `favoris_voitures`(`id_user`, `id_voiture`,`date_ajout_favoris`) VALUES (?,?,CURDATE());";
        
    PreparedStatement pre=con.prepareStatement(req);
        
     
    
   
     pre.setInt(1,t.getUser().getId_utilisateur());
     pre.setInt(2,t.getVoit().getId_voiture());
    
     
     
        
     

     int rowsInserted = pre.executeUpdate();
     if (rowsInserted > 0) {
         verif=true;
                           }
            
     }catch(SQLException e){
            System.out.println(e);
            verif=false;
        }
    return verif;
    }

    
    public boolean update(Favoris_voitures t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void supprime(Favoris_voitures t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public List<Favoris_voitures> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public Favoris_voitures findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
   