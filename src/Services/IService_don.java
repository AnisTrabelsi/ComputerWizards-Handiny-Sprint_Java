/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anis
 */
public interface IService_don<T> {
        void ajouter_don(T t) throws SQLException;
 
  
    void modifier_don(T t) throws SQLException;
    void modifier_don_admin(T t) throws SQLException;

    boolean supprimer_don(int id) throws SQLException;

    List<T> readAll_don() throws SQLException;
   List<T> sortbydate_don() throws SQLException;
   List<T> findbydate_don(Date d) throws SQLException;
   
   List<T> findbytype(String s) throws SQLException;
    T findById_don(int id) throws SQLException;
}
