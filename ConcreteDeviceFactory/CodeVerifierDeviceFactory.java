/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcreteDeviceFactory;

import ConcreteDevice.CodeVerifier;
import Device.Device;
import Factory.DeviceFactory;

/**
 *
 * @author Compuj
 */
public class CodeVerifierDeviceFactory extends DeviceFactory {

    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("CodeVerifier")){
            return new CodeVerifier();
        }
        return null;
    }
    
}
