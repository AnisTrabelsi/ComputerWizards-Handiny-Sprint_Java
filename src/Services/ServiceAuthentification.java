/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Utilisateur;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Chayma
 */
public class ServiceAuthentification {

    Connection con = DataSource.getInstance().getConnection();
    Utilisateur u = new Utilisateur();
   private  int id_auth ;
    boolean verif ;

    public int getId_auth() {
        return id_auth;
    }

    public void setId_auth(int id_auth) {
        this.id_auth = id_auth;
    }

 
  
   

    public boolean auth() throws SQLException {
           

       String req ="SELECT * FROM user WHERE login=? and mot_de_passe=?";
        try {
        PreparedStatement pre =con.prepareStatement(req);
        pre.setString(1, u.getLogin());
        pre.setString(2, u.getMot_de_passe());
        ResultSet res = pre.executeQuery();
        if(!res.next()){
                 verif =true ;
                 id_auth=u.getId_utilisateur();

                 
        
            } 
         
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }

       
        return verif;
        
    }
}
