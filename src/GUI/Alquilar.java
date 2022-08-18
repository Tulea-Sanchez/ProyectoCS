/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Cliente.*;
import Herencia.*;
import java.time.LocalDate;
import org.json.simple.JSONObject;

/**
 *
 * @author Tulea4ever
 */
public class Alquilar extends javax.swing.JFrame {

    private String usuario,action;
    private Revista revista;
    private Libro libro;
    ManagerCliente cliente = new ManagerCliente();
    /**
     * Creates new form Alquilar
     */
    public Alquilar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        labelLibro = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        textfielLibro = new javax.swing.JTextField();
        labelEditorial = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        texfielEditorial = new javax.swing.JTextField();
        textfielUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labelVolumen = new javax.swing.JLabel();
        textfielVolumen = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        textfielCodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelLibro.setBackground(new java.awt.Color(255, 255, 255));
        labelLibro.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelLibro.setForeground(new java.awt.Color(255, 255, 255));
        labelLibro.setText("LIBRO:");

        textfielLibro.setEditable(false);
        textfielLibro.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textfielLibro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textfielLibro.setText("NOMBRE LIBRO");

        labelEditorial.setBackground(new java.awt.Color(255, 255, 255));
        labelEditorial.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelEditorial.setForeground(new java.awt.Color(255, 255, 255));
        labelEditorial.setText("EDITORIAL:");

        labelUsuario.setBackground(new java.awt.Color(255, 255, 255));
        labelUsuario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setText("USUARIO:");

        labelFecha.setBackground(new java.awt.Color(255, 255, 255));
        labelFecha.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(255, 255, 255));
        labelFecha.setText("FECHA");

        texfielEditorial.setEditable(false);
        texfielEditorial.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        texfielEditorial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        texfielEditorial.setText("NOMBRE EDITORIAL");

        textfielUsuario.setEditable(false);
        textfielUsuario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textfielUsuario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textfielUsuario.setText("NOMBRE USUARIO");

        jButton1.setForeground(new java.awt.Color(0, 204, 102));
        jButton1.setText("ALQUILAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("CANCELAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        labelVolumen.setBackground(new java.awt.Color(255, 255, 255));
        labelVolumen.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelVolumen.setForeground(new java.awt.Color(255, 255, 255));
        labelVolumen.setText("VOLUMEN:");

        textfielVolumen.setEditable(false);
        textfielVolumen.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textfielVolumen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textfielVolumen.setText("VOLUMEN REVISTA");
        textfielVolumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfielVolumenActionPerformed(evt);
            }
        });

        labelCodigo.setBackground(new java.awt.Color(255, 255, 255));
        labelCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelCodigo.setForeground(new java.awt.Color(255, 255, 255));
        labelCodigo.setText("CODIGO:");

        textfielCodigo.setEditable(false);
        textfielCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textfielCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textfielCodigo.setText("CODIGO");

        jDesktopPane2.setLayer(labelLibro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textfielLibro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(labelEditorial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(labelUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(labelFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(texfielEditorial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textfielUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(labelVolumen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textfielVolumen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(labelCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(textfielCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(labelLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfielLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(labelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textfielCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(labelVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textfielVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textfielUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(labelEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(texfielEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(textfielCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(textfielLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texfielEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfielUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfielVolumen, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(94, 94, 94)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textfielVolumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfielVolumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfielVolumenActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        actionAlquilar();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Alquilar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alquilar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alquilar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alquilar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alquilar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelEditorial;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelLibro;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelVolumen;
    private javax.swing.JTextField texfielEditorial;
    private javax.swing.JTextField textfielCodigo;
    private javax.swing.JTextField textfielLibro;
    private javax.swing.JTextField textfielUsuario;
    private javax.swing.JTextField textfielVolumen;
    // End of variables declaration//GEN-END:variables

public void action(Libro xlibro,String xusuario){
    
    
    this.libro = xlibro;
    this.usuario = xusuario;

    
    textfielCodigo.setText(libro.getCodigo());
    textfielLibro.setText(libro.getNombre());
    texfielEditorial.setText(libro.getEditorial());
    textfielUsuario.setText(usuario);
    labelFecha.setText(LocalDate.now().toString());
    labelVolumen.setVisible(false);
    textfielVolumen.setVisible(false);
    action = "libro";
    //alquilar(this.libro,usuario);
}

public void action(Revista xRevista,String xusuario){
    
    revista = xRevista;
    this.usuario = xusuario;
    
    
    textfielCodigo.setText(revista.getCodigo());
    textfielLibro.setText(revista.getNombre());
    texfielEditorial.setText(revista.getEditorial());
    textfielUsuario.setText(usuario);
    labelFecha.setText(LocalDate.now().toString());
    textfielVolumen.setText(revista.getVolumen());
    labelLibro.setText("REVISTA:");
    labelVolumen.setVisible(true);
    textfielVolumen.setVisible(true);
    //alquilar(this.revista,usuario);
    action = "revista";
}

public void actionAlquilar(){
    
    if (action.equalsIgnoreCase("libro")){alquilar(this.libro,usuario);}
    else{alquilar(this.revista,usuario);}
}


public void alquilar(Revista xRevista,String xcodigoUsuario){
    
    String datos = cliente.JsonAlquilar(xRevista, xcodigoUsuario).toString();
    System.out.println("Json alquilar "+datos);
    if(cliente.alquilarSend(datos)){dispose();}
    else{System.out.println("alquilar void error"+cliente.alquilarSend(datos));}
}

public void alquilar(Libro xlibro,String xcodigoUsuario){
    
    String datos = cliente.JsonAlquilar(xlibro, xcodigoUsuario).toString();
    
    if(cliente.alquilarSend(datos)){dispose();}
    else{System.out.println("alquilar void error"+cliente.alquilarSend(datos));}
    
}




}