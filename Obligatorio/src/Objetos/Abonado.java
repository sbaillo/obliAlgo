/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Santiago
 */
public class Abonado {
    private int abonadoID;
    private String abonadoNombre;
    private String abonadoDireccion;
    private String abonadoTel;

    public int getAbonadoID() {
        return abonadoID;
    }

    public void setAbonadoID(int abonadoID) {
        this.abonadoID = abonadoID;
    }

    public String getAbonadoNombre() {
        return abonadoNombre;
    }

    public void setAbonadoNombre(String abonadoNombre) {
        this.abonadoNombre = abonadoNombre;
    }

    public String getAbonadoDireccion() {
        return abonadoDireccion;
    }

    public void setAbonadoDireccion(String abonadoDireccion) {
        this.abonadoDireccion = abonadoDireccion;
    }

    public String getAbonadoTel() {
        return abonadoTel;
    }

    public void setAbonadoTel(String abonadoTel) {
        this.abonadoTel = abonadoTel;
    }

    public Abonado(int abonadoID, String abonadoNombre, String abonadoDireccion, String abonadoTel) {
        this.abonadoID = abonadoID;
    }
}
