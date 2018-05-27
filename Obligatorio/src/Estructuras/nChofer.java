/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Chofer;

/**
 *
 * @author Santiago
 */
public class nChofer {
    Chofer dato;
    nChofer siguiente;

    public Chofer getDato() {
        return dato;
    }

    public void setDato(Chofer dato) {
        this.dato = dato;
    }

    public nChofer getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nChofer siguiente) {
        this.siguiente = siguiente;
    }

    public nChofer(Chofer dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    
}
