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
  ListaAbonado abonados;
  ColaEspera espera;
  nZona siguiente;

    public ListaAbonado getAbonados() {
        return abonados;
    }

    public void setAbonados(ListaAbonado abonados) {
        this.abonados = abonados;
    }

    public ListaMovil getMoviles() {
        return moviles;
    }

    public void setMoviles(ListaMovil moviles) {
        this.moviles = moviles;
    }

    public ColaEspera getEspera() {
        return espera;
    }

    public void setEspera(ColaEspera espera) {
        this.espera = espera;
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
    
    public nZona(Zona dato) {
        this.dato = dato;
        this.siguiente = null;
        this.moviles = new ListaMovil();
        this.abonados = new ListaAbonado();
        this.espera = new ColaEspera();
    }
}
