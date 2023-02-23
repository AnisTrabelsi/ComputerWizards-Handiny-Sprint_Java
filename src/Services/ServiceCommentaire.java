/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Commentaire;
import Entite.Sujet;
import Utils.DataSource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        String req = "INSERT INTO `Commentaire` (`contenu_commentaire`, `date_publication`, `nb_mentions`,`piecejointe`, `id_sujet`, `id_utilisateur`) VALUES (?, CURDATE(), 0, ?, ?, 1);";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, t.getContenu_commentaire());
        pre.setString(2, t.getPiecejointe());
        pre.setInt(3, t.getSujet().getId_sujet());
        pre.executeUpdate();

        // mettre à jour le nombre de sujets de la catégorie correspondante
        String countReq = "SELECT COUNT(*) as nb_commentaires FROM Commentaire WHERE id_sujet = ?";
        PreparedStatement countStmt = con.prepareStatement(countReq);
        countStmt.setInt(1, t.getSujet().getId_sujet());
        ResultSet rs = countStmt.executeQuery();
        int nbComments = 0;
        if (rs.next()) {
            nbComments = rs.getInt("nb_commentaires");
        }
        countStmt.close();
        rs.close();

        String updateReq = "UPDATE `Sujet` SET `nb_commentaires` = ? WHERE `id_sujet` = ?";
        PreparedStatement updateStmt = con.prepareStatement(updateReq);
        updateStmt.setInt(1, nbComments);
        updateStmt.setInt(2, t.getSujet().getId_sujet());
        updateStmt.executeUpdate();

        updateStmt.close();

    }

    @Override
    public void update(Commentaire t) throws SQLException {
        String req = "UPDATE `Commentaire` SET"
                + "`contenu_commentaire` = '" + t.getContenu_commentaire() + "', "
                + "`date_publication` = '" + t.getDate_publication() + "', "
                + "`nb_mentions` = '" + t.getNb_mentions()
                + "`piecejointe` = '" + t.getPiecejointe()
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

        String req = "select * from Commentaire";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setId_commentaire(res.getInt("id_commentaire"));
            commentaire.setContenu_commentaire(res.getString("contenu_commentaire"));
            commentaire.setDate_publication(res.getDate("date_publication"));
            commentaire.setNb_mentions(res.getInt("nb_mentions"));
            commentaire.setPiecejointe(res.getString("piecejointe"));
            listcomm.add(commentaire);
        }
        return listcomm;
    }

    @Override
    public Commentaire findById(int id) throws SQLException {
        Commentaire comment = new Commentaire();
        Sujet suj = null;
        String req = "select * from Commentaire WHERE  id_commentaire =" + id;
        Statement ste = con.createStatement();
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            String contenu_commentaire = res.getString("contenu_commentaire");
            String piecejointe = res.getString("piecejointe");

            Commentaire c1 = new Commentaire(contenu_commentaire, piecejointe, suj);
            comment = c1;
        }
        return comment;
    }

}
