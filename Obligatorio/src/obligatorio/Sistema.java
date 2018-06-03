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
    int[][] mapa;

    //ok
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
        //Inicializa la matriz en 0
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = 0;
            }
        }
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno destruirSistemaEmergencias() {
        zonas = null;
        moviles = null;
        mapa = null;
        return new Retorno(Retorno.Resultado.OK);
    }

    //OK
    @Override
    public Retorno registrarMovil(String movilID, int zonaID) {
        nZona p = zonas.obtenerElemento(zonaID);
        nMovil m = buscarNodoMovil(movilID);

        if (p == null) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        if (m != null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //ya existe el movil
        }
        //Ya sabemos que la zona existe y el movil no existe => Agrego a la lista
        Movil movil = new Movil(movilID, p.getDato().getZonaNombre());
        p.getMoviles().agregarOrd(movil);
        //Agrego en la lista ordenada de todos los moviles
        this.moviles.agregarOrd(movil);
        return new Retorno(Retorno.Resultado.OK);
    }

    //Revisar con el profe a que se refiere "esta asignado a un viaje"
    @Override
    public Retorno deshabilitarMovil(String movilID) {
        nMovil movilBuscado = buscarNodoMovil(movilID);

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }

        if (movilBuscado.getDato().getEstado() == Estado.NO_DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_2); //El movil ya esta deshabilitado
        }
        if (movilBuscado.getDato().getEstado() == Estado.EN_EMERGENCIA) {
            return new Retorno(Retorno.Resultado.ERROR_3); //El movil está en emergencia
        }
        moviles.obtenerElemento(movilID).getDato().setEstado(Estado.NO_DISPONIBLE);
        movilBuscado.getDato().setEstado(Estado.NO_DISPONIBLE);
        return new Retorno(Retorno.Resultado.OK);
    }

    //Revisar con el profe a que se refiere "esta asignado a un viaje"
    @Override
    public Retorno habilitarMovil(String movilID) {
        nMovil movilBuscado = buscarNodoMovil(movilID);
        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }

        if (movilBuscado.getDato().getEstado() == Estado.DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_2); //El movil ya esta habilitado
        }
        if (movilBuscado.getDato().getEstado() == Estado.EN_EMERGENCIA) {
            return new Retorno(Retorno.Resultado.ERROR_3); //El movil está en emergencia
        }
        moviles.obtenerElemento(movilID).getDato().setEstado(Estado.DISPONIBLE);
        movilBuscado.getDato().setEstado(Estado.DISPONIBLE);
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno eliminarMovil(String movilID) {
        nMovil movilBuscado = buscarNodoMovil(movilID);

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        if (movilBuscado.getDato().getEstado() != Estado.DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_2); //El movil no esta disponible o esta en emergencia
        }

        //Busco la zona donde esta el movil
        nZona aux = buscarZonaDeMovil(movilID);
        //Borra el movil de las listas
        aux.getMoviles().borrarElemento(movilID);
        moviles.borrarElemento(movilID);
        return new Retorno(Retorno.Resultado.OK);
    }

