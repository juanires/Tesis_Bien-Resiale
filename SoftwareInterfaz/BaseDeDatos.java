
package SoftwareInterfaz;

import java.sql.ResultSet;

/**
 * Interfaz para interactuar con una base de datos. 
 * Describe todas las operaciones sobre la base de datos.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public interface BaseDeDatos {
    
    /**
    * Conectar a la base de datos. 
    */
    void conectar();
        
    /**
    * Desconectar la base de datos. 
    */
    void desconectar();
        
    /**
    * Consultar a la base de datos. 
    * @param consulta Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    ResultSet consultar(String consulta);
    
    /**
    * Comprobar si es un ususario registrado. Se comprueba si el código
    * que se pasa como parámetro se corresponde con el codigo de un usuario
    * cargado en la base de datos.
    * 
    * @param codigo Código a consultar.
    * 
    * @return true si el codigo corresponde a un usuario registrado, de lo 
    * contrario retorna false.
    */
    boolean usuarioRegistrado(String codigo);
    
}
