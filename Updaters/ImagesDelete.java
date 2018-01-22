package Updaters;
import Readers.ReaderLastSnapshot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Borra las imágenes almacenadas en un directorio determinado. El directorio es el establecido
 * en el campo ABSOLUTE_PATH.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ImagesDelete {
  
    private static final String ABSOLUTE_PATH ="/home/pi/ProyectoIntegrador/DjangoProjects/tesis/events/static/events/images/";
    private static final String RELATIVE_PATH ="/events/images/";
    
    /**
     * Borra la lista de imágenes que se pasa como parámetro.  
     * @param images lista de los nombres de las imágenes a borrar.
     */
    public static void clean(ArrayList<String> images){
        int count = 0;
       
        while(count < images.size()){
            try{
                String cmd = "rm "+ABSOLUTE_PATH.substring(0,ABSOLUTE_PATH.lastIndexOf(RELATIVE_PATH))+ images.get(count); 
                Runtime.getRuntime().exec(cmd).waitFor(); 
            }
            catch (IOException ex) {
                Logger.getLogger(ReaderLastSnapshot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ImagesDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
            count++;
        }
    }
}
