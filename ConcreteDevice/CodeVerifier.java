package ConcreteDevice;
import Device.Device;
import Readers.ReaderDate;
import Readers.ReaderLastSnapshot;

/**
 *
 * @author Compuj
 */
public class CodeVerifier extends Device implements Runnable{

    private Thread thread;
    private int userId;
    
    public CodeVerifier(){
        thread = null;
        userId = -1;
    }
    
    @Override
    protected void configure() {}

    @Override
    public void start() {
        
        if(thread == null) {
              thread = new Thread(this);
              thread.start();
        }
        setActive(true);
    }

    @Override
    public void active(boolean option) {}

    @Override
    public void run() {
       
        while(true){
            if(isActive()){
                //----------------ACCIONES QUE REALIZA EL HILO----------------------- 
                monitor.disparar(transitions.get(0));
                //Si el codigo pertenece a un usuario de la base de datos y que ademas esté activo
                userId = dataBase.registeredUser("users_user", "code", device.getCode(),"is_active");
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
    
    @Override
    public String getCode(){
        return null;
    }
    
    public int getPinState(){
        return 0;
    }
    
}
