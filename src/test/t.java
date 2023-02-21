/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author Chaima
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Services;
import entities.Sous_service;
import interfaces.InterfaceService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyConnection;

/**
 *
 * @author HP
 */
public class Sous_serviceS implements InterfaceService<Sous_service> {

    Connection cnx;
    PreparedStatement ste;

    public Sous_serviceS() {
        cnx = MyConnection.getInstance().getCnx();
    }
//------------------------------------------------------------------------------

    public void ajouter(Sous_service s) {
        String sql = "insert into sous_service (nom_sc,id_ss) values (?,?) ";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, s.getNom_sc());
            ste.setInt(2, s.getSs().getId_serv());
            ste.executeUpdate();
            System.out.println("sous_service crée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//------------------------------------------------------------------------------

    @Override
    public List<Sous_service> getAll() {
        ServiceService a = new ServiceService();
        List<Sous_service> list = new ArrayList<>();
        try {
            String sql = "select * from sous_service";
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while (res.next()) {
                Services serv = a.findServiceById(res.getInt(3));
                System.out.println(res.getInt(3));
                Sous_service sc = new Sous_service(res.getInt(1), res.getString("nom_sc"), serv);
                list.add(sc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
//------------------------------------------------------------------------------

    @Override
    public void supprimer(Sous_service s) {
        String sql = "delete from service where id_serv=?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, s.getId_sc());
            ste.executeUpdate();
            System.out.println("sous_service supprimé");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//------------------------------------------------------------------------------ 

    public Sous_service findSSById(int id) {
        Sous_service ss = new Sous_service();
        ServiceService s = new ServiceService();
        try {
            String sql = "select * from sous_service where id_sc=" + id + "";
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while (res.next()) {
                Services serv = s.findServiceById(res.getInt(3));
                ss = new Sous_service(id, res.getString("nom_sc"), serv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ss;
    }
//------------------------------------------------------------------------------     

    public List<Sous_service> findSSByIdService(int id_ss) {
        Sous_service ss = new Sous_service();
        ServiceService s = new ServiceService();
        List<Sous_service> list = new ArrayList<>();
        try {
            String sql = "select * from sous_service where id_ss=" + id_ss + "";
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while (res.next()) {
                Services serv = s.findServiceById(res.getInt(3));
                ss = new Sous_service(res.getInt(1), res.getString("nom_sc"), serv);
                list.add(ss);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
Vous avez envoyé

