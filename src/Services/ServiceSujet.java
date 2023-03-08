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
import Entite.Covoiturage;
import Entite.Sujet;
import Entite.Utilisateur;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author bengh
 */
public class ServiceSujet implements IService<Sujet> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceSujet() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    ServiceCategorie ser = new ServiceCategorie();

    public void ajouter(Sujet t) throws SQLException {

//        Categorie categorie = new Categorie();
        String req = "INSERT INTO `Sujet` ( `titre_sujet`, `date_creation_sujet`, `contenu_sujet`, `nb_commentaires`, `etat`, `tags`, `id_categorie`, `id_utilisateur`) VALUES (?, CURDATE(), ?, 0, ?, ?, ?, ?);";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getTitre_sujet());
        pre.setString(2, t.getContenu_sujet());
        pre.setString(3, t.getEtat());
        pre.setString(4, t.getTags());
        pre.setInt(5, t.getCat().getId_categorie());
        pre.setInt(6, t.getUser().getId_utilisateur());
        pre.executeUpdate();

        // mettre à jour le nombre de sujets de la catégorie correspondante
        String countReq = "SELECT COUNT(*) as nb_sujets FROM Sujet WHERE id_categorie = ?";
        PreparedStatement countStmt = con.prepareStatement(countReq);
        countStmt.setInt(1, t.getCat().getId_categorie());
        ResultSet rs = countStmt.executeQuery();
        int nbSujets = 0;
        if (rs.next()) {
            nbSujets = rs.getInt("nb_sujets");
        }
        countStmt.close();
        rs.close();

        String updateReq = "UPDATE `Categorie` SET `nb_sujets` = ? WHERE `id_categorie` = ?";
        PreparedStatement updateStmt = con.prepareStatement(updateReq);
        updateStmt.setInt(1, nbSujets);
        updateStmt.setInt(2, t.getCat().getId_categorie());
        updateStmt.executeUpdate();

        updateStmt.close();

    }

    @Override
    public void update(Sujet t) throws SQLException {
        String req = " UPDATE Sujet SET titre_sujet= ?, contenu_sujet = ? , etat  = ? , tags = ?, id_categorie = ?  WHERE id_sujet= ?;";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getTitre_sujet());
        pre.setString(2, t.getContenu_sujet());
        pre.setString(3, t.getEtat());
        pre.setString(4, t.getTags());
        pre.setInt(5, t.getCat().getId_categorie());
        pre.setInt(6, t.getId_sujet());

        pre.executeUpdate();
        System.out.println("Sujet modifiée !");
    }

    @Override
    public boolean supprime(Sujet t) throws SQLException {
        try {
            String req = "DELETE FROM `Sujet` WHERE `id_sujet` = " + t.getId_sujet() + ";";
            int res = ste.executeUpdate(req);
            return res > 0;
        } catch (SQLException ex) {
            System.out.println("Y a pas de sujet à supprimer, erreur !" + t.getId_sujet() + ": " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Sujet> readAll() throws SQLException {
        ArrayList<Sujet> listsuj = new ArrayList<>();

        String req = "select * from Sujet s";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
//            Categorie categorie = new Categorie();
//                categorie.setId_categorie(res.getInt("id_categorie"));
//                categorie.setNom_categorie(res.getString("nom_categorie"));
//                categorie.setDate_creation_categorie(res.getDate("date_creation_categorie"));
//                categorie.setNb_sujets(res.getInt("nb_sujets"));

            Sujet sujet = new Sujet();
            sujet.setId_sujet(res.getInt("id_sujet"));
            sujet.setTitre_sujet(res.getString("titre_sujet"));
            sujet.setDate_creation_sujet(res.getDate("date_creation_sujet"));
            sujet.setContenu_sujet(res.getString("contenu_sujet"));
            sujet.setNb_commentaires(res.getInt("nb_commentaires"));
            sujet.setEtat(res.getString("etat"));
            sujet.setTags(res.getString("tags"));
            sujet.setCat(ser.findById(res.getInt("id_categorie")));
            listsuj.add(sujet);
        }
        return listsuj;
    }

    @Override
    public Sujet findById(int id_sujet) throws SQLException {
        Sujet sujet = null;
        String req = "SELECT * FROM `sujet` WHERE `id_sujet` = " + id_sujet + ";";
        try (ResultSet rs = ste.executeQuery(req)) {
            if (rs.next()) {
                sujet = new Sujet(
                        rs.getInt("id_sujet"),
                        rs.getString("titre_sujet"),
                        rs.getString("contenu_sujet"),
                        rs.getString("etat"),
                        rs.getString("tags"));
            }
        } catch (SQLException ex) {
            System.out.println("Un sujet avec cet id " + id_sujet + " est non trouvé !" + ex.getMessage());
        }
        return sujet;
    }

    public List<String> readNoms() throws SQLException {
        ArrayList<String> listSuj = new ArrayList<>();
        String req = "select titre_sujet from Sujet";
        try {
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                listSuj.add(res.getString("titre_sujet"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listSuj;
    }

    public List<String> getAllTags() throws SQLException {
        ArrayList<String> listSuj = new ArrayList<>();
        String req = "select tags from Sujet";
        try {
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                listSuj.add(res.getString("tags"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listSuj;
    }

    public List<Sujet> SujetByUser(Utilisateur u) throws SQLException {

        ArrayList<Sujet> listsuj = new ArrayList<>();
        String req = "SELECT * FROM Sujet WHERE id_utilisateur = " + u.getId_utilisateur();
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            Sujet sujet = new Sujet();
            sujet.setId_sujet(res.getInt("id_sujet"));
            sujet.setTitre_sujet(res.getString("titre_sujet"));
            sujet.setDate_creation_sujet(res.getDate("date_creation_sujet"));
            sujet.setContenu_sujet(res.getString("contenu_sujet"));
            sujet.setNb_commentaires(res.getInt("nb_commentaires"));
            sujet.setEtat(res.getString("etat"));
            sujet.setTags(res.getString("tags"));
            sujet.setCat(ser.findById(res.getInt("id_categorie")));
            listsuj.add(sujet);
        }
        return listsuj;
    }

    @Override
    public void ajouter_don(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sujet findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Sujet t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sujet findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> sort_date_demande_don_of_user(int id) throws SQLException {
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
    public void modifier(Sujet c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sujet> recuperer(Sujet c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
