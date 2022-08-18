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
import org.json.simple.JSONObject;

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
    protected static final String url = "jdbc:mysql://localhost:3306/proyectocs?characterEncoding=utf8";

    

    public static void main(String[] args) {

        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user,passwd);
            System.out.println("Se establecio conexion con las BD");
        } catch (Exception e){
            System.out.println("Error al conectar main BDmanager" + e);
        }
    }
    
    public static Connection Conexion() {

        con = null;

        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            System.out.println("Error de conexion() bdmanager " + e);
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

            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
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
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                
                if (usuario.equalsIgnoreCase(resultado.getString("user"))
                        && contra.equalsIgnoreCase(resultado.getString("passwd"))){
                    conclucion = true;
                    break;
                    
                }

            } while(resultado.next());
        } catch(Exception e){}
        return conclucion;
    }
    
    public static String BuscaridUsuario(String usuario,String contra){
        Conexion();
        String conclucion = "";
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from usuarios ";
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                
                if (usuario.equalsIgnoreCase(resultado.getString("user"))
                        && contra.equalsIgnoreCase(resultado.getString("passwd"))){
                    conclucion = resultado.getString("id");
                    //System.out.println("resultado id "+ conclucion);
                    break;
                    
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
            conclucion = true;
            
        } catch(Exception e){System.out.println(e);conclucion = false;}
        return conclucion;
    }
    
    
    public static Boolean Alquilar(JSONObject datos){
        Boolean conclucion = false;
        String tabla = "";
        
        if (datos.get("action").toString().equals("alquilarLibros")){tabla = "libros";}
        else {tabla = "revistas";}
        
        try{
            
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            
            System.out.println("alquilar boolean bd server antes del querry");
            String Query = (String) "INSERT INTO alquileres (id,nombre,usuario,editorial,volumen,tipo) "
                    + "values ('"+Integer.parseInt(datos.get("cod_libro").toString())+"',"
                    + "'"+datos.get("nombre").toString()+"',"  
                    + "'"+datos.get("cod_usuario").toString()+"',"  
                    + "'"+datos.get("editorial").toString()+"',"
                    + "'"+datos.get("volumen").toString()+"',"
                    + "'"+tabla+"')";  

            //insert de los datos
            declaracion.executeUpdate(Query);
            
            Query = (String) "UPDATE "+tabla+" SET disponible = false where id = "+Integer.parseInt(datos.get("cod_libro").toString());  
            
            declaracion.executeUpdate(Query);
            conclucion = true;
            
        } catch(Exception e){System.out.println("Except alquilar bd server"+e);conclucion = false;}
        return conclucion;
    }
    
    public static ResultSet selectAll(String dato){
        Conexion();
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from "+dato;
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            
        } catch(Exception e){System.out.println(e);}
        
        return resultado;
    }
    
    public static ResultSet selectAllalquileres(String dato,String usuario){
        Conexion();
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from alquileres where usuario = '"+usuario+"'";
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            
        } catch(Exception e){System.out.println(e);}
        
        return resultado;
    }
    
    public static int countSelect(String dato){
        Conexion();
        int size = 0;
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from "+dato;
            //traer resultados del select
            ResultSet rs  = declaracion.executeQuery(Query);
            
            while (rs.next()) {
    	        //System.out.println(rs.getString("id_libro")+"\t\t"+rs.getString("nombre"));
    	        size++;
            }
            
        } catch(Exception e){System.out.println(e);}
        
        //System.out.println("Conteo: "+size);
        return size;
    }
    
    public static int countSelectAlquileres(String dato,String usuario){
        Conexion();
        int size = 0;
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from "+dato+" where usuario = '"+usuario+"'";
            //traer resultados del select
            ResultSet rs  = declaracion.executeQuery(Query);
            
            while (rs.next()) {
    	        //System.out.println(rs.getString("id_libro")+"\t\t"+rs.getString("nombre"));
    	        size++;
            }
            
        } catch(Exception e){System.out.println(e);}
        
        //System.out.println("Conteo: "+size);
        return size;
    }
    
    
    public static Boolean actionDevolver(JSONObject datos){
        Boolean conclucion = false;
        String tabla = "";
        System.out.println(datos.get("tipo").toString());
        if (datos.get("tipo").toString().equals("libros")){tabla = "libros";}
        else {tabla = "revistas";}
        try{
            
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            System.out.println("tabla action devolver "+tabla);
            //System.out.println("devolver boolean bd server antes del querry");
            String Query = "DELETE FROM alquileres where id = '"+(datos.get("id")+"' and tipo = '"+datos.get("tipo")+"'");
            System.out.println("Query action devolver "+Query);
            //insert de los datos
            declaracion.executeUpdate(Query);
            
            Query = (String) "UPDATE "+tabla+" SET disponible = true where id = "+Integer.parseInt(datos.get("id").toString());  
            
            declaracion.executeUpdate(Query);
            conclucion = true;
            
        } catch(Exception e){System.out.println("Except devolver bd server"+e);conclucion = false;}
        return conclucion;
    }

    
}
