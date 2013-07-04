package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.CPU;
import com.epam.azimkhan.devices.entity.CPU.CPUSocket;

public class CPUParser extends DeviceParser{

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
			CPU cpu = (CPU) device;
			
			switch (cpuParameter) {
			case CACHE_SIZE:
				cpu.setCacheSize(Integer.parseInt(value));
				return true;
			case FREQUENCY:
				cpu.setFrequency(Integer.parseInt(value));
				//TODO regex
				return true;
			case NUMBER_OF_CORES:
				cpu.setNumberOfCores(Integer.parseInt(value));
				return true;
			case NUMBER_OF_THREADS:
				cpu.setNumberOfThreads(Integer.parseInt(value));
				return true;
			case SOCKET:
				cpu.setSocket(CPUSocket.valueOf(value.toUpperCase()));
				return true;
			default:
				return false;
			}
		
		}
		
		return false;
	}
	

	@Override
	public boolean canHandle(String type) {
		return type.toLowerCase().equals("cpu");
	}

	

}
