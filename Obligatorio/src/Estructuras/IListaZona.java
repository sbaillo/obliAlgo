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
public interface IListaZona {
   
    public boolean esVacia();
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();    
    public void agregarOrd(Zona dato);
    public void borrarElemento(int zonaID);
    public int cantElementos();
    public nZona obtenerElemento(int zonaID);
    public void mostrarREC(nZona n)  ;
    public void mostrarInversoREC(nZona n);
    public boolean existeDatoREC(nZona n, int zonaID);
    public boolean existeDatoPorNombreREC(nZona n, String nombre);
    public boolean estaLlena();
    
}

