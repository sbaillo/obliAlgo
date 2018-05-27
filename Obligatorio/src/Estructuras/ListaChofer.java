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
public class ListaChofer implements IListaChofer{
    nChofer inicio;
    nChofer ultimo;
    int cantidadElementos;

    public nChofer getInicio() {
        return inicio;
    }

    public void setInicio(nChofer inicio) {
        this.inicio = inicio;
    }

    public nChofer getUltimo() {
        return ultimo;
    }

    public void setUltimo(nChofer ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public ListaChofer() {
        this.inicio = null;
        this.ultimo = null;
        this.cantidadElementos = 0;
    }

    private void agregarInicio(Chofer dato) {
        nChofer nuevo = new nChofer(dato);
        if (!this.esVacia()) {
            nuevo.setSiguiente(this.getInicio());
        } else {
            this.setUltimo(nuevo);
        }
        this.setInicio(nuevo);
    }

    private void agregarFinal(Chofer dato) {
        nChofer nuevo = new nChofer(dato);
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
        nChofer aux = this.inicio;
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
        nChofer aux = this.getInicio();
        while (aux != null) {
            if(aux.getSiguiente() != null)
                System.out.print(aux.getDato().getCedula() + ";" +aux.getDato().getCedula() + "|");
            else
                System.out.print(aux.getDato().getCedula() + ";" +aux.getDato().getCedula());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrd(Chofer dato) {
          nChofer nuevo = new nChofer(dato);
        nChofer aux =this.inicio;
        this.cantidadElementos=this.cantidadElementos+1;
        if (this.esVacia()|| dato.getCedula().compareTo(this.getInicio().getDato().getCedula()) < 0)
            this.agregarInicio(dato);
        else
        {   
            if (dato.getCedula().compareTo(this.getUltimo().getDato().getCedula()) > 0)
                this.agregarFinal(dato);
            else
            {
                while (aux.getSiguiente().getDato().getCedula().compareTo(dato.getCedula()) < 0)
                    aux=aux.siguiente;
                
                    nuevo.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(nuevo);
            }
            
        }
    }

    @Override
    public void borrarElemento(String choferID) {
        if (this.esVacia())
            return;
        this.cantidadElementos=this.cantidadElementos-1;
        if (this.inicio.getDato().getCedula() == choferID)
            this.borrarInicio();
        else
        {
            nChofer aux=this.inicio;
            while (aux.getSiguiente()!=null && aux.getSiguiente().getDato().getCedula() != choferID)
                aux=aux.getSiguiente();
            
            if (aux.getSiguiente()!=null){
                nChofer borrar = aux.getSiguiente();
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
    public nChofer obtenerElemento(String choferID) {
        nChofer aux=this.inicio;
        while (aux!=null && aux.getDato().getCedula() != choferID)
            aux=aux.getSiguiente();
        //encontre dato o llegue al final
        return aux;
    }

    @Override
    public void mostrarREC(nChofer n) {
       if(n!=null){
            System.out.println(n.getDato().getNombre()+ ";" + n.getDato().getCedula());
            mostrarREC(n.getSiguiente());
    }
    }

    @Override
    public void mostrarInversoREC(nChofer n) {
        if(n!=null){
            mostrarREC(n.getSiguiente());
            System.out.println(n.getDato().getNombre()+ ";" + n.getDato().getCedula());
    }
    }
    
    @Override
    public boolean existeDatoREC(nChofer n, String choferID) {
       if (n != null){
            if (n.getDato().getCedula() == choferID)
                return true;
            else
                return existeDatoREC(n.getSiguiente(),choferID);
        }
        else
             return false;
    }

    
    
    }
    
    
    
    

