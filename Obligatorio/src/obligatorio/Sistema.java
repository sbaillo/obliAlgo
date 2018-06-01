/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

import Estructuras.*;
import Objetos.*;

/**
 *
 * @author alumnoFI
 */
public class Sistema implements ISistema {

    ListaZona zonas;
    ListaMovil moviles;
    //Se inicializa en cero
    int[][] mapa;

    
    @Override
    public Retorno crearSistemaEmergencias(int cantzonas) {
        if (cantzonas <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        this.zonas = new ListaZona(cantzonas);
        this.moviles = new ListaMovil();
        this.mapa = new int[cantzonas][cantzonas];
        
        int filas = mapa.length;
        int columnas = mapa[0].length;
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = 0;
            }
        }
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno destruirSistemaEmergencias() {
        zonas = null;
        moviles = null;
        mapa = null;
        return new Retorno(Retorno.Resultado.OK);
    }

    ///VER MAS
    @Override
    public Retorno registrarMovil(String movilID, int zonaID) {
        if (!this.zonas.existeDatoREC(zonas.getInicio(), zonaID)) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        //validar ACA que el movil no exista y tirar error 2
        nZona aux = zonas.getInicio();
        boolean existe = false;
        while (aux != null && existe == false) {
            if (!aux.getMoviles().esVacia()) {
                existe = aux.getMoviles().existeDatoREC(aux.getMoviles().getInicio(), movilID);
            }

            aux = aux.getSiguiente();

        }
        if (existe) {
            return new Retorno(Retorno.Resultado.ERROR_2); //ya existe el movil
        }

        //Ya sabemos que la zona existe y el movil no existe => Agrego a la lista
        nZona p = zonas.obtenerElemento(zonaID);
        Movil movil = new Movil(movilID, p.getDato().getZonaNombre());
        p.getMoviles().agregarOrd(movil);

        //Agrego en la lista ordenada de todos los moviles
        this.moviles.agregarOrd(movil);
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno deshabilitarMovil(String movilID) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno habilitarMovil(String movilID) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno eliminarMovil(String movilID) {
        //validar que el movil exista
        nMovil movilBuscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null && movilBuscado == null) {
            if (!aux.getMoviles().esVacia()) {
                if (aux.getMoviles().existeDatoREC(aux.getMoviles().getInicio(), movilID)) {
                    movilBuscado = aux.getMoviles().obtenerElemento(movilID);
                }else
                {
                aux = aux.getSiguiente();
                }
            }
        }

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }

