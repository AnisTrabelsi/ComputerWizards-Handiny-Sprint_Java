/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entite.Covoiturage;
import Entite.Utilisateur;
import java.sql.*;
import java.sql.Connection;
import Utils.DataSource;
import Utils.SessionManager;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    Utilisateur user =Utilisateur.getCurrent_user();

//public void setrolelocataire () throws SQLException{
////Utilisateur u =new Utilisateur ();
//String req ="INSERT INTO utilisateur`(role`) VALUES ('locataire') ;" ;
//ste.executeUpdate(req);
//}
//public void setroleproprietaire () throws SQLException{
////Utilisateur u =new Utilisateur ();
//String req ="INSERT INTO utilisateur`( role`) VALUES ('proprietaire') ;" ;
//ste.executeUpdate(req);
//}
    @Override
    public void ajouter(Utilisateur u) throws SQLException {

        String req = "INSERT INTO user ( nom, prenom, cin, email, telephone, login, mot_de_passe, date_de_naissance, region, adresse,`code_postal`, role)"
                + "VALUES ( '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getCin() + "', '" + u.getEmail() + "', '" + u.getTelephone() + "', '" + u.getLogin() + "', '" + u.getMot_de_passe() + "', '" + u.getDate_de_naissance() + "', '" + u.getRegion() + "', '" + u.getAdresse() + "', '" + u.getCode_postal() + "', '" + u.getRole() + "');";

        ste.executeUpdate(req);
        Utilisateur.setCurrent_user(u);
        SessionManager.getInstance(u.getId_utilisateur(), u.getNom(), u.getPrenom(), u.getCin(), u.getEmail(), u.getTelephone(), u.getLogin(), u.getMot_de_passe(), u.getDate_de_naissance(), u.getRegion(), u.getAdresse(), u.getCode_postal(), u.getRole());

        System.out.println("Un utilisateur est ajouté ");
    }

    @Override
    public void update(Utilisateur u) throws SQLException {
        try {
            String req = "UPDATE user SET email = '" + u.getEmail() + "', telephone = '" + u.getTelephone() + "', login = '" + u.getLogin()
                    + "', adresse = '" + u.getAdresse() + "' WHERE user.`id_user` = " + user.getId_utilisateur();
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
            String req = "DELETE FROM user WHERE id_user = " + u.getId_utilisateur();
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

        String req = " SELECT * FROM user ";
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

        String req = "SELECT * FROM user WHERE  id_user =" + id_utilisateur;
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

            Utilisateur u1 = new Utilisateur(id_utilisateur, nom, prenom, cin, email, telephone, login, mot_de_passe, date_de_naissance, region, Adresse, code_postal, role);
            u = u1;
        }

        return u;
    }

    public boolean emailExists(String email) throws SQLException {
        boolean exists = false;

        String req = "SELECT COUNT(*) FROM user WHERE email  = ?;";
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

        String req = "SELECT COUNT(*) FROM user WHERE login  = ?;";
        PreparedStatement stmt = con.prepareStatement(req);

        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            exists = (count > 0);
        }

        return exists;
    }

    @Override
    public int rechercherparcin(String cin) throws SQLException {

        int id = 0;
        String req = "select * FROM `user` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            id = res.getInt(1);

        }

        return id;
    }

    @Override
    public String rechercherparcin_nom(String cin) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("nom");

        }

        return s;
    }

    @Override
    public String rechercherparcin_prenom(String cin) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("prenom");

        }

        return s;
    }

    public void mettreAJourMotDePasse(Utilisateur u) throws SQLException {
        String req = "UPDATE `user` SET `mot_de_passe` = ? WHERE `id_user` = ?";
        PreparedStatement stmt = con.prepareStatement(req);
        stmt.setString(1, u.getMot_de_passe());
        stmt.setInt(2, u.getId_utilisateur());
        stmt.executeUpdate();
        System.out.println("Le mot de passe de l'utilisateur " + u.getNom() + " a été mis à jour");
    }
//   public boolean emailExists(String email) throws SQLException {
//        boolean exists = false;
//
//        String req = "SELECT COUNT(*) FROM `user` WHERE `email`  = ?;";
//        PreparedStatement stmt = con.prepareStatement(req);
//
//        stmt.setString(1, email);
//        ResultSet rs = stmt.executeQuery();
//
//        if (rs.next()) {
//            int count = rs.getInt(1);
//            exists = (count > 0);
//        }
//
//        return exists;
//    }

//    public boolean loginExists(String login) throws SQLException {
//        boolean exists = false;
//
//        String req = "SELECT COUNT(*) FROM `user` WHERE `login`  = ?;";
//        PreparedStatement stmt = con.prepareStatement(req);
//
//        stmt.setString(1, login);
//        ResultSet rs = stmt.executeQuery();
//
//        if (rs.next()) {
//            int count = rs.getInt(1);
//            exists = (count > 0);
//        }
//
//        return exists;
//    }
    public boolean verifierEmailBd(String email) {
        boolean emailExists = false;

        try {
            String sql = "SELECT COUNT(*) AS count FROM user WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    emailExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailExists;
    }

    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private SecureRandom random = new SecureRandom();

    public String getAlphaNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }

    public static int getIdByEmail(String email) {
        int id = -1;
        Connection con = DataSource.getInstance().getConnection();

        try {
            String sql = "SELECT id_user FROM user WHERE email = ?";
            // String sql = "UPDATE user SET code = ? WHERE id_user = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, email);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id_user");
                    }
                }
            }
        } catch (SQLException e) {
        }

        return id;
    }

    public void updateCode(String code, int userId) {
        if (userId == 0) {
            System.out.println("Invalid user id");
            return;
        }
        try {
            String sql = "UPDATE user SET code = ? WHERE id_user = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, code);
                stmt.setInt(2, userId);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String rechercherparcin_email(String cin) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where cin = " + cin + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("email");

        }

        return s;
    }

    @Override
    public int rechercherparid(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rechercherparid_nom(int id) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where id_user = " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("nom");

        }

        return s;
    }

    @Override
    public String rechercherparcid_cin(int id) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where id_user = " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("cin");

        }

        return s;
    }

    @Override
    public String rechercherparid_email(int id) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where id_user = " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("email");

        }

        return s;
    }

    @Override
    public String rechercherparcid_prenom(int id) throws SQLException {
        String s = "";
        String req = "select * FROM `user` where id_user = " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            s = res.getString("prenom");

        }

        return s;
    }

    @Override
    public int rechercherid_parrrcin(String id) throws SQLException {
        int a = 0;
        String req = "select * FROM `user` where cin = " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            a = res.getInt("id_user");

        }

        return a;
    }

    @Override
    public void ajouter_don(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_don_admin(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> readAll_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> sortbydate_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> findbydate_don(Date d) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> findbytype(String s) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur findById_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> stattype() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter_demande_don_admin(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_demande_don_admin(Utilisateur t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> readAll_demande_don() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur findById_demande_don(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> find_demande_don_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> sort_date_demande_don_of_users() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> sort_date_demande_don_of_user(int id) throws SQLException {
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
    public void modifier(Utilisateur c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> recuperer(Utilisateur c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
