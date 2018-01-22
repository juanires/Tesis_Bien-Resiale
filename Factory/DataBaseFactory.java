package Factory;
import ConcreteDataBase.Sqlite3;
import DataBase.DataBase;

/**
 * Fábrica de objetos que interactúan con una bases de datos. Esta clase se encarga de crear objetos "ConcreteDataBase" 
 * mediante su método abstracto "getDataBase" que interactúan con una base de datos, 
 * el tipo de base de datos con la que interactúan depende del parámetro que se le pase a este método.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class DataBaseFactory {
    
    /**
    * Crea un nuevo objeto que interactúa con una base de datos. 
    * 
    * @param type especifica el nombre del tipo de base de datos con la que se quiere interactuar. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDataBase del cual se quiere crear una instancia. Por ejemplo si se quiere crear un
    * objeto que interactúe con una base de datos SQLite3, el parámtro debe ser "sqlite3".
    * @param url path de la base de datos.
    * 
    * @return ConcreteDataBase, objeto que va a interactuar con la base de datos.
    */
    public static DataBase getDataBase(String url,String type){
        
        switch(type){
            case "sqlite3":
                return new Sqlite3(url);
            default: return null;
        }
    }
}
