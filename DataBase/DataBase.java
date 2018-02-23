package DataBase;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Clase abstracta que define los métodos que deben implementar los "ConcreteDataBase". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public abstract class DataBase {
    
    protected String url;/**Conectar a la base de datos.*/
    
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
    * @param sqlStatement Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    protected abstract ResultSet consult(String sqlStatement);
    
    /**
    * Insertar un dato en la base de datos. 
    * @param sqlStatement sentencia sql a ejecutar.
    * @return 1 Si se insertó el dato correctamente ó 0 si falló.
    */
    public abstract int insert(String sqlStatement);
    
    /**
    * Actualizar un dato en la base de datos. 
    * @param sqlStatement sentencia sql a ejecutar.
    * @return 1 Si se actualizó el dato correctamente ó 0 si falló.
    */
    public abstract int update(String sqlStatement);
    
    /**
    * Eliminar un dato en la base de datos. 
    * @param sqlStatement sentencia sql a ejecutar.
    * @return 1 Si se eliminó el dato correctamente ó 0 si falló.
    */
    protected abstract int delete(String sqlStatement);
    
    /**
    * Comprobar si es un ususario registrado. Se comprueba si el código
    * que se pasa como parámetro se corresponde con el codigo de un usuario
    * cargado en la base de datos.
    * 
    * @param code Código a consultar.
    * 
    * @return true si el codigo corresponde a un usuario registrado, de lo 
    * contrario retorna false.
    */
    public abstract int registeredUser(String code);

    /**
    * Eliminar eventos de la base de datos. 
    * @param tablesOfEvents ArrayList de String que contiene el nombre de las tablas de la base de datos
    * que registran los distintos tipos de eventos.
    * @return ArrayList de tipo String que contiene el path de todas las fotos asociadas a los eventos borrados. 
    */
    public abstract ArrayList deleteEvents(ArrayList tablesOfEvents);
    
    /**
     * Retorna una lista con los nombres de las tablas de la base de datos, que almacenan datos de eventos.
     * @return ArrayList de tipo String que contiene los nombres de las tablas de la base de datos, que almacenan datos de eventos.
     */
    public abstract ArrayList tablesList();
    
    /**
     * Retorna true si la hora actual se encuentra dentro de la franja horaria de detección de movimiento, de lo contrario
     * retorna false.
     * @return true si la hora actual se encuentra dentro de la franja horaria de detección de movimiento, de lo contrario
     * retorna false.
    */
    public abstract boolean movementSlotTime();    
    
    /**
     * Se da de baja un usuario si ya ha transcurrido su fecha de expiración.
     * 
     * @param date fecha de expiración del usuario
    */
    public abstract void usersUpdate(String date);
    
    /**
     * Se inserta un nuevo evento en la tabla correspondiente a los accesos permitidos o 
     * denegados dependiendo el parametro "permitted". Si su valor es true entonces se ha consedido
     * el acceso y se registra el evento en la tabla correspondiente a los accesos permitidos.
     * Si el parámetro es false, se registra el evento en la tabla de accesos denegados.
     * 
     * @param UserId identificación del ususario que desea acceder
     * @param permitted determina si el acceso ha sido permitido
    */
    public abstract void insertEventPermittedAccess(int UserId, boolean permitted);
    
    /**
     * Se inserta un nuevo evento en la tabla que registra las detecciones de movimiento. Esta función registra
     * los eventos de un detector de movimiento; si en un futuro se adicionaran más sensores de movimiento al
     * sistema, se le debería pasar como parámetro a esta función un identificador del sensor 
     * para determinar en que tabla se registra el evento.
     * 
    */
    public abstract void insertEventMovement();
    
    /**
    * Se inserta un nuevo evento en la tabla que registra los toques de timbre.
    */
    public abstract void insertEventButton();
    
    /**
    * Se inserta un nuevo evento en la tabla que registra la apertura de puerta vía  web.
     * @param data identificador del usuario que abre la puerta desde la web.
    */
    public abstract void insertEventWeb(String data);
}
