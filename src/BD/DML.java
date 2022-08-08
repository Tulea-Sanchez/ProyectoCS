/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tulea4ever
 */
public class DML {
    static Statement declaracion;
    static ResultSet resultado;

    public DML() {
    }
    
    
    
    
    //conectar la declaracion con BD
    public static Statement iniciar() {
        
        try{
            //Iniciar conexion
            BDManager Conex = new BDManager();
            Conex.Conexion();
            declaracion = Conex.Conexion().createStatement();
        }
        catch (SQLException e){}
        return declaracion;
    }
    
    //insertar a la base de datos, declarar nombre de base de datos + json con datos
    public static void insert(){
        try{
            declaracion = iniciar();
            resultado = declaracion.executeQuery("select * from prueba");
            resultado.next();
            do {
                System.out.println(resultado.getString("nombre"));

            } while(resultado.next());
        } catch(Exception e){}
        
    }
    
    public static void select(String tabla,String variable,String dato){
        Boolean conclucion = false;
        try{
            declaracion = iniciar();
            //declaracion corta para evitar inyeccion
            String Query = "select * from "+tabla+" where "+variable+" LIKE \""+dato.substring(0,3)+"%\"";
            System.out.println(Query);
            //traer resultados del select
            resultado = declaracion.executeQuery(Query);
            resultado.next();
            do {
                System.out.println(resultado.getString(variable));
                if (dato.equalsIgnoreCase(resultado.getString(variable))){
                    conclucion = true;
                    break;
                    //insertar aca el codigo para la creacion del json y
                    //convertir la funcion a return json
                }

            } while(resultado.next());
        } catch(Exception e){}
        
    }
    
}
