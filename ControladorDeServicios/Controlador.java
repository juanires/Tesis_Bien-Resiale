package ControladorDeServicios;

import SoftwareInterfaz.SIControladorDeServicios;
import SoftwareInterfaz.SIServicio;
import java.util.ArrayList;

/**
 *
 * @author Compuj
 */
public class Controlador implements SIControladorDeServicios {
    
    private ArrayList <SIServicio> servicios;
    
    public Controlador(){
        
        servicios = new ArrayList<SIServicio>();
    }
    
    
    @Override
    public void agregarServicio(SIServicio servicio){
        
        servicios.add(servicio);
        
    }
    
    @Override
    public int cargarServicio(SIServicio servicio){
      
        //Si se corresponde con un servicio agregado al controlador, se intenta cargar
        for(int i=0; i<servicios.size();i++){ 
            if(servicio.getNombre() == servicios.get(i).getNombre()){
                
                return servicio.iniciar();
            }
        }
        return 0;
    }
    
    @Override
    public int cargarTodosLosServicios(){
        
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
    
    @Override
    public void cargarServiciosCaidos(){
         
        for(int i=0; i<servicios.size();i++){
            if(servicios.get(i).estado()== 0){
                servicios.get(i).reiniciar();
            }
        } 
    }
    
    @Override
    public void listarServicios(){
        for(int i=0; i<servicios.size();i++){
            System.out.print("Servicio: "+servicios.get(i).getNombre());
            System.out.print(" Estado: "+servicios.get(i).estado());
        }
    }
}
