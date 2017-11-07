package ServiceController;
import Service.Service;
import SoftwareInterface.SIServiceController;
import java.util.ArrayList;

/**
 * Clase para controlar uno o más servicios. 
 * Describe todas las operaciones sobre el grupo de servicios.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public class ServiceController implements SIServiceController {
    
    private ArrayList <Service> services;
    
    /**
    * Crea un nuevo controlador. 
    */
    public ServiceController(){
        services = new ArrayList<Service>();
    }
    
    /**
    * Agregar servicio al controlador. 
    * 
    * @param servicio El servicio que se quiere agregar al controlador.
    */
    @Override
    public void addService(Service service){
        
        services.add(service);
    }
    
    /**
    * Cargar e iniciar el servicio. Se verifica que sea un servicio cargado
    * previamente en el controlador, y si es así se lo intenta iniciar.
    * 
    * @param service Servicio a iniciar.
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int startService(String nameService){
      
        for(int i=0; i<services.size();i++){ 
            if(nameService.equals(services.get(i).getName())){
                
                return services.get(i).start();
            }
        }
        return 0;
    }
    
   
     /**
    * Iniciar todos los service del controlador. 
    * 
    * @return 1 si todos los servicios del controlador se han iniciado
    * correctamente, de lo contrario retorna 0.
    */
    @Override
    public int startAllServices(){
        
        int cargados=0;
        for(int i=0; i<services.size();i++){
            cargados += services.get(i).start();
        } 
        
        //Se verifica que se hallan cargado la totalidad de los servicios
        //del controlador
        if(cargados == services.size()){
            return 1;
        }
        else {
            return 0;
        }
    }
    
     public int stopService (String nameService){
        
        for(int i=0; i<services.size();i++){ 
            if(nameService.equals(services.get(i).getName())){
                
                return services.get(i).stop();
            }
        }
        return 0;
    }
    
    
    
    public int stopAllService (){
        
        for(int i=0; i<services.size();i++){ 
            if(services.get(i).stop() == 0){
                return 0;
            }
        }
        return 1;
    }
    
    
    /**
    * Estado de un servicio del controlador. Debe ser un servicio agregado
    * previamente al controlador.
    * 
    * @param service Servicio del que se quiere conocer el estado.
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
    @Override
    public int stateService(Service service){
        
        //Si se corresponde con un servicio agregado al controlador, se intenta cargar
        for(int i=0; i<services.size();i++){ 
            if(service.getName() == services.get(i).getName()){
                
                return service.state();
            }
        }
        return 0;
    }
    
    /**
    * Estado de todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios del controlador se encuentra activos, 
    * de lo contrario retorna 0.
    */
    @Override
    public int stateAllServices(){
        
        int estados=0;
        for(int i=0; i<services.size();i++){
            estados += services.get(i).state();
        } 
        
        //Se verifica que se hallan cargado la totalidad de los servicios
        //del controlador
        if(estados == services.size()){
            return 1;
        }
        else {
            return 0;
        }
    }
    
    /**
    * Iniciar los servicios caidos. Se reinician los servicios del controlador
    * que se encuentren inactivos.
    *
    */
    @Override
    public void restartDroppedService(){
         
        for(int i=0; i<services.size();i++){
            if(services.get(i).state()== 0){
                services.get(i).restart();
            }
        } 
    }
    
     /**
    * Imprime por pantalla una lista de los servicios del controlador. 
    */
    @Override
    public void listServices(){
        for(int i=0; i<services.size();i++){
            System.out.println("Servicio: "+services.get(i).getName()+" Estado: "+services.get(i).state());
        }
    }
}
