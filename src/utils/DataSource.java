/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chaima
 */
public class DataSource {

    private static DataSource data;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/handiny2";
    private String login = "root";
    private String pwd = "";

    private DataSource() {

        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }
}

