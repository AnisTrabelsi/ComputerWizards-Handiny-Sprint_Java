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

    @Override
    public void ajouter(Sujet t) throws SQLException {
        String req = "INSERT INTO `Sujet` ( `titre_sujet`, `date_creation_sujet`, `date_derniere_maj`, `contenu_sujet`, `nb_commentaires`, `etat`, `tags`, `id_categorie`, `id_utilisateur`) VALUES "
                + "( '" + t.getTitre_sujet() 
                + "', '" + t.getDate_creation_sujet() 
                + "', '" + t.getDate_derniere_maj() 
                + "', '" + t.getContenu_sujet() 
                + "', '" + t.getNb_commentaires() 
                + "', '" + t.getEtat() 
                + "', '" + t.getTags()
                + "', '" + t.getCat().getId_categorie()
                + "', '" + t.getUser().getId_utilisateur()
                + "');";

        ste.executeUpdate(req);
    }

    @Override
    public void update(Sujet t) throws SQLException {
        String req = "UPDATE `Sujet` SET "
                + "`titre_sujet` = '" + t.getTitre_sujet() + "', "
                + "`date_creation_sujet` = '" + t.getDate_creation_sujet() + "', "
                + "`date_derniere_maj` = '" + t.getDate_derniere_maj() + "', "
                + "`contenu_sujet` = '" + t.getContenu_sujet() + "', "
                + "`nb_commentaires` = '" + t.getNb_commentaires() + "', "
                + "`etat` = '" + t.getEtat() + "', "
                + "`epingle` = '" + t.getTags() + "' "
                +"`id_categorie` = '" + t.getCat().getId_categorie()
                +"`id_utilisateur` = '" + t.getUser().getId_utilisateur()
                + "WHERE `id_sujet` = " + t.getId_sujet() + ";";

        ste.executeUpdate(req);
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

        String req = "select * from Sujet s INNER JOIN Categorie c ON s.id_categorie=c.id_categorie";

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
                sujet.setDate_derniere_maj(res.getDate("date_derniere_maj"));
                sujet.setContenu_sujet(res.getString("contenu_sujet"));
                sujet.setNb_commentaires(res.getInt("nb_commentaires"));
                sujet.setEtat(res.getString("etat"));
                sujet.setTags(res.getString("tags"));
            listsuj.add(sujet);
        }
        return listsuj;
    }

    @Override
    public Sujet findById(int id) throws SQLException {
        Sujet sujet = null;
        String req = "select * from Sujet s INNER JOIN Categorie c ON s.id_categorie=c.id_categorie where s.id_categorie=c.id_categorie";
        try ( ResultSet res = ste.executeQuery(req)) {
            if (res.next()) {
//                Categorie categorie = new Categorie();
//                categorie.setId_categorie(res.getInt("id_categorie"));
//                categorie.setNom_categorie(res.getString("nom_categorie"));
//                categorie.setDate_creation_categorie(res.getDate("date_creation_categorie"));
//                categorie.setNb_sujets(res.getInt("nb_sujets"));
                sujet.setId_sujet(res.getInt("id_sujet"));
                sujet.setTitre_sujet(res.getString("titre_sujet"));
                sujet.setDate_creation_sujet(res.getDate("date_creation_sujet"));
                sujet.setDate_derniere_maj(res.getDate("date_derniere_maj"));
                sujet.setContenu_sujet(res.getString("contenu_sujet"));
                sujet.setNb_commentaires(res.getInt("nb_commentaires"));
                sujet.setEtat(res.getString("etat"));
                sujet.setTags(res.getString("tags"));
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Un sujet avec cet id "+ id +  " est non trouvé !" + ex.getMessage());
        }
        return sujet;
    }
}
