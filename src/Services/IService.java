/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Chayma
 */
public interface IService <T> {
    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException;

    boolean  supprime(T t) throws SQLException;

    List<T> readAll() throws SQLException;
    T findById(int id) throws SQLException;

    
}
