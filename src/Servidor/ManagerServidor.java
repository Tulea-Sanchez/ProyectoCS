package Servidor;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList; 

public class ManagerServidor extends BDManager{
    
    JsonManagerServer dataJson = new JsonManagerServer();
    public static Socket clientSocket;
    public static PrintWriter out = null;
    
    
    public static void main(String[] args){
        
        ServerSocket server = null;
        
        try{
            server = new ServerSocket(32000);
            server.setReuseAddress(true);
            
            while (true){
                Socket client = server.accept();
                System.out.println("Nuevo cliente conectado: " 
                + client.getInetAddress().getHostAddress());
                
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
            
            
        }catch(Exception e){e.printStackTrace();}
    }
    
static class ClientHandler implements Runnable{
    public Socket clientSocket;
    
    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }
    
    @Override
    public void run(){
        //PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));
                
            String line = null;
            
            while ((line = in.readLine()) != null){
                System.out.printf("Dato enviado por el cliente: %s\n", line);
                action(line);
                
            }
                
            
        }
        catch(Exception e){}
        finally {
            try{
                if (out!= null){
                    out.close();
                }
                if (in != null){
                    in.close();
                    clientSocket.close();
                }
                
            }catch(Exception e){}
        }
    }
}


    public static void action(String cadena){
       
        JSONObject JS = CreateString(cadena);
        if (JS.get("action").equals("login")){
            login(JS);   
        }    
        else if (JS.get("action").equals("Registration")){
            Registration(JS);
        }
        else if (JS.get("action").equals("libros")){
            sendLibros();  
        }
        else if (JS.get("action").equals("revistas")){
            sendRevistas(); 
        }
        
    }
    
    
    public static void login(JSONObject JS){
        String resp = "404";
        String nombre = JS.get("user").toString();
        String passwd = JS.get("password").toString();
        
        if (verficiarLogin(nombre,passwd)){
            resp = "200";
        }
        
        out.println(resp);
        
    }

    public static void Registration(JSONObject JS){
        String resp = "404";
        String nombre = JS.get("user").toString();
        String passwd = JS.get("password").toString();
        
        if (Registration(nombre,passwd)){
            resp = "200";
        }
                
        out.println(resp);
    }
    
    public static void sendLibros(){
        ResultSet datos = selectAll("libros");
        int size = countSelect("libros");
        String js;
        out.println(size);
        try{
            
            do{
               
                String id = datos.getString("id_libro");
                String nomb = datos.getString("nombre");
                String edit = datos.getString("editorial");
                String disp = datos.getString("disponible");
                
                js = CreateJsonLibros(id,nomb,edit,disp).toString();
                
                out.println(js);
                
            }while (datos.next());

        }catch(Exception e){}
        out.println("200");
        
    }
    
    public static void sendRevistas(){
        ResultSet datos = selectAll("revistas");
        int size = countSelect("revistas");
        String js;
        out.println(size);
        try{
            
            do{
               
                String id = datos.getString("id_revista");
                String nomb = datos.getString("nombre");
                String edit = datos.getString("editorial");
                String volum = datos.getString("volumen");
                String disp = datos.getString("disponible");
                js = CreateJsonRevista(id,nomb,edit,volum,disp).toString();
                out.println(js);
                
            }while (datos.next());

        }catch(Exception e){}
        out.println("200");
        
    }
        
}
