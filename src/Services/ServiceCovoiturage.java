/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Covoiturage;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author sanabenfadhel
 */
public class ServiceCovoiturage implements IService<Covoiturage>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Covoiturage p;

    public ServiceCovoiturage() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public boolean  ajouter(Covoiturage c) throws SQLException
    {
                boolean verif=true;

    try {   String req = "INSERT INTO `Covoiturage`(`id_cov`, `id_utilisateur`, `depart`, `destination`, `date_covoiturage`, `Prix` , `nbrplace`, `nom` , `telephone`) VALUES ( ?,?,?,?,?,?,?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
            pre.setInt(1, c.getId_cov());

    pre.setInt(2, c.getId_utilisateur());
        pre.setString(3, c.getDepart());
        pre.setString(4, c.getDestination());
         pre.setDate(5,(c.getDate_covoiturage()));
        
        pre.setInt(6, c.getPrix());
                pre.setInt(7, c.getNbrplace());
 pre.setString(8, c.getNom());
        pre.setString(9, c.getTelephone());
         int rowsInserted = pre.executeUpdate();
     if (rowsInserted > 0) {
         
                           }
            
     }catch(SQLException e){
            System.out.println(e);
            verif=false;
        }
    return verif;
    }
    

    @Override
    public void update(Covoiturage c) throws SQLException {
        String req="UPDATE `Covoiturage` SET `id_utilisateur`=?,`depart`=?,`destination`=?,`date_covoiturage`=?,`Prix`=? ,`nbrplace`=? where `id_cov` ="+c.getId_cov()+"";
        
        PreparedStatement pre=con.prepareStatement(req);

    pre.setInt(1, c.getId_utilisateur());
        pre.setString(2, c.getDepart());
        pre.setString(3, c.getDestination());
         pre.setDate(4,(c.getDate_covoiturage()));
        
        pre.setInt(5, c.getPrix());
                pre.setInt(6, c.getNbrplace());
        pre.executeUpdate();
        System.out.println("covoiturage modifié !");

    }

    @Override
public boolean supprime(int id) throws SQLException {

        String req = "DELETE FROM `Covoiturage` WHERE id_cov = " + id + ";";

        if ((ste.executeUpdate(req)) == 1) {
            return true;
        }

        return false;

    }
    public List<Covoiturage> readAll() throws SQLException {
        ArrayList<Covoiturage> listper = new ArrayList<>();

        String req = "select * from Covoiturage";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_cov = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            Date date_covoiturage = res.getDate(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
 String nom = res.getString(8);
            String telephone = res.getString(9);
            Covoiturage c = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage ,Prix , nbrplace , nom , telephone);
            // System.out.println(p);
            listper.add(c);
        }
        return listper;
    }

    @Override
    public Covoiturage findById(int id) throws SQLException {
Covoiturage d=new Covoiturage(); 
        String req = "select * FROM Covoiturage where id_cov = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
       int id_cov = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            Date date_covoiturage = res.getDate(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
            String nom = res.getString(8);
            String telephone = res.getString(9);
         d = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage , Prix , nbrplace, nom , telephone);
    }
        
        return d;
    }
    
    
      public List<Covoiturage> sortbydate() throws SQLException {
        ArrayList<Covoiturage> listper = new ArrayList<>();

        String req = "select * from Covoiturage ORDER by Prix";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
           int id_cov = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            Date date_covoiturage = res.getDate(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
             String nom = res.getString(8);
            String telephone = res.getString(9);
            Covoiturage p = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage , Prix , nbrplace, nom , telephone);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
}
