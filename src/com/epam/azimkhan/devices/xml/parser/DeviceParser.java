package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.util.EnumUtils;

public abstract class DeviceParser {
	enum DeviceField {
		NAME {
			@Override
			public boolean handle(Device device, String value) {
				device.setName(value);
				return true;
			}
		}, ORIGIN {
			@Override
			public boolean handle(Device device, String value) {
				device.setOrigin(value);
				return true;
			}
		}, MANUFACTURER {
			@Override
			public boolean handle(Device device, String value) {
				device.setManufacturer(value);
				return true;
			}
		}, PRICE {
			@Override
			public boolean handle(Device device, String value) {
				device.setPrice(Integer.parseInt(value));
				//TODO parse
				return true;
			}
		}, CRITICAL {
			@Override
			public boolean handle(Device device, String value) {
				device.setCritical(Boolean.parseBoolean(value));
				return true;
				//TODO catch 
			}
		};
		
		public abstract boolean handle(Device device, String value);
	}

	public abstract Device getDevice();
	
	public abstract void init();

	public boolean parseField(String name, String value) {

		if (name != null && value != null) {
			
			DeviceField filed = EnumUtils.lookup(DeviceField.class, name);
			
			if (filed != null){
				return filed.handle(getDevice(), value);
			} else{
				return false;
			}
		}

		return false;
	}

}
