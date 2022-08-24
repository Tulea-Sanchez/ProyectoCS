package Servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//import java.sql.*;

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
    
    //DECLARACION DE DATOS
    static Statement declaracion;
    static ResultSet resultado;
    private static Connection con = null;
    protected static final String driver = "com.mysql.jdbc.Driver";
    protected static final String user = "root";
    protected static final String passwd = "password123!";
    protected static final String url = "jdbc:mysql://localhost:3306/proyectocs?characterEncoding=utf8";

    
    //MAIN PARA EJECUCION AUTOMATICA
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
    
    //INICIAR COMUNICACION 
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
    //FUNCION PARA SELECCIONAR TODO DE UNA TABLA MEDIANTE WHERE ESPECIFICADO
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
                    
                }
            } while(resultado.next());
        } catch(Exception e){}
        
    }
    
    //FUNCION DE VERIFICAR LOS DATOS LOGIN ENVIADOS POR EL CLIENTE
    public static Boolean verficiarLogin(String usuario,String contra){
        //INICIA LA CONEXION CON BD
        Conexion();
        //DECLARA EL RESULTADO
        Boolean conclucion = false;
        
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from usuarios where user LIKE \""+usuario.substring(0,3)+"%\"";
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                //VERIFICAR SI LOS DATOS COINCIDEN CON LA BD
                if (usuario.equalsIgnoreCase(resultado.getString("user"))
                        && contra.equalsIgnoreCase(resultado.getString("passwd"))){
                    conclucion = true;
                    break;    
                }

            } while(resultado.next());
        } catch(Exception e){}
        //DEVOLVER RESPUSTA AL CLIENTE
        return conclucion;
    }
    
    //BUSCAR EL ID DE UN USUARIO MEDIANTE SU CONTRASEñA Y NOMBRE
    public static String BuscaridUsuario(String usuario,String contra){
        //CONECTAR CON LAS BD
        Conexion();
        //DECLARAR LA RESPUESTA AL CLIETE
        String conclucion = "";
        
        try{
            declaracion = iniciar();
            //SELCCIONAR LOS DATOS EN LA BD
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
    
    //FUNCION PARA REGISTRAR DATOS DE USUARIO ENVIADOS POR CLIENTE
    public static Boolean Registration(String user,String pass){
        //DECLARAR LA RESPUESTA 
        Boolean conclucion = false;
        try{
            //INICIAR LA COMUNICACION
            declaracion = iniciar();
            //CREAR EL QUERY PARA INSERT 
            String Query = (String) "INSERT INTO USUARIOS (user,passwd) values ('"+user+"','"+pass+"')";  
            //insert de los datos
            int x = declaracion.executeUpdate(Query);
            conclucion = true;
            
        } catch(Exception e){System.out.println(e);conclucion = false;}
        return conclucion;
    }
    
    //FUNCION PARA ALQUILAR LIBROS O REVISTAS
    public static Boolean Alquilar(JSONObject datos){
        //DECLARACION DE LA RESPUESTA
        Boolean conclucion = false;
        //VARIABLE PARA LA TABLA A INSERTAR
        String tabla = "";
        //VERIFICAR SI ES UN LIBRO O REVISTA
        if (datos.get("action").toString().equals("alquilarLibros")){tabla = "libros";}
        else {tabla = "revistas";}
        
        try{
            //INICIAR CONEXION CON BD
            declaracion = iniciar();
            //CREAR QUERY PARA INSERTAR LOS DATOS A LA TABLA ALQUILERES
            String Query = (String) "INSERT INTO alquileres (id,nombre,usuario,editorial,volumen,tipo) "
                    + "values ('"+Integer.parseInt(datos.get("cod_libro").toString())+"',"
                    + "'"+datos.get("nombre").toString()+"',"  
                    + "'"+datos.get("cod_usuario").toString()+"',"  
                    + "'"+datos.get("editorial").toString()+"',"
                    + "'"+datos.get("volumen").toString()+"',"
                    + "'"+tabla+"')";  

            //EJECUTAR EL INSERT
            declaracion.executeUpdate(Query);
            //DECLARAR NUEVO QUERY PARA ACTUALIZAR EL ESTADO DEL LIBRO/ REVISTA
            Query = (String) "UPDATE "+tabla+" SET disponible = false where id = "+Integer.parseInt(datos.get("cod_libro").toString());  
            //EJECUTAR EL UPDATE
            declaracion.executeUpdate(Query);
            conclucion = true;
            
        } catch(Exception e){System.out.println("Except alquilar bd server"+e);conclucion = false;}
        //DEVOLVER SI SE ACTUALIZO E INSERTO CORRECTAMENTE O NO
        return conclucion;
    }
    
    //FUNCION PARA SELECCIONAR TODO DE UNA TABLA
    public static ResultSet selectAll(String dato){
        Conexion();
        try{
            //INICIAR COMUNICACION CON LA BD
            declaracion = iniciar();
            //DELCARACION DEL QUERY
            String Query = "select * from "+dato;
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            
        } catch(Exception e){System.out.println(e);}
        //DEVOLVER LOS RESULTADOS DEL SELECT
        return resultado;
    }
    
    //FUNCION PARA SELECCIONAR TODO EN ALQUILERES MEDIANTE CONDICIONAL USUARIO
    public static ResultSet selectAllalquileres(String dato,String usuario){
        //INICIAR LA CONEXION CON BD
        Conexion();
        try{
            //DELCARACION
            declaracion = iniciar();
            //CREAR EL QUERY PARA EL SELECT MEDIANTE UNA CONDICION USUARIO
            String Query = "select * from alquileres where usuario = '"+usuario+"'";
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            
        } catch(Exception e){System.out.println(e);}
        
        return resultado;
    }
    
    //FUNCION PARA CONTAR LOS RESULTADOS OBTENIDOS Y ENVIAR AL CLIENTE 
    public static int countSelect(String dato){
        //INICIA LA CONEXION
        Conexion();
        //DECLARACION DEL TAMANO
        int size = 0;
        try{
            declaracion = iniciar();
            //DECLARAR EL QUERY MEDIANTE TABLAS
            String Query = "select * from "+dato;
            //traer resultados del select
            ResultSet rs  = declaracion.executeQuery(Query);
            //BUCLE PARA DEFINIR CUANTAS LINEAS DE DATOS SE OBTUVO
            while (rs.next()) {
    	        //System.out.println(rs.getString("id_libro")+"\t\t"+rs.getString("nombre"));
    	        size++;
            }
            
        } catch(Exception e){System.out.println(e);}
        
        //DEVOLVER EN NUMERO DE DATOS FINALES
        return size;
    }
    
    //FUNCION PARA CONTAR LOS ALQUILERES MEDIANTE CONDICIONAL
    public static int countSelectAlquileres(String dato,String usuario){
        //INICIAR COMUNICACION
        Conexion();
        //DECLARAR VARIABLE CONTEO
        int size = 0;
        try{
            declaracion = iniciar();
            //declaracion corta DE QUERY MEDIANTE CONDICIONAL
            String Query = "select * from "+dato+" where usuario = '"+usuario+"'";
            //traer resultados del select
            ResultSet rs  = declaracion.executeQuery(Query);
            //BUCLE PARA DEFINIR LA CANTIDAD DE DATOS OBTENIDO
            while (rs.next()) {
    	        //System.out.println(rs.getString("id_libro")+"\t\t"+rs.getString("nombre"));
    	        size++;
            }
            
        } catch(Exception e){System.out.println(e);}
        
        //RETORNO DE CUANTOS DATOS SE ENVIARAN AL CLIENTE
        return size;
    }
    
    //FUNCION PARA DEVOLVER UN LIBRO O REVISTA
    public static Boolean actionDevolver(JSONObject datos){
        //DECLARAR RESPUESTA PARA CLIENTE
        Boolean conclucion = false;
        //DECLARACION TALBA
        String tabla = "";
        //VERIFICAR SI DEVULVEN UN LIBRO O REVISTA
        if (datos.get("tipo").toString().equals("libros")){tabla = "libros";}
        else {tabla = "revistas";}
        try{
            
            declaracion = iniciar();
            //DECLARACION DE QUERY PARA BORRAR EN ALQUILERES
            String Query = "DELETE FROM alquileres where id = '"+(datos.get("id")+"' and tipo = '"+datos.get("tipo")+"'");

            //EJECUCCION DEL QUERY
            declaracion.executeUpdate(Query);
            //NUEVO QUERY PARA ACTUALIZAR ESTADO DE LIBRO O REVISTA
            Query = (String) "UPDATE "+tabla+" SET disponible = true where id = "+Integer.parseInt(datos.get("id").toString());  
            //EJECUCCION DEL QUERY
            declaracion.executeUpdate(Query);
            conclucion = true;
            
        } catch(Exception e){System.out.println("Except devolver bd server"+e);conclucion = false;}
        //RETORNO DEL RESULTADO PARA CLIENTE
        return conclucion;
    }

    
}
