package Factory;
import ConcreteDataBase.Sqlite3;
import DataBase.DataBase;

/**
 *
 * @author Compuj
 */
public class DataBaseFactory {
    
    public static DataBase getDataBase(String url,String type){
        
        switch(type){
            case "sqlite3":
                return new Sqlite3(url);
            default: return null;
        }
    }
}
