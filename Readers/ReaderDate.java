package Readers;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Clase que lee la fecha actual.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ReaderDate {
    
    /**
    * Lee la fecha actual.
    * @return la fecha actual en formato UTC.
    */
    public static String read(){
        
       return LocalDateTime.now(ZoneId.of("UTC")).toString();
    }
}
