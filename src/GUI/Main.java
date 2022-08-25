/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Cliente.estadoServidor;
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
public class Main extends estadoServidor{
        
    private static String host = "localhost";    
    private static int port = 29000;
    private static PrintWriter envio;
    private static BufferedReader Respuesta;
    
    
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        if (estadoServidor()){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    new Login().setVisible(true);
                }
            });
        }
    }
    
    //Abstract verificacion estado del servidor
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
        

