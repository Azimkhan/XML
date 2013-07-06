package com.epam.azimkhan.devices.xml.parser.factory;

import com.epam.azimkhan.devices.xml.parser.component.CPUParser;
import com.epam.azimkhan.devices.xml.parser.component.DeviceParser;
import com.epam.azimkhan.devices.xml.parser.component.InternalStorageParser;
import com.epam.azimkhan.devices.xml.parser.component.PeripheralParser;
import com.epam.azimkhan.devices.xml.parser.component.RAMParser;


/**
 * Device parser factory
 * 
 * @version 1.0
 */
public class DeviceParserFactory {

	public enum Parser{
		CPU {
			@Override
			public DeviceParser getParser() {
				return new CPUParser();
			}
		}, RAM {
			@Override
			public DeviceParser getParser() {
				return new RAMParser();
			}
		}, STORAGE {
			@Override
			public DeviceParser getParser() {
				return new InternalStorageParser();
			}
		}, PERIPHERAL {
			@Override
			public DeviceParser getParser() {
				return new PeripheralParser();
			}
		};
		
		public abstract DeviceParser getParser();
		
	}
	
	/**
	 * Check if device is supported
	 * @param name
	 * @return
	 */
	public boolean hasDevice(String name){
		
		if (name != null){
			for(Parser p : Parser.values()){
				if (p.name().equals(name.toUpperCase()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Get device parser
	 * @param name
	 * @return
	 */
	public DeviceParser getParser(String name){
		if (hasDevice(name)){
			return Parser.valueOf(name.toUpperCase()).getParser();
		}
		
		return null;
	}

	
}
