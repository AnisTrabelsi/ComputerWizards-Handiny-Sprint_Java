/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Entite.Reservation_voiture;
import Entite.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Chaima
 */
public class ServiceReservation_Voiture implements IService <Reservation_voiture> {
Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceReservation_Voiture() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } {
    
}

    @Override
    public boolean ajouter(Reservation_voiture t) throws SQLException {
      boolean verif=true;
      
      try { String req = "INSERT INTO `reservation_voiture`(`date_debut_reservation`, `date_fin_reservation`, `id_utilisateur`, `id_voiture`,`description_reservation`, `etat_demande_reservation`, `date_demande_reservation`) VALUES (?,?,?,?,?,'en attente',CURDATE());";
        
        PreparedStatement pre=con.prepareStatement(req);
        
     
    
     pre.setDate(1,new java.sql.Date(t.getDate_debut_reservation().getTime()));
     pre.setDate(2,new java.sql.Date(t.getDate_fin_reservation().getTime()));
     pre.setInt(3,t.getUser().getId_utilisateur());
     pre.setInt(4,t.getVoit().getId_voiture());
     pre.setString(5,t.getDescription_reservation());
     
     
        
     

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
    public boolean update(Reservation_voiture t) throws SQLException {
        boolean verif=true;
     
        
        try {
            
          String req = "UPDATE reservation_voiture SET `date_debut_reservation`=?, `date_fin_reservation`=?, `description_reservation`=? WHERE id_reservation_voiture=? ";
          PreparedStatement pre = con.prepareStatement(req);
        
        pre.setDate(1,new java.sql.Date(t.getDate_debut_reservation().getTime()));
        pre.setDate(2,new java.sql.Date(t.getDate_fin_reservation().getTime()));
          pre.setString(3,t.getDescription_reservation());
          pre.setInt(4,t.getId_reservation_voiture());
        

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
    public void supprime(Reservation_voiture t) throws SQLException {
       
       
        try {
            String req = "DELETE FROM `reservation_voiture` WHERE id_reservation_voiture = " + t.getId_reservation_voiture();
            
            ste.executeUpdate(req);
            
            System.out.println("réservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation_voiture> readAll() throws SQLException {
       
    
        List<Reservation_voiture> list = new ArrayList<>();
        try {
            String req = "Select * from reservation_voiture ";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
               Reservation_voiture p = new Reservation_voiture(rs.getInt("id_reservation_voiture"),rs.getDate("date_debut_reservation"), rs.getDate("date_fin_reservation"),rs.getString("description_reservation"),rs.getString("etat_demande_reservation"),rs.getDate("date_demande_reservation"));
               
               list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Reservation_voiture findById(int id) throws SQLException {
        Reservation_voiture v = null;
        try {
            String req = "Select * from `reservation_voiture` WHERE id_reservation_voiture = " + id + ";";
            
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                       v = new Reservation_voiture(rs.getInt("id_reservation_voiture"),rs.getDate("date_debut_reservation"), rs.getDate("date_fin_reservation"),rs.getString("description_reservation"),rs.getString("etat_demande_reservation"),rs.getDate("date_demande_reservation"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return v;
    }
    public double calculerPrixTotal(Voiture v, LocalDate debut, LocalDate fin) {
    double prixParJour = v.getPrix_location();
    double prixTotal=0;
    if (debut != null && fin != null && debut.isBefore(fin)) {
        
        /*Cette ligne calcule la différence en nombre de jours entre deux dates, en utilisant la classe Duration de Java 8.

debut.atStartOfDay() et fin.atStartOfDay() retournent des objets LocalDateTime représentant le début de la journée des dates sélectionnées (00h00).

La méthode Duration.between() calcule la durée entre ces deux objets, et retourne un objet Duration qui représente cette durée. Enfin, la méthode toDays() retourne le nombre de jours entre les deux dates.*/
        long daysBetween = Duration.between(debut.atStartOfDay(), fin.atStartOfDay()).toDays();
         prixTotal = prixParJour * daysBetween;}
    return prixTotal;
}

}
    
