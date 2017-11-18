import ConcreteDataBase.Sqlite3;
import ConcreteDeviceFactory.*;
import DataBase.DataBase;
import ServiceController.ServiceController;
import Device.Device;
import DeviceController.DeviceController;
import Factory.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Monitor.*;
import SoftwareInterface.SIDeviceController;
import SoftwareInterface.SIServiceController;
import java.util.ArrayList;
import java.util.Collection;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
    public static void main(String[] args) {
          
    //CREACION MONITOR
    ProcesadorPetri proc = new ProcesadorPetri(17,17,"/home/pi/ProyectoIntegrador/MatrizIncidencia3_2.txt","/home/pi/ProyectoIntegrador/MatrizEstado3_2.txt");
    Monitor mon = new Monitor(proc);
    
    //CREACION DE CONTROLADORES
    GpioController gpio = GpioFactory.getInstance();
    SIDeviceController deviceController = new DeviceController(15);
    SIServiceController serviceController = new ServiceController();
    
    //CREACION DE BASE DE DATOS
    DataBase dataBase = DataBaseFactory.getDataBase("/home/pi/ProyectoIntegrador/baseDeDatos/prueba.sqlite","sqlite3"); 
   
    
    //CREACION DE FABRICAS
    DeviceFactory factoryGPIO = new GPIODeviceFactory();
    DeviceFactory factoryCamera = new CameraDeviceFactory();
    DeviceFactory factorySerial = new SerialDeviceFactory();
    DeviceFactory factoryCodeVerifier = new CodeVerifierDeviceFactory();
    DeviceFactory factoryWebEvent = new WebEventDeviceFactory();
        
    //CREACION DE DISPOSITIVOS
    deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, mon, Arrays.asList(14,8,10), "movement", gpio, 4,0 ,"GpioListener"));
    deviceController.addDevice(factoryCamera.implementsDevice(dataBase, mon, Arrays.asList(3,9), "Camera", 8080 ,"Camera"));
    deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, mon, Arrays.asList(14,8,10), "button", gpio, 5,0, "GpioListener"));
    deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, mon, Arrays.asList(2), "door", gpio, 2,3, "GpioOutput"));
    deviceController.addDevice(factorySerial.implementsDevice(mon, Arrays.asList(0), "readerCode", "SerialComunications"));
    deviceController.addDevice(factoryCodeVerifier.implementsDevice(dataBase, mon, Arrays.asList(4,15,8,1,7,8,16), "userCode", deviceController.getDevice("readerCode"), "CodeVerifier"));
    deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, mon, Arrays.asList(12,5,13), "sendCode", 9000, deviceController.getDevice("readerCode"), "WebEventSendCode"));
    deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, mon, Arrays.asList(11,8,6), "WebEventOpenDoor", 9001, deviceController.getDevice("readerCode"), "WebEventOpenDoor"));

   //CREACION DE LOS SERVICIOS
   
    
    
    //PRUEBA DE LA FUNCION ACTIVE   
    try {Thread.sleep(20000);} 
    catch (InterruptedException ex) {}
  //  System.out.println("DESACTIVADO");
  //  deviceController.setActiveDevice("button", false);
    try {Thread.sleep(20000);}
    catch (InterruptedException ex) {}
  //  System.out.println("ACTIVADO");
   // deviceController.setActiveDevice("button", true);
    //-------------------------------------------------
     /*
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
