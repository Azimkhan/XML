package com.epam.azimkhan.devices.entity;



/**
 * Peripheral device
 */
public class PeripheralDevice extends Device{
	/**
	 * Device groups
	 *
	 */
	public enum DeviceGroup{
		MULTIMEDIA, INPUT, OUTPUT, STORAGE
	}
	
	/**
	 * Device group
	 */
	private DeviceGroup deviceGroup;
	
	public PeripheralDevice() {
		super();
	}

	
	public PeripheralDevice(String name, String manufacturer, String origin,
			int price, boolean critical,
			DeviceGroup deviceGroup) {
		super(name, manufacturer, origin, price, critical);
		this.setDeviceGroup(deviceGroup);
	}

	/**
	 * get device group
	 * @return
	 */
	public DeviceGroup getDeviceGroup() {
		return deviceGroup;
	}

	/**
	 * set device group
	 * @param deviceGroup
	 */
	public void setDeviceGroup(DeviceGroup deviceGroup) {
		this.deviceGroup = deviceGroup;
	}
	
	
	
	
}
