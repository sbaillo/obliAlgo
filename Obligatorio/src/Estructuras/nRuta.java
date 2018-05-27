/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Ruta;

/**
 *
 * @author Santiago
 */
public class nRuta {
  Ruta dato;
  nRuta siguiente;

    public Ruta getDato() {
        return dato;
    }

    public void setDato(Ruta dato) {
        this.dato = dato;
    }

    public nRuta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nRuta siguiente) {
        this.siguiente = siguiente;
    }

    public nRuta(Ruta dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
}
