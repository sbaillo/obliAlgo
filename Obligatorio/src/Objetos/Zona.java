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
public class Zona {
    private int zonaID;
    private String zonaNombre;
    private static int zonaIDcont = 1;

    public int getZonaID() {
        return zonaID;
    }

    public void setZonaID(int zonaID) {
        this.zonaID = zonaID;
    }

    public String getZonaNombre() {
        return zonaNombre;
    }

    public void setZonaNombre(String zonaNombre) {
        this.zonaNombre = zonaNombre;
    }

    public Zona(String zonaNombre) {
        this.zonaID = zonaIDcont++;
        this.zonaNombre = zonaNombre;
    }
    
}