/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Json;
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
    
    private static boolean verificarLogin(){
        boolean respuesta = false;
        
        return respuesta;
    }
    
    public JSONObject StringJson(String cadena){
        
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
        objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){}
        
        return objetoJson;
    }
}
