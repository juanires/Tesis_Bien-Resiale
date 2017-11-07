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
    
    private Connection conexion;
    
    /**
    * Crea un nuevo objeto SQLite3
    * 
    * @param url Ubicación de la base de datos.
    */
    public Sqlite3(String url){
        this.url= url;
        this.conexion=null;
    }
    
    /**
    * Conectar a la base de datos. 
    * 
    */
    @Override
    public void conectar(){
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
            if (conexion!=null) {
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
    public void desconectar(){
        try {
            conexion.close();
        } 
        catch (SQLException ex) {
         System.out.println("Error al cerrar base de datos");   
        }
    }
    
    /**
    * Consultar a la base de datos. 
    * @param consulta Consulta sql a ejecutar.
    * @return ResultSet. Retorna el resultado de la consulta.
    */
    @Override
    public ResultSet consultar(String consulta){
        
        ResultSet resultado = null;
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            resultado = st.executeQuery();
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return resultado;
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
    public boolean usuarioRegistrado(String codigo){
        
        conectar();
        
        boolean registrado = false;
        ResultSet resultado = consultar("SELECT * FROM control_datos_usuarios_dj WHERE clave_usuario='"+codigo+"'");
        try {
            registrado = resultado.next();
        } 
        catch (SQLException ex) {
            Logger.getLogger(ConcreteDataBase.Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        
        return registrado;  
    }
    
    
}
