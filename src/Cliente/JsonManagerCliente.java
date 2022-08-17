/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Tulea4ever
 */
public class JsonManagerCliente {

    
    
    
    
    
    
    public static String CreateString(String user, String passwd) {
        
        String json = "{\"user\":+user+,\"pass\":+passwd}"; 
        
        return json;
    }
    
    
    
    public static JSONObject StringJson(String cadena){
        
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
        objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){System.out.println("Jsonmancliente StringJson:"+e);}
        
        //System.out.println(objetoJson.get("nombre")+"objeto");
        return objetoJson;
    }
    
    public JSONObject Jsonlogin (String user,String passwd){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "login");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    public JSONObject Jsonlibros(){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "libros");
        return datos;        
    }
    
    public JSONObject JsonRevistas(){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "revistas");
        return datos;        
    }
    
    public JSONObject JsonRegistration (String user,String passwd){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "Registration");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    
}
