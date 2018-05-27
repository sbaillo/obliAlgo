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
public interface IListaRuta {
    public boolean esVacia();
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();    
    public void agregarInicio(Ruta dato);
    public void borrarElemento(int IDzonaOrigen, int IDzonaDestino);
    public int cantElementos();
    public nRuta obtenerElemento(int IDzonaOrigen, int IDzonaDestino);
    public boolean existeDatoREC(nRuta n, int IDzonaOrigen, int IDzonaDestino);
}
