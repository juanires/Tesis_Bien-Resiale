package Readers;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
/**
 *
 * @author Compuj
 */
public class ReaderDate {
    
    public static String read(){
        /*
        Date d = new Date();
        Calendar c = new GregorianCalendar(); 
        c.setTime(d);

        String day = Integer.toString(c.get(Calendar.DATE));
        String month = Integer.toString(c.get(Calendar.MONTH));
        String year = Integer.toString(c.get(Calendar.YEAR));
        String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String second = Integer.toString(c.get(Calendar.SECOND));
        
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second; 
        */
       // LocalDateTime.now(ZoneId.of("UTC"));
        return LocalDateTime.now().toString();
    }
    
}
