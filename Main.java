
import BaseDeDatos.Sqlite3;
import Hardware.Boton;
import Hardware.CamaraWeb;
import Hardware.Campana;
import Hardware.Electrocerradura;
import Hardware.Movimiento;
import Hardware.RFID;
import HardwareInterfaz.Camara;
import HardwareInterfaz.Cerradura;
import HardwareInterfaz.Lector;
import HardwareInterfaz.Pulsador;
import HardwareInterfaz.Sensor;
import HardwareInterfaz.Sonido;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
  
    public static void main(String[] args) {
        
        Sqlite3 bsd = new Sqlite3("/home/pi/Documents/ProyectoIntegrador/BaseDeDatos/database.sqlite");
        //Sqlite3 bsd = new Sqlite3("/ramDisk/ram0/database.sqlite");
       
        
       System.out.println(bsd.usuarioRegistrado("5000AA7B9E"));
       
        /*Sonido camp = new Campana(3);
        Lector rfid = new RFID();
        Sensor mov = new Movimiento(4);
        Cerradura cerr = new Electrocerradura(0,5);
        Camara cam = new CamaraWeb(8083);
        Pulsador pul = new Boton(2);
        
        camp.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        rfid.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        mov.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        cam.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        cerr.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        pul.start();
      // Camara cam = new CamaraWeb(8083);
      /* 
     //  cam.capturaVideo(5);
        cam.capturaFoto();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        cam.capturaFoto();
       
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         cam.capturaFoto();
        
       
        
        
        /*
        System.out.println("Desactivado");
        mov.desactivar();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Activado");
        mov.activar();
     */   
    }
     
}
