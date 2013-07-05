package com.epam.azimkhan.devices.runner;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractXMLBuilder;
import com.epam.azimkhan.devices.xml.builder.DeviceXMLBuilderFactory;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public class Runner {

	public static final Logger logger  = Logger.getRootLogger();
	
	public static void main(String[] args){
		DeviceXMLBuilderFactory factory = new DeviceXMLBuilderFactory();
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		
		try {
			AbstractXMLBuilder builder = factory.createBuilder("sax");
			List<Device> devices = builder.build(filename);
			System.out.println(devices);
		} catch (BuilderException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
