package ConcreteDataBase;

import DataBase.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */

public class Sqlite3 extends DataBase  {
    
    private Connection connect;
    
    /**
    * Crea un nuevo objeto SQLite3
    * 
    * @param url Ubicación de la base de datos.
    */
    public Sqlite3(String url){
        this.url= url;
       // connect();
    }
    
    /**
    * Conectar a la base de datos. 
    * 
    */
    @Override
    public void connect(){
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);
            if (connect!=null) {
                System.out.println("Conectado");
            }
        }
        catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }

    /**
    * Desconectar la base de datos. 
    */
    @Override
    public void disconnect(){
        try {
            connect.close();
        } 
        catch (SQLException ex) {
         System.out.println("Error al cerrar base de datos");   
        }
    }
    
    /**
    * Consultar a la base de datos. 
    * @param consult Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    @Override
    protected ResultSet consult(String sqlStatement){
      
        ResultSet result = null;
        try {
            PreparedStatement st = connect.prepareStatement(sqlStatement);
            result = st.executeQuery();
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return result;
    }
    
    
    public int insert(String sqlStatement){
      connect();
        try {
            PreparedStatement st = connect.prepareStatement(sqlStatement);
            st.execute();
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            disconnect();
            return 0;
        }
        disconnect();
        return 1;
    }
    
    public int update(String sqlStatement){
        
        connect();
        try {
            PreparedStatement st = connect.prepareStatement(sqlStatement);
            st.execute();
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            disconnect();
            return 0;
        }
        disconnect();
        return 1;
    }
    
    protected int delete(String sqlStatement){
        try {
            PreparedStatement st = connect.prepareStatement(sqlStatement);
            st.execute();
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            disconnect();
            return 0;
        }
        return 1;
    }
    
    /**
    * Comprobar si es un ususario registrado. Se comprueba si el código
    * que se pasa como parámetro se corresponde con el codigo de un usuario
    * cargado en la base de datos. En esta funcion se conecta con la base de
    * datos, se realiza la cosulta y luego se desconecta la base de datos.
    * 
    * @param codigo Código a consultar.
    * 
    * @return true si el codigo corresponde a un usuario registrado, de lo 
    * contrario retorna false.
    */
    @Override
    public int registeredUser(String code){
       
        int userId = -1;
        String dayOfWeek = LocalDate.now().getDayOfWeek().toString().toLowerCase().concat("_id");; //Se obtiene el nombre del dia de la semana 
       //Como en la tabla estos campos son claves foraneas y por esto Django le agrega "_id", tambien se lo agrega para que coincida con el nombre del campo
        
        connect();
        ResultSet result = consult("SELECT user.id, is_staff, begin, end FROM users_user as user, users_timezone as time WHERE user.code='"+code+"' and user.is_active = 1 and "+dayOfWeek+"= time.id");
        //ResultSet result = consult("SELECT user.id, is_staff, begin, end FROM users_user as user, users_timezone as time WHERE user.id=3 and user.is_active = 1 and "+dayOfWeek+"= time.id");
        if(result!=null){
            try {
                result.next();
                if(result.getBoolean("is_staff")){ //Si es administrador
                    userId = result.getInt("id"); //No se verifica la franja horaria
                }
                else{ //Si NO es administrador
                    LocalTime begin = LocalTime.parse(result.getString("begin"), DateTimeFormatter.ISO_LOCAL_TIME);
                    LocalTime end = LocalTime.parse(result.getString("end"), DateTimeFormatter.ISO_LOCAL_TIME);
                    if(LocalTime.now().isAfter(begin) && LocalTime.now().isBefore(end)){
                       userId = result.getInt("id");
                    }
                }
                /*while (result.next()) { //De los resultados obtenidos se consulta si alguno esta activo
                }*/
            } 
            catch (SQLException ex) {
                //Logger.getLogger(ConcreteDataBase.Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
                userId = -1;
            }
        }
        disconnect();
        return userId;
    }
    
    @Override
    public ArrayList deleteEvents(LocalDateTime date, ArrayList tablesOfEvents){
        ArrayList <String> imagesOfEventsToBeDeleted = new ArrayList();
        String limitDate = date.toLocalDate().toString().concat(" "+date.toLocalTime().toString());
        int count = 0;
        connect();
       
        while(count < tablesOfEvents.size()){//Se consultan todas las tablas de eventos
            ResultSet result = consult("SELECT id,image FROM "+tablesOfEvents.get(count) +" WHERE date_time <'"+limitDate+"'" );
            if(result!=null){
                try {
                    while(result.next()) { //Mietras hayan filas a eliminar
                        imagesOfEventsToBeDeleted.add(result.getString("image"));
                        delete("DELETE FROM "+tablesOfEvents.get(count)+" WHERE id = "+result.getInt("id"));
                    }
                } 
                catch (SQLException ex) {
                    //Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            count ++;
        }
        disconnect();
        return imagesOfEventsToBeDeleted;
    }
    
    public ArrayList tablesList(){
        ArrayList<String> tables = new ArrayList();
        connect();
        ResultSet result = consult("SELECT name FROM sqlite_master WHERE type='table'" );
        if(result!=null){
            try {
                while(result.next()) { //Mietras hayan filas a eliminar
                   tables.add(result.getString("name"));
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        disconnect();
        return tables;
    }
    
}
