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
public interface IListaAbonado {
    public boolean esVacia();
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();    
    public void agregarOrd(Abonado dato);
    public void borrarElemento(int abonadoID);
    public int cantElementos();
    public nAbonado obtenerElemento(int abonadoID);
    public void mostrarREC(nAbonado n)  ;
    public void mostrarInversoREC(nAbonado n);
    public boolean existeDatoREC(nAbonado n, int abonadoID);
}
