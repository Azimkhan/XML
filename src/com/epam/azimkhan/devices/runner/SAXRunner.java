/**
 * 
 */
package com.epam.azimkhan.devices.runner;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.sax.SAXDeviceHandler;

/**
 * SAX version
 */
public class SAXRunner {

	public static final Logger logger = Logger.getRootLogger();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SAXDeviceHandler handler = new SAXDeviceHandler();
			reader.setContentHandler(handler);
			reader.parse(filename);
			List<Device> devices = handler.getDevices();
			
			
		} catch (SAXException e) {
			logger.error(String.format("Parse exception: %s", e.getMessage()));
		} catch (IOException e) {
			logger.error(String.format("Input/output error: %s", e.getMessage()));
		}

	}

}
