/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Tulea4ever
 */
public class Revista extends Libro{
    //DECLARACION DE NUEVO ATRIBUTO
    private static String volumen;
    //CONSTRUCTOR + HERENCIA
    public Revista(String codigo, String nombre, String editorial,String volumen) {
        super(codigo, nombre, editorial);
        this.volumen = volumen;
    }
    //GETS AND SETS
    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }
    
    
}
