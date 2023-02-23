/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anis
 */
public class MyConnection {
        private static MyConnection data;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/handiny";
    private String login = "root";
    private String pwd = "";

    private MyConnection() {

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

    public static MyConnection getInstance() {
        if (data == null) {
            data = new MyConnection();
        }
        return data;
    }
}
