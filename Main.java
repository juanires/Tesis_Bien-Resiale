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
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import java.util.Arrays;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Main {
    public static void main(String[] args) {
    
        //CREACION MONITOR
        ProcesadorPetri proc = new ProcesadorPetri(26,28,"/home/pi/ProyectoIntegrador/MatrizIncidencia4.txt","/home/pi/ProyectoIntegrador/MatrizEstado4.txt");
        Monitor monitor = new Monitor(proc);

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

         //CREACION DE LOS SERVICIOS
        serviceController.addService(ServiceFactory.getService("motion"));
        
        //CREACION DE DISPOSITIVOS
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(23,14,19), "movement", gpio, 4,0 ,"GpioListener"));
        deviceController.addDevice(factoryCamera.implementsDevice(dataBase, monitor, Arrays.asList(5,15), "Camera", 8080 ,"Camera"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(23,14,19), "button", gpio, 5,0, "GpioListener"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(2), "door", gpio, 2,3, "GpioOutput"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(10,4), "GreeLed", gpio, 12,0, "GpioOutput"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(9,3), "YellowLed", gpio, 13,0, "GpioOutput"));
        deviceController.addDevice(factorySerial.implementsDevice(monitor, Arrays.asList(0), "readerCode", "SerialComunications"));
        deviceController.addDevice(factoryCodeVerifier.implementsDevice(dataBase, monitor, Arrays.asList(6,24,14,1,13,14,25), "userCode", deviceController.getDevice("readerCode"), "CodeVerifier"));
        deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, monitor, Arrays.asList(21,11,22), "sendCode", 9000, deviceController.getDevice("readerCode"), "WebEventSendCode"));
        deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, monitor, Arrays.asList(20,14,12), "WebEventOpenDoor", 9001, deviceController.getDevice("readerCode"), "WebEventOpenDoor"));

       monitor.disparar(18);//SE INICIA EL PROGRAMA
       monitor.disparar(17);//Se habilita el led Verde


        while(true){

            if (serviceController.stateAllServices()==1){//Si no hay problemas con servicios y el led esta encendido
               if(deviceController.getDevice("YellowLed").getPinState()==1)
                    monitor.disparar(7); //Se deshabilita el led Amarillo
            }
            else{
                if(deviceController.getDevice("YellowLed").getPinState()==0)//Si hay problemas con servicios y el led esta apagado
                    monitor.disparar(16);//Se habilita el led Amarillo
                    serviceController.restartDroppedService(); //Se reinician los servicios caidos
            }
            try {Thread.sleep(20000);} 
            catch (InterruptedException ex) {}
        }
    }
}
