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

public class ManagerCliente {
    
    public static void main(String[] args){
        
        String host = "localhost";
        int port = 32000;
        
        try (Socket socket = new Socket(host,port)){
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            
            Scanner scanner = new Scanner(System.in);
            String line = null;
            
            while(!"exit".equalsIgnoreCase(line)){
                line = scanner.nextLine();
                
                out.println(line);
                out.flush(); // revisar que se envien todos los datos
                
                System.out.println("Respuesta del servidor: "+ in.readLine());
            }
        }catch(Exception e){e.printStackTrace();}
    }
}
