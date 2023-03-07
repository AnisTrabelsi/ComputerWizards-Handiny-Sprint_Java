/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.PostsSauvegardés;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String req = "select * from PostsSauvegardés";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            PostsSauvegardés ps = new PostsSauvegardés();
            ps.setSujet(ss.findById(res.getInt("id_sujet")));
            listsauv.add(ps);
        }
        return listsauv;
    }

    @Override
    public PostsSauvegardés findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
