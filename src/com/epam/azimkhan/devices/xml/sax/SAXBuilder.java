package com.epam.azimkhan.devices.xml.sax;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractXMLBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

/**
 * SAX device builder
 */
public class SAXBuilder extends AbstractXMLBuilder {
	static final String JAXP_SCHEMA_LANGUAGE =
	        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    
	    static final String W3C_XML_SCHEMA =
	        "http://www.w3.org/2001/XMLSchema";
	    
	@Override
	public List<Device> build(String filename) throws BuilderException {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		
		XMLReader reader;
		try {
			SAXParser parser = factory.newSAXParser();
			SAXDeviceHandler handler = new SAXDeviceHandler();
			
			parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);			
			reader = parser.getXMLReader();
			reader.setErrorHandler(handler);
			reader.setContentHandler(handler);
			reader.parse(filename);
			
			return handler.getDevices();
		} catch (SAXException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (ParserConfigurationException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
	}

}
