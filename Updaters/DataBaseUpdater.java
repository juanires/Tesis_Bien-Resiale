package Updaters;
import DataBase.DataBase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Compuj
 */
public class DataBaseUpdater implements Runnable {
    
    private Thread thread;
    DataBase database;
    ArrayList<String> tablesOfEvents;
    LocalDateTime limitTime ;
    int yearsLimit;
    int monthsLimit;
    int daysLimit;
    
    public DataBaseUpdater(DataBase database){
        thread = new Thread(this);
        this.database = database;
        tablesOfEvents = new ArrayList();
        setTimeLapseOfDeleteEvents(2,0,0);
        loadTableName();
        thread.start();
    }
    
    @Override
    public void run() {
        while(true){
           
            try {Thread.sleep(86400000);} //La actualizacion se hace una vez por dia 
            catch (InterruptedException ex){}
            usersUpdate();
            deleteEvents();
        }
    }
      
    public void usersUpdate(){
        database.update("UPDATE users_user SET is_active = 0 WHERE is_staff = 0 and expiration_date <'"+LocalDate.now().toString()+"'");
    }
    
    public void deleteEvents(){
        limitTime = LocalDateTime.now().minusYears(yearsLimit).minusMonths(monthsLimit).minusDays(daysLimit);
        ImagesDelete.clean(database.deleteEvents(limitTime, tablesOfEvents));
    }
    
    protected void loadTableName(){
        ArrayList<String> tables = database.tablesList();
        int count = 0;
        while(count < tables.size()){
            if(tables.get(count).substring(0, 6).equalsIgnoreCase("events"))
                tablesOfEvents.add(tables.get(count));
            count++;
        }
    }
    
    public void setTimeLapseOfDeleteEvents(int year,int month,int day){
        this.yearsLimit = year;
        this.monthsLimit = month;
        this.daysLimit = day;
    }
}
