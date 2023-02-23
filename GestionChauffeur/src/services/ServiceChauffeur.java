/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import modules.Chauffeur;
import Utils.MyConnection;
import java.sql.Connection;
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
public class ServiceChauffeur implements IServiceChauffeur<Chauffeur> {

    Connection con = MyConnection.getInstance().getConnection();
    private Statement ste;

    public ServiceChauffeur() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouterChauffeur(Chauffeur d) throws SQLException {

        String req = "INSERT INTO `Chauffeur` (`id_chauffeur`, `CIN`, `Nom`, `Adresse`) VALUES (?, ?, ?, ?);";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, d.getid_chauffeur());
        pre.setString(2, d.getCIN());
        pre.setString(3, d.getNom());
        pre.setString(4, d.getAdresse());
        pre.executeUpdate();

    }

    @Override
    public void modifierChauffeur(Chauffeur d) throws SQLException {
        String req = " UPDATE chauffeur SET id_chauffeur= ?, CIN = ? , Nom  = ?,Adresse = ? WHERE id_chauffeur= " + d.getId_chauffeur() + ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getid_chauffeur());
        pre.setString(2, d.getCIN());
        pre.setString(3, d.getNom());
        pre.setString(4, d.getAdresse());
        pre.executeUpdate();
        System.out.println("Chauffeur modifié !");

    }
/*
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
            don p = new don(id_don, id_utilisateur, type, image_don, Description);
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
         d = new don(id_don, id_utilisateur, type, image_don, Description);
    }
        
        return d;
    }

    @Override
    public void ajouterChauffeur(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierChauffeur(Chauffeur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerChauffeur(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> readAllChauffeur() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chauffeur findById_chauffeur(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    */

    @Override
    public boolean supprimerChauffeur(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chauffeur> readAllChauffeur() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chauffeur findById_chauffeur(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
