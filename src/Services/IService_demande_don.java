/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author anis
 */
public interface IService_demande_don<T> {
     void ajouter_demande_don(T t) throws SQLException;
 void ajouter_demande_don_admin(T t) throws SQLException;

    void update_demande_don(T t) throws SQLException;
    void update_demande_don_admin(T t) throws SQLException;

    boolean supprime_demande_don(int id) throws SQLException;

    List<T> readAll_demande_don() throws SQLException;

    T findById_demande_don(int id) throws SQLException;
    
      List<T> find_demande_don_of_user(int id) throws SQLException;
      List<T> sort_date_demande_don_of_users() throws SQLException;
       List<T> sort_date_demande_don_of_user(int id) throws SQLException;
         HashMap<String,Integer> stattype() throws SQLException;
}
