/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Categorie;
import Entite.Sujet;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

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
        String req = "INSERT INTO `Sujet` ( `titre_sujet`, `date_creation_sujet`, `contenu_sujet`, `nb_commentaires`, `etat`, `tags`, `id_categorie`, `id_utilisateur`) VALUES (?, CURDATE(), ?, 0, ?, ?, ?, 1);";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getTitre_sujet());
        pre.setString(2, t.getContenu_sujet());
        pre.setString(3, t.getEtat());
        pre.setString(4, t.getTags());
        pre.setInt(5, t.getCat().getId_categorie());
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

}
