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
import java.util.Date;
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

    try {   String req = "INSERT INTO `Covoiturage`(`id_cov`, `id_utilisateur`, `depart`, `destination`, `date_covoiturage`, `Prix` , `nbrplace`) VALUES ( ?,?,?,?,?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
            pre.setInt(1, c.getId_cov());

    pre.setInt(2, c.getId_utilisateur());
        pre.setString(3, c.getDepart());
        pre.setString(4, c.getDestination());
         pre.setString(5,(c.getDate_covoiturage()));
        
        pre.setInt(6, c.getPrix());
                pre.setInt(7, c.getNbrplace());

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
         pre.setString(4,(c.getDate_covoiturage()));
        
        pre.setInt(5, c.getPrix());
                pre.setInt(6, c.getNbrplace());
        pre.executeUpdate();
        System.out.println("covoiturage modifié !");

    }

    @Override
 public boolean supprime(Covoiturage c) throws SQLException {
        try{
            String req ="DELETE FROM `Covoiturage` WHERE id_cov ="+c.getId_cov()+"";
         Statement state;
         Connection cnx=DataSource.getInstance().getConnection();
         state=cnx.createStatement();
         state.execute(req);
         return true;
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
     @Override
    public List<Covoiturage> readAll() throws SQLException {
        ArrayList<Covoiturage> listper = new ArrayList<>();

        String req = "select * from Covoiturage";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_cov = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            String date_covoiturage = res.getString(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
 
            Covoiturage c = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage ,Prix , nbrplace );
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
            String date_covoiturage = res.getString(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
         d = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage , Prix , nbrplace);
    }
        
        return d;
    }
    
    
      public List<Covoiturage> sortbydate() throws SQLException {
        ArrayList<Covoiturage> listper = new ArrayList<>();

        String req = "select * from don ORDER by Prix";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
           int id_cov = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String depart = res.getString("depart");
            String destination = res.getString(4);
            String date_covoiturage = res.getString(5);
            int Prix = res.getInt(6);
            int nbrplace = res.getInt(7);
            Covoiturage p = new Covoiturage(id_cov, id_utilisateur, depart, destination, date_covoiturage , Prix , nbrplace);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
}
