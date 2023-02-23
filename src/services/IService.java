/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Chaima
 */
public interface IService<T> {

    boolean ajouter(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    void supprime(T t) throws SQLException;

    List<T> readAll() throws SQLException;

    T findById(int id) throws SQLException;
}

