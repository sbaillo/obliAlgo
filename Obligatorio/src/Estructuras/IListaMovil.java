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
public interface IListaMovil {
    public boolean esVacia();
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();    
    public void agregarOrd(Movil dato);
    public void borrarElemento(String movilID);
    public int cantElementos();
    public nMovil obtenerElemento(String movilID);
    public void mostrarREC(nMovil n)  ;
    public void mostrarInversoREC(nMovil n);
    public boolean existeDatoREC(nMovil n, String movilID);
}
