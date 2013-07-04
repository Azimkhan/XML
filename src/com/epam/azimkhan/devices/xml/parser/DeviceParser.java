package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.Device;

public abstract class DeviceParser {
	public enum DeviceField {
		NAME, ORIGIN, MANUFACTURER, PRICE, CRITICAL
	}

	protected Device device;

	public abstract void init();

	public boolean parseField(String name, String value) {

		if (name != null && value != null) {
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

		return false;
	}


	public abstract boolean canHandle(String type);

	public Device getDevice() {
		return device;
	}
}
