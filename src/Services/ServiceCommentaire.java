/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Commentaire;
import Entite.Sujet;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bengh
 */
public class ServiceCommentaire implements IService<Commentaire> {

    Connection con = DataSource.getInstance().getConnection();

    private Statement ste;

    public ServiceCommentaire() {

        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Commentaire t) throws SQLException {
        String req = "INSERT INTO `Commentaire` ( `contenu_commentaire`, `date_publication`, `nb_mentions`, `id_sujet`, `id_utilisateur`) VALUES "
                + "( '" + t.getContenu_commentaire() 
                + "', '" + t.getDate_publication() 
                + "', '" + t.getNb_mentions() 
                + "', '" + t.getSujet().getId_sujet()
                + "', '" + t.getUser().getId_utilisateur()
                + "');";

        ste.executeUpdate(req);
    }

    @Override
    public void update(Commentaire t) throws SQLException {
        String req = "UPDATE `Commentaire` SET"
                + "`contenu_commentaire` = '" + t.getContenu_commentaire() + "', "
                + "`date_publication` = '" + t.getDate_publication() + "', "
                + "`nb_mentions` = '" + t.getNb_mentions() 
                + "`id_sujet` = '" + t.getSujet().getId_sujet()
                + "`id_utilisateur` = '" + t.getUser().getId_utilisateur()
                + "WHERE `id_commentaire` = " + t.getId_commentaire() + ";";

        ste.executeUpdate(req);
    }

    @Override
    public boolean supprime(Commentaire t) throws SQLException {
        try {
            String req = "DELETE FROM `Commentaire` WHERE `id_commentaire` = " + t.getId_commentaire() + ";";
            int res = ste.executeUpdate(req);
            return res > 0;
        } catch (SQLException ex) {
            System.out.println("Y a pas de commentaires à supprimer, erreur !" + t.getId_commentaire() + ": " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Commentaire> readAll() throws SQLException {
        ArrayList<Commentaire> listcomm = new ArrayList<>();

        String req = "select * from Commentaire c INNER JOIN Sujet s where c.idSujet=s.idSujet";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
//            Sujet sujet = new Sujet();
//                sujet.setId_sujet(res.getInt("id_sujet"));
//                sujet.setTitre_sujet(res.getString("titre_sujet"));
//                sujet.setDate_creation_sujet(res.getDate("date_creation_sujet"));
//                sujet.setDate_derniere_maj(res.getDate("date_derniere_maj"));
//                sujet.setContenu_sujet(res.getString("contenu_sujet"));
//                sujet.setNb_commentaires(res.getInt("nb_commentaires"));
//                sujet.setEtat(res.getString("etat"));
//                sujet.setEpingle(res.getBoolean("Epingle"));
            Commentaire commentaire = new Commentaire();
                commentaire.setId_commentaire(res.getInt("id_commentaire"));
                commentaire.setContenu_commentaire(res.getString("contenu_commentaire"));
                commentaire.setDate_publication(res.getDate("date_publication"));
                commentaire.setNb_mentions(res.getInt("nb_mentions"));
            listcomm.add(commentaire);
        }
        return listcomm;
    }

    @Override
    public Commentaire findById(int id) throws SQLException {
        Commentaire commentaire = null;
        String req = "SELECT * FROM `Commentaire` WHERE `id_commentaire` = " + id + ";";
        try ( ResultSet res = ste.executeQuery(req)) {
            if (res.next()) {
                commentaire.setId_commentaire(res.getInt("id_commentaire"));
                commentaire.setContenu_commentaire(res.getString("contenu_commentaire"));
                commentaire.setDate_publication(res.getDate("date_publication"));
                commentaire.setNb_mentions(res.getInt("nb_mentions"));
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Un Commentaire avec cet id " + id + " est non trouvé !" + ex.getMessage());
        }
        return commentaire;
    }

}
