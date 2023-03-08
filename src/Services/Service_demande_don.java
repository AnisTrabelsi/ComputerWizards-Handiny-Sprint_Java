/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Covoiturage;
import Entite.demande_don;
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

/**
 *
 * @author anis
 */
public class Service_demande_don implements IService<demande_don> {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public Service_demande_don() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    

        @Override
    public void ajouter_demande_don_admin(demande_don d) throws SQLException {
        String req = "INSERT INTO `demande_don` ( `id_utilisateur`, `id_don`, `date_demande`,`justificatif_handicap`,`type_produit_demande`,`Remarques`,`etat`) VALUES (?, ?, CURDATE(), ?,?,?,?);";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
        pre.setString(3, d.getJustificatif_handicap());
               pre.setString(4, d.getType_produit_demande());
          pre.setString(5, d.getRemarques());
pre.setString(6, d.getEtat());
        pre.executeUpdate();
    }
    
    @Override
    public void ajouter_demande_don(demande_don d) throws SQLException {
        String req = "INSERT INTO `demande_don` ( `id_utilisateur`, `id_don`, `date_demande`,`justificatif_handicap`,`type_produit_demande`,`Remarques`,`etat`) VALUES (?, ?, CURDATE(), ?,?,?,'en cours');";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
        pre.setString(3, d.getJustificatif_handicap());
               pre.setString(4, d.getType_produit_demande());
          pre.setString(5, d.getRemarques());

        pre.executeUpdate();
    }

    @Override
    public void update_demande_don(demande_don d) throws SQLException {
        String req = " UPDATE demande_don SET  id_utilisateur = ? , id_don  = ?,date_demande = CURDATE(),justificatif_handicap = ?,type_produit_demande = ? ,Remarques = ?,etat = ? WHERE id_demande_don= " + d.getId_demande_don() + ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
   
        pre.setString(3, d.getJustificatif_handicap());
         pre.setString(4, d.getType_produit_demande());
           pre.setString(5, d.getRemarques());
        pre.setString(6, d.getEtat());
        pre.executeUpdate();
        System.out.println("demande_don modifié !");
    }

    
     @Override
    public void update_demande_don_admin(demande_don d) throws SQLException {
        String req = " UPDATE demande_don SET  id_utilisateur = ? , id_don  = ?,date_demande = ?,justificatif_handicap = ?,type_produit_demande = ? ,Remarques = ?,etat = ? WHERE id_demande_don= " + d.getId_demande_don() + ";";
        PreparedStatement pre = con.prepareStatement(req);
          pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
          pre.setDate(3, d.getDate_demande());
        pre.setString(4, d.getJustificatif_handicap());
               pre.setString(5, d.getType_produit_demande());
          pre.setString(6, d.getRemarques());
  pre.setString(7, d.getEtat());
        pre.executeUpdate();
        System.out.println("demande_don modifié !");
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        String req = "DELETE FROM `demande_don` WHERE id_demande_don = " + id + ";";

        if ((ste.executeUpdate(req)) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public List<demande_don> readAll_demande_don() throws SQLException {
        ArrayList<demande_don> listper = new ArrayList<>();

        String req = "select * from demande_don";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
            String t = res.getString(6);
              String rem = res.getString(7);
            String etat = res.getString(8);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif,t,rem, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public demande_don findById_demande_don(int id) throws SQLException {
        demande_don d = new demande_don();
        String req = "select * FROM `demande_don` WHERE id_demande_don= " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
           String t = res.getString(6);
              String rem = res.getString(7);
            String etat = res.getString(8);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif,t,rem, etat);
        }

        return d;
    }

    @Override
    public List<demande_don> find_demande_don_of_user(int id) throws SQLException {
        ArrayList<demande_don> listper = new ArrayList<>();

        String req = "select * from demande_don where id_utilisateur =" + id + ";";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
                String t = res.getString(6);
              String rem = res.getString(7);
            String etat = res.getString(8);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif,t,rem, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public List<demande_don> sort_date_demande_don_of_users() throws SQLException {
        ArrayList<demande_don> listper = new ArrayList<>();

        String req = "select * from demande_don ORDER BY date_demande ";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
              String t = res.getString(6);
              String rem = res.getString(7);
            String etat = res.getString(8);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif,t,rem, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
      @Override
    public List<demande_don> sort_date_demande_don_of_user(int id) throws SQLException {
        ArrayList<demande_don> listper = new ArrayList<>();

        String req = "select * from demande_don where id_utilisateur = " + id + " ORDER BY date_demande ;";

         ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
            String t = res.getString(6);
              String rem = res.getString(7);
            String etat = res.getString(8);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif,t,rem, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
      public HashMap<String,Integer> stattype() throws SQLException{
     HashMap<String,Integer> mp = new HashMap<>();
       String req = "select COUNT(*),`type_produit_demande` from demande_don GROUP BY `type_produit_demande`;";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
     mp.put(res.getString("type_produit_demande"), res.getInt("COUNT(*)"));
            
        }
    
     return mp;
     }

    @Override
    public void ajouter_don(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public demande_don findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherparcin(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_nom(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_prenom(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcin_email(String cin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherparid(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_nom(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcid_prenom(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_email(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparcid_cin(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rechercherid_parrrcin(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(demande_don t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public demande_don findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ajouter(Covoiturage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Covoiturage t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Covoiturage> sortbydate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(demande_don c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<demande_don> recuperer(demande_don c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
