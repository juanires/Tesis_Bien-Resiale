package ConcreteService;
import Service.Service;
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
public class ConcreteService extends Service {
    
     private String nameService;
    
    
    /**
    * Crea un nuevo servicio. 
    * 
    * @param servicio Nombre del servicio que se quiere asociar al objeto.
    */
    public ConcreteService(String nameService){
        this.nameService = nameService;
    }
    
    
    /**
    * Iniciar el servicio. Ejecuta una llamada de sistema para iniciar
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha iniciado el servicio.
    * 
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int start(){
        
        try {
            Process process;
            process = Runtime.getRuntime().exec("/etc/init.d/"+nameService+" start");
            InputStream is = process.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String result = buffer.readLine();
            
            if(result.contains("Starting "+nameService)){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
    * Detener el servicio. Ejecuta una llamada de sistema para detener
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha detenido el servicio.
    * 
    * @return 1 si el servicio se ha detenido correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int stop(){
        
        try {
            Process process;
            process = Runtime.getRuntime().exec("/etc/init.d/"+nameService+" stop");
            InputStream is = process.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String result = buffer.readLine();
            
            if(result.contains("Stopping "+nameService)){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
    * Reiniciar el servicio. Ejecuta una llamada de sistema para reiniciar
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha reiniciado el servicio.
    * 
    * @return 1 si el servicio se ha reiniciado correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int restart(){
        
        try {
            Process process;
            process = Runtime.getRuntime().exec("/etc/init.d/"+nameService+" restart");
            InputStream is = process.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String result = buffer.readLine();
            
            if(result.contains("Restarting "+nameService)){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
    * Estado el servicio. Ejecuta una llamada de sistema para obtener el estado
    * del servicio y evalua la respuesta de la misma para determinar el estado.
    * 
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
    @Override
    public int state(){
                 
        try {
            Process process;
            process = Runtime.getRuntime().exec("/etc/init.d/"+nameService+" status");
            InputStream is = process.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            
            //Se descartan las dos primeras lineas y se lee la tercera
            buffer.readLine();
            buffer.readLine();
            String result = buffer.readLine();
            
            if(result.contains("Active: active")){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return 0;
    }
    
    /**
    * Obtener el nombre del servicio. 
    * 
    * @return Nombre del servicio.
    */
    @Override
    public String getName(){
        
        return nameService;
    }
    
}
