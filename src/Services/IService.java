/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anis
 */
public interface IService<T> {

    ////////////////////////////////////don  Anis
    void ajouter_don(T t) throws SQLException;

    void modifier_don(T t) throws SQLException;

    void modifier_don_admin(T t) throws SQLException;

    boolean supprimer_don(int id) throws SQLException;

    List<T> readAll_don() throws SQLException;

    List<T> sortbydate_don() throws SQLException;

    List<T> findbydate_don(Date d) throws SQLException;

    List<T> findbytype(String s) throws SQLException;

    T findById_don(int id) throws SQLException;

    HashMap<String, Integer> stattype() throws SQLException;

    ///////////////////////////////////////////////demande don Anis
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
    /////////////////////////////////////////////////////utilisateur Anis

    int rechercherparcin(String cin) throws SQLException;

    String rechercherparcin_nom(String cin) throws SQLException;

    String rechercherparcin_prenom(String cin) throws SQLException;

    String rechercherparcin_email(String cin) throws SQLException;

    int rechercherparid(int id) throws SQLException;

    String rechercherparid_nom(int id) throws SQLException;

    String rechercherparcid_prenom(int id) throws SQLException;

    String rechercherparid_email(int id) throws SQLException;

    String rechercherparcid_cin(int id) throws SQLException;

    int rechercherid_parrrcin(String id) throws SQLException;
    ////////////////////////////////////////////////////// Chayma Utilisateur

    void ajouter(T t) throws SQLException;

    void update(T t) throws SQLException;

    boolean supprime(T t) throws SQLException;

    List<T> readAll() throws SQLException;

    T findById(int id) throws SQLException;

}
