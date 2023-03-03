/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Utilisateur;
import java.sql.*;
import java.sql.Connection;
import Utils.DataSource;
import Utils.SessionManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.security.jgss.GSSUtil.login;
//implements IService < Utilisateur >

/**
 *
 * @author Chayma
 */
public class ServiceUtilisateur implements IService< Utilisateur> {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceUtilisateur() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

//public void setrolelocataire () throws SQLException{
////Utilisateur u =new Utilisateur ();
//String req ="INSERT INTO `utilisateur`(`role`) VALUES ('locataire') ;" ;
//ste.executeUpdate(req);
//}
//public void setroleproprietaire () throws SQLException{
////Utilisateur u =new Utilisateur ();
//String req ="INSERT INTO `utilisateur`( `role`) VALUES ('proprietaire') ;" ;
//ste.executeUpdate(req);
//}
    @Override
    public void ajouter(Utilisateur u) throws SQLException {

        String req = "INSERT INTO `user`( `nom`, `prenom`, `cin`, `email`, `telephone`, `login`, `mot_de_passe`, `date_de_naissance`, `region`, `adresse`,`code_postal`, `role`)"
                + "VALUES ( '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getCin() + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getLogin() + "', '" + u.getMot_de_passe() + "', '" + u.getDate_de_naissance() + "', '" + u.getRegion() + "', '" + u.getAdresse() + "', '" + u.getCode_postal() + "', '" + u.getRole() + "');";

        ste.executeUpdate(req);
        Utilisateur.setCurrent_user(u);
        SessionManager.getInstance(u.getId_utilisateur(), u.getNom(), u.getPrenom(), u.getCin(), u.getEmail(), u.getTelephone(), u.getLogin(), u.getMot_de_passe(), u.getDate_de_naissance(), u.getRegion(), u.getAdresse(), u.getCode_postal(), u.getRole());

        System.out.println("Un utilisateur est ajouté ");
    }

    @Override
    public void update(Utilisateur u) throws SQLException {
        try {
            String req = "UPDATE `user` SET `nom` = '" + u.getNom() + "', `prenom` = '" + u.getPrenom() + "', `cin` = '" + u.getCin()
                    + "', `email` = '" + u.getEmail() + "', `telephone` = '" + u.getTelephone() + "', `login` = '" + u.getLogin()
                    + "', `mot_de_passe` = '" + u.getMot_de_passe() + "', `date_de_naissance` = '" + u.getDate_de_naissance() + "', `region` = '" + u.getRegion() + "', `adresse` = '"
                    + u.getAdresse() + "', `code_postal` = '" + u.getCode_postal() + "', `role` = '" + u.getRole() + "' WHERE `user`.`id_user` = " + u.getId_utilisateur();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur mis à jour ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprime(Utilisateur u) throws SQLException {
        try {
            String req = "DELETE FROM `user` WHERE id_user = " + u.getId_utilisateur();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur suprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public List<Utilisateur> readAll() throws SQLException {

        ArrayList<Utilisateur> listutilisateur = new ArrayList<>();

        String req = " SELECT * FROM `user` ";
        Statement statement = con.createStatement();
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id_utilisateur = res.getInt(1);
            String nom = res.getString(2);
            String prenom = res.getString(3);
            String cin = res.getString(4);
            String email = res.getString(5);
            String telephone = res.getString(6);
            String login = res.getString(7);
            String mot_de_passe = res.getString(8);
            Date date_de_naissance = res.getDate(9);
            String region = res.getString(10);
            String Adresse = res.getString(11);
            int code_postal = res.getInt(12);
            String role = res.getString(13);
            Utilisateur u = new Utilisateur(id_utilisateur, nom, prenom, cin, email, telephone, login, mot_de_passe, date_de_naissance, region, Adresse, code_postal, role);
            System.out.println(u);
            listutilisateur.add(u);
        }
        return listutilisateur;
    }

    @Override
    public Utilisateur findById(int id_utilisateur) throws SQLException {

        String req = "SELECT * FROM `utilisateur`WHERE  id_utilisateur =" + id_utilisateur;
        Utilisateur u = new Utilisateur();
        Statement ste = con.createStatement();
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {

            String nom = res.getString(2);
            String prenom = res.getString(3);
            String cin = res.getString(4);
            String email = res.getString(5);
            String telephone = res.getString(6);
            String login = res.getString(7);
            String mot_de_passe = res.getString(8);
            Date date_de_naissance = res.getDate(9);
            String region = res.getString(10);
            String Adresse = res.getString(11);
            int code_postal = res.getInt(12);
            String role = res.getString(13);

            Utilisateur u1 = new Utilisateur(nom, prenom, cin, email, telephone, login, mot_de_passe, date_de_naissance, region, Adresse, code_postal, role);

            u = u1;
        }

        return u;
    }

    public boolean emailExists(String email) throws SQLException {
        boolean exists = false;

        String req = "SELECT COUNT(*) FROM `user` WHERE `email`  = ?;";
        PreparedStatement stmt = con.prepareStatement(req);

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            exists = (count > 0);
        }

        return exists;
    }

    public boolean loginExists(String login) throws SQLException {
        boolean exists = false;

        String req = "SELECT COUNT(*) FROM `user` WHERE `login`  = ?;";
        PreparedStatement stmt = con.prepareStatement(req);

        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            exists = (count > 0);
        }

        return exists;
    }

}