        if (movilBuscado.getDato().getEstado() != Estado.DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_2); //El movil no esta disponible o esta en emergencia
        }

        //Borra el movil de las listas
        aux.getMoviles().borrarElemento(movilID);
        moviles.borrarElemento(movilID);
        return new Retorno(Retorno.Resultado.OK);
    }

    //OK pero investigar el metodo de cohen
    @Override
    public Retorno buscarMovil(String movilID) {
        //Lo busco en la lista de moviles
        nMovil movilBuscado = null;
        if (this.moviles.existeDatoREC(this.moviles.getInicio(), movilID)) {
            movilBuscado = this.moviles.obtenerElemento(movilID);
        }

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }

        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        retorno.valorString = "Datos móvil: " + movilBuscado.getDato().getMovilID().toString() + "|Estado: " + movilBuscado.getDato().getEstado().toString() + "|Zona: " + movilBuscado.getDato().getZonaMovil().toString() + "|#Emergencias: " + movilBuscado.getDato().getEmergencias();
        return retorno;
    }

    //OK
    @Override
    public Retorno informeMovil() {
        nMovil aux = moviles.getInicio();
        String informe = "";

        while (aux != null) {

            informe += (aux.getDato().getMovilID() + ";" + aux.getDato().getEstado().toString() + ";" + aux.getDato().getZonaMovil());
            if (aux.getSiguiente() != null) {
                informe += "|";
            }

            aux = aux.getSiguiente();
        }

        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

    //OK
    @Override
    public Retorno informeMovil(int zonaID) {
        if (!this.zonas.existeDatoREC(zonas.getInicio(), zonaID)) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        int disponibles = 0;
        String informe = "";

        nZona z = zonas.obtenerElemento(zonaID);
        nMovil aux = z.getMoviles().getInicio();

        while (aux != null) {

            if (aux.getDato().getEstado() == Estado.DISPONIBLE) {
                informe += aux.getDato().getMovilID();
                disponibles++;
            }

            if (aux.getSiguiente() != null && aux.getSiguiente().getDato().getEstado() == Estado.DISPONIBLE) {
                informe += ";";
            }

            if (aux.getSiguiente() == null) {
                informe += ("|Total Móviles disponibles: " + disponibles);
            }
            aux = aux.getSiguiente();
        }

        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

    //OK
    public Retorno cambiarUbicacion(String movilID, int zonaID) {
        //Validar que exista la zona de destino
        if (!this.zonas.existeDatoREC(zonas.getInicio(), zonaID)) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        //validar que el movil no exista y tirar error 2
        nMovil movilBuscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null && movilBuscado == null) {
            if (!aux.getMoviles().esVacia()) {
                if (aux.getMoviles().existeDatoREC(aux.getMoviles().getInicio(), movilID)) {
                    movilBuscado = aux.getMoviles().obtenerElemento(movilID);
                }
                else
                {
                    aux = aux.getSiguiente();
                }
            }
        }

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //no existe el movil
        }

        if (movilBuscado.getDato().getEstado() != Estado.DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_3); //El movil no esta disponible o esta en emergencia
        }

        //zona de destino
        nZona p = zonas.obtenerElemento(zonaID);
        //aux es la zona de origen
        //Borro el movil de esa zona
        aux.getMoviles().borrarElemento(movilID);

        //limpio el nodo siguiente para desengancharlo completamente
        movilBuscado.setSiguiente(null);

        //lo agrego ordenado a la zona de destino
        movilBuscado.getDato().setZonaMovil(p.getDato().getZonaNombre());
        movilBuscado.getDato().setEmergencias(movilBuscado.getDato().getEmergencias() + 1);
        p.getMoviles().agregarOrdNodo(movilBuscado);

        //Actualizo la lista de todos los moviles
        this.moviles.obtenerElemento(movilID).setDato(movilBuscado.getDato());
        return new Retorno(Retorno.Resultado.OK);
    }

    //OK
    @Override
    public Retorno agregarZona(String zonaNombre) {
        //Verifica que la lista de zonas no este llena
        if (zonas.estaLlena()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        //Verifica que no exista una zona con el nombre dado
        if (zonas.existeDatoPorNombreREC(zonas.getInicio(), zonaNombre)) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        //Agrega la zona con el nombre dado
        Zona z = new Zona(zonaNombre);
        zonas.agregarOrd(z);
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno listarZonas() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    //NUEVO - Verificar el retorno segun la letra
    @Override
    public Retorno agregarRuta(int zonaOrigen, int zonaDestino, int minutosViaje) {
        //Le resto 1 porque el ID de la zona va de 1 a n
        this.mapa[zonaOrigen - 1][zonaDestino - 1] = minutosViaje;
        this.mapa[zonaDestino - 1][zonaOrigen - 1] = minutosViaje;
        return new Retorno(Retorno.Resultado.OK);
    }

    //NUEVO - Verificar el retorno segun la letra
    @Override
    public Retorno modificarDemora(int zonaOrigen, int zonaDestino, int minutosViaje) {
        this.mapa[zonaOrigen - 1][zonaDestino - 1] = minutosViaje;
        this.mapa[zonaDestino - 1][zonaOrigen - 1] = minutosViaje;
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno movilMasCercano(int zonaID) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno rutaMasRapida(int zonaOrigen, int zonaDestino) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno informeZonas() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno zonasEnRadio(int zonaID, int duracionViaje) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    //NUEVO
    @Override
    public Retorno registrarChofer(String movilID, String nombre, String cedula) {
        //validar que el movil exista
        nMovil movilBuscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null && movilBuscado == null) {
            if (!aux.getMoviles().esVacia()) {
                if (aux.getMoviles().existeDatoREC(aux.getMoviles().getInicio(), movilID)) {
                    movilBuscado = aux.getMoviles().obtenerElemento(movilID);
                }
            }
            aux = aux.getSiguiente();
        }
        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        Chofer c = new Chofer(nombre, cedula);
        //Agrego el chofer en las listas
        movilBuscado.getChoferes().agregarOrd(c);
        moviles.obtenerElemento(movilID).getChoferes().agregarOrd(c);
        return new Retorno(Retorno.Resultado.OK);
    }

    //NUEVO
    @Override
    public Retorno eliminarChofer(String movilID, String cedula) {
        //validar que el movil exista
        nMovil movilBuscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null && movilBuscado == null) {
            if (!aux.getMoviles().esVacia()) {
                if (aux.getMoviles().existeDatoREC(aux.getMoviles().getInicio(), movilID)) {
                    movilBuscado = aux.getMoviles().obtenerElemento(movilID);
                }
            }
            aux = aux.getSiguiente();
        }
        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        if (!movilBuscado.getChoferes().existeDatoREC(movilBuscado.getChoferes().getInicio(), movilID)) {
            return new Retorno(Retorno.Resultado.ERROR_2); //no existe el chofer en ese movil
        }
        movilBuscado.getChoferes().borrarElemento(cedula);
        moviles.obtenerElemento(movilID).getChoferes().borrarElemento(cedula);
        return new Retorno(Retorno.Resultado.OK);
    }

    //NUEVO
    @Override
    public Retorno informeChoferes(String movilID) {
        if (!moviles.existeDatoREC(moviles.getInicio(), movilID)) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        String informe = "";
        nChofer aux = moviles.obtenerElemento(movilID).getChoferes().getInicio();
        while (aux != null) {
            informe += (aux.getDato().getNombre() + ";" + aux.getDato().getCedula());
            if (aux.getSiguiente() != null) {
                informe += "|";
            }
            aux = aux.getSiguiente();
        }
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

    //NUEVO
    @Override
    public Retorno registrarAbonadol(int abonadoID, String abonadoNombre, String abonadoDireccion,
            String abonadoTel, int zonaID) {
        if (!zonas.existeDatoREC(zonas.getInicio(), zonaID)) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        } //validar que el abonado no exista
        boolean existe = false;
        nZona aux = zonas.getInicio();
        while (aux != null && existe == false) {
            if (!aux.getAbonados().esVacia()) {
                if (aux.getAbonados().existeDatoREC(aux.getAbonados().getInicio(), abonadoID)) {
                    existe = true;
                }
            }
            aux = aux.getSiguiente();
        }
        if (existe) {
            return new Retorno(Retorno.Resultado.ERROR_2); //ya existe el abonado
        }
        Abonado a = new Abonado(abonadoID, abonadoNombre, abonadoDireccion, abonadoTel);
        zonas.obtenerElemento(zonaID).getAbonados().agregarOrd(a);
        return new Retorno(Retorno.Resultado.OK);
    }
    
    //NUEVO
    @Override
    public Retorno eliminarAbonado(int abonadoID) {
        nAbonado abonadoBuscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null && abonadoBuscado == null) {
            if (!aux.getAbonados().esVacia()) {
                if (aux.getAbonados().existeDatoREC(aux.getAbonados().getInicio(), abonadoID)) {
                    abonadoBuscado = aux.getAbonados().obtenerElemento(abonadoID);
                }
            }
            aux = aux.getSiguiente();
        }
        if (abonadoBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        aux.getAbonados().borrarElemento(abonadoID);
        return new Retorno(Retorno.Resultado.OK);
    }
    
    //NUEVO
    @Override
    public Retorno informeAbonadosZona(int zonaID) {
        if (!zonas.existeDatoREC(zonas.getInicio(), zonaID)) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        }
        nZona z = zonas.obtenerElemento(zonaID);
        nAbonado aux = z.getAbonados().getInicio();
        int cantidad = 0;
        String informe = z.getDato().getZonaID() + ";" + z.getDato().getZonaNombre();
        while (aux != null) {
            informe += ("|" + aux.getDato().getAbonadoID());
        }
        informe += ("|Total_abonados_disponibles: " + cantidad);
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

       
    public void mostrarMatriz(int[][] M) {
        int filas = M.length;
        int columnas = M[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(M[i][j] + "-");
            }
            System.out.println();
        }
    }

}
