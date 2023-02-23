/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Covoiturage;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sanabenfadhel
 * @param <Covoiturage>
 */
public interface IService<Covoiturage> {

    boolean ajouter(Covoiturage t) throws SQLException;

    void update(Covoiturage t) throws SQLException;
   List<Covoiturage> sortbydate() throws SQLException;


    boolean supprime(Covoiturage t) throws SQLException;
    List<Covoiturage> readAll() throws SQLException;

    Covoiturage findById(int id) throws SQLException;
}
