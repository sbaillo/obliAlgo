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
public class Movil {
    private String movilID;
    private Estado estado;
    private int emergencias;
    private String zonaMovil;

    public String getMovilID() {
        return movilID;
    }

    public void setMovilID(String movilID) {
        this.movilID = movilID;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public int getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(int emergencias) {
        this.emergencias = emergencias;
    }

    public String getZonaMovil() {
        return zonaMovil;
    }

    public void setZonaMovil(String zonaMovil) {
        this.zonaMovil = zonaMovil;
    }
    
    
    public Movil(String movilID, String zona) {
        this.movilID = movilID;
        this.zonaMovil = zona;
        this.estado = Estado.DISPONIBLE;
        this.emergencias = 0;
    }
    
    
}
