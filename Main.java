import ConcreteDataBase.Sqlite3;
import ConcreteDeviceFactory.*;
import DataBase.DataBase;
import ServiceController.ServiceController;
import Device.Device;
import Factory.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Monitor.*;
import SoftwareInterface.SIServiceController;
import java.util.ArrayList;
import java.util.Collection;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
    public static void main(String[] args) {
    
 
      
      
       // Sqlite3 dataBase = new Sqlite3("/home/pi/ProyectoIntegrador/baseDeDatos/prueba.sqlite");
        
        //dataBase.insert("insert into movement (date,snapshot) " + "values ('"+ReaderDate.read()+"','"+ReaderLastSnapshot.read()+"')");
   
    
    try {Thread.sleep(20000);} 
    catch (InterruptedException ex) {}
    
    
    
        
   
    GpioController gpio = GpioFactory.getInstance();
    ProcesadorPetri proc = new ProcesadorPetri(16,13,"/home/pi/Documents/ProyectoIntegrador/MatrizPrograma/MatrizIncidencia.txt","/home/pi/Documents/ProyectoIntegrador/MatrizPrograma/MatrizEstado.txt");
    Monitor mon = new Monitor(proc);
    
    ArrayList <Device> devices = new ArrayList();
    DeviceFactory factoryGPIO = new GPIODeviceFactory();
    DeviceFactory factoryCamera = new CameraDeviceFactory();
    DeviceFactory FactorySerial = new SerialDeviceFactory();
    DataBase dataBase = DataBaseFactory.getDataBase("/home/pi/ProyectoIntegrador/baseDeDatos/prueba.sqlite","sqlite3"); 
    
    devices.add(factoryGPIO.implementsDevice(dataBase, mon, Arrays.asList(2,3,4,5), "Output1", gpio, 4,5 ,"GpioOutput"));
    devices.add(factoryGPIO.implementsDevice(dataBase, mon, Arrays.asList(2,3,4,5), "Listener2", gpio, 5,0, "GpioListener"));
    devices.add(FactorySerial.implementsDevice(dataBase, mon, Arrays.asList(2,3,4,5), "RFID", "SerialComunications"));
   
        /*
        //try {Thread.sleep(5000);} 
    //catch (InterruptedException ex) {}
    
    
    while(true){
        try {Thread.sleep(5000);} 
        catch (InterruptedException ex) {}
    
    }
    /*
        int [] arr = {2,3};
        
        System.out.println(arr[3]);
 
   // HICamara cam = new CamaraWeb(8083,moni,transCam,1);
    GpioController gpio = GpioFactory.getInstance();
    HIPulsador pulsador2 = new Boton(gpio,4);
   // HIPulsador pulsador = new Boton(gpio,5);
    HISonido camp = new Campana(gpio,5);
   // HISensor mov = new Movimiento(gpio,5,moni, transPuls,2);
    HILector rfid = new RFID();
    rfid.start();
    pulsador2.start();   
    camp.start();
    
    while(true){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Desactivado");
       
        rfid.desactivar();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        System.out.println("ACTIVADO");
        rfid.activar();
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
