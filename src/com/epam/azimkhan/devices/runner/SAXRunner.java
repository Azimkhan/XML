/**
 * 
 */
package com.epam.azimkhan.devices.runner;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.azimkhan.devices.entity.core.Device;
import com.epam.azimkhan.devices.xml.sax.SAXContentHandler;

/**
 * SAX version
 */
public class SAXRunner {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SAXContentHandler handler = new SAXContentHandler();
			reader.setContentHandler(handler);
			reader.parse(filename);
			List<Device> devicesYoYo = handler.getDevices();
			for(Device d :devicesYoYo ){
				System.out.println(d.getName());
				System.out.println(d.getOrigin());
				System.out.println(d.getManufacturer());
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
			//TODO Logging
		} catch (IOException e) {
			e.printStackTrace();
			//TODO logging
		}

	}

}
