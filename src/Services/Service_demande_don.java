/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.demande_don;
import Entite.don;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anis
 */
public class Service_demande_don implements IService_demande_don<demande_don> {

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
    public void ajouter_demande_don(demande_don d) throws SQLException {
        String req = "INSERT INTO `demande_don` ( `id_utilisateur`, `id_don`, `date_demande`,`justificatif_handicap`,`etat`) VALUES (?, ?, ?, ?,'en cours');";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
        pre.setDate(3, d.getDate_demande());
        pre.setString(4, d.getJustificatif_handicap());
       

        pre.executeUpdate();
    }

    @Override
    public void update_demande_don(demande_don d) throws SQLException {
        String req = " UPDATE demande_don SET  id_utilisateur = ? , id_don  = ?,date_demande = ?,justificatif_handicap = ? ,etat = ? WHERE id_demande_don= " + d.getId_demande_don() + ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_utilisateur());
        pre.setInt(2, d.getId_don());
        pre.setDate(3, d.getDate_demande());
        pre.setString(4, d.getJustificatif_handicap());
        pre.setString(5, d.getEtat());
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
            String etat = res.getString(6);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif, etat);
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
            String etat = res.getString(6);
             d = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif, etat);
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
            String etat = res.getString(6);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public List<demande_don> sort_date_demande_don_of_user() throws SQLException {
        ArrayList<demande_don> listper = new ArrayList<>();

        String req = "select * from demande_don ORDER BY date_demande ";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_demande_don = res.getInt(1);
            int id_utilisateur = res.getInt(2);
            int id_don = res.getInt("id_don");
            Date datedemande = res.getDate(4);
            String justif = res.getString(5);
            String etat = res.getString(6);
            demande_don p = new demande_don(id_demande_don, id_utilisateur, id_don, datedemande, justif, etat);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
}
