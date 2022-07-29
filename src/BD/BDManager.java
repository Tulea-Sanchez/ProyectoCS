package BD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//import java.sql.*;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 *
 * @author Tulea4ever
 */
public class BDManager {

    private static Connection con = null;
    protected static final String driver = "com.mysql.jdbc.Driver";
    protected static final String user = "root";
    protected static final String passwd = "password123!";
    protected static final String url = "jdbc:mysql://localhost:3306/proyectocs?characterEncoding=utf8";

    

    public static void main(String[] args) {

        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user,passwd);
        } catch (Exception e){
            System.out.println("Error al conectar " + e);
        }

    }
    
    public Connection Conexion() {

        con = null;

        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, passwd);
            System.out.println("Se establecio conexion con las BD");

        } catch (Exception e) {
            System.out.println("Error de conexion " + e);
        }
        return con;
    }

}
