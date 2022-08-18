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
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;

public class ManagerCliente extends JsonManagerCliente{
    
    private static String host = "localhost";    
    private static int port = 29000;
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
            
            
        }catch(Exception e){System.out.println("except login "+e);}
        
        return status;
    }
    
    
    public boolean alquilarSend(String jsonString){
        
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
            
            
        }catch(Exception e){System.out.println("except"+e);}
        
        return status;
    }
    
    public String idUsuario(String jsonString){
        String status = "";
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            for(int i = 0;i<1;i++){
                envio.println(jsonString);
                envio.flush(); // revisar que se envien todos los datos
                String x = "1";
                if (Respuesta.readLine().equals("1")){status = x;}
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
            
            while(cadena!="200"){
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            cadena = Respuesta.readLine();
            //System.out.println("Respuesta del servidor: "+ Respuesta.readLine());
            break;
            }
            
            
            
        }catch(Exception e){System.out.println("except"+e);}
        
        return cadena;
    }
    
    
    public DefaultTableModel sendReceivesLibros(String jsonString){
        Object [] fila=new Object[4];
        String cadena = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("ESTADO");
        
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            
            int size = Integer.parseInt(Respuesta.readLine());
            
            for (int i = 0;i<size;i++){
                
                
                cadena = Respuesta.readLine();
                JSONObject js = StringJson(cadena);
                //System.out.println("sendReceives "+js);
                if(js.get("disponible").equals("1")){
                    fila[0]=js.get("id");
                    fila[1]=js.get("nombre");
                    fila[2]=js.get("editorial");
                    fila[3]="disponible";
                    modelo.addRow(fila); 
                }
            }
            
        }catch(Exception e){System.out.println("except sendRevistas"+e);}
        
        return modelo;
    }
    
    public DefaultTableModel sendReceivesAlquileres(String jsonString){
        Object [] fila=new Object[7];
        String cadena = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("VOLUMEN");
        modelo.addColumn("USUARIO");
        modelo.addColumn("TIPO");
        modelo.addColumn("FECHA");
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            //System.out.println("sendReceivesalquileres "+jsonString);
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            
            int size = Integer.parseInt(Respuesta.readLine());
            System.out.println("sendReceives size"+size);
            for (int i = 0;i<size;i++){
                
                
                cadena = Respuesta.readLine();
                System.out.println("sendReceives "+cadena);
                JSONObject js = StringJson(cadena);
                //System.out.println("sendReceives "+js);
                fila[0]=js.get("id");
                fila[1]=js.get("nombre");
                fila[2]=js.get("editorial");
                fila[3]=js.get("volumen");
                fila[4]=js.get("usuario");
                fila[5]=js.get("tipo");
                fila[6]=js.get("fecha");
                modelo.addRow(fila); 

            }
            
        }catch(Exception e){System.out.println("except sendalquileres"+e);}
        
        return modelo;
    }
    
    public DefaultTableModel sendReceivesRevistas(String jsonString){
        Object [] fila=new Object[5];
        String cadena = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("VOLUMEN");
        modelo.addColumn("ESTADO");
        
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            
            int size = Integer.parseInt(Respuesta.readLine());
            
            for (int i = 0;i<size;i++){
                
                cadena = Respuesta.readLine();
                JSONObject js = StringJson(cadena);
                if(js.get("disponible").equals("1")){
                    fila[0]=js.get("id");
                    fila[1]=js.get("nombre");
                    fila[2]=js.get("editorial");
                    fila[3]=js.get("volumen");
                    fila[4]="disponible";
                    modelo.addRow(fila); 
                }
            }
            
        }catch(Exception e){System.out.println("except sendRevistas"+e);}
        
        return modelo;
    }
    
    
    
    public boolean sendDevolver(String jsonString){
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
    
    /*public Array send(String jsonString){
        String cadena = "";
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            while(Respuesta.readLine()!="200"){
            envio.println(jsonString);
            envio.flush(); // revisar que se envien todos los datos
            cadena = Respuesta.readLine();
            System.out.println("Respuesta del servidor: "+ Respuesta.readLine());
            break;
            }
            
            
        }catch(Exception e){System.out.println("except");}
        
        return cadena;
    }*/
    

    
    
    
}
