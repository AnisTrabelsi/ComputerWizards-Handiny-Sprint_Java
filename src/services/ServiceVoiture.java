/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Entite.Utilisateur;
import Entite.Voiture;
import utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Chaima
 */
public class ServiceVoiture implements IService<Voiture> {
Connection con=DataSource.getInstance().getConnection();

    
    private Statement ste;

    public ServiceVoiture() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public boolean ajouter(Voiture t) throws SQLException {
        boolean verif=true;
  java.util.Date date_util = new java.util.Date();
  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
    try { String req = "INSERT INTO `voiture` (`immatriculation`,`marque`,`modele`,`boite_vitesse`,`kilometrage`,`carburant`,`image_voiture`,`description`,`prix_location`,`date_validation_technique`,`id_utilisateur`) VALUES ( ?,?,?,?,?,?,?,?,?,?,?);";
        
        PreparedStatement pre=con.prepareStatement(req);
        
     
        pre.setString(1,t.getImmatriculation());
        pre.setString(2, t.getMarque());
        pre.setString(3, t.getModele());
        pre.setString(4, t.getBoite_vitesse());
        pre.setString(5, t.getKilometrage());
        pre.setString(6, t.getCarburant());
        pre.setString(7, t.getImage_voiture());
        pre.setString(8, t.getDescription());
        pre.setDouble(9, t.getPrix_location());
        pre.setDate(10,new java.sql.Date(t.getDate_validation_technique().getTime()));
        pre.setInt(11,t.getUser().getId_utilisateur());
       
     

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
    

    @Override
    public boolean update(Voiture t) throws SQLException {
     boolean verif=true;
     
        
        try {
            
          String req = "UPDATE voiture SET immatriculation=?, marque=?,modele=?,boite_vitesse=?, kilometrage=?,carburant=?, image_voiture=?, description=?, prix_location=?, date_validation_technique=? WHERE id_voiture=?";
          PreparedStatement pre = con.prepareStatement(req);
        
        pre.setString(1,t.getImmatriculation());
        pre.setString(2, t.getMarque());
        pre.setString(3, t.getModele());
        pre.setString(4, t.getBoite_vitesse());
        pre.setString(5, t.getKilometrage());
        pre.setString(6, t.getCarburant());
        pre.setString(7, t.getImage_voiture());
        pre.setString(8, t.getDescription());
        pre.setDouble(9, t.getPrix_location());
        pre.setDate(10,new java.sql.Date(t.getDate_validation_technique().getTime()));
        pre.setInt(11,t.getId_voiture());
        

        int rowsUpdated = pre.executeUpdate();
        if (rowsUpdated > 0) {
            verif=true;
        }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            verif=false;
        }
        return verif;
        
        
    }


    @Override
    public void supprime(Voiture t) throws SQLException {
       
        try {
            String req = "DELETE FROM `voiture` WHERE id_voiture = ?";
            PreparedStatement statement = con.prepareStatement(req);
            statement.setInt(1,t.getId_voiture());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A car was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }
        
    }
    
    
     public boolean delete(int id) throws SQLException {
    String req = "DELETE FROM `voiture` WHERE id_voiture = " + id + ";";
    int rowsDeleted = ste.executeUpdate(req);

    if (rowsDeleted == 1) {
        System.out.println("La voiture avec l'ID " + id + " a été supprimée.");
        return true;
    } else if (rowsDeleted == 0) {
        System.out.println("Aucune voiture n'a été trouvée avec l'ID " + id + ".");
    } else {
        System.out.println("Plus d'une voiture a été supprimée - lignes affectées : " + rowsDeleted + ".");
    }

    return false;
}
    


    @Override
    public List<Voiture> readAll() throws SQLException {
    
        List<Voiture> list = new ArrayList<>();
        try {
            String req = "Select * from voiture";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
              Voiture v = new Voiture(rs.getInt("id_voiture"),rs.getString("immatriculation"),rs.getString("marque"),rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("kilometrage"),rs.getString("carburant"),rs.getString("image_voiture"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
               //Voiture v = new Voiture(rs.getInt("id_voiture"),rs.getString("marque"), rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
   

    public List<Voiture> trierParModele() throws SQLException {
        List<Voiture> voitures = readAll();
        List<Voiture> voituresTrie = voitures.stream()
            .sorted(Comparator.comparing(Voiture::getModele))
            .collect(Collectors.toList());
    return voituresTrie;
}
    public List<Voiture> filtre(String searchText)throws SQLException{
        List<Voiture> voitures = readAll();
        List<Voiture> filteredVoitures = voitures.stream()
                .filter(v -> v.getMarque().toLowerCase().contains(searchText) || v.getModele().toLowerCase().contains(searchText)||v.getBoite_vitesse().toLowerCase().contains(searchText)||v.getCarburant().toLowerCase().contains(searchText)||Double.toString(v.getPrix_location()).contains(searchText)|| v.getDescription().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        return filteredVoitures;
    }
     public List<Voiture> trierParMarque() throws SQLException {
        List<Voiture> voitures = readAll();
        List<Voiture> voituresTrie = voitures.stream()
            .sorted(Comparator.comparing(Voiture::getMarque))
            .collect(Collectors.toList());
    return voituresTrie;
}
      public List<Voiture> trierParPrix() throws SQLException {
        List<Voiture> voitures = readAll();
        List<Voiture> voituresTrie = voitures.stream()
            .sorted(Comparator.comparing(Voiture::getPrix_location))
            .collect(Collectors.toList());
    return voituresTrie;
}

   @Override
public Voiture findById(int id) throws SQLException {
    Voiture v = null;
    try {
        String req = "SELECT * FROM `voiture` v JOIN `utilisateur` u ON v.id_utilisateur = u.id_utilisateur WHERE v.id_voiture = " + id + ";";

        ResultSet rs = ste.executeQuery(req);
        if (rs.next()) {
            Utilisateur u = new Utilisateur(
                rs.getInt("u.id_utilisateur"),
                rs.getString("u.nom"),
                rs.getString("u.prenom"),
                rs.getString("u.cin"),
                rs.getString("u.email"),
                rs.getString("u.telephone"),
                rs.getString("u.login"),
                rs.getString("u.mot_de_passe"),
                rs.getDate("u.date_de_naissance"),
                rs.getString("u.pays"),
                rs.getInt("u.code_postal"),
                rs.getString("u.role"),
                rs.getString("u.adresse")
            );
            v = new Voiture(rs.getInt("v.id_voiture"), rs.getString("v.immatriculation"), rs.getString("v.marque"), rs.getString("v.modele"), rs.getString("v.boite_vitesse"), rs.getString("v.kilometrage"), rs.getString("v.carburant"), rs.getString("v.image_voiture"), rs.getString("v.description"), rs.getDouble("v.prix_location"), rs.getDate("v.date_validation_technique"), u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return v;
}
public Voiture findById2(int id) throws SQLException {
        Voiture v = null;
        try {
            String req = "Select * from `voiture` WHERE id_voiture = " + id + ";";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                // v = new Voiture(rs.getInt("id_voiture"),rs.getString("immatriculation"),rs.getString("marque"), rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("kilometrage"),rs.getString("carburant"),rs.getString("image_voiture"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
                 v = new Voiture(rs.getInt("id_voiture"),rs.getString("immatriculation"), rs.getString("marque"), rs.getString("modele"),rs.getString("boite_vitesse"),rs.getString("kilometrage"),rs.getString("carburant"),rs.getString("image_voiture"),rs.getString("description"),rs.getDouble("prix_location"),rs.getDate("date_validation_technique"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return v;
    }




    public List<Voiture> searchByModele(String searchString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
    

