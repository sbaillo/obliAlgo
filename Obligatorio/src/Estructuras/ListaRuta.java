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
public class ListaRuta implements IListaRuta {
    nRuta inicio;
    nRuta ultimo;
    int cantidadElementos;

    public nRuta getInicio() {
        return inicio;
    }

    public void setInicio(nRuta inicio) {
        this.inicio = inicio;
    }

    public nRuta getUltimo() {
        return ultimo;
    }

    public void setUltimo(nRuta ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public ListaRuta() {
        this.inicio = null;
        this.ultimo = null;
        this.cantidadElementos = 0;
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
        nRuta aux = this.inicio;
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
        nRuta aux = this.getInicio();
        while (aux != null) {
            if(aux.getSiguiente() != null)
                System.out.print(aux.getDato().getZonaOrigen() + ";" +aux.getDato().getZonaDestino() + ";" +aux.getDato().getMinutosViaje() +"|");
            else
                System.out.print(aux.getDato().getZonaOrigen() + ";" +aux.getDato().getZonaDestino() + ";" +aux.getDato().getMinutosViaje());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarInicio(Ruta dato) {
        nRuta nuevo = new nRuta(dato);
        if (!this.esVacia()) {
            nuevo.setSiguiente(this.getInicio());
        } else {
            this.setUltimo(nuevo);
        }
        this.setInicio(nuevo);
    }

    @Override
    public void borrarElemento(int IDzonaOrigen, int IDzonaDestino) {
        if (this.esVacia())
            return;
        this.cantidadElementos=this.cantidadElementos-1;
        if (this.inicio.getDato().getZonaOrigen() == IDzonaOrigen && this.inicio.getDato().getZonaDestino() == IDzonaDestino)
            this.borrarInicio();
        else
        {
            nRuta aux=this.inicio;
            while (aux.getSiguiente()!=null && aux.getSiguiente().getDato().getZonaOrigen() != IDzonaOrigen && aux.getSiguiente().getDato().getZonaDestino() != IDzonaDestino)
                aux=aux.getSiguiente();
            
            if (aux.getSiguiente()!=null){
                nRuta borrar = aux.getSiguiente();
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
    public nRuta obtenerElemento(int IDzonaOrigen, int IDzonaDestino) {
        nRuta aux=this.inicio;
        while (aux!=null && aux.getDato().getZonaOrigen() != IDzonaOrigen && aux.getDato().getZonaDestino() != IDzonaDestino)
            aux=aux.getSiguiente();
        //encontre dato o llegue al final
        return aux;
    }

    
    @Override
    public boolean existeDatoREC(nRuta n, int IDzonaOrigen, int IDzonaDestino) {
        if (n != null){
            if (n.getDato().getZonaOrigen() == IDzonaOrigen && n.getDato().getZonaDestino() == IDzonaDestino)
                return true;
            else
                return existeDatoREC(n.getSiguiente(),IDzonaOrigen,IDzonaDestino);
        }
        else
             return false;
    }

       
    
}
