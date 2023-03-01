/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;

/**
 *
 * @author anis
 */
interface IService_utilisateur<T> {
       int rechercherparcin(String cin) throws SQLException;
         String rechercherparcin_nom(String cin) throws SQLException;
           String rechercherparcin_prenom(String cin) throws SQLException; 
           String rechercherparcin_email(String cin) throws SQLException;
           
            int rechercherparid(int id ) throws SQLException;
         String rechercherparid_nom(int id ) throws SQLException;
           String rechercherparcid_prenom(int id ) throws SQLException; 
           String rechercherparid_email(int id ) throws SQLException;
              String rechercherparcid_cin(int id ) throws SQLException;
               int rechercherid_parrrcin(String id) throws SQLException;
           
}
