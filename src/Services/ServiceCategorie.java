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
import Entite.Categorie;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bengh
 */
public class ServiceCategorie implements IService<Categorie> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceCategorie() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Categorie t) throws SQLException {
        String req = "INSERT INTO `Categorie` (`nom_categorie`, `date_creation_categorie`, `nb_sujets`) VALUES (?, CURDATE(), 0);";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getNom_categorie());
        pre.executeUpdate();

    }

    @Override
    public void update(Categorie t) throws SQLException {
        String req = " UPDATE Categorie SET nom_categorie= ?, date_creation_categorie = CURDATE() , nb_sujets  = ? WHERE id_categorie= ?;";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getNom_categorie());
        pre.setInt(2, t.getNb_sujets());
        pre.setInt(3, t.getId_categorie());
        pre.executeUpdate();
        System.out.println(t.toString());
        System.out.println("Catégorie modifiée !");
    }

    @Override
    public boolean supprime(Categorie t) throws SQLException {
        try {
            String req = "DELETE FROM `Categorie` WHERE `id_categorie` = " + t.getId_categorie() + ";";
            int res = ste.executeUpdate(req);
            System.out.println("Catégorie supprimée avec succès");
            return res > 0;
        } catch (SQLException ex) {
            System.out.println("Y a pas de catégories à supprimer, erreur !" + t.getId_categorie() + ": " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Categorie> readAll() throws SQLException {
        ArrayList<Categorie> listcat = new ArrayList<>();

        String req = "select * from Categorie";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt(1);
            String nom_categorie = res.getString(2);
            Date date_creation_categorie = res.getDate(3);
            int nb_sujets = res.getInt(4);
            Categorie c = new Categorie(id, nom_categorie, date_creation_categorie, nb_sujets);
            listcat.add(c);
        }
        return listcat;
    }

    public List<String> readNoms() throws SQLException {
        ArrayList<String> listcat = new ArrayList<>();
        String req = "select nom_categorie from Categorie";
        try {
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                listcat.add(res.getString("nom_categorie"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listcat;
    }

    @Override
    public Categorie findById(int id) throws SQLException {
        Categorie categorie = null;
        String req = "SELECT * FROM `categorie` WHERE `id_categorie` = " + id + ";";
        try (ResultSet rs = ste.executeQuery(req)) {
            if (rs.next()) {
                categorie = new Categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("nom_categorie"),
                        rs.getDate("date_creation_categorie"),
                        rs.getInt("nb_sujets")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Une Categorie avec cet id " + id + " est non trouvé !" + ex.getMessage());
        }
        return categorie;
    }

    public int getIdByName(String nom_categorie) throws SQLException {
        String req = "SELECT id_categorie FROM categorie WHERE nom_categorie = ?";
        PreparedStatement stmt = con.prepareStatement(req);
        stmt.setString(1, nom_categorie);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_categorie");
        } else {
            return -1; // or throw an exception to indicate that no matching row was found
        }
    }

    @Override
    public void ajouter_don(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categorie findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categorie findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> sort_date_demande_don_of_user(int id) throws SQLException {
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
