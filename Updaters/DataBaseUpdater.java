package Updaters;
import DataBase.DataBase;
import Monitor.Monitor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Actualiza la base de datos. Las actualizaciones se realizan cada 24 horas, y se actualiza:
 * # Se da de baja un usuario de cuerdo a su fecha de expiración.
 * # Borrado de eventos cuya fecha de ocurrencia supera el tiempo establecido.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class DataBaseUpdater implements Runnable {
    
    private Thread thread;
    private Monitor monitor;
    private List<Integer> transitions;
    DataBase database;
    ArrayList<String> tablesOfEvents;
       
    /**
     * Crea un nuevo objeto DataBaseUpdater.
     * @param database base de datos a actualizar.
     */
    public DataBaseUpdater(DataBase database, Monitor monitor, List<Integer> transitions){
        thread = new Thread(this);
        this.database = database;
        this.monitor = monitor;
        this.transitions = transitions;
        tablesOfEvents = new ArrayList();
        thread.start();
    }
    
    /**
     * Método que lleva a cabo la ejecución de las actualizaciones cada 24hs.
     */
    @Override
    public void run() {
        while(true){
           //try {Thread.sleep(10000);}
            try {Thread.sleep(86400000);} //La actualizacion se hace una vez por dia 
            catch (InterruptedException ex){}
            monitor.disparar(transitions.get(0));
            loadTableName();
            usersUpdate();
            deleteEvents();
            monitor.disparar(transitions.get(1));
        }
    }
     
    /**
     * Se da de baja un usuario si ya ha transcurrido su fecha de expiración.
    */
    public void usersUpdate(){
        database.usersUpdate(LocalDate.now((ZoneId.of("UTC-3"))).toString());
    }
    
    /**
     * Se eliminan todos los eventos y fotos asociadas a los mismos, cuya fecha de ocurrencia más el tiempo 
     * establecido (mediante el método setTimeLapseOfDeleteEvents) supera a la fecha actual.
     */
    public void deleteEvents(){
        ImagesDelete.clean(database.deleteEvents(tablesOfEvents));
    }
    
    /**
     * Carga los nombres de las tablas que registran los distintos tipos de eventos. 
     */
    protected void loadTableName(){
        ArrayList<String> tables = database.tablesList();
        int count = 0;
        while(count < tables.size()){
            if(tables.get(count).substring(0, 6).equalsIgnoreCase("events"))
                tablesOfEvents.add(tables.get(count));
            count++;
        }
    }
}
