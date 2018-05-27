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
public interface IListaChofer {
    public boolean esVacia();
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();    
    public void agregarOrd(Chofer dato);
    public void borrarElemento(String choferID);
    public int cantElementos();
    public nChofer obtenerElemento(String choferID);
    public void mostrarREC(nChofer n)  ;
    public void mostrarInversoREC(nChofer n);
    public boolean existeDatoREC(nChofer n, String choferID);
}
