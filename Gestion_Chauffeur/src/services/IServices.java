/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chauffeur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 * @param <C>
 */
public interface IServices <C> {
    
     public void ajouter(C c) throws SQLException;
     public void modifier(C c) throws SQLException;
     public void supprimer(int id) throws SQLException;
     public List<C> recuperer(C c) throws SQLException;
       public List<Chauffeur> readAll() throws SQLException;
    
}
