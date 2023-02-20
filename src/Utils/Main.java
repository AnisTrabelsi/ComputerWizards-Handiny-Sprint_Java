/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.*;

/**
 *
 * @author bengh
 */
public class Main {

    public static void main(String[] args) {
        // TODO code application logic here

//        DataSource dat=DataSource.getInstance();
//        System.out.println(dat);
//        DataSource dat1=DataSource.getInstance();
//        System.out.println(dat1);
//        
//        DataSource dat2=DataSource.getInstance();
//        System.out.println(dat2);
//        
        Connection con = DataSource.getInstance().getConnection();

        System.out.println(con);

    }

}
