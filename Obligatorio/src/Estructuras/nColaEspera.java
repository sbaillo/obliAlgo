/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author alumnoFI
 */
public class nColaEspera {
    Object dato;
    nColaEspera siguiente;
    
    public nColaEspera(Object dato) {
        this.siguiente = null;
        this.dato = dato;
    }

    public nColaEspera getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nColaEspera siguiente) {
        this.siguiente = siguiente;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }
}
