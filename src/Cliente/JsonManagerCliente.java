/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Herencia.*;
/**
 *
 * @author Tulea4ever
 */
public class JsonManagerCliente {

    //CREAR JSON PARA LOGIN 
    public static String CreateString (String user, String passwd) {
        
        String json = "{\"user\":+user+,\"pass\":+passwd}"; 
        
        return json;
    }
    
    
    //CONVERIR UNA CADENA EN FORMATO JSON A UN OBJETO JSON
    public JSONObject StringJson(String cadena){
        
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
        objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){System.out.println("Jsonmancliente StringJson:"+e);}
        return objetoJson;
    }
    
    //CREAR JSON QUE SE ENVIA PARA VERIFICAR LOGIN
    public JSONObject Jsonlogin (String user,String passwd){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "login");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    //CREAR JSON PARA DEVOLVER LIBRO O REVISTA
    public JSONObject JsonDevolver (String id,String tipo){

        JSONObject datos = new JSONObject();
        datos.put("action", "devolver");
        datos.put("id", id);
        datos.put("tipo", tipo);
        
    
        return datos;        
    }
    
    //CREAR JSON PARA OBTENER EL ID DE UN USUARIO 
    public JSONObject JsonID (String user,String passwd){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "id");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    //CREAR JSON PARA DATOS DE ALQUILERES LIBROS
    public JSONObject JsonAlquiler (String id,String nombre,String editorial,String usuario){
 
        JSONObject datos = new JSONObject();
        datos.put("action", "alquilarLibro");
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", editorial);
        datos.put("volumen", "null");
        datos.put("usuario", usuario);
        return datos;        
    }
    
    //CREAR JSON PARA DATOS DE ALQUILERES REVISTAS
    public JSONObject JsonAlquiler (String id,String nombre,String editorial,String volumen,String usuario){

        JSONObject datos = new JSONObject();
        datos.put("action", "alquilarRevista");
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", editorial);
        datos.put("volumen", volumen);
        datos.put("usuario", usuario);
        return datos;        
    }
    
    //CREAR JSON PARA SOLICITAR LIBROS AL SERVER
    public JSONObject Jsonlibros(){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "libros");
        return datos;        
    }
    
    //CREAR JSON PARA SOLICITAR ALQUILERES AL SERVER
    public JSONObject JsonAlquileres(String nombre){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "alquileres");
        datos.put("usuario", nombre);
        return datos;        
    }
    
    //CREAR JSON PARA SOLICITAR REVISTAS AL SERVER
    public JSONObject JsonRevistas(){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "revistas");
        return datos;        
    }
    
    //CREAR JSON PARA REGISTRAR UN NUEVO USUARIO
    public JSONObject JsonRegistration (String user,String passwd){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "Registration");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    //CREAR JSON PARA ALQUILAR UN LIBRO
    public JSONObject JsonAlquilar (Libro libro,String codigoUsuario){
         
        JSONObject datos = new JSONObject();
        datos.put("action", "alquilarLibros");
        datos.put("tabla", "libros");
        datos.put("cod_libro", libro.getCodigo());
        datos.put("nombre", libro.getNombre());
        datos.put("editorial", libro.getEditorial());
        datos.put("volumen", "");
        datos.put("cod_usuario", codigoUsuario);
        return datos;        
    }
    
    //CREAR JSON PARA ALQUILAR UNA REVISTA
    public JSONObject JsonAlquilar (Revista revista,String codigoUsuario){
        
        JSONObject datos = new JSONObject();
        datos.put("action", "alquilarRevistas");
        datos.put("tabla", "revistas");
        datos.put("cod_libro", revista.getCodigo());
        datos.put("nombre", revista.getNombre());
        datos.put("editorial", revista.getEditorial());
        datos.put("volumen", revista.getVolumen());
        datos.put("cod_usuario", codigoUsuario);
        return datos;        
    }
    
    
}
