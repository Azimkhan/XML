package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.RAM;
import com.epam.azimkhan.devices.entity.RAM.RAMType;

public class RAMParser extends DeviceParser{

	public enum RAMParameter{
		MEMORY_SIZE, FREQUENCY, TYPE;
	}
	
	@Override
	public void init() {
		device = new RAM();
		
	}
	@Override
	public boolean parseParameter(String name, String value) {
		RAMParameter parameter = RAMParameter.valueOf(name.toUpperCase());
		RAM ram = (RAM) device;
		
		switch (parameter) {
		case MEMORY_SIZE:
			ram.setMemorySize(Integer.parseInt(value));
			return true;
		case FREQUENCY:
			ram.setFrequency(Integer.parseInt(value));
			return true;
		case TYPE:
			ram.setType(RAMType.valueOf(value.toUpperCase()));
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean canHandle(String type) {
		return type.toLowerCase().equals("ram");
	}

	

}
