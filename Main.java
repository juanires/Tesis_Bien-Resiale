import ConcreteDeviceFactory.*;
import DataBase.DataBase;
import ServiceController.ServiceController;
import DeviceController.DeviceController;
import Factory.*;
import Monitor.*;
import Readers.*;
import SoftwareInterface.SIDeviceController;
import SoftwareInterface.SIServiceController;
import Updaters.DataBaseUpdater;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Clase principal. Inicializa todos los objetos que componen el sistema de control de acceso.
 * En las variables DETECTION_TIME_INIT_MOTION_SENSOR y DETECTION_TIME_FINALIZE_MOTION_SENSOR se especifica
 * la franja horaria en que se va a detectar movimiento, es decir se activa el sensor.
 * CONEXIONES DE LOS DEVICES:
 * # GPIO 2: Cerradura Magnética
 * # GPIO 3: Campana
 * # GPIO 4: Sensor de movimiento
 * # GPIO 5: Pulsador
 * # GPIO 12: Led verde
 * # GPIO 13: Led amarillo
 * # PUERTO 50000: Apertura de puerta desde la web
 * # PUERTO 50001: Solicitud desde la web de envío de código
 * # PUERTO USB: Cámara web
 * 
 * Cada 10 segundos: 1)Se realiza la comprobación de estado de los servicios 2) Se verifica si la hora actual
 * está dentro de la franja de detección de movimiento.
 * 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */


