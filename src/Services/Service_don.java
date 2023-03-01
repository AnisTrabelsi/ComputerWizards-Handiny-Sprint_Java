/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.don;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anis
 */
public class Service_don implements IService_don<don> {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public Service_don() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    
      @Override
    public void ajouter_don(don d) throws SQLException {

        String req = "INSERT INTO `don` (`id_utilisateur`, `type`, `image_don`, `description`,`date_ajout`) VALUES (?,?,?,?,CURDATE());";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getId_utilisateur());
        pre.setString(2, d.getType());
        pre.setString(3, d.getImage_don());
        pre.setString(4, d.getDescription());
      
        pre.executeUpdate();

    }
    
    
    @Override
    public void modifier_don_admin(don d) throws SQLException {
        String req = " UPDATE don SET id_utilisateur= ?, type = ? , image_don  = ?,description = ?,date_ajout = ? WHERE id_don= " + d.getId_don() + ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_utilisateur());
        pre.setString(2, d.getType());
        pre.setString(3, d.getImage_don());
        pre.setString(4, d.getDescription());
        pre.setDate(5, d.getDate_ajout());
        pre.executeUpdate();
        System.out.println("don modifié !");

    }
    
    
    
    @Override
    public void modifier_don(don d) throws SQLException {
        String req = " UPDATE don SET id_utilisateur= ?, type = ? , image_don  = ?,description = ?,date_ajout = CURDATE() WHERE id_don= " + d.getId_don() + ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_utilisateur());
        pre.setString(2, d.getType());
        pre.setString(3, d.getImage_don());
        pre.setString(4, d.getDescription());
        pre.executeUpdate();
        System.out.println("don modifié !");

    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {

        String req = "DELETE FROM `don` WHERE id_don = " + id + ";";

        if ((ste.executeUpdate(req)) == 1) {
            return true;
        }

        return false;

    }

    @Override
    public List<don> readAll_don() throws SQLException {
        ArrayList<don> listper = new ArrayList<>();

        String req = "select * from don";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String type = res.getString("type");
            String image_don = res.getString(4);
            String Description = res.getString(5);
              Date datee = res.getDate(6);
            don p = new don(id_don, id_utilisateur, type, image_don, Description,datee);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    
    
        @Override
    public List<don> sortbydate_don() throws SQLException {
        ArrayList<don> listper = new ArrayList<>();

        String req = "select * from don ORDER by date_ajout";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String type = res.getString("type");
            String image_don = res.getString(4);
            String Description = res.getString(5);
              Date datee = res.getDate(6);
            don p = new don(id_don, id_utilisateur, type, image_don, Description,datee);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
    
      @Override
    public List<don> findbydate_don(Date d) throws SQLException {
        ArrayList<don> listper = new ArrayList<>();

        String req = "select * from don where date_ajout = " + d + ";" ;

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String type = res.getString("type");
            String image_don = res.getString(4);
            String Description = res.getString(5);
              Date datee = res.getDate(6);
            don p = new don(id_don, id_utilisateur, type, image_don, Description,datee);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
    
    
    @Override
    public don findById_don(int id) throws SQLException {
don d=new don(); 
        String req = "select * FROM `don` where id_don = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
        int id_don = res.getInt(1);
        int id_utilisateur = res.getInt(2);
        String type = res.getString("type");
        String image_don = res.getString(4);
        String Description = res.getString(5);
        Date datee = res.getDate(6);
         d = new don(id_don, id_utilisateur, type, image_don, Description,datee);
    }
        
        return d;
    }

    
     @Override
    public List<don> findbytype(String s) throws SQLException {
        ArrayList<don> listper = new ArrayList<>();

        String req = "SELECT * FROM `don` WHERE type = '" + s + "';";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            String type = res.getString("type");
            String image_don = res.getString(4);
            String Description = res.getString(5);
              Date datee = res.getDate(6);
            don p = new don(id_don, id_utilisateur, type, image_don, Description,datee);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
    
     public HashMap<String,Integer> stattype() throws SQLException{
     HashMap<String,Integer> mp = new HashMap<String,Integer>();
       String req = "select COUNT(*),`type` from don GROUP BY `type`;";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
     mp.put(res.getString("type"), res.getInt("COUNT(*)"));
            
        }
    
     return mp;
     }
    
    
    
}
