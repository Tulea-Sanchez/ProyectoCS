package Servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//import java.sql.*;
import BD.*;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tulea4ever
 */
public class BDManager extends JsonManagerServer{
    
    static Statement declaracion;
    static ResultSet resultado;
    private static Connection con = null;
    protected static final String driver = "com.mysql.jdbc.Driver";
    protected static final String user = "root";
    protected static final String passwd = "password123!";
    protected static final String url = "jdbc:mysql://localhost:3306/proyectolbd?characterEncoding=utf8";

    

    public static void main(String[] args) {

        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user,passwd);
            System.out.println("Se establecio conexion con las BD");
        } catch (Exception e){
            System.out.println("Error al conectar " + e);
        }
        
        //System.out.println(verficiarLogin("admin","admin"));
    }
    
    public static Connection Conexion() {

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
    
    //conectar la declaracion con BD
    public static Statement iniciar() {
        
        try{
            //Iniciar conexion
            BDManager Conex = new BDManager();
            Conex.Conexion();
            declaracion = Conex.Conexion().createStatement();
        }
        catch (SQLException e){}
        return declaracion;
    }
    
    //insertar a la base de datos, declarar nombre de base de datos + json con datos
    public static void insert(){
        try{
            declaracion = iniciar();
            resultado = declaracion.executeQuery("select * from prueba");
            resultado.next();
            do {
                System.out.println(resultado.getString("nombre"));

            } while(resultado.next());
        } catch(Exception e){}
        
        
        
    }
    
    public static void select(String tabla,String variable,String dato){
        Boolean conclucion = false;
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from "+tabla+" where "+variable+" LIKE \""+dato.substring(0,3)+"%\"";
            System.out.println(Query);
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                System.out.println(resultado.getString(variable));
                if (dato.equalsIgnoreCase(resultado.getString(variable))){
                    conclucion = true;
                    break;
                    //insertar aca el codigo para la creacion del json y
                    //convertir la funcion a return json
                }

            } while(resultado.next());
        } catch(Exception e){}
        
    }
    
    public static Boolean verficiarLogin(String usuario,String contra){
        Conexion();
        Boolean conclucion = false;
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from usuarios where user LIKE \""+usuario.substring(0,3)+"%\"";
            System.out.println(Query);
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                System.out.println("dato bd "+resultado.getString("user"));
                System.out.println("dato usuario "+usuario);
                System.out.println("dato bd "+resultado.getString("passwd"));
                System.out.println("dato contra "+contra);
                if (usuario.equalsIgnoreCase(resultado.getString("user"))
                        && contra.equalsIgnoreCase(resultado.getString("passwd"))){
                    conclucion = true;
                    break;
                    //insertar aca el codigo para la creacion del json y
                    //convertir la funcion a return json
                }

            } while(resultado.next());
        } catch(Exception e){}
        return conclucion;
    }
    
    
    public static Boolean Registration(String user,String pass){
        Boolean conclucion = false;
        try{
            
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = (String) "INSERT INTO USUARIOS (user,passwd) values ('"+user+"','"+pass+"')";  
            //insert de los datos
            int x = declaracion.executeUpdate(Query);
            System.out.println("valor declaracion "+x);
            conclucion = true;
            
        } catch(Exception e){System.out.println(e);conclucion = false;}
        return conclucion;
    }

}
