/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteDeviceFactory;

import ConcreteDevice.WebEventOpenDoor;
import ConcreteDevice.WebEventSendCode;
import Device.Device;
import Factory.DeviceFactory;

/**
 *
 * @author Compuj
 */
public class WebEventDeviceFactory extends DeviceFactory {

    @Override
    protected Device createDevice(String type) {
   
        if(type.equalsIgnoreCase("WebEventSendCode")){
            return new WebEventSendCode();
        }
        if(type.equalsIgnoreCase("WebEventOpenDoor")){
            return new WebEventOpenDoor();
        }
        return null;
    }
    
}
