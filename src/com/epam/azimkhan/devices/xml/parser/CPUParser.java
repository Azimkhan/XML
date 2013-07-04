package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.CPU;
import com.epam.azimkhan.devices.entity.CPU.CPUSocket;
import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.util.EnumUtils;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

public class CPUParser extends DeviceParser{

	private CPU device;
	
	public enum CPUParameter{
		FREQUENCY, NUMBER_OF_CORES, NUMBER_OF_THREADS, CACHE_SIZE, SOCKET
	}
	
	@Override
	public void init() {
		this.device = new CPU();
		
	}
	
	@Override
	public boolean parseField(String name, String value){
		if (super.parseField(name, value)){
			return true;
		}
		
		if (name != null && value != null){
			
			CPUParameter cpuParameter = CPUParameter.valueOf(name.toUpperCase());
			
			
			switch (cpuParameter) {
			case CACHE_SIZE:
				device.setCacheSize(Integer.parseInt(value));
				return true;
			case FREQUENCY:
				try {
					device.setFrequency(FieldParser.parseFrequency(value));
					return true;
				} catch (ParseException e) {
					return false;
				}
			case NUMBER_OF_CORES:
				device.setNumberOfCores(Integer.parseInt(value));
				return true;
			case NUMBER_OF_THREADS:
				device.setNumberOfThreads(Integer.parseInt(value));
				return true;
			case SOCKET:
				CPUSocket socket = EnumUtils.lookup(CPUSocket.class, value);
				if (socket != null){
					device.setSocket(socket);
					return true;
				} else{
					return false;
				}
			default:
				return false;
			}
		
		}
		
		return false;
	}
	

	@Override
	public Device getDevice(){
		return device;
	}
	

}
