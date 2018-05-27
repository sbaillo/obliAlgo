/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Zona;

/**
 *
 * @author Santiago
 */
public class nZona {
  Zona dato;
  ListaMovil moviles;
  nZona siguiente;

    public ListaMovil getMoviles() {
        return moviles;
    }

    public void setMoviles(ListaMovil moviles) {
        this.moviles = moviles;
    }

    public nZona(Zona dato) {
        this.dato = dato;
        this.siguiente = null;
        this.moviles = new ListaMovil();
    }

    public Zona getDato() {
        return dato;
    }

    public void setDato(Zona dato) {
        this.dato = dato;
    }

    public nZona getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nZona siguiente) {
        this.siguiente = siguiente;
    }

    

  
}
