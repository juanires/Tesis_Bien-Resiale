
import Hardware.Boton;
import Hardware.CamaraWeb;
import Hardware.Campana;
import Hardware.Movimiento;
import Hardware.RFID;
import HardwareInterfaz.Camara;
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
        
        Sonido cam = new Campana(3);
        cam.start();
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
