
import BaseDeDatos.Sqlite3;
import ControladorDeServicios.Controlador;
import Hardware.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import HardwareInterfaz.*;
import Monitor.*;
import Servicios.Servicio;
import SoftwareInterfaz.*;
import java.util.ArrayList;
import java.util.Collection;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
  
    public static void main(String[] args) {
        
        int [] transCam = {4};
        int [] transPuls = {10,11};
      
    ProcesadorPetri proc = new ProcesadorPetri(16,13, "/home/pi/ProyectoIntegrador/MatrizIncidencia.txt","/home/pi/ProyectoIntegrador/MatrizEstado.txt");
    //ProcesadorPetri proc = new ProcesadorPetri(16,13, "/home/pi/Documents/ProyectoIntegrador/MatrizPrograma/MatrizIncidencia.txt","/home/pi/Documents/ProyectoIntegrador/MatrizPrograma/MatrizEstado.txt");
    Monitor moni = new Monitor(proc);
   // HICamara cam = new CamaraWeb(8083,moni,transCam,1);
    GpioController gpio = GpioFactory.getInstance();
    HIPulsador pulsador2 = new Boton(gpio,4);
    //HIPulsador pulsador = new Boton(gpio,5,moni, transPuls,2);
    
   // HISensor mov = new Movimiento(gpio,5,moni, transPuls,2);
    HILector rfid = new RFID();
    rfid.start();
    pulsador2.start();   
   
    while(true){
    try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   //pulsador.start();   
    
    //mov.start();
    //cam.start();
   
    //   cont.agregarServicio(servi);
    
        
       
        
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
