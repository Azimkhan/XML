package com.epam.azimkhan.devices.xml.sax.parser;

import com.epam.azimkhan.devices.entity.core.RandomAccessMemory;
import com.epam.azimkhan.devices.entity.core.RandomAccessMemory.RAMType;

public class RAMParser extends DeviceParser{

	public enum RAMParameter{
		MEMORY_SIZE, FREQUENCY, TYPE;
	}
	public RAMParser(){
		device = new RandomAccessMemory();
	}
	
	@Override
	public boolean parseParameter(String name, String value) {
		RAMParameter parameter = RAMParameter.valueOf(name.toUpperCase());
		RandomAccessMemory ram = (RandomAccessMemory) device;
		
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
