package Readers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se utiliza para obtener el path de la última foto capturada. Para que ésta clase funcione correctamente
 * deben establecerse manualmente el valor de los campos ABSOLUTE_PATH (path absoluto) y RELATIVE_PATH (path relativo respecto
 * de la configuración de Django). La clase puede retornar el path absoluto o el path relativo de la foto dependiendo 
 * la implementación de su método.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ReaderSnapshot {
       
    private static final String ABSOLUTE_PATH ="/home/pi/ProyectoIntegrador/tesis/events/static/events/images/";
    private static final String RELATIVE_PATH ="/events/images/";
    
    /**
     * Método que retorna el path relativo de la última foto capturada. El path relativo depende de donde se configure
     * en el programa Django.
     * @return path relativo de la última foto capturada.
     */
    public static String readLastSnapshoot(){
            
        try{
            // Se lanza el ejecutable.
            Process p;
            p = Runtime.getRuntime().exec("readlink " + ABSOLUTE_PATH + "lastsnap.jpg");
            // Se obtiene el stream de salida del programa
            p.waitFor();
            while(p.isAlive()){
               try { Thread.currentThread().sleep(250);} 
               catch (InterruptedException ex) {} 
            }
            InputStream is = p.getInputStream();
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
            BufferedReader br = new BufferedReader (new InputStreamReader (is));
            //Se lee la primera linea (que contiene el nombre de la foto) y se la concatena al path relativo.
            String salida= RELATIVE_PATH + br.readLine();
            br.close();
            is.close();
            p.destroyForcibly();
            return salida;   
        }
        catch (IOException ex) {
            Logger.getLogger(ReaderSnapshot.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(ReaderSnapshot.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR2");
            return null;
        }
    }
    
    public static String getAbsolutePath(){
        return ABSOLUTE_PATH;
    }
    
    public static String getRelativePath(){
        return RELATIVE_PATH;
    }
    
}
