package com.epam.azimkhan.devices.xml.sax;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractXMLBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

/**
 * SAX device builder
 */
public class SAXBuilder extends AbstractXMLBuilder {
	
	@Override
	public List<Device> build(InputStream instream) throws BuilderException {
		
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
			reader.parse(new InputSource(instream));
			
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
