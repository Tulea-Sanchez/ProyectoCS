/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Tulea4ever
 */
public class prueba {
    
    
    public static void main(String[] args){
    
    
    
    JSONObject json = Jsonlogin("Michael","passwd");
    
    System.out.println(json.get("user"));
    System.out.println(json.get("password"));
    
    String x = json.toString();
    System.out.println(x);
    
    JSONObject json2 = StringJson(x);
    System.out.println(json2.get("user"));
    System.out.println(json2.get("password"));
    
    }
    
    
    
    
    
    
    
    
    
    public static JSONObject Jsonlogin (String user,String passwd){
        
        
        JSONObject datos = new JSONObject();
        datos.put("user", user);
        datos.put("password", "passwd");
        return datos;
        
               
    }
    
    
    public static JSONObject StringJson(String cadena){
        
        JSONObject objetoJson = null;
        JSONParser parser = new JSONParser();
        try{
        objetoJson = (JSONObject) parser.parse(cadena);
        }catch(Exception e){}
        
        return objetoJson;
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
