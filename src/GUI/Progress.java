/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Tulea4ever
 */
public class Progress {
    
    JFrame frame = new JFrame();
    JProgressBar bar = new JProgressBar();
    
    
    
    Progress(){
        
        bar.setValue(0);
        bar.setBounds(0,0,420,50);
        //bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli",Font.BOLD,25));
        bar.setForeground(Color.GREEN);
        frame.add(bar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(445,100);
        frame.setLayout(null);
        frame.setVisible(true);
        //frame.setUndecorated(true);
        
        
        
    }
    
    public void fill(int tiempo){
        int count = 0;
        
        while(count <=100){
            bar.setValue(count);
            
            try{
                Thread.sleep(tiempo);
            }catch(Exception e){}
            count +=10;
        }   
    }
}
