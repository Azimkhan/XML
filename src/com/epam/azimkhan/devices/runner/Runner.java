package com.epam.azimkhan.devices.runner;

import java.util.List;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractBuilder;
import com.epam.azimkhan.devices.xml.builder.DeviceBuilderFactory;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public class Runner {

	public static void main(String[] args){
		DeviceBuilderFactory factory = new DeviceBuilderFactory();
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		
		try {
			AbstractBuilder builder = factory.createBuilder("sax");
			List<Device> devices = builder.build(filename);
			System.out.println(devices);
		} catch (BuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
