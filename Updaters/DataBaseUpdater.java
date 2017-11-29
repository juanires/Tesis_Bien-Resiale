package Updaters;

import DataBase.DataBase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Compuj
 */
public class DataBaseUpdater {
    
    DataBase database;
    ArrayList<String> tablesOfEvents;
    LocalDateTime limitTime ;
    int yearsLimit;
    int monthsLimit;
    int daysLimit;
    
    public DataBaseUpdater(DataBase database){
        this.database = database;
        tablesOfEvents = new ArrayList();
        setTimeLapseOfDeleteEvents(2,0,0);
        loadTableName();
    }
    
    public void usersUpdate(){
        database.update("UPDATE users_user SET is_active = 0 WHERE expiration_date <'"+LocalDate.now().toString()+"'");
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
