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
public class ListaMovil implements IListaMovil{
    nMovil inicio;
    nMovil ultimo;
    int cantidadElementos;

    public nMovil getInicio() {
        return inicio;
    }

    public void setInicio(nMovil inicio) {
        this.inicio = inicio;
    }

    public nMovil getUltimo() {
        return ultimo;
    }

    public void setUltimo(nMovil ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public ListaMovil() {
        this.inicio = null;
        this.ultimo = null;
        this.cantidadElementos = 0;
    }
    
     private void agregarInicio(Movil dato) {
        nMovil nuevo = new nMovil(dato);
        if (!this.esVacia()) {
            nuevo.setSiguiente(this.getInicio());
        } else {
            this.setUltimo(nuevo);
        }
        this.setInicio(nuevo);
    }

    private void agregarFinal(Movil dato) {
        nMovil nuevo = new nMovil(dato);
        if (this.esVacia()) {
            this.setInicio(nuevo);
        } else {
            ultimo.setSiguiente(nuevo);
        }
        this.setUltimo(nuevo);
    }
    
    
    @Override
    public boolean esVacia() {
        return this.getInicio()==null;
    }

    @Override
    public void borrarInicio() {
        if (!this.esVacia()) {
           this.cantidadElementos=this.cantidadElementos-1;
            this.setInicio(this.inicio.getSiguiente());
            if (this.getInicio() == null) {
                this.setUltimo(null);
            }
        }
    }

    @Override
    public void borrarFin() {
        nMovil aux = this.inicio;
        if (!this.esVacia()) {
        this.cantidadElementos=this.cantidadElementos-1;
            
            if (this.inicio==this.ultimo){
                this.setInicio(null);
                this.setUltimo(null);
            }else{    
            while (aux.getSiguiente() != this.ultimo) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
            ultimo = aux;
            }
        }
    }

    @Override
    public void vaciar() {
        this.setInicio(null);
        this.setUltimo(null);
        this.cantidadElementos=0;
    }

    @Override
    public void mostrar() {
        nMovil aux = this.getInicio();
        while (aux != null) {
            if(aux.getSiguiente() != null)
                System.out.print(aux.getDato().getMovilID() + "|");
            else
                System.out.print(aux.getDato().getMovilID());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrd(Movil dato) {
        nMovil nuevo = new nMovil(dato);
        nMovil aux =this.inicio;
        this.cantidadElementos=this.cantidadElementos+1;
        if (this.esVacia()|| dato.getMovilID().compareTo(this.getInicio().getDato().getMovilID()) < 0)
            this.agregarInicio(dato);
        else
        {   
            if (dato.getMovilID().compareTo(this.getUltimo().getDato().getMovilID()) > 0)
                this.agregarFinal(dato);
            else
            {
                while (aux.getSiguiente().getDato().getMovilID().compareTo(dato.getMovilID()) < 0)
                    aux=aux.siguiente;
                
                    nuevo.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(nuevo);
            }
            
        }
    }

    @Override
    public void borrarElemento(String movilID) {
        if (this.esVacia())
            return;
        this.cantidadElementos=this.cantidadElementos-1;
        if (this.inicio.getDato().getMovilID() == movilID)
            this.borrarInicio();
        else
        {
            nMovil aux=this.inicio;
            while (aux.getSiguiente()!=null && aux.getSiguiente().getDato().getMovilID() != movilID)
                aux=aux.getSiguiente();
            
            if (aux.getSiguiente()!=null){
                nMovil borrar = aux.getSiguiente();
                aux.setSiguiente(borrar.getSiguiente());
                borrar.setSiguiente(null);
            }
        }
    }

    @Override
    public int cantElementos() {
        return this.cantidadElementos;
    }

    @Override
    public nMovil obtenerElemento(String movilID) {
        nMovil aux=this.inicio;
        while (aux!=null && aux.getDato().getMovilID() != movilID)
            aux=aux.getSiguiente();
        //encontre dato o llegue al final
        return aux;
    }

    @Override
    public void mostrarREC(nMovil n) {
        if(n!=null){
            System.out.println(n.getDato().getMovilID());
            mostrarREC(n.getSiguiente());
    }
    }
    
    @Override
    public void mostrarInversoREC(nMovil n) {
        if(n!=null){
            mostrarREC(n.getSiguiente());
            System.out.println(n.getDato().getMovilID());
    }
    }

    @Override
    public boolean existeDatoREC(nMovil n, String movilID) {
        if (n != null){
            if (n.getDato().getMovilID() == movilID)
                return true;
            else
                return existeDatoREC(n.getSiguiente(),movilID);
        }
        else
             return false;
    }

        
}
