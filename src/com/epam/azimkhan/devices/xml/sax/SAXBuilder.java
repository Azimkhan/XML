package com.epam.azimkhan.devices.xml.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public class SAXBuilder extends AbstractBuilder {

	@Override
	public List<Device> build(String filename) throws BuilderException {
		
		XMLReader reader;
		try {
			reader = XMLReaderFactory.createXMLReader();

			SAXDeviceHandler handler = new SAXDeviceHandler();
			reader.setContentHandler(handler);
			reader.parse(filename);
			
			return handler.getDevices();
		} catch (SAXException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
	}

}
