/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Tulea4ever
 */
public class Libro {
    
    //DECLARACION DE VARIABLES
    private static String codigo;
    private static String nombre;
    private static String editorial;

    //CONSTRUCTOR
    public Libro(String codigo, String nombre, String editorial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.editorial = editorial;
    }
    //GETS AND SETS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
}
