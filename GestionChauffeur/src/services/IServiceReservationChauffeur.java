/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mehdi
 */
public interface IServiceReservationChauffeur<T> {
     void ajouterReservationChauffeur(T t) throws SQLException;

    void updateReservationChauffeur(T t) throws SQLException;

    boolean supprimeReservationChauffeur(int id) throws SQLException;

    List<T> readAllReservationChauffeur() throws SQLException;

    T findById_ReservationChauffeur(int id) throws SQLException;
    
      List<T> findReservationChauffeur_of_user(int id) throws SQLException;
      List<T> sortReservationChauffeur_of_user() throws SQLException;
}
