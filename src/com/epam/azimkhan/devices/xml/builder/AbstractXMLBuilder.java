package com.epam.azimkhan.devices.xml.builder;

import java.util.List;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public abstract class AbstractXMLBuilder {
	public static final String JAXP_SCHEMA_LANGUAGE =
	        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    
	public static final String W3C_XML_SCHEMA =
	        "http://www.w3.org/2001/XMLSchema";
	    
	
	public abstract List<Device> build(String filename) throws BuilderException;

}
