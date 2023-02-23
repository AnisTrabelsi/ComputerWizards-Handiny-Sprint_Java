/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anis
 */
public interface IServiceChauffeur<T> {
        void ajouterChauffeur(T t) throws SQLException;

    void modifierChauffeur(T t) throws SQLException;

    boolean supprimerChauffeur(int id) throws SQLException;

    List<T> readAllChauffeur() throws SQLException;

    T findById_chauffeur(int id) throws SQLException;
}
