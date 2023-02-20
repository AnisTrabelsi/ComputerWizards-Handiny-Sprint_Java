/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Categorie;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
        String req = "INSERT INTO `Categorie` ( `nom_categorie`, `date_creation_categorie`, `nb_sujets`) VALUES "
                + "( '" + t.getNom_categorie() + "', '" + t.getDate_creation_categorie() + "', '" + t.getNb_sujets() + "');";

        ste.executeUpdate(req);
    }

    @Override
    public void update(Categorie t) throws SQLException {
        String req = " UPDATE Categorie SET nom_categorie= ?, date_creation_categorie = ? , nb_sujets  = ? WHERE id_categorie= ?;";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(4, t.getId_categorie());
        pre.setString(1, t.getNom_categorie());
        pre.setDate(2, t.getDate_creation_categorie());
        pre.setInt(3, t.getNb_sujets());
        pre.executeUpdate();
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

    @Override
    public Categorie findById(int id) throws SQLException {
        Categorie categorie = null;
        String req = "SELECT * FROM `Categorie` WHERE `id_categorie` = " + id + ";";
        try ( ResultSet rs = ste.executeQuery(req)) {
            if (rs.next()) {
                categorie = new Categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("nom_categorie"),
                        rs.getDate("date_creation_categorie"),
                        rs.getInt("nb_sujets")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Un Categorie avec cet id " + id + " est non trouvé !" + ex.getMessage());
        }
        return categorie;
    }

}