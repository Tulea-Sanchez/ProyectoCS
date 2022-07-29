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
import java.util.Scanner;

public class ManagerServidor {
    
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
    private final Socket clientSocket;
    
    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }
    
    @Override
    public void run(){
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));
                
            String line = null;
            
            while ((line = in.readLine()) != null){
                
                System.out.printf("Dato enviado por el cliente: %s\n", line);
                out.println("200");
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
}
