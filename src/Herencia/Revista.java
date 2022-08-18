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
    
    private static String volumen;

    public Revista(String codigo, String nombre, String editorial,String volumen) {
        super(codigo, nombre, editorial);
        this.volumen = volumen;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }
    
    
}
