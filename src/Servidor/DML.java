/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tulea4ever
 */
public abstract class DML {
    
    static Statement declaracion;
    static ResultSet resultado;
    //constructor
    public DML() {
    }
    
    //FUNCIONAES DML PARA BD
    public abstract void select();
    public abstract Boolean verficiarLogin();
    
    
}
