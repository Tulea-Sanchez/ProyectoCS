package Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tulea4ever
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import Json.*;
import org.json.simple.JSONObject;

public class ManagerCliente extends JsonManagerCliente{
    
    private static String host = "localhost";    
    private static int port = 32000;
    private static JSONObject response;
    private static PrintWriter envio;
    private static BufferedReader Respuesta;

    public ManagerCliente() {
    }
    
    
    
    
  /* public static void main(String[] args){
        
        JSONObject x = JsonManagerCliente.JsonRegistration("michael","1234");
        boolean r = false;
        r = sendSesion(x.toString());
        System.out.println("Respuesta server: "+r);
        System.out.println("response"+r);
        
        
        
    }*/
    
    public boolean sendSesion(String jsonString){
        boolean status = false;
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            while(true){
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            if (Respuesta.readLine().equals("200")){status = true;}
            //System.out.println("Respuesta del servidor:"+ Respuesta.readLine());
            break;
            }
            
            
        }catch(Exception e){System.out.println("except");}
        
        return status;
    }
    
    public String send(String jsonString){
        String cadena = "";
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            while(true){
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            cadena = Respuesta.readLine();
            System.out.println("Respuesta del servidor: "+ Respuesta.readLine());
            break;
            }
            
            
        }catch(Exception e){System.out.println("except");}
        
        return cadena;
    }
    
    
    
    
    
}
