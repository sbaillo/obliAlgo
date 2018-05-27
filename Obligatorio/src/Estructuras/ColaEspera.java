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
public class ColaEspera implements IColaEspera{
    nColaEspera primero;
    nColaEspera ultimo;
    int cantelementos;
    int maximo;

    public ColaEspera(int maximo) {
        this.primero = null;
        this.ultimo = null;
        this.cantelementos = 0;
        this.maximo = maximo;
    }
    
    public ColaEspera() {
        this.primero = null;
        this.ultimo = null;
        this.cantelementos = 0;
        this.maximo = 9999;
    }

    public nColaEspera getPrimero() {
        return primero;
    }

    public void setPrimero(nColaEspera primero) {
        this.primero = primero;
    }

    public nColaEspera getUltimo() {
        return ultimo;
    }

    public void setUltimo(nColaEspera ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantelementos() {
        return cantelementos;
    }

    public void setCantelementos(int cantelementos) {
        this.cantelementos = cantelementos;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    @Override
    public void encolar(Object dato) {
        if (!this.esllena()) {
            nColaEspera nuevo = new nColaEspera(dato);
            if (!this.esVacia()) {
                nuevo.setSiguiente(this.getPrimero());
            } else {
                this.setUltimo(nuevo);
            }
            this.setUltimo(nuevo);
            this.cantelementos = this.cantelementos + 1;
        }
    }

    @Override
    public void desencolar() {
        nColaEspera aux = this.primero;

        if (!this.esVacia()) {
            this.cantelementos = this.cantelementos - 1;

            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                while (aux.getSiguiente() != this.ultimo) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                ultimo = aux;
            }
        }
    }

    @Override
    public boolean esVacia() {
        return this.primero == null;
    }

    @Override
    public boolean esllena() {
        return this.cantelementos == this.maximo;
    }

    @Override
    public Object frente() {
        if(!this.esVacia())
            return this.ultimo.getDato();
        else
            return "Vacìa";
    }

    @Override
    public int elementos() {
        return this.cantelementos;
    }
}
