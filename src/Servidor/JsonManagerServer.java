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

    
    
    
    
    
    
    public static String CreateString(String user, String passwd) {
        
        String json = "{\"user\":"+user+",\"pass\":"+passwd+"}"; 
        
        return json;
    }
    
    public static JSONObject CreateJsonLibros (String id,String nombre,String edi,String disp){
        
        
        JSONObject datos = new JSONObject();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", edi);
        datos.put("disponible", disp);
        return datos;
      
    }
    
    public static JSONObject CreateJsonRevista (String id,String nombre,String edi,String volum,String disp){
        
        
        JSONObject datos = new JSONObject();
        datos.put("id", id);
        datos.put("nombre", nombre);
        datos.put("editorial", edi);
        datos.put("volumen", volum);
        datos.put("disponible", disp);
        return datos;
      
    }
    
    
    public static JSONObject CreateString(String cadena){
        
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
        objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){}
        
        return objetoJson;
    }
    
    
    
    public static JSONObject Jsonlogin (String user,String passwd){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "login");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;
      
    }
    
    public static JSONObject JsonRegistration (String user,String passwd){
        
        
        JSONObject datos = new JSONObject();
        datos.put("action", "Registration");
        datos.put("user", user);
        datos.put("password", passwd);
        return datos;        
    }
    
    private static void extractJson(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
}
