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
public class ListaAbonado implements IListaAbonado {
    nAbonado inicio;
    nAbonado ultimo;
    int cantidadElementos;

    public nAbonado getInicio() {
        return inicio;
    }

    public void setInicio(nAbonado inicio) {
        this.inicio = inicio;
    }

    public nAbonado getUltimo() {
        return ultimo;
    }

    public void setUltimo(nAbonado ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public ListaAbonado() {
        this.inicio = null;
        this.ultimo = null;
        this.cantidadElementos = cantidadElementos;
    }
    
    
    private void agregarInicio(Abonado dato) {
        nAbonado nuevo = new nAbonado(dato);
        if (!this.esVacia()) {
            nuevo.setSiguiente(this.getInicio());
        } else {
            this.setUltimo(nuevo);
        }
        this.setInicio(nuevo);
    }

    private void agregarFinal(Abonado dato) {
        nAbonado nuevo = new nAbonado(dato);
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
        nAbonado aux = this.inicio;
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
        nAbonado aux = this.getInicio();
        while (aux != null) {
            if(aux.getSiguiente() != null)
                System.out.print(aux.getDato().getAbonadoID() + "|");
            else
                System.out.print(aux.getDato().getAbonadoID());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrd(Abonado dato) {
        nAbonado nuevo = new nAbonado(dato);
        nAbonado aux =this.inicio;
        this.cantidadElementos=this.cantidadElementos+1;
        if (this.esVacia()|| dato.getAbonadoID() < this.getInicio().getDato().getAbonadoID())
            this.agregarInicio(dato);
        else
        {   
            if (dato.getAbonadoID() > this.getUltimo().getDato().getAbonadoID())
                this.agregarFinal(dato);
            else
            {
                while (aux.getSiguiente().getDato().getAbonadoID()<dato.getAbonadoID())
                    aux=aux.siguiente;
                
                    nuevo.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(nuevo);
            }
            
        }
    }

    @Override
    public void borrarElemento(int abonadoID) {
       if (this.esVacia())
            return;
        this.cantidadElementos=this.cantidadElementos-1;
        if (this.inicio.getDato().getAbonadoID() == abonadoID)
            this.borrarInicio();
        else
        {
            nAbonado aux=this.inicio;
            while (aux.getSiguiente()!=null && aux.getSiguiente().getDato().getAbonadoID() != abonadoID)
                aux=aux.getSiguiente();
            
            if (aux.getSiguiente()!=null){
                nAbonado borrar = aux.getSiguiente();
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
    public nAbonado obtenerElemento(int abonadoID) {
        nAbonado aux=this.inicio;
        while (aux!=null && aux.getDato().getAbonadoID() != abonadoID)
            aux=aux.getSiguiente();
        //encontre dato o llegue al final
        return aux;
    }

    @Override
    public void mostrarREC(nAbonado n) {
        if(n!=null){
            System.out.println(n.getDato().getAbonadoID() +"|");
            mostrarREC(n.getSiguiente());
    }
    }
    
    @Override
    public void mostrarInversoREC(nAbonado n) {
        if(n!=null){
            mostrarREC(n.getSiguiente());
            System.out.println(n.getDato().getAbonadoID() +"|");
    }
    }

    @Override
    public boolean existeDatoREC(nAbonado n, int abonadoID) {
        if (n != null){
            if (n.getDato().getAbonadoID() == abonadoID)
                return true;
            else
                return existeDatoREC(n.getSiguiente(),abonadoID);
        }
        else
             return false;
    }

   
    
}
