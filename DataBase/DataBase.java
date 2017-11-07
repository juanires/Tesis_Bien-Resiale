package DataBase;

import java.sql.ResultSet;

/**
 *
 * @author Compuj
 */
public abstract class DataBase {
   
    protected String url;
    /**
    * Conectar a la base de datos. 
    */
    public abstract void conectar();
        
    /**
    * Desconectar la base de datos. 
    */
    public abstract void desconectar();
        
    /**
    * Consultar a la base de datos. 
    * @param consulta Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    public abstract ResultSet consultar(String consulta);
    
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
    public abstract boolean usuarioRegistrado(String codigo);
}
