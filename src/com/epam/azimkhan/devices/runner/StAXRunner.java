package com.epam.azimkhan.devices.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;
import com.epam.azimkhan.devices.xml.stax.StAXDeviceAnalyzer;

public class StAXRunner {
public static final Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args){
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		
		
		XMLInputFactory factory = XMLInputFactory.newFactory();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filename));
			XMLStreamReader reader = factory.createXMLStreamReader(fis);
			List<Device> devices = StAXDeviceAnalyzer.buildList(reader);
			
		} catch (FileNotFoundException e) {
			// TODO logging
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO logging
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
