/**
 * 
 */
package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.entity.PeripheralDevice;
import com.epam.azimkhan.devices.entity.PeripheralDevice.DeviceGroup;
import com.epam.azimkhan.devices.util.EnumUtils;

/**
 * Peripheral device parser
 *
 */
public class PeripheralParser extends DeviceParser {

	/**
	 * peripheral field handler
	 */
	enum PeripheralFieldHandler {
		GROUP {
			@Override
			public boolean handle(PeripheralDevice device, String value) {
				DeviceGroup group = EnumUtils.lookup(DeviceGroup.class, value);
				if (group != null){
					device.setDeviceGroup(group);
					return true;
				} else {
					return true;
				}
			}
		};
		
		public abstract boolean handle(PeripheralDevice device, String value);
	}
	private PeripheralDevice device;
	
	/**
	 * get device
	 */
	@Override
	public Device getDevice() {
		return device;
	}

	/**
	 * init
	 */
	@Override
	public void init() {
		device = new PeripheralDevice();

	}
	
	/**
	 * parse peripheral field
	 */
	@Override
	public boolean parseField(String name, String value){
		if (super.parseField(name, value)){
			return true;
		}
		
		if (name != null && value != null){
			PeripheralFieldHandler handler = EnumUtils.lookup(PeripheralFieldHandler.class, name);
			if (handler != null){
				return handler.handle(device, value);
			} else{
				return false;
			}
		}
		
		return false;
	}

}
