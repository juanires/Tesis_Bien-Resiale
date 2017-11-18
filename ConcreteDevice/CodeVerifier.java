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
    
    public CodeVerifier(){
        thread = null;
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
                //Si el codigo pertenece a un usuario de la base de datos
                if(dataBase.registeredUser("userCode", "code", device.getCode())){
                    monitor.disparar(transitions.get(1));
                    monitor.disparar(transitions.get(2));
                    dataBase.insert("insert into permitted " + "values ('"+ReaderDate.read()+"','"+ReaderLastSnapshot.read()+"')");
                    monitor.disparar(transitions.get(3)); //Se retornan los recursos
                }
                else{
                    monitor.disparar(transitions.get(4));
                    monitor.disparar(transitions.get(5));
                    dataBase.insert("insert into denied " + "values ('"+ReaderDate.read()+"','"+ReaderLastSnapshot.read()+"')");
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
