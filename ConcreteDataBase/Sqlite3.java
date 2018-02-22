package ConcreteDataBase;
import DataBase.DataBase;
import Readers.ReaderTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase interactua con una base de datos sqlite3. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
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
    * @param sqlStatement Consulta sql a ejecutar.
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
    
    
    @Override
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
    
    @Override
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
    
    @Override
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
    * cargado en la base de datos. 
    * 
    * @param code Código a consultar.
    * 
    * @return true si el codigo corresponde a un usuario registrado, de lo 
    * contrario retorna false.
    */
    @Override
    public int registeredUser(String code){
       
        int userId = -1;
        String dayOfWeek = LocalDate.now((ZoneId.of("UTC-3"))).getDayOfWeek().toString().toLowerCase().concat("_id"); //Se obtiene el nombre del dia de la semana 
       //Como en la tabla estos campos son claves foraneas y por esto Django le agrega "_id", tambien se lo agrega para que coincida con el nombre del campo
        
        connect();
        ResultSet result = consult("SELECT user.id, is_staff, begin, end FROM users_user as user, users_timezone as time WHERE user.code='"+code+"' and user.is_active = 1 and "+dayOfWeek+"= time.id");
        if(result!=null){
            try {
                result.next();
                if(result.getBoolean("is_staff")){ //Si es administrador
                    userId = result.getInt("id"); //No se verifica la franja horaria
                }
                else{ //Si NO es administrador, se verifica la franja horaria
                    LocalTime begin = LocalTime.parse(result.getString("begin"), DateTimeFormatter.ISO_LOCAL_TIME);
                    LocalTime end = LocalTime.parse(result.getString("end"), DateTimeFormatter.ISO_LOCAL_TIME);
                    if(ReaderTime.isTimeSlot(begin, end)){
                       userId = result.getInt("id");
                    }
                }
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
    public ArrayList deleteEvents(ArrayList tablesOfEvents){
        ArrayList <String> imagesOfEventsToBeDeleted = new ArrayList(); //Aqui se guardarán el listado de los nombres de las fotos a borrar
        int count = 0; //Contador para almacenar el número de tablas de eventos que se procesaron
        connect(); //Se conecta a la base de datos
        String limitDate;
        //Se consulta desde la tabla correspondiente, el tiempo máximo que se almacenan los eventos
        ResultSet result = consult("SELECT year, month FROM events_eventsduration WHERE id='1'");
        if(result!=null){
            try {
                result.next();
                //A la fecha actual se le resta la cantidad de años y meses establecidos en la tabla de la base de datos
                limitDate = LocalDate.now().minusYears(result.getInt("year")).minusMonths(result.getInt("month")).toString();
        
                while(count < tablesOfEvents.size()){//Se consultan todas las tablas de eventos
                    result = consult("SELECT id,image FROM "+tablesOfEvents.get(count) +" WHERE date_time <'"+limitDate+"'" );
                    if(result!=null){
                        while(result.next()) { //Mietras hayan filas a eliminar
                            imagesOfEventsToBeDeleted.add(result.getString("image"));
                            delete("DELETE FROM "+tablesOfEvents.get(count)+" WHERE id = "+result.getInt("id"));
                        }
                    }
                    count ++;
                }
            }
            catch (SQLException ex) {
               Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
        }
        disconnect();
        return imagesOfEventsToBeDeleted;
    }
    
    @Override
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
                //Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        disconnect();
        return tables;
    }
    
    @Override
    public boolean movementSlotTime(){
        
        boolean response = false; 
        connect();
        ResultSet result = consult("SELECT begin, end FROM events_movementtimezone as timeZone WHERE timeZone.id='1'");
        if(result!=null){
            try {
                result.next();
                //Si NO es administrador, se verifica la franja horaria
                LocalTime begin = LocalTime.parse(result.getString("begin"), DateTimeFormatter.ISO_LOCAL_TIME);
                LocalTime end = LocalTime.parse(result.getString("end"), DateTimeFormatter.ISO_LOCAL_TIME);
                if(ReaderTime.isTimeSlot(begin, end)){
                   response = true;
                }
            } 
            catch (SQLException ex) {
                //Logger.getLogger(ConcreteDataBase.Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
                response = false;
            }
        }
        disconnect();
        return response;
    }
    
    
}