//OK
    @Override
    public Retorno buscarMovil(String movilID) {
        //Busco el movil
        nMovil movilBuscado = buscarNodoMovil(movilID);

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        retorno.valorString = "Datos móvil: " + movilBuscado.getDato().getMovilID().toString() + "|Estado: "
                + movilBuscado.getDato().getEstado().toString() + "|Zona: "
                + movilBuscado.getDato().getZonaMovil().toString() + "|#Emergencias: "
                + movilBuscado.getDato().getEmergencias();
        return retorno;
    }

    //OK
    @Override
    public Retorno informeMovil() {
        nMovil aux = moviles.getInicio();
        String informe = "";
        while (aux != null) {
            informe += (aux.getDato().getMovilID() + ";" + aux.getDato().getEstado().toString() + ";"
                    + aux.getDato().getZonaMovil());
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
        //Validar que exista la zona de destino
        nZona z = zonas.obtenerElemento(zonaID);
        if (z == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        }
        int disponibles = 0;
        String informe = "";
        nMovil aux = z.getMoviles().getInicio();
        while (aux != null) {
            if (aux.getDato().getEstado() == Estado.DISPONIBLE) {
                informe += aux.getDato().getMovilID();
                disponibles++;
            }
            if (aux.getSiguiente() != null && aux.getSiguiente().getDato().getEstado()
                    == Estado.DISPONIBLE) {
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
        nZona z = zonas.obtenerElemento(zonaID);
        if (z == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        }
        //validar que el movil no exista y tirar error 2
        nMovil movilBuscado = buscarNodoMovil(movilID);
        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //no existe el movil
        }
        if (movilBuscado.getDato().getEstado() != Estado.DISPONIBLE) {
            return new Retorno(Retorno.Resultado.ERROR_3); //El movil no esta disponible o esta en emergencia
        }
        //aux es la zona de origen
        //Borro el movil de esa zona
        nZona aux = buscarZonaDeMovil(movilID);
        aux.getMoviles().borrarElemento(movilID);
        //limpio el nodo siguiente para desengancharlo completamente
        movilBuscado.setSiguiente(null);
        //lo agrego ordenado a la zona de destino
        movilBuscado.getDato().setZonaMovil(z.getDato().getZonaNombre());
        movilBuscado.getDato().setEstado(Estado.EN_EMERGENCIA);
        movilBuscado.getDato().setEmergencias(movilBuscado.getDato().getEmergencias() + 1);
        z.getMoviles().agregarOrdNodo(movilBuscado);
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

    //OK
    @Override
    public Retorno listarZonas() {
        String informe = "";
        nZona aux = zonas.getInicio();
        while (aux != null) {
            informe += (aux.getDato().getZonaID() + ";" + aux.getDato().getZonaNombre());
            if (aux.getSiguiente() != null) {
                informe += "|";
            }
            aux = aux.getSiguiente();
        }
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

//ok
    @Override
    public Retorno agregarRuta(int zonaOrigen, int zonaDestino, int minutosViaje) {
        if (zonas.obtenerElemento(zonaOrigen) == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //la zona de origen no existe
        }
        if (zonas.obtenerElemento(zonaDestino) == null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //la zona de destino no existe
        }
        if (minutosViaje < 0) {
            return new Retorno(Retorno.Resultado.ERROR_3); //minutos viaje menor que 0
        }
        //Le resto 1 porque el ID de la zona va de 1 a n
        this.mapa[zonaOrigen - 1][zonaDestino - 1] = minutosViaje;
        this.mapa[zonaDestino - 1][zonaOrigen - 1] = minutosViaje;
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno modificarDemora(int zonaOrigen, int zonaDestino, int minutosViaje) {

        if (zonas.obtenerElemento(zonaOrigen) == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //la zona de origen no existe
        }
        if (zonas.obtenerElemento(zonaDestino) == null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //la zona de destino no existe
        }
        if (minutosViaje < 0) {
            return new Retorno(Retorno.Resultado.ERROR_3); //minutos viaje menor que 0
        }
        this.mapa[zonaOrigen - 1][zonaDestino - 1] = minutosViaje;
        this.mapa[zonaDestino - 1][zonaOrigen - 1] = minutosViaje;
        return new Retorno(Retorno.Resultado.OK);
    }

    //NUEVO - preguntar si tiene que devolver todos los moviles o solo los disponibles
    @Override
    public Retorno movilMasCercano(int zonaID) {
        nZona z = zonas.obtenerElemento(zonaID);

        if (z == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //la zona no existe
        }
        String informe = "";

        //Informa si hay moviles en la zona y retorna TODOS
        if (!z.getMoviles().esVacia()) {
            nMovil aux = z.getMoviles().getInicio();
            while (aux != null) {
                informe += (aux.getDato().getMovilID() + ";0");

                if (aux.getSiguiente() != null) {
                    informe += "|";
                }
                aux = aux.getSiguiente();
            }
        } else //Informa moviles en la zona mas cercana y retorna TODOS
        {
            int indice = zonamMasCercana(this.mapa, zonaID - 1);
            nZona zMasCercana = zonas.obtenerElemento(indice + 1);
            if (!zMasCercana.getMoviles().esVacia()) {
                nMovil aux = zMasCercana.getMoviles().getInicio();
                while (aux != null) {
                    informe += (aux.getDato().getMovilID() + ";" + this.mapa[zonaID - 1][indice]);
                    if (aux.getSiguiente() != null) {
                        informe += "|";
                    }
                    aux = aux.getSiguiente();
                }
            } else { //Si no hay moviles en la zona cercana
                informe = "No hay moviles en esta zona ni en la zona mas cercana";
            }
        }
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
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

    //ok
    @Override
    public Retorno registrarChofer(String movilID, String nombre, String cedula) {
        //validar que el movil exista
        nMovil movilBuscado = buscarNodoMovil(movilID);

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }

        Chofer c = new Chofer(nombre, cedula);
        //Agrego el chofer en las listas
        movilBuscado.getChoferes().agregarOrd(c);
        moviles.obtenerElemento(movilID).getChoferes().agregarOrd(c);
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno eliminarChofer(String movilID, String cedula) {
        //validar que el movil exista
        nMovil movilBuscado = buscarNodoMovil(movilID);

        if (movilBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe el movil
        }
        if (!movilBuscado.getChoferes().existeDatoREC(movilBuscado.getChoferes().getInicio(), cedula)) {
            return new Retorno(Retorno.Resultado.ERROR_2); //no existe el chofer en ese movil
        }

        movilBuscado.getChoferes().borrarElemento(cedula);
        moviles.obtenerElemento(movilID).getChoferes().borrarElemento(cedula);
        return new Retorno(Retorno.Resultado.OK);
    }

//ok
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

//ok
    @Override
    public Retorno registrarAbonadol(int abonadoID, String abonadoNombre, String abonadoDireccion,
            String abonadoTel, int zonaID) {

        nZona z = zonas.obtenerElemento(zonaID);
        if (z == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        }
        //validar que el abonado no exista
        nAbonado buscado = buscarNodoAbonado(abonadoID);

        if (buscado != null) {
            return new Retorno(Retorno.Resultado.ERROR_2); //ya existe el abonado
        }

        Abonado a = new Abonado(abonadoID, abonadoNombre, abonadoDireccion, abonadoTel);
        z.getAbonados().agregarOrd(a);
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno eliminarAbonado(int abonadoID) {
        nAbonado abonadoBuscado = buscarNodoAbonado(abonadoID);

        if (abonadoBuscado == null) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        nZona z = buscarZonaDeAbonado(abonadoID);

        z.getAbonados().borrarElemento(abonadoID);
        return new Retorno(Retorno.Resultado.OK);
    }

    //ok
    @Override
    public Retorno informeAbonadosZona(int zonaID) {
        nZona z = zonas.obtenerElemento(zonaID);
        if (z == null) {
            return new Retorno(Retorno.Resultado.ERROR_1); //no existe la zona
        }

        nAbonado aux = z.getAbonados().getInicio();
        int cantidad = 0;
        String informe = z.getDato().getZonaID() + ";" + z.getDato().getZonaNombre();
        while (aux != null) {
            informe += ("|" + aux.getDato().getAbonadoID());
            cantidad++;
            aux = aux.getSiguiente();
        }
        informe += ("|Total_abonados_disponibles: " + cantidad);
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = informe;
        return ret;
    }

    //ok
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

    //ok
    public nMovil buscarNodoMovil(String movilID) {
        nMovil buscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null) {
            if (!aux.getMoviles().esVacia()) {
                buscado = aux.getMoviles().obtenerElemento(movilID);
                if (buscado != null) {
                    return buscado;
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    //ok
    public nZona buscarZonaDeMovil(String movilID) {
        nMovil buscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null) {
            if (!aux.getMoviles().esVacia()) {
                buscado = aux.getMoviles().obtenerElemento(movilID);
                if (buscado != null) {
                    return aux;
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    //ok
    public nAbonado buscarNodoAbonado(int abonadoID) {
        nAbonado buscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null) {
            if (!aux.getAbonados().esVacia()) {
                buscado = aux.getAbonados().obtenerElemento(abonadoID);
                if (buscado != null) {
                    return buscado;
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    //ok
    public nZona buscarZonaDeAbonado(int abonadoID) {
        nAbonado buscado = null;
        nZona aux = zonas.getInicio();
        while (aux != null) {
            if (!aux.getAbonados().esVacia()) {
                buscado = aux.getAbonados().obtenerElemento(abonadoID);
                if (buscado != null) {
                    return aux;
                }
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    //OK
    public static int zonamMasCercana(int[][] mapa, int zona) {
        int largo = mapa.length;
        int minimo = mapa[zona][0];
        int zonaMasCercana = zona;
        for (int i = 0; i < largo; i++) {
            if (mapa[zona][i] != 0 && mapa[zona][i] < minimo) {
                minimo = mapa[zona][i];
                zonaMasCercana = i;
            }

        }
        return zonaMasCercana;
    }

}
