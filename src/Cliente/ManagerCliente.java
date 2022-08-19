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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
 import javax.swing.JOptionPane;

public class ManagerCliente extends JsonManagerCliente{
    
    private static String host = "localhost";    
    private static int port = 29000;
    private static JSONObject response;
    private static PrintWriter envio;
    private static BufferedReader Respuesta;

    public ManagerCliente() {
    }
    
    //ENVIAR AL SERVIDOR DATOS DE USUARIO PARA VERIFICAR
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
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar Sesion");}
        return status;
    }
    
    //ENVIAR DATOS PARA ALQUILAR, DEVUELVE 200 SI SE MODIFICA LA BD EXITOSO
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
                break;
            }
               
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar alquileres MC");}
        
        return status;
    }
    
    //CONECTAR CON EL SERVIDOR PARA OBTENER ID DE USUARIO
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
                break;
            }
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviAR idUsuario MC");}
        
        return status;
    }
    
    
    //ENVIAR SOLICITUDES AL SERVIDOR MEDIANTE JSON Y ACTIONS
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
                break;
            }
            

        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar SEND MC");}
        
        return cadena;
    }
    
    //ENVIAR Y RECIBIR DATOS DE LIBROS PARA CONVERIR EN TABLEMODEL
    public DefaultTableModel sendReceivesLibros(String jsonString){
        
        Object [] fila=new Object[4];
        String cadena = "";
        //DECLARACION DEL MODELO
        DefaultTableModel modelo = new DefaultTableModel();
        //AGREGAR LOS NOMBRES DE COLUMNAS
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("ESTADO");
        
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            envio.println(jsonString);
            envio.flush(); 
            //OBTENER LA CANTIDAD DE DATOS A RECIBIR
            int size = Integer.parseInt(Respuesta.readLine());
            
            //GUARDAR LAS SIGUIENTES X CANTIDAD DE SOLICITUDES EN MODEL
            for (int i = 0;i<size;i++){
                
                cadena = Respuesta.readLine();
                JSONObject js = StringJson(cadena);

                if(js.get("disponible").equals("1")){
                    fila[0]=js.get("id");
                    fila[1]=js.get("nombre");
                    fila[2]=js.get("editorial");
                    fila[3]="disponible";
                    modelo.addRow(fila); 
                }
            }
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar MODELO LIBROS MC");}
        
        return modelo;
    }
    
    //ENVIAR Y RECIBIR DATOS DE ALQUILERES PARA CONVERIR EN TABLEMODEL
    public DefaultTableModel sendReceivesAlquileres(String jsonString){
        
        Object [] fila=new Object[7];
        String cadena = "";
        //DECLARACION DEL MODELO LIMPIO
        DefaultTableModel modelo = new DefaultTableModel();
        //AGREGAR COLUMNAS CON SUS NOMBRES
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

            envio.println(jsonString);
            envio.flush(); 
            //DEFINIR CUANTOS DATOS ENVIARA EL SERVIDOR
            int size = Integer.parseInt(Respuesta.readLine());

            for (int i = 0;i<size;i++){
                
                cadena = Respuesta.readLine();
                System.out.println("sendReceives "+cadena);
                //COMVERTIR CADENA A JSON PARA FACILITAR LECTURA
                JSONObject js = StringJson(cadena);
                fila[0]=js.get("id");
                fila[1]=js.get("nombre");
                fila[2]=js.get("editorial");
                fila[3]=js.get("volumen");
                fila[4]=js.get("usuario");
                fila[5]=js.get("tipo");
                fila[6]=js.get("fecha");
                modelo.addRow(fila); 

            }
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar MODELO ALQUILERES MC");}
        return modelo;
    }
    
    //ENVIAR Y RECIBIR DATOS DE REVISTAS PARA AGREGAR A UN MODELO TABLE
    public DefaultTableModel sendReceivesRevistas(String jsonString){
        
        Object [] fila=new Object[5];
        String cadena = "";
        //DECLARAR NUEVO MODELO LIMPIO
        DefaultTableModel modelo = new DefaultTableModel();
        //INSERTAR COLUMNAS Y NOMBRES
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("VOLUMEN");
        modelo.addColumn("ESTADO");
        
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            //ENVIAR JSON AL SERVIDOR PARA QUE ENVIE LOS DATOS
            envio.println(jsonString);
            envio.flush();
            //RECIBIR LA CANTIDAD DE DATOS QUE SERAN ENVIADOS
            int size = Integer.parseInt(Respuesta.readLine());
            
            for (int i = 0;i<size;i++){
                
                cadena = Respuesta.readLine();
                //CREACION DE JSON PARA LECTURA
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
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar MODELO REVISTAS MC");}
        
        return modelo;
    }
    
    //ENVIAR DATOS PARA DEVOLVER UN LIBRO O REVISTA, ACTUALIZAR BD
    public boolean sendDevolver(String jsonString){
        
        boolean status = false;
        try (Socket socket = new Socket(host,port)){
            
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            //ENVIAR EL JSON CON LOS DATOS A MODIFICAR Y ESPERAR RESPUESTA
            while(true){
                envio.println(jsonString);
                envio.flush(); 
                if (Respuesta.readLine().equals("200")){status = true;}
                break;
            }
            
            
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "enviar ENVIO DEVOLUCIONES MC");}
        
        return status;
    }
    
}
