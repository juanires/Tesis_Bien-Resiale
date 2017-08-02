
import BaseDeDatos.Sqlite3;
import ControladorDeServicios.Controlador;
import Hardware.Boton;
import Hardware.CamaraWeb;
import Hardware.Campana;
import Hardware.Electrocerradura;
import Hardware.Movimiento;
import Hardware.RFID;
import java.util.logging.Level;
import java.util.logging.Logger;
import HardwareInterfaz.HIPulsador;
import HardwareInterfaz.HISensor;
import HardwareInterfaz.HISonido;
import HardwareInterfaz.HICamara;
import HardwareInterfaz.HICerradura;
import HardwareInterfaz.HILector;
import Servicios.Servicio;
import SoftwareInterfaz.SIControladorDeServicios;
import SoftwareInterfaz.SIServicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
  
    public static void main(String[] args) {
        
       Controlador cont = new Controlador();
        
       cont.agregarServicio(new Servicio ("freeswitch"));
       
        System.out.println(cont.cargarTodosLosServicios());
       
        /*HISonido camp = new Campana(3);
        HILector rfid = new RFID();
        HISensor mov = new Movimiento(4);
        HICerradura cerr = new Electrocerradura(0,5);
        HICamara cam = new CamaraWeb(8083);
        HIPulsador pul = new Boton(2);
        
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
      // HICamara cam = new CamaraWeb(8083);
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
