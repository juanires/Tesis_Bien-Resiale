/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Readers;

import java.time.LocalTime;

/**
 *
 * @author Compuj
 */
public class ReaderTime {
    
    public static String read(){
       
        return LocalTime.now().toString();
    }
     
    public static boolean isTimeSlot(LocalTime begin, LocalTime end){

        if(begin.isAfter(end)){//Se tiene en cuanta si el caso de que el rango de hora abarque horas de distintos dias (ej 10pm a 7am)
            if(LocalTime.now().isAfter(begin) || LocalTime.now().isBefore(end)){
                return true;
            }
        }
        else{
            if(LocalTime.now().isAfter(begin) && LocalTime.now().isBefore(end)){
                return true;
            }
        }
        return false;
    } 
}
