/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Movil;

/**
 *
 * @author Santiago
 */
public class nMovil {
    Movil dato;
    ListaChofer choferes;
    ColaEspera colaEspera;
    nMovil siguiente;

    public Movil getDato() {
        return dato;
    }

    public void setDato(Movil dato) {
        this.dato = dato;
    }

    public ListaChofer getChoferes() {
        return choferes;
    }

    public void setChoferes(ListaChofer choferes) {
        this.choferes = choferes;
    }
    

    public nMovil getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nMovil siguiente) {
        this.siguiente = siguiente;
    }

    public nMovil(Movil dato) {
        this.dato = dato;
        this.siguiente = null;
        this.choferes = new ListaChofer();
        this.colaEspera = new ColaEspera();
    }
    
    
}
