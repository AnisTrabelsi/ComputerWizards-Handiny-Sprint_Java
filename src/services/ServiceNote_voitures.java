/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entite.Note_voitures;
import Entite.Reservation_voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.DataSource;

/**
 *
 * @author Chaima
 */
public class ServiceNote_voitures implements IService<Note_voitures> {
    

Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceNote_voitures() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } 

    @Override
    public boolean ajouter(Note_voitures t) throws SQLException {
       boolean verif=true;
      
      try { String req = "INSERT INTO `note_voitures`(`id_utilisateur`, `id_voiture`,`note`) VALUES (?,?,?);";
        
        PreparedStatement pre=con.prepareStatement(req);
        
     
    

     pre.setInt(1,t.getUser().getId_utilisateur());
     pre.setInt(2,t.getVoit().getId_voiture());
     pre.setDouble(3,t.getNote());
     
        
     

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
    public Map<String, Double> calculerNoteMoyenneParMarque() throws SQLException {
    Map<String, Double> result = new HashMap<>();
    try {
        String req = "SELECT v.marque, AVG(nv.note) AS note_moyenne " +
                     "FROM note_voitures nv " +
                     "JOIN voiture v ON nv.id_voiture = v.id_voiture " +
                     "GROUP BY v.marque";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            String marque = rs.getString("marque");
            double noteMoyenne = rs.getDouble("note_moyenne");
            result.put(marque, noteMoyenne);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return result;
}



    @Override
    public boolean update(Note_voitures t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprime(Note_voitures t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Note_voitures> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Note_voitures findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
