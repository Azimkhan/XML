package com.epam.azimkhan.devices.xml.builder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.io.FileInput;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public abstract class AbstractXMLBuilder {
	public static final String JAXP_SCHEMA_LANGUAGE =
	        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    
	public static final String W3C_XML_SCHEMA =
	        "http://www.w3.org/2001/XMLSchema";
	    
	public static final Logger logger = Logger.getRootLogger();
	
	public List<Device> buildList(String filename) throws BuilderException{
		
		List<Device> devices = null;
		
		String content;
		try {
			content = FileInput.readContent(filename);
			ByteArrayInputStream instraem = new ByteArrayInputStream(content.getBytes());
			devices = build(instraem);
			
			return devices;
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
		
		
		
		
	}

	protected abstract List<Device> build(InputStream file) throws BuilderException;
}
