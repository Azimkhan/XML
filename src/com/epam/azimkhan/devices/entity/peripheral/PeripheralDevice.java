package com.epam.azimkhan.devices.entity.peripheral;

import java.util.HashMap;
import com.epam.azimkhan.devices.entity.core.Device;

/**
 * Peripheral device
 */
public class PeripheralDevice extends Device{
	public enum DeviceGroup{
		MULTIMEDIA, INPUT, OUTPUT, STORAGE
	}
	
	private DeviceGroup deviceGroup;
	
	private HashMap<String, String> parameters = new HashMap<String, String>();

	
	public PeripheralDevice() {
		super();
	}

	public PeripheralDevice(String name, String manufacturer, String origin,
			int price, double powerConsumption, boolean critical,
			DeviceGroup deviceGroup) {
		super(name, manufacturer, origin, price, powerConsumption, critical);
		this.setDeviceGroup(deviceGroup);
	}

	/**
	 * @param key
	 * @param value
	 * @return 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public String setParameter(String key, String value) {
		return parameters.put(key, value);
	}

	/**
	 * @param key
	 * @return String
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public String getParameter(String key) {
		return parameters.get(key);
	}

	public DeviceGroup getDeviceGroup() {
		return deviceGroup;
	}

	public void setDeviceGroup(DeviceGroup deviceGroup) {
		this.deviceGroup = deviceGroup;
	}
	
	
	
	
}
