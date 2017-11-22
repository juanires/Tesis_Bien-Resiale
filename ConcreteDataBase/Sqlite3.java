package ConcreteDataBase;

import DataBase.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public ResultSet consult(String sqlStatement){
      
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
    public int registeredUser(String nameTable, String nameColumnCode, String code, String nameColumnToCompare){
         
        int user = -1;
        connect();
        ResultSet result = consult("SELECT * FROM " + nameTable + " WHERE "+ nameColumnCode +"='"+code+"'");
               
        try {
            while (result.next()) { //De los resultados obtenidos se consulta si alguno esta activo
                if(result.getBoolean(nameColumnToCompare)){
                    user = result.getInt("id");
                }
            }
            disconnect();
            return user; //En caaso de que no haya  usuario con el codigo especificado y que ademas esté activo
        } 
        catch (SQLException ex) {
            Logger.getLogger(ConcreteDataBase.Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
            disconnect();
            return -1;
        }
    }
}
