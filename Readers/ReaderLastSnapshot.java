package Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Compuj
 */
public class ReaderLastSnapshot {
       
    private static final String ABSOLUTE_PATH ="/home/pi/ProyectoIntegrador/DjangoProjects/tesis/events/static/events/images/";
    private static final String RELATIVE_PATH ="/events/images/";
    
    public static String read(){
          
        try{
            // Se lanza el ejecutable.
            Process p;
            p = Runtime.getRuntime().exec("readlink " + ABSOLUTE_PATH + "lastsnap.jpg");
            // Se obtiene el stream de salida del programa
            InputStream is = p.getInputStream();
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
            BufferedReader br = new BufferedReader (new InputStreamReader (is));
            // Se lee la primera linea
            return RELATIVE_PATH + br.readLine();
            
        }
        catch (IOException ex) {
            Logger.getLogger(ReaderLastSnapshot.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }   
}
