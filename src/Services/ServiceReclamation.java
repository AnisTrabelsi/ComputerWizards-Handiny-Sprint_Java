/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Reclamation;
import Entite.Utilisateur;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chayma
 */
public class ServiceReclamation implements IService <Reclamation>{
    Reclamation r = new Reclamation  ();

    
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste ;

    public ServiceReclamation() {
        try {
            ste =con.createStatement();
        } catch (SQLException ex) {
       System.out.println(ex) ;        
        }
    }
        
//////    public boolean ajouter(Voiture t) throws SQLException {
//////        boolean verif=true;
//////  java.util.Date date_util = new java.util.Date();
//////  java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
//////    try { String req = "INSERT INTO voiture (immatriculation,`marque`,`modele`,`boite_vitesse`,`kilometrage`,`carburant`,`image_voiture`,`description`,`prix_location`,`date_validation_technique`,`id_utilisateur`) VALUES ( ?,?,?,?,?,?,?,?,?,?,?);";
//////        
//////        PreparedStatement pre=con.prepareStatement(req);
//////        
//////     
//////        pre.setString(1,t.getImmatriculation());
//////        pre.setString(2, t.getMarque());
//////        pre.setString(3, t.getModele());
//////        pre.setString(4, t.getBoite_vitesse());
//////        pre.setString(5, t.getKilometrage());
//////        pre.setString(6, t.getCarburant());
//////        pre.setString(7, t.getImage_voiture());
//////        pre.setString(8, t.getDescription());
//////        pre.setDouble(9, t.getPrix_location());
//////        pre.setDate(10,new java.sql.Date(t.getDate_validation_technique().getTime()));
//////        pre.setInt(11,t.getUser().getId_utilisateur());
//////       
//////     
//////
//////     int rowsInserted = pre.executeUpdate();
//////     if (rowsInserted > 0) {
//////         verif=true;
//////                           }
//////            
//////     }catch(SQLException e){
//////            System.out.println(e);
//////            verif=false;
//////        }
//////    return verif;
//////    }


  @Override
    public void ajouter(Reclamation r) throws SQLException {


    try {String req = "INSERT INTO reclamation (`id_utilisateur`, `type_reclamation`, `etat_reclamation`, `description`) VALUES (3,?,?,?);";
     PreparedStatement pre=con.prepareStatement(req);
    // pre.setInt(1,r.getUser().getId_utilisateur());
     pre.setString(1, r.getType_reclamation());
     pre.setString(2, r.getEtat_reclamation());
     pre.setString(3, r.getDescription());
     pre.executeUpdate();
  
            System.out.println(r.toString());
     }catch(SQLException e){
            System.out.println(e);
     
    }
    
    
//    {
//       // `id_utilisateur`,
//    String req = "INSERT INTO `reclamation` (`id_utilisateur`,`type_reclamation`, `etat_reclamation`, `description`) VALUES (?,?,?,?);";
//
//     PreparedStatement pre=con.prepareStatement(req);
//    // pre.setInt(1,r.getId_reclamation());
//     pre.setInt(1,r.getId_utilisateur());
//     pre.setString(2, r.getType_reclamation());
//     pre.setString(3, r.getEtat_reclamation());
//     pre.setString(4, r.getDescription());
//     
//     pre.executeUpdate();
//        System.out.println("Une reclamation est ajoutée ");
//    }
//    }
    }

    @Override
    public void update(Reclamation r) throws SQLException {

try {
            String req = "UPDATE `reclamation` SET `type_reclamation` = '" + r.getType_reclamation() + "', `etat_reclamation` = '" + r.getEtat_reclamation() + "', `description` = '"+ r.getDescription()  + "' WHERE `reclamation`.`id_reclamation` = " + r.getId_reclamation();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation mise à jour ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public boolean supprime(Reclamation r) throws SQLException {
//}

    @Override
    public List<Reclamation> readAll() throws SQLException {

         ArrayList<Reclamation> listreclamation=new ArrayList<>();
        
        String req=" SELECT * FROM `Reclamation` ";
        Statement statement = con.createStatement();
        ResultSet res=ste.executeQuery(req);
        
        while (res.next()) {   
            
            int id_utilisateur  =res.getInt(1);
            String type_reclamation =res.getString(2);
            String etat_reclamation=res.getString(3);
            String description = res.getString(4);
            Reclamation r = new Reclamation (type_reclamation,  etat_reclamation, description);
            System.out.println(r);
            listreclamation.add(r);
        }
            return listreclamation;
    }

    @Override
    public Reclamation findById(int id_reclamation) throws SQLException {
    Reclamation r = new Reclamation ();
    Utilisateur u = new Utilisateur ();
    String req = "SELECT * FROM `reclamation`WHERE  id_reclamation =" +id_reclamation ;
    Statement ste = con.createStatement();
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
            int id_utilisateur = res.getInt(2);
            String type_reclamation =res.getString(3);
            String etat_reclamation=res.getString(4);
            String description = res.getString(5);
           // System.out.println(r);
    Reclamation r1 = new Reclamation (id_utilisateur,type_reclamation, etat_reclamation,description,u);
    r=r1 ;
    }
    return r ;
    }
    
    public Reclamation findById_Utilisateur(int id_utilisateur) throws SQLException {
    Reclamation r = new Reclamation ();
    Utilisateur u = new Utilisateur ();
    String req = "SELECT * FROM `reclamation`WHERE  id_utilisateur =" +id_utilisateur ;
    Statement ste = con.createStatement();
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
            int id_reclamation = res.getInt(1);
            String type_reclamation =res.getString(3);
            String etat_reclamation=res.getString(4);
            String description = res.getString(5);
           // System.out.println(r);
    Reclamation r1 = new Reclamation (id_reclamation,type_reclamation, etat_reclamation,description,u);
    r=r1 ;
    }
    return r ;
    }

    @Override
    public boolean supprime(Reclamation r) throws SQLException {
try {
            String req = "DELETE FROM `Reclamation` WHERE id_reclamation = " + r.getId_reclamation();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("la reclamation est suprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    return true ;    }
      
        
}
    

   
    

