/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Covoiturage;
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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chayma
 */
public class ServiceReclamation implements IService<Reclamation> {

    Reclamation r = new Reclamation();

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceReclamation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Reclamation r) throws SQLException {

        try {
            String req = "INSERT INTO reclamation (`id_utilisateur`, `type_reclamation`, `etat_reclamation`, `description`) VALUES (3,?,?,?);";
            PreparedStatement pre = con.prepareStatement(req);
            // pre.setInt(1,r.getUser().getId_utilisateur());
            pre.setString(1, r.getType_reclamation());
            pre.setString(2, r.getEtat_reclamation());
            pre.setString(3, r.getDescription());
            pre.executeUpdate();

            System.out.println(r.toString());
        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    @Override
    public void update(Reclamation r) throws SQLException {
        String req = "UPDATE `reclamation` SET `type_reclamation` = ?, `etat_reclamation` = ?, `description` = ?, `reponse` = ? WHERE `id_reclamation` = ?";
        try (PreparedStatement st = con.prepareStatement(req)) {
            st.setString(1, r.getType_reclamation());
            st.setString(2, r.getEtat_reclamation());
            st.setString(3, r.getDescription());
            st.setString(4, r.getReponse());
            st.setInt(5, r.getId_reclamation());
            st.executeUpdate();
            System.out.println("Reclamation mise à jour ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void Traiter (Reclamation r) throws SQLException {
        String req = "UPDATE `reclamation` SET `etat_reclamation` = ?, `reponse` = ? WHERE `id_reclamation` = ?";
                PreparedStatement st = con.prepareStatement(req);
                st.setString(1, r.getEtat_reclamation());
                st.setString(2, r.getReponse());
                st.setInt(3, r.getId_reclamation());
                st.executeUpdate();
                System.out.println("Reclamation mise à jour ");
    
    }

    @Override
    public List<Reclamation> readAll() throws SQLException {

        ArrayList<Reclamation> listreclamation = new ArrayList<>();

        String req = " SELECT * FROM `reclamation` ";
       // Statement statement = con.createStatement();
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {

            int id_reclamation = res.getInt(1);
            String type_reclamation = res.getString(3);
            String etat_reclamation = res.getString(4);
            String description = res.getString(5);
            Reclamation r = new Reclamation(id_reclamation,type_reclamation, etat_reclamation, description);
            System.out.println(r);
            listreclamation.add(r);
        }
        return listreclamation;
    }

    @Override
    public Reclamation findById(int id_reclamation) throws SQLException {
        Reclamation r = new Reclamation();
        Utilisateur u = new Utilisateur();
        String req = "SELECT * FROM `reclamation`WHERE  id_reclamation =" + id_reclamation;
        Statement ste = con.createStatement();
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id_utilisateur = res.getInt(2);
            String type_reclamation = res.getString(3);
            String etat_reclamation = res.getString(4);
            String description = res.getString(5);
            // System.out.println(r);
            Reclamation r1 = new Reclamation(id_utilisateur, type_reclamation, etat_reclamation, description, u);
            r = r1;
        }
        return r;
    }

    public List<Reclamation> findById_Utilisateur(int id_utilisateur) throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        Utilisateur u = new Utilisateur();
        String req = "SELECT * FROM `reclamation` WHERE id_utilisateur =" + id_utilisateur;
        Statement ste = con.createStatement();
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int id_reclamation = res.getInt(1);
            String type_reclamation = res.getString(3);
            String etat_reclamation = res.getString(4);
            String description = res.getString(5);
            Reclamation r = new Reclamation(id_reclamation, type_reclamation, etat_reclamation, description);
            reclamations.add(r);
        }
        return reclamations;
    }

    @Override
    public boolean supprime(Reclamation r) throws SQLException {
        try {
            String req = "DELETE FROM `reclamation` WHERE `id_reclamation` = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, r.getId_reclamation());
            int res = st.executeUpdate();
            return res > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;

        }
    }

    @Override
    public void ajouter_don(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> sort_date_demande_don_of_user(int id) throws SQLException {
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
    public void modifier(Reclamation c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> recuperer(Reclamation c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
