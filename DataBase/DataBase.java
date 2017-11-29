package DataBase;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Compuj
 */
public abstract class DataBase {
   
    protected String url;
    /**
    * Conectar a la base de datos. 
    */
    public abstract void connect();
        
    /**
    * Desconectar la base de datos. 
    */
    public abstract void disconnect();
        
    /**
    * Consultar a la base de datos. 
    * @param consulta Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    protected abstract ResultSet consult(String sqlStatement);
    
    public abstract int insert(String sqlStatement);
    
    public abstract int update(String sqlStatement);
    
    protected abstract int delete(String sqlStatement);
    
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
    public abstract int registeredUser(String code);

    public abstract ArrayList deleteEvents(LocalDateTime date, ArrayList tablesOfEvents);
    
    public abstract ArrayList tablesList();
}
