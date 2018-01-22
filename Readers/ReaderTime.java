package Readers;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Clase que lee la hora actual.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ReaderTime {
    
    /**
     * Lee la hora actual.
     * @return la hora actual.
     */
    public static String read(){
       
        return LocalTime.now().toString();
    }
     
    /**
     * Determina si la hora actual se encuentra en la franja horaria determinada por los parámetros begin y end.
     * @param begin hora de comienzo.
     * @param end hora de finalización.
     * @return True su la hora actual se encuentra dentro de la franja horaria, de lo contrario retorna False.
     */
    public static boolean isTimeSlot(LocalTime begin, LocalTime end){
        LocalTime now = LocalTime.now(ZoneId.of("UTC-03"));
        if(begin.isAfter(end)){//Se tiene en cuanta si el caso de que el rango de hora abarque horas de distintos dias (ej 10pm a 7am)
            if(now.isAfter(begin) || now.isBefore(end)){
                return true;
            }
        }
        else{
            if(now.isAfter(begin) && now.isBefore(end)){
                return true;
            }
        }
        return false;
    } 
}
