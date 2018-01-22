package ConcreteDevice;
import Device.Device;
import Readers.ReaderDate;
import Readers.ReaderLastSnapshot;

/**
 * Esta clase se encarga de verificar el código leído por el lector y actuar en consecuencia. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class CodeVerifier extends Device implements Runnable{

    private Thread thread;
    private int userId;
    
    /**
    * Crea un nuevo objeto CodeVerifier.
    */
    public CodeVerifier(){
        thread = null;
        userId = -1;
    }
    
    /**
    * Se realiza la configuración del objeto. En este caso en particular es un método con un cuerpo vacío.
    */
    @Override
    protected void configure() {}

    /**
    * Crea e inicializa el hilo, configura y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        
        if(thread == null) {
              thread = new Thread(this);
              thread.start();
        }
        setActive(true);
    }

    /**
    * Activa o desactiva la acción que realiza el objeto.
    * @param option "true" para activar, "false" para desactivar.
    */
    @Override
    public void active(boolean option) {}
    
     /**
    * No se implementa para objeto.
    * @return null.
    */
    @Override
    public String getCode(){
        return null;
    }
    
    /**
    * No se implementa para objeto.
    * @return 0.
    */
    public int getPinState(){
        return 0;
    }
    
    /**
    * Ejecuta las acciones del objeto. En el cuerpo de éste método se establecen las acciones que realiza el objeto.
    * En este caso, se comprueba si el código leido se corresponde con un usuario de la base de datos que tenga el acceso habilitado
    * para la hora y dia actual:
    * Si el resultado obtenido se corresponde con un número mayor a cero (id del usuario), entonces se abre la puerta y registra 
    * el evento en la base de datos (en la tabla "events_permittedaccess").
    * Si el resultado obtenido es cero (no está permitido el ingreso), entonces registra el evento en la base de datos
    * (en la tabla "events_deniedaccess").
    */
    @Override
    public void run() {
       
        while(true){
            if(isActive()){
                //----------------ACCIONES QUE REALIZA EL HILO----------------------- 
                monitor.disparar(transitions.get(0));
                //Se verifica el codigo pertenece a un usuario de la base de datos y que ademas esté activo
                userId = dataBase.registeredUser(device.getCode());
                if(userId > 0){
                    monitor.disparar(transitions.get(1));
                    monitor.disparar(transitions.get(2));
                    dataBase.insert("insert into events_permittedaccess (date_time,user_id,image) " + "values ('"+ReaderDate.read()+"',"+ userId +",'"+ReaderLastSnapshot.read()+"')");
                    userId = -1;
                    monitor.disparar(transitions.get(3)); //Se retornan los recursos
                }
                else{
                    userId = -1;
                    monitor.disparar(transitions.get(4));
                    monitor.disparar(transitions.get(5));
                    dataBase.insert("insert into events_deniedaccess " + " (date_time,image)values ('"+ReaderDate.read()+"','"+ReaderLastSnapshot.read()+"')");
                    monitor.disparar(transitions.get(6));
                }
            }
        }
    }
}
