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
            server = new ServerSocket(29000);
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

    //VERIFICAR QUE ACCION REQUIERE EL CLIENTE
    public static void action(String cadena){
        //GUARDAR LA CADENA JSON RECIBIDA Y CONVERTIRLA EN OBJETO JSON
        JSONObject JS = CreateString(cadena);
        //SI REQUIERE LOGIN
        if (JS.get("action").equals("login")){
            login(JS);   
        }
        //SI REQUIERE REGISTRAR
        else if (JS.get("action").equals("Registration")){
            Registration(JS);
        }
        //SI REQUIERE LE ENVIEN LOS LIBRO
        else if (JS.get("action").equals("libros")){
            sendLibros();  
        }
        //SI REQUIERE LE ENVIEN LAS REVISTAS
        else if (JS.get("action").equals("revistas")){
            sendRevistas(); 
        }
        //SI REQUIERE OBTENER EL ID DEL USUARIO
        else if (JS.get("action").equals("id")){
            System.out.println("id action");
            idUsuario(JS); 
        }
        //SI REQUIERE ALQUILAR UN LIBRO
        else if (JS.get("action").equals("alquilarLibros")){
            System.out.println("alquilar");
            statusAlquiler(JS); 
        }
        //SI REQUIERE ALQUILAR UNA REVISTA
        else if (JS.get("action").equals("alquilarRevistas")){
            System.out.println("alquilar");
            statusAlquiler(JS); 
        }
        //SI REQUIERE EL ENVIO DE LOS ALQUILERES
        else if (JS.get("action").equals("alquileres")){
            System.out.println("alquileres");
            sendAlquileres(JS); 
        }
        //SI REQUIERE DEVOLVER UN LIBRO O REVISTA
        else if (JS.get("action").equals("devolver")){
            System.out.println("devolver");
            sendDevolver(JS); 
        }
        //SI EL SERVIDOR ESTA ACTIVO
        else if (JS.get("action").equals("estado")){
            System.out.println("estado");
            estado();
        }
        
    }
    
    //ENVIA LOS DATOS A BDMANAGER PARA VERIFICAR Y ENVIAR RESPUESTA A CLIENTE
    public static void login(JSONObject JS){
        String resp = "404";
        String nombre = JS.get("user").toString();
        String passwd = JS.get("password").toString();
        //VERIFICAR SI LOS DATOS SON CORRECTOS
        if (verficiarLogin(nombre,passwd)){
            resp = "200";
        }
        //ENVIAR RESPUESTA AL CLIENTE
        out.println(resp);
        
    }
    
    //FUNCION PRA VERIFICAR EL ID DE UN USUARIO
    public static void idUsuario(JSONObject JS){
        String resp = "404";
        String nombre = JS.get("user").toString();
        String passwd = JS.get("password").toString();
        //SE OBTIENEN EL ID DEL USUARIO
        resp = BuscaridUsuario(nombre,passwd);
        //SE DEVUELVE EL ID AL CLIENTE
        out.println(resp);
    }
    
    //FUNCION PARA REGISTRAR USUARIOS
    public static void Registration(JSONObject JS){
        //SE DECLARA ERROR INICIALMENTE
        String resp = "404";
        String nombre = JS.get("user").toString();
        String passwd = JS.get("password").toString();
        //SI LOS DATOS SE INGRESAN CORRECTAMENTE SE DA ESTADO 200
        if (Registration(nombre,passwd)){
            resp = "200";
        }
        //SE ENVIA RESPUESTA AL SERVIDOR
        out.println(resp);
    }
    
    //FUNCION PARA ALQUILAR
    public static void statusAlquiler(JSONObject JS){
        //SE DECLARA ERROR INICIALMENTE
        String resp = "404";
        //SI SE ACTUALIZA E INSERTAN LOS DATOS CORRECTAMENTE PASA A ESTADO 200
        if (Alquilar(JS)){
            resp = "200";
        }
        //SE ENVIA RESPUESTA AL CLIENTE
        out.println(resp);
    }
    
    //FUNCION PARA DEVOLVER LOS LIBROS DE LA BD
    public static void sendLibros(){
        //SE CREA EL RESULTSET DE LOS DATOS EXTRAIDOS
        ResultSet datos = selectAll("libros");
        //SE DECLARA CUANDO DATOS SE OBTUVIERON
        int size = countSelect("libros");
        //SE DECLARA EL STRING JS
        String js;
        //SE ENVIA AL CLIENTE CUANTOS DATOS RECIBIRA
        out.println(size);
        
        try{
            
            do{
                //SE ALMACENAN LOS DATOS EXTRAIDOS
                String id = datos.getString("id");
                String nomb = datos.getString("nombre");
                String edit = datos.getString("editorial");
                String disp = datos.getString("disponible");
                //SE CREA EL STRING JSON PARA ENVIAR
                js = CreateJsonLibros(id,nomb,edit,disp).toString();
                //SE ENVIAN LOS DATOS AL CLIENTE UNO A UNO
                out.println(js);
                
            }while (datos.next());

        }catch(Exception e){}
        //SE VERIFICA QUE TODO FUE CORRECTO
        out.println("200");
    }
    
    //FUNCION PARA ENVIAR REVISTAS AL CLIENTE
    public static void sendRevistas(){
        //SE DECLARA EL RESULT SET CON LOS DATOS EXTRAIDOS
        ResultSet datos = selectAll("revistas");
        //SE DECLARA CUANTOS DATOS SE OBTUVIERON
        int size = countSelect("revistas");
        //SE DELCARA UN STRIN JS
        String js;
        //SE ENVIA AL CLIENTE CUANTOS DATOS SE LE ENVIARAN
        out.println(size);
        try{
            
            do{
                //SE ALMACENAN LOS DATOS EXTRAIDOS POR LINEA
                String id = datos.getString("id");
                String nomb = datos.getString("nombre");
                String edit = datos.getString("editorial");
                String volum = datos.getString("volumen");
                String disp = datos.getString("disponible");
                //SE CREA UN STRING JSON PARA ENVIAR
                js = CreateJsonRevista(id,nomb,edit,volum,disp).toString();
                //SE ENVIAN LOS DATOS AL CLIENTE
                out.println(js);
                
            }while (datos.next());

        }catch(Exception e){}
        //SE VERIFICA QUE TODO FUE EXITOSO
        out.println("200");
        
    }
    
    //FUNCION PARA ENVIAR LOS ALQUILERES
    public static void sendAlquileres(JSONObject JS){
        //SE DECLARA UN RESULT SET CON LOS DATOS DE LOS ALQUILERES DEL USUARIO
        ResultSet datos = selectAllalquileres("alquileres",JS.get("usuario").toString());
        //SE OBTIENE LA CANTIDAD DE DATOS A ENVIAR
        int size = countSelectAlquileres("alquileres",JS.get("usuario").toString());
        //DECLARACION DE UN STING JS
        String js;
        //SE ENVIA AL CLIENTE CUANTOS DATOS RECIBIRA
        out.println(size);
        try{
            
            do{
                //SE ALMACENAN LOS DATOS
                String id = datos.getString("id");
                System.out.println("alquileres send id "+id);
                String nomb = datos.getString("nombre");
                String edit = datos.getString("editorial");
                String volum = datos.getString("volumen");
                String user = datos.getString("usuario");
                String tip = datos.getString("tipo");
                String fecha = datos.getString("fecha");
                //CREA JSON Y CONVIERTE A STRING PARA ENVIO
                js = CreateJsonAlquileres(id,nomb,edit,volum,user,tip,fecha).toString();
                //SE ENVIA EL STRING CON LOS DATOS AL CLIENTE
                out.println(js);
                
            }while (datos.next());

        }catch(Exception e){String id = "";
                String nomb = "";
                String edit = "";
                String volum = "";
                String disp = "";
                js = CreateJsonRevista(id,nomb,edit,volum,disp).toString();
                out.println(js);}
        //out.println("200");
        
    }
    
    //FUNCION PARA DEVOLVER LIBROS O REVISTAS
    public static void sendDevolver(JSONObject JS){
        //SE DELCARA INCIADO COMO ERROR
        String resp = "404";
        //SE VERIFICA SI LA ACTUALIZACION DE DATOS ES CORRECTA
        if (actionDevolver(JS)){
            //SE CAMBIA EL ESTADO A CORRECTO
            resp = "200";
        }
        //SE INFORMA AL SERVIDOR QUE TODO BIEN
        out.println(resp);
        
    }
    
    //FUNCION PARA SERVIDOR EN LINEA
    public static void estado(){
        
        out.println("200");
        
    }
        
}
