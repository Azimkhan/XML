package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.entity.RAM;
import com.epam.azimkhan.devices.entity.RAM.RAMType;
import com.epam.azimkhan.devices.util.EnumUtils;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

public class RAMParser extends DeviceParser{

	
	enum RAMFieldHandler{
		MEMORY_SIZE {
			@Override
			public boolean handle(RAM device, String value) {
				try {
					device.setMemorySize(FieldParser.parseSize(value));
					return true;
				} catch (ParseException e) {
					return false;
				}
			}
		}, FREQUENCY {
			@Override
			public boolean handle(RAM device, String value) {
				try {
					device.setFrequency(FieldParser.parseFrequency(value));
					return true;
				} catch (ParseException e) {
					return false;
				}
			}
		}, TYPE {
			@Override
			public boolean handle(RAM device, String value) {
				RAMType type = EnumUtils.lookup(RAMType.class, value);
				if (type != null){
					device.setType(type);
					return true;
				} else{
					return false;
				}
			}
		};

		public abstract boolean handle(RAM device, String value);
	}
	
	private RAM device;
	
	
	@Override
	public void init() {
		device = new RAM();
		
	}
	
	
	@Override
	public boolean parseField(String name, String value) {
		if (super.parseField(name, value)){
			return true;
		}
		
		if(name != null && value != null){
			RAMFieldHandler handler = EnumUtils.lookup(RAMFieldHandler.class, name);
			if (handler != null){
				return handler.handle(device, value);
			} else{
				return false;
			}
		}
		
		return false;
		
	}


	@Override
	public Device getDevice() {
		return device;
	}

	

}
