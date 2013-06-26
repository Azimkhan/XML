package com.epam.azimkhan.devices.xml.sax.parser;

import com.epam.azimkhan.devices.entity.core.Device;

public abstract class DeviceParser {
	public enum DeviceField{
		NAME, ORIGIN, MANUFACTURER, PRICE, CRITICAL
	}
	
	protected Device device;
	
	public boolean parseAttribute(String name, String value){
	
		DeviceField filed = DeviceField.valueOf(name.toUpperCase());
		
		switch (filed) {
		case NAME:
			device.setName(value);
			return true;
		case CRITICAL:
			device.setCritical(Boolean.valueOf(value));
			return true;
		case MANUFACTURER:
			device.setManufacturer(value);
			return true;
		case ORIGIN:
			device.setOrigin(value);
			return true;
		case PRICE: 
			device.setPrice(Integer.parseInt(value));
			return true;
		default:
			return false;
		}
	}
	
	public abstract boolean parseParameter(String name, String value);
	public abstract boolean canHandle(String type);
	
	public Device getDevice(){
		return device;
	}
}
