/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;
import Cliente.*;
import Json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Tulea4ever
 */
public class JsonManagerServer {

    //CREAR STRING PARA USUARIO Y PASSWORD
    public static String CreateString(String user, String passwd) {
        
        String json = "{\"user\":"+user+",\"pass\":"+passwd+"}"; 
        
        return json;
    }
    
    //CRAER JSON DE LIBROS MEDIANTE DATOS RECIBIDOS DEL CLIENTE
    public static JSONObject CreateJsonLibros (String id,String nombre,String edi,String disp){
        
        //DECLARAR OBJETO JSON E INSERTAR DATOS
        JSONObject datos = new JSONObject();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", edi);
        datos.put("disponible", disp);
        return datos;
    }
    
    //CREAR JSON PARA REVISTAS
    public static JSONObject CreateJsonRevista (String id,String nombre,String edi,String volum,String disp){
        
        //DECLARAR OBJETO JSON E INSERTAR DATOS
        JSONObject datos = new JSONObject();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", edi);
        datos.put("volumen", volum);
        datos.put("disponible", disp);
        return datos;
    }
    
    //CREAR JSON PARA ALQUILERES MEDIANTE DATOS INSERTADOS
    public static JSONObject CreateJsonAlquileres (String id,String nombre,String edi,String volum,String usuario,String tipo,String fecha){
        
        
        JSONObject datos = new JSONObject();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", edi);
        datos.put("volumen", volum);
        datos.put("usuario", usuario);
        datos.put("tipo", tipo);
        datos.put("fecha", fecha);
        return datos;
    }
    
    //CREAR JSON MEDIANTE UNA CADENA CON FORMATO JSON
    public static JSONObject CreateString(String cadena){
        //CREACIN DEL OBJETO
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
            //SI LA CONVERSION ES CORRECTA
            objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){}
        //RETORNA EL OBJETO JSON
        return objetoJson;
    }
    
    //CREACION DE LOGIN JSON PARA LECTURA DE LOS DATOS
    public static JSONObject Jsonlogin (String user,String passwd){
        //CREACION DE OBJETO E INSERT LOS DATOS
        JSONObject datos = new JSONObject();
        datos.put("action", "login");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;
    }
    
    //CREAR JSON PARA REGISTRO
    public static JSONObject JsonRegistration (String user,String passwd){
        
        //DELCARACION DE OBJETO E INSERTAR DATOS
        JSONObject datos = new JSONObject();
        datos.put("action", "Registration");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
}
