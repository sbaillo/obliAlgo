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
public interface ISistema {
   Retorno crearSistemaEmergencias(int cantzonas);
   Retorno destruirSistemaEmergencias(); 
   Retorno registrarMovil(String movilID, int zonaID);
   Retorno deshabilitarMovil(String movilID);
   Retorno habilitarMovil(String movilID);
   Retorno eliminarMovil(String movilID);
   Retorno buscarMovil(String movilID);
   Retorno informeMovil();
   Retorno informeMovil (int zonaID);    
   Retorno cambiarUbicacion(String movilID, int zonaID);   
   Retorno agregarZona(String zonaNombre);
   Retorno listarZonas();  
   Retorno agregarRuta(int zonaOrigen, int zonaDestino, int minutosViaje);   
   Retorno modificarDemora(int zonaOrigen, int zonaDestino, int minutosViaje);
   Retorno movilMasCercano(int zonaID); 
   Retorno rutaMasRapida(int zonaOrigen, int zonaDestino); 
   Retorno informeZonas();
   Retorno zonasEnRadio(int zonaID, int duracionViaje);
   Retorno registrarChofer(String movilID, String nombre, String cedula); 
   Retorno eliminarChofer(String movilID, String cedula);  
   Retorno informeChoferes(String movilID);
   Retorno registrarAbonadol(int abonadoID, String abonadoNombre, String abonadoDireccion, String abonadoTel, int zonaID);  
   Retorno eliminarAbonado(int abonadoID);
   Retorno informeAbonadosZona(int zonaID); 
   Retorno registrarCiudad(String ciudad);  
}
