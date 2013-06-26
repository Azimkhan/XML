package com.epam.azimkhan.devices.xml.sax;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.azimkhan.devices.entity.core.Device;
import com.epam.azimkhan.devices.xml.sax.parser.CPUParser;
import com.epam.azimkhan.devices.xml.sax.parser.DeviceParser;
import com.epam.azimkhan.devices.xml.sax.parser.RAMParser;

/**
 * SAX parser for devices
 */
public class SAXContentHandler extends DefaultHandler{
	
	
	private LinkedList<Device> devices = new LinkedList<>();
	private LinkedList<DeviceParser> parsers = new LinkedList<>();
	
	private String lastParameterName = null;
	private String lastFieldName = null;
	private DeviceParser currentParser = null;
			
	public SAXContentHandler(){
		parsers.add(new CPUParser());
		parsers.add(new RAMParser());
	}
	
	@Override
	public void startDocument() throws SAXException {
		//TODO logging
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO logging
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
			// TODO separate method
			if (localName.equals("device")){
				String type = attributes.getValue("type");
				if (type != null){
					//loop through parsers
					for(DeviceParser parser : parsers){
						//if parser found
						if (parser.canHandle(type)){
							currentParser = parser;
							break;
						}
					}
					
					if (currentParser == null){
						throw new SAXException(String.format("Unable to parse device with type '%s'", type));
					}
			 	} else{
					throw new SAXException("attribute type is required");
				}
			} else {
				// TODO separate method
				if (!localName.equals("parameters") && !localName.equals("devices")){
					if (localName.equals("parameter")){
						String parameterName = attributes.getValue("name");
						if (parameterName != null){
							lastParameterName = parameterName;
						}
					} else{
						lastFieldName = localName;
					}
				}
			}
			
			
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
			if(localName.equals("device")){
				devices.add(currentParser.getDevice());
				currentParser = null;
			}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		String value = new String(ch, start, length);
		
		if (lastParameterName != null){
			if (currentParser.parseParameter(lastParameterName, value)){
				lastParameterName = null;
			} else {
				throw new SAXException(String.format("Unable to parse paramter '%s'",lastParameterName));
			}
		}
		
		if (lastFieldName != null){
			if (currentParser.parseAttribute(lastFieldName, value)){
				lastFieldName = null;
			} else {
				throw new SAXException(String.format("Unable to parse field '%s'",lastParameterName));
			}
		}
	}

	public List<Device> getDevices() {
		return devices;
	}
	
	
	
}
