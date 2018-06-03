/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 *
 * @author alumnoFI
 */
public class Obligatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //si recorro la fila el valor minimo de la fila es la menor duracion del viaje. La columna de eso me va a dar la zona mas cercana
        
        //investigar al mover movil por que en el informe de movil por zona todavia me muestra el movil que movi en la zona equivocada
        
        //investigar por que si borro un movil sigue apareciendo en la lista de moviles.

        Sistema s = new Sistema();
        Prueba p = new Prueba();

        //prueba1(s, p);
        //prueba2(s, p);
        //prueba3(s, p);
        
        s.crearSistemaEmergencias(4);
        s.agregarZona("Pocitos");
        s.agregarZona("Malvin");
        s.agregarZona("Centro");
        s.agregarZona("Centro");
        s.agregarZona("Aguada");
        s.zonas.mostrar();
        
        System.out.println();
        
        s.registrarMovil("ABC123", 1);
        s.registrarMovil("ZBC123", 1);
        s.registrarMovil("XBC123", 1);
        s.registrarMovil("BBC456", 1);
        
        s.registrarMovil("LBC4512", 2);
        s.registrarMovil("CBC4512", 2);
        s.registrarMovil("EBC4512", 2);
        s.registrarMovil("DAC123", 2);
        
        System.out.println("");
        System.out.println("Mostrar lista de todos los moviles");
        s.moviles.mostrarREC(s.moviles.getInicio());
        
        
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 1");
        s.zonas.obtenerElemento(1).getMoviles().mostrar();
        
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 2");
        s.zonas.obtenerElemento(2).getMoviles().mostrar();
        
        s.cambiarUbicacion("LBC4512", 1);
   
        System.out.println("");
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 1 despues de meterle el movil LBC4512");
        s.zonas.obtenerElemento(1).getMoviles().mostrar();
        
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 2 despues de sacarle el movil LBC4512");
        s.zonas.obtenerElemento(2).getMoviles().mostrar();
        
        System.out.println("");
        System.out.println("Mostrar lista de todos los moviles despues de mover");
        s.moviles.mostrarREC(s.moviles.getInicio());
        
        s.cambiarUbicacion("CBC4512", 1);

        
        System.out.println("");
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 1 despues de meterle el movil CBC4512");
        s.zonas.obtenerElemento(1).getMoviles().mostrar();
        
        System.out.println("");
        System.out.println("Mostrar lista de los moviles en zona 2 despues de sacarle el movil CBC4512");
        s.zonas.obtenerElemento(2).getMoviles().mostrar();
        
        System.out.println("");
        System.out.println("Mostrar lista de todos los moviles despues de mover");
        s.moviles.mostrarREC(s.moviles.getInicio());
        
        System.out.println(s.moviles.obtenerElemento("CBC4512").getDato().getZonaMovil());
        System.out.println(s.moviles.obtenerElemento("CBC4512").getDato().getEmergencias());
        
        System.out.println("");
        System.out.println("Buscar móvil CBC4512");
        
        System.out.println(s.buscarMovil("CBC4512").valorString);
        
        System.out.println("");
        System.out.println("Buscar móvil LBC4512");
        
        System.out.println(s.buscarMovil("LBC4512").valorString);
        
        System.out.println("");
        System.out.println("Buscar móvil DAC123");
        
        System.out.println(s.buscarMovil("DAC123").valorString);
        System.out.println(s.buscarMovil("DAC123").resultado);
        
        
        System.out.println("");
        System.out.println("Informe de todos los moviles ordenados por matricula");
        System.out.println(s.informeMovil().resultado);
        System.out.println(s.informeMovil().valorString);
        
        
        System.out.println("");
        System.out.println("Informe de moviles disponibles en zona 1");
        System.out.println(s.informeMovil(1).resultado);
        System.out.println(s.informeMovil(1).valorString);
        
        System.out.println("");
        System.out.println(s.eliminarMovil("XBC123").resultado);
        System.out.println("Mostrar lista de todos los moviles despues de eliminar XBC123");
        s.moviles.mostrarREC(s.moviles.getInicio());
        s.zonas.obtenerElemento(1).getMoviles().mostrar();
        
        s.agregarRuta(1,2,4);
        s.agregarRuta(1,3,3);
        s.agregarRuta(1,4,5);
        s.agregarRuta(2,3,1);
        s.agregarRuta(2,4,2);
        s.agregarRuta(3,4,3);
        System.out.println("");
        System.out.println("");
        System.out.println("MAPITA");
        System.out.println("");
        s.mostrarMatriz(s.mapa);
        
        
        s.modificarDemora(1,4,1);
        
        System.out.println("");
        System.out.println("MAPITA");
        System.out.println("");
        s.mostrarMatriz(s.mapa);
        
        System.out.println("");
        System.out.println("Cambiar de ubicacion un movil que no existe");
        System.out.println(s.cambiarUbicacion("RTC4512", 1).resultado);
        
        System.out.println("");
        System.out.println("Agrego chofer al movil DAC123");
        System.out.println(s.registrarChofer("DAC123", "JUAN", "42285949").resultado);
        
        System.out.println("");
        System.out.println("Agrego otro chofer al movil DAC123");
        System.out.println(s.registrarChofer("DAC123", "PEDRO", "16387654").resultado);
        
        System.out.println("");
        System.out.println("Informe de choferes del movil DAC123");
        System.out.println(s.informeChoferes("DAC123").valorString);
        
        System.out.println("");
        System.out.println("Elimino a Pedro del movil DAC123");
        System.out.println(s.eliminarChofer("DAC123", "16387654").resultado);
                
        System.out.println("");
        System.out.println("Informe de choferes del movil DAC123 despues de borrar a Pedro");
        System.out.println(s.informeChoferes("DAC123").valorString);
        
        System.out.println("");
        System.out.println("Registro Abonado 1 en Zona 1");
        System.out.println(s.registrarAbonadol(1, "Abonado 1", "Direccion 1", "099025913", 1).resultado);
        
        System.out.println("");
        System.out.println("Registro Abonado 2 en Zona 1");
        System.out.println(s.registrarAbonadol(2, "Abonado 2", "Direccion 2", "099025913", 1).resultado);
        
        System.out.println("");
        System.out.println("Informe abonados en Zona 1");
        System.out.println(s.informeAbonadosZona(1).valorString);
        
        System.out.println("");
        System.out.println("Elimino Abonado 2 en Zona 1");
        System.out.println(s.eliminarAbonado(2).resultado);
        
        System.out.println("");
        System.out.println("Informe abonados en Zona 1 despues de borrar al Abonado 2");
        System.out.println(s.informeAbonadosZona(1).valorString);
        
        System.out.println("");
        System.out.println("Listado de Zonas");
        System.out.println(s.listarZonas().resultado);
        System.out.println(s.listarZonas().valorString);
        
        
        System.out.println("");
        System.out.println("Movil mas cercano de Zona 1");
        System.out.println(s.movilMasCercano(1).valorString);
        
         System.out.println("");
        System.out.println("Movil mas cercano de Zona 2");
        System.out.println(s.movilMasCercano(2).valorString);
        
        System.out.println("");
        System.out.println("Movil mas cercano de Zona 3");
        System.out.println(s.movilMasCercano(3).valorString);
        
        System.out.println("");
        System.out.println("Movil mas cercano de Zona 4");
        System.out.println(s.movilMasCercano(4).valorString);
              
    }

    static void prueba1(Sistema s, Prueba p) {
        p.ver(s.crearSistemaEmergencias(3).resultado, Retorno.Resultado.OK, "Se crea el sistema para 3 zonas");
        p.ver(s.agregarZona("Montevideo").resultado, Retorno.Resultado.OK, "Se agrego Montevideo");
        p.ver(s.agregarZona("Montevideo").resultado, Retorno.Resultado.ERROR_2, "Se intentoagregar Montevideo que ya existe");
        p.ver(s.agregarZona("Punta del Este").resultado, Retorno.Resultado.OK, "Se agrego Punta del Este");
        p.ver(s.agregarZona("La Paloma").resultado, Retorno.Resultado.OK, "Se agrego La Paloma");
        p.ver(s.agregarZona("Maldonado").resultado, Retorno.Resultado.ERROR_1, "Se intento agregar Maldonado pero el sistema esta completo");
        p.ver(s.destruirSistemaEmergencias().resultado, Retorno.Resultado.OK, "Se destruye sistema");
        p.imprimirResultadosPrueba();
    }

    static void prueba2(Sistema s, Prueba p) {
        p.imprimirComentario("CREAMOS SISTMA PARA 5 CIUDADES");
        p.ver(s.crearSistemaEmergencias(5).resultado, Retorno.Resultado.OK, "Se crea el sistema de reservas");
        p.imprimirComentario("INGRESAMOS ZONAS");
        p.ver(s.agregarZona("Montevideo").resultado, Retorno.Resultado.OK, "Se ingresa Montevideo");
        p.ver(s.agregarZona("Santiago").resultado, Retorno.Resultado.OK, "Se ingresa Santiago");
        p.ver(s.agregarZona("Lima").resultado, Retorno.Resultado.OK, "Se ingresa Lima");
        p.ver(s.agregarZona("San Pablo").resultado, Retorno.Resultado.OK, "Se ingresa San Pablo");
        p.ver(s.agregarZona("New York").resultado, Retorno.Resultado.OK, "Se ingresa New York");

        p.imprimirComentario("INGRESAMOS CIUDAD REPETIDA");
        p.ver(s.agregarZona("Montevideo").resultado, Retorno.Resultado.ERROR_1, "Montevideo ya existe");

        p.imprimirComentario("INTENTAMOS SOBREPASAR EL LIMITE DE CIUDADES");
        p.ver(s.agregarZona("Buenos Aires").resultado, Retorno.Resultado.ERROR_1, "Se sobrepasa el límite de ciudades gestionados por el sistema");

        p.imprimirResultadosPrueba();

    }

    static void prueba3(Sistema s, Prueba p) {
        p.imprimirComentario("CREAMOS SISTMA PARA 5 CIUDADES");
        p.ver(s.crearSistemaEmergencias(5).resultado, Retorno.Resultado.OK, "Se crea el sistema de reservas");
        p.imprimirComentario("INGRESAMOS ZONAS");
        p.ver(s.agregarZona("Pocitos").resultado, Retorno.Resultado.OK, "Se ingresa Pocitos");
        p.ver(s.agregarZona("Punta Carretas").resultado, Retorno.Resultado.OK, "Se ingresa Punta Carretas");
        p.ver(s.agregarZona("Parque Rodo").resultado, Retorno.Resultado.OK, "Se ingresa Parque Rodo");
        p.ver(s.agregarZona("Buceo").resultado, Retorno.Resultado.OK, "Se ingresa Buceo");
        p.ver(s.agregarZona("Malvin").resultado, Retorno.Resultado.OK, "Se ingresa Malvin");

        p.ver(s.registrarMovil("PCS123", 1).resultado, Retorno.Resultado.OK, "Agrego movil PCS123 a Pocitos ");
        p.ver(s.registrarMovil("PCS123", 1).resultado, Retorno.Resultado.ERROR_2, "Agrego movil PCS123 que ya existe en Pocitos ");
        p.ver(s.registrarMovil("PCS123", 20).resultado, Retorno.Resultado.ERROR_1, "Agrego movil PCS123 a zona que no existe ");

        p.ver(s.deshabilitarMovil("PCS123").resultado, Retorno.Resultado.OK, "Se deshabilita PCS123");
        p.ver(s.deshabilitarMovil("PCS123").resultado, Retorno.Resultado.ERROR_2, "Se deshabilita PCS123 que ya estaba deshabilitado");
        p.ver(s.habilitarMovil("PCS123").resultado, Retorno.Resultado.OK, "se habilita movil PCS123");
        p.ver(s.eliminarMovil("PCS123").resultado, Retorno.Resultado.OK, "Se elimina mobil PCS123");
        p.imprimirResultadosPrueba();
    }
}
