package com.epam.azimkhan.devices.entity;

import java.io.Serializable;



/**
 * Peripheral device
 */
public class PeripheralDevice extends Device implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2709784830043668825L;


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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((deviceGroup == null) ? 0 : deviceGroup.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeripheralDevice other = (PeripheralDevice) obj;
		if (deviceGroup != other.deviceGroup)
			return false;
		return true;
	}
	
	
	
	
}