public class Main {
    /**
     * Ejecuta el programa principal.
     * @param args 
     */
    public static void main(String[] args) {
            
        
        //VARIABLES
        LocalTime DETECTION_TIME_INIT_MOTION_SENSOR = LocalTime.of(20,45); // El inicio de deteccion es a las 20 hs
        LocalTime DETECTION_TIME_FINALIZE_MOTION_SENSOR = LocalTime.of(7,0); // La finalizacion de deteccion es a las 7.45 hs
        
        
        //CREACION MONITOR
        ProcesadorPetri proc = new ProcesadorPetri(31,31,"/home/pi/ProyectoIntegrador/SoftwareAdministrador/Matrices/MatrizIncidencia6.txt","/home/pi/ProyectoIntegrador/SoftwareAdministrador/Matrices/MatrizEstado6.txt");
        Monitor monitor = new Monitor(proc);

        //CREACION DE CONTROLADORES
        GpioController gpio = GpioFactory.getInstance();
        SIDeviceController deviceController = new DeviceController(15);
        SIServiceController serviceController = new ServiceController();

        //CREACION DE BASE DE DATOS
        DataBase dataBase = DataBaseFactory.getDataBase("/home/pi/ProyectoIntegrador/tesis/db.sqlite3","sqlite3"); 
        
        //CREACION DE FABRICAS
        DeviceFactory factoryGPIO = new GPIODeviceFactory();
        DeviceFactory factoryCamera = new CameraDeviceFactory();
        DeviceFactory factorySerial = new SerialDeviceFactory();
        DeviceFactory factoryCodeVerifier = new CodeVerifierDeviceFactory();
        DeviceFactory factoryWebEvent = new WebEventDeviceFactory();

         //CREACION DE LOS SERVICIOS
        serviceController.addService(ServiceFactory.getService("motion"));
                
        //CREACION DE DISPOSITIVOS
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(26,15,21), "movement", gpio, 4,0 ,"GpioListenerMovement"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(26,15,18,25), "button", gpio, 5,0, "GpioListenerButton"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(9), "bell", gpio, 3,3, "GpioOutputBell"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(2), "door", gpio, 2,1, "GpioOutputLock"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(11,4), "GreeLed", gpio, 12,0, "GpioOutputLed"));
        deviceController.addDevice(factoryGPIO.implementsDevice(dataBase, monitor, Arrays.asList(10,3), "YellowLed", gpio, 13,0, "GpioOutputLed"));
        deviceController.addDevice(factoryCamera.implementsDevice(dataBase, monitor, Arrays.asList(5,16), "Camera", 8080 ,"Camera"));
        deviceController.addDevice(factorySerial.implementsDevice(monitor, Arrays.asList(0), "readerCode", "SerialComunications"));
        deviceController.addDevice(factoryCodeVerifier.implementsDevice(dataBase, monitor, Arrays.asList(6,27,15,1,14,15,28), "userCode", deviceController.getDevice("readerCode"), "CodeVerifier"));
        deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, monitor, Arrays.asList(23,12,24), "sendCode", 50001, deviceController.getDevice("readerCode"), "WebEventSendCode"));
        deviceController.addDevice(factoryWebEvent.implementsDevice(dataBase, monitor, Arrays.asList(22,15,13), "WebOpenDoor", 50000, deviceController.getDevice("readerCode"), "WebEventOpenDoor"));

        //CREACION E INICIALIZACION DEL UPDATER(ACTUALIZADOR DE BASE DE DATOS)
        DataBaseUpdater dataBaseUpdater = new DataBaseUpdater(dataBase,monitor,Arrays.asList(29,30));
            
        //SE DISPARAN LAS TRANSICIONES QUE HABILITAN LOS DISPOSITIVOS
        monitor.disparar(20);//SE INICIA EL PROGRAMA
        monitor.disparar(19);//Se habilita el led Verde
  

        while(true){
            
            //COMPROBACION DEL FUNCIONAMIENTO DE SERVICIOS
            verifyServices(monitor,serviceController,deviceController);
             monitor.disparar(29);
            //COMPROBACION DEL HORARIO EN QUE SE DETECTA MOVIMIENTO
            verifyTimeMotionSensor(dataBase,deviceController, DETECTION_TIME_INIT_MOTION_SENSOR, DETECTION_TIME_FINALIZE_MOTION_SENSOR);
            monitor.disparar(30);
            try {Thread.sleep(10000);} 
            catch (InterruptedException ex) {}
        }
    }
    
    /**
     * Verifica el estado de los servicios. Cuando uno de los servicios  está detenido o no está funcionando
     * correctamente, se enciende el Led amarillo para indicar que hay problemas con un servicio. Detectado este
     * inconveniente se reinicia el servicio caído y se verifica nuevamente el estado, si todo es correcto se apaga
     * el led amarillo, de lo contrario continúa encendido.
     * @param monitor monitor que controla el funcionamiento de los dispositivos.
     * @param serviceController controlador de servicios mediante el cual se interactúa con los servicios.
     * @param deviceController  controlador de dispositivos mediante el cual se interactúa con los dispositivos.
     */
    static public void verifyServices(Monitor monitor, SIServiceController serviceController,SIDeviceController deviceController){
        //COMPROBACION DEL FUNCIONAMIENTO DE SERVICIOS
        if (serviceController.stateAllServices()==1){//Si no hay problemas con servicios y el led esta encendido
           if(deviceController.getDevice("YellowLed").getPinState()==1){
                monitor.disparar(7); //Se deshabilita el led Amarillo
           }
        }
        else{
            if(deviceController.getDevice("YellowLed").getPinState()==0){//Si hay problemas con servicios y el led esta apagado
                monitor.disparar(17);//Se habilita el led Amarillo
                serviceController.restartDroppedService(); //Se reinician los servicios caidos
            }
        }
    }
     
    /**
     * Se verifica si se encuentra dentro de la franja horaria que se debe detectar movimiento. Se comprueba si la
     * hora actual está dentro de los límites horarios establecidos en los parámetros.
     * @param deviceController controlador de dispositivos mediante el cual se interactúa con los dispositivos.
     * @param detectionTimeInit hora de inicio de la detección.
     * @param detectionTimeFinalize hora de finalización de la detección.
     */
    static public void verifyTimeMotionSensor(DataBase db,SIDeviceController deviceController,LocalTime detectionTimeInit,LocalTime detectionTimeFinalize){
       //Se obtiene la hora actual y se verificaa si esta en el rango horario de deteccion
      if(db.movementSlotTime()){
            if(!deviceController.getDevice("movement").isActive()){ //Si el dispositivo no esta activo
                deviceController.getDevice("movement").setActive(true); //Se activa
            }
        }
        else{ //Si no se esta en el rango de deteccion
            if(deviceController.getDevice("movement").isActive()){  //Si esta activado
                deviceController.getDevice("movement").setActive(false); //Se desactiva
            }
        }
      
       
       
       
      /* 
       if(ReaderTime.isTimeSlot(detectionTimeInit, detectionTimeFinalize)){
            if(!deviceController.getDevice("movement").isActive()){ //Si el dispositivo no esta activo
                deviceController.getDevice("movement").setActive(true); //Se activa
            }
        }
        else{ //Si no se esta en el rango de deteccion
            if(deviceController.getDevice("movement").isActive()){  //Si esta activado
                deviceController.getDevice("movement").setActive(false); //Se desactiva
            }
        }*/
    }
}
