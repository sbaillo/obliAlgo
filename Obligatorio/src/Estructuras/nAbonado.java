/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Abonado;


/**
 *
 * @author Santiago
 */
public class nAbonado {
    Abonado dato;
    nAbonado siguiente;

    public Abonado getDato() {
        return dato;
    }

    public void setDato(Abonado dato) {
        this.dato = dato;
    }

    public nAbonado getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nAbonado siguiente) {
        this.siguiente = siguiente;
    }

    public nAbonado(Abonado dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    
}
