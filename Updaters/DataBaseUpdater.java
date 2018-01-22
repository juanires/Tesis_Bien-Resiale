package Updaters;
import DataBase.DataBase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    DataBase database;
    ArrayList<String> tablesOfEvents;
    LocalDateTime limitTime ;
    int yearsLimit;
    int monthsLimit;
    int daysLimit;
    
    /**
     * Crea un nuevo objeto DataBaseUpdater.
     * @param database base de datos a actualizar.
     */
    public DataBaseUpdater(DataBase database){
        thread = new Thread(this);
        this.database = database;
        tablesOfEvents = new ArrayList();
        setTimeLapseOfDeleteEvents(2,0,0);
        loadTableName();
        thread.start();
    }
    
    /**
     * Método que lleva a cabo la ejecución de las actualizaciones cada 24hs.
     */
    @Override
    public void run() {
        while(true){
           
            try {Thread.sleep(86400000);} //La actualizacion se hace una vez por dia 
            catch (InterruptedException ex){}
            usersUpdate();
            deleteEvents();
        }
    }
     
    /**
     * Se da de baja un usuario si ya ha transcurrido su fecha de expiración.
    */
    public void usersUpdate(){
        database.update("UPDATE users_user SET is_active = 0 WHERE is_staff = 0 and expiration_date <'"+LocalDate.now().toString()+"'");
    }
    
    /**
     * Se eliminan todos los eventos y fotos asociadas a los mismos, cuya fecha de ocurrencia más el tiempo 
     * establecido (mediante el método setTimeLapseOfDeleteEvents) supera a la fecha actual.
     */
    public void deleteEvents(){
        limitTime = LocalDateTime.now().minusYears(yearsLimit).minusMonths(monthsLimit).minusDays(daysLimit);
        ImagesDelete.clean(database.deleteEvents(limitTime, tablesOfEvents));
    }
    
    /**
     * Caga el nombre de las tablas de eventos. 
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
    
    /**
     * Se establece el tiempo que tiene que transcurrir desde la ocurrencia del evento hasta que sea borrado.
     * @param year
     * @param month
     * @param day 
     */
    public void setTimeLapseOfDeleteEvents(int year,int month,int day){
        this.yearsLimit = year;
        this.monthsLimit = month;
        this.daysLimit = day;
    }
}
