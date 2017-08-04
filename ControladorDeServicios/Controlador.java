package ControladorDeServicios;

import SoftwareInterfaz.SIControladorDeServicios;
import SoftwareInterfaz.SIServicio;
import java.util.ArrayList;

/**
 * Clase para controlar uno o más servicios. 
 * Describe todas las operaciones sobre el grupo de servicios.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public class Controlador implements SIControladorDeServicios {
    
    private ArrayList <SIServicio> servicios;
    
    /**
    * Crea un nuevo controlador. 
    */
    public Controlador(){
        servicios = new ArrayList<SIServicio>();
    }
    
    /**
    * Agregar servicio al controlador. 
    * 
    * @param servicio El servicio que se quiere agregar al controlador.
    */
    @Override
    public void agregarServicio(SIServicio servicio){
        
        servicios.add(servicio);
    }
    
    /**
    * Cargar e iniciar el servicio. Se verifica que sea un servicio cargado
    * previamente en el controlador, y si es así se lo intenta iniciar.
    * 
    * @param servicio Servicio a iniciar.
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int iniciarServicio(SIServicio servicio){
      
        for(int i=0; i<servicios.size();i++){ 
            if(servicio.getNombre() == servicios.get(i).getNombre()){
                
                return servicio.iniciar();
            }
        }
        return 0;
    }
    
     /**
    * Iniciar todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios del controlador se han iniciado
    * correctamente, de lo contrario retorna 0.
    */
    @Override
    public int iniciarTodosLosServicios(){
        
        int cargados=0;
        for(int i=0; i<servicios.size();i++){
            cargados += servicios.get(i).iniciar();
        } 
        
        //Se verifica que se hallan cargado la totalidad de los servicios
        //del controlador
        if(cargados == servicios.size()){
            return 1;
        }
        else {
            return 0;
        }
    }
    
    /**
    * Estado de un servicio del controlador. Debe ser un servicio agregado
    * previamente al controlador.
    * 
    * @param servicio Servicio del que se quiere conocer el estado.
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
    @Override
    public int estadoServicio(SIServicio servicio){
        
        //Si se corresponde con un servicio agregado al controlador, se intenta cargar
        for(int i=0; i<servicios.size();i++){ 
            if(servicio.getNombre() == servicios.get(i).getNombre()){
                
                return servicio.estado();
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
    public int estadoTodosLosServicios(){
        
        int estados=0;
        for(int i=0; i<servicios.size();i++){
            estados += servicios.get(i).estado();
        } 
        
        //Se verifica que se hallan cargado la totalidad de los servicios
        //del controlador
        if(estados == servicios.size()){
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
    public void cargarServiciosCaidos(){
         
        for(int i=0; i<servicios.size();i++){
            if(servicios.get(i).estado()== 0){
                servicios.get(i).reiniciar();
            }
        } 
    }
    
     /**
    * Imprime por pantalla una lista de los servicios del controlador. 
    */
    @Override
    public void listarServicios(){
        for(int i=0; i<servicios.size();i++){
            System.out.println("Servicio: "+servicios.get(i).getNombre()+" Estado: "+servicios.get(i).estado());
        }
    }
}
