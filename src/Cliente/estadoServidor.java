/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 *
 * @author Tulea4ever
 */
public abstract class estadoServidor {
    
    private static String host = "localhost";    
    private static int port = 29000;
    private static PrintWriter envio;
    private static BufferedReader Respuesta;
    
    public static boolean estadoServidor(){
        //DELECARACION DE RESPUESTA
        boolean status = false;
        //CONECTAR CON EL SERVIDOR
        try (Socket socket = new Socket(host,port)){
            //DECLARACIONES PARA ENVIO Y RECIBO
            envio = new PrintWriter(socket.getOutputStream(),true);
            Respuesta = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            //CREAR JSON PARA ENVIAR
            JSONObject datos = new JSONObject();
            datos.put("action", "estado");
            //ENVIAR CADENA Y VERIFICAR SI RECIBIMOS RESPUESTA
            while(true){
                envio.println(datos.toString());
                envio.flush(); // revisar que se envien todos los datos
                //SI EL SERVIDOR RESPONDE ES VERDADERO
                if (Respuesta.readLine().equals("200")){status = true;}
                break;
            }
               
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al "
                + "verificar el estado del servidor");}
        //RETORNO DE RESULTADO
        return status;
    }
    
}
