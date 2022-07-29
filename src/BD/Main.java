package BD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;

/**
 *
 * @author Tulea4ever
 */
public class Main extends DML{
    
    static Statement stmt;
    static ResultSet rs;
    
    
    
    
    public static void main(String[] args) {
        
        DML operacion = new DML();
        
        select("prueba","nombre","otro");
        /*try{
        
        BDManager Conex = new BDManager();
        Conex.Conexion();
        stmt = Conex.Conexion().createStatement();
        rs = stmt.executeQuery("select * from prueba");
        rs.next();
        do {
            System.out.println(rs.getString("nombre"));
        
        } while(rs.next());
        }
        catch (SQLException e){}*/
    }
    
}
