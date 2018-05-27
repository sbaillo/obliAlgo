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
public class ListaZona implements IListaZona {

    nZona inicio;
    nZona ultimo;
    int cantidadElementos;
    int elementosMaximos;

    public nZona getInicio() {
        return inicio;
    }

    public void setInicio(nZona inicio) {
        this.inicio = inicio;
    }

    public nZona getUltimo() {
        return ultimo;
    }

    public void setUltimo(nZona ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public int getElementosMaximos() {
        return elementosMaximos;
    }

    public void setElementosMaximos(int elementosMaximos) {
        this.elementosMaximos = elementosMaximos;
    }

    public ListaZona(int max) {
        this.inicio = null;
        this.ultimo = null;
        this.cantidadElementos = 0;
        this.elementosMaximos = max;
    }

    private void agregarInicio(Zona dato) {
        nZona nuevo = new nZona(dato);
        if (!this.esVacia()) {
            nuevo.setSiguiente(this.getInicio());
        } else {
            this.setUltimo(nuevo);
        }
        this.setInicio(nuevo);
    }

    private void agregarFinal(Zona dato) {
        nZona nuevo = new nZona(dato);
        if (this.esVacia()) {
            this.setInicio(nuevo);
        } else {
            ultimo.setSiguiente(nuevo);
        }
        this.setUltimo(nuevo);
    }

    @Override
    public boolean esVacia() {
        return this.getInicio() == null;
    }

    @Override
    public void borrarInicio() {
        if (!this.esVacia()) {
            this.cantidadElementos = this.cantidadElementos - 1;
            this.setInicio(this.inicio.getSiguiente());
            if (this.getInicio() == null) {
                this.setUltimo(null);
            }
        }
    }

    @Override
    public void borrarFin() {
        nZona aux = this.inicio;
        if (!this.esVacia()) {
            this.cantidadElementos = this.cantidadElementos - 1;

            if (this.inicio == this.ultimo) {
                this.setInicio(null);
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
    public void vaciar() {
        this.setInicio(null);
        this.setUltimo(null);
        this.cantidadElementos = 0;
    }

    @Override
    public void mostrar() {
        nZona aux = this.getInicio();
        while (aux != null) {
            if (aux.getSiguiente() != null) {
                System.out.print(aux.getDato().getZonaID() + ";" + aux.getDato().getZonaNombre() + "|");
            } else {
                System.out.print(aux.getDato().getZonaID() + ";" + aux.getDato().getZonaNombre());
            }
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrd(Zona dato) {
        nZona nuevo = new nZona(dato);
        nZona aux = this.inicio;
        if (this.cantidadElementos < this.elementosMaximos) {
            this.cantidadElementos = this.cantidadElementos + 1;
            if (this.esVacia() || dato.getZonaID() < this.getInicio().getDato().getZonaID()) {
                this.agregarInicio(dato);
            } else {
                if (dato.getZonaID() > this.getUltimo().getDato().getZonaID()) {
                    this.agregarFinal(dato);
                } else {
                    while (aux.getSiguiente().getDato().getZonaID() < dato.getZonaID()) {
                        aux = aux.siguiente;
                    }

                    nuevo.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(nuevo);
                }

            }
        }
    }

    @Override
    public void borrarElemento(int zonaID) {
        if (this.esVacia()) {
            return;
        }
        this.cantidadElementos = this.cantidadElementos - 1;
        if (this.inicio.getDato().getZonaID() == zonaID) {
            this.borrarInicio();
        } else {
            nZona aux = this.inicio;
            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().getZonaID() != zonaID) {
                aux = aux.getSiguiente();
            }

            if (aux.getSiguiente() != null) {
                nZona borrar = aux.getSiguiente();
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
    public nZona obtenerElemento(int zonaID) {
        nZona aux = this.inicio;
        while (aux != null && aux.getDato().getZonaID() != zonaID) {
            aux = aux.getSiguiente();
        }
        //encontre dato o llegue al final
        return aux;
    }

    @Override
    public void mostrarREC(nZona n) {
        if (n != null) {
            System.out.println(n.getDato());
            mostrarREC(n.getSiguiente());
        }
    }

    @Override
    public void mostrarInversoREC(nZona n) {
        if (n != null) {
            mostrarREC(n.getSiguiente());
            System.out.println(n.getDato());
        }
    }

    @Override
    public boolean existeDatoREC(nZona n, int zonaID) {
        if (n != null) {
            if (n.getDato().getZonaID() == zonaID) {
                return true;
            } else {
                return existeDatoREC(n.getSiguiente(), zonaID);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean existeDatoPorNombreREC(nZona n, String nombre) {
        if (n != null) {
            if (n.getDato().getZonaNombre() == nombre) {
                return true;
            } else {
                return existeDatoPorNombreREC(n.getSiguiente(), nombre);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean estaLlena() {
        return cantidadElementos == elementosMaximos;
    }

}
