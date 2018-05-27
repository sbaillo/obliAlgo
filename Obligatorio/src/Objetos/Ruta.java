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
public class Ruta {
    private int zonaOrigen;
    private int zonaDestino;
    private int minutosViaje;

    public int getZonaOrigen() {
        return zonaOrigen;
    }

    public void setZonaOrigen(int zonaOrigen) {
        this.zonaOrigen = zonaOrigen;
    }

    public int getZonaDestino() {
        return zonaDestino;
    }

    public void setZonaDestino(int zonaDestino) {
        this.zonaDestino = zonaDestino;
    }

    public int getMinutosViaje() {
        return minutosViaje;
    }

    public void setMinutosViaje(int minutosViaje) {
        this.minutosViaje = minutosViaje;
    }

    public Ruta(int zonaOrigen, int zonaDestino, int minutosViaje) {
        this.zonaOrigen = zonaOrigen;
        this.zonaDestino = zonaDestino;
        this.minutosViaje = minutosViaje;
    }
    
    
}
