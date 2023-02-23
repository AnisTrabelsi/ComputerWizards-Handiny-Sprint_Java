/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entite.reservation_covoiturage;

/**
 *
 * @author abbes
 */
public class Service_reservation_cov implements IService_reservationcov<reservation_covoiturage> {
     Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public Service_reservation_cov() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter_reservation_cov(reservation_covoiturage d) throws SQLException {
        String req = "INSERT INTO `reservation_covoiturage` ( `id_cov`, `id_utilisateur`, `prix_covoiturage`) VALUES (?, ?, ?);";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getId_cov());
        pre.setInt(2, d.getId_utilisateur());
                pre.setInt(3, d.getPrix_covoiturage());

   
       

        pre.executeUpdate();
    }

    @Override
    public void update_reservation_cov(reservation_covoiturage d) throws SQLException {
        String req = " UPDATE reservation_covoiturage SET  id_cov = ? , id_utilisateur  = ?,prix_covoiturage = ? WHERE id_reserv_cov= " + d.getId_reserv_cov()+ ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId_cov());
        pre.setInt(2, d.getId_utilisateur());
        pre.setInt(3, d.getPrix_covoiturage());
     
        pre.executeUpdate();
        System.out.println("demande_don modifié !");
    }

    @Override
    public boolean supprime_reservation_cov(int id) throws SQLException {
        String req = "DELETE FROM `reservation_covoiturage` WHERE id_reserv_cov = " + id + ";";

        if ((ste.executeUpdate(req)) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public List<reservation_covoiturage> readAll_reservation_cov() throws SQLException {
        ArrayList<reservation_covoiturage> listper = new ArrayList<>();

        String req = "select * from reservation_covoiturage";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
           
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

    @Override
    public reservation_covoiturage findById_reservation_cov(int id) throws SQLException {
        reservation_covoiturage d = new reservation_covoiturage();
        String req = "select * FROM `reservation_covoiturage` WHERE id_reserv_cov= " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
           
             d = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage);
        }

        return d;
    }

    @Override
    public List<reservation_covoiturage> find_reservation_cov_of_user(int id) throws SQLException {
        ArrayList<reservation_covoiturage> listper = new ArrayList<>();

        String req = "select * from reservation_covoiturage where id_utilisateur =" + id + ";";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
          int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }

   
    
}
