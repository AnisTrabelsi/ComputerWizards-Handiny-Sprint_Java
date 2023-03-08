/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author bengh
 */
import Entite.PostsSauvegardés;
import Entite.Utilisateur;
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
 * @author bengh
 */
public class ServiceSauvegarde implements IService<PostsSauvegardés> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceSauvegarde() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(PostsSauvegardés t) throws SQLException {
        String req = "INSERT INTO `PostsSauvegardés` (`id_utilisateur`, `id_sujet`, `date_ajout_sauvegarde`) VALUES (?, ?, CURDATE());";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, t.getUser().getId_utilisateur());
        pre.setInt(2, t.getSujet().getId_sujet());
        pre.executeUpdate();
    }

    @Override
    public void update(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> readAll() throws SQLException {
        ArrayList<PostsSauvegardés> listsauv = new ArrayList<>();
        ServiceSujet ss = new ServiceSujet();
        ServiceUtilisateur su = new ServiceUtilisateur();
        String req = "select * from `PostsSauvegardés`;";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            PostsSauvegardés ps = new PostsSauvegardés(su.findById(res.getInt("id_utilisateur")), ss.findById(res.getInt("id_sujet")));
//            ps.setSujet(ss.findById(res.getInt("id_sujet")));
//            ps.setUser(su.findById(res.getInt("id_utilisateur")));
            System.out.println(ps.toString());
            listsauv.add(ps);
        }
        return listsauv;
    }

    public List<PostsSauvegardés> readAllByUser(Utilisateur u) throws SQLException {
        ArrayList<PostsSauvegardés> listsauv = new ArrayList<>();
        ServiceSujet ss = new ServiceSujet();
        ServiceUtilisateur su = new ServiceUtilisateur();
        String req = "SELECT * FROM `postssauvegardés` WHERE `id_utilisateur` = ? ;";
        PreparedStatement preparedStatement = con.prepareStatement(req);
        preparedStatement.setInt(1, u.getId_utilisateur());
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            PostsSauvegardés ps = new PostsSauvegardés();
            ps.setSujet(ss.findById(res.getInt("id_sujet")));
            ps.setUser(su.findById(res.getInt("id_utilisateur")));
            System.out.println(ps.toString());
            listsauv.add(ps);
        }

        return listsauv;
    }

    @Override
    public PostsSauvegardés findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_don(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PostsSauvegardés findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(PostsSauvegardés t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PostsSauvegardés findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PostsSauvegardés> sort_date_demande_don_of_user(int id) throws SQLException {
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

}
