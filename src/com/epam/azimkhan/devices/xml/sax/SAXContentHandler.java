package com.epam.azimkhan.devices.xml.sax;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.azimkhan.devices.entity.core.Device;
import com.epam.azimkhan.devices.xml.parser.CPUParser;
import com.epam.azimkhan.devices.xml.parser.DeviceParser;
import com.epam.azimkhan.devices.xml.parser.RAMParser;

/**
 * "Simple API for XML Processing" handler
 */
public class SAXContentHandler extends DefaultHandler {

	/**
	 * List of parsed devices
	 */
	private LinkedList<Device> devices = new LinkedList<>();

	/**
	 * List of parsers
	 */
	private LinkedList<DeviceParser> parsers = new LinkedList<>();

	/**
	 * Last parameter name
	 */
	private String lastParameterName = null;

	/**
	 * Last field name, e.g. <name>, <origin> and etc.
	 */
	private String lastFieldName = null;

	/**
	 * Current device parser
	 */
	private DeviceParser currentParser = null;

	public SAXContentHandler() {
		parsers.add(new CPUParser());
		parsers.add(new RAMParser());
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO logging
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO logging
	}

	/**
	 * On element start
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// If is device
		if (localName.equals("device")) {
			findDeviceParser(attributes);
		} else if (!localName.equals("parameters")
				&& !localName.equals("devices")){
			// otherwise
			if (localName.equals("parameter")) {
				remeberParameterName(attributes);
			} else {
				lastFieldName = localName;
			}

		}

	}

	/**
	 * On element end
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("device")) {
			devices.add(currentParser.getDevice());
			currentParser = null;
		}
	}

	/**
	 * Handle tag characters
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String value = new String(ch, start, length).trim();

		if (lastParameterName != null) {
			if (currentParser.parseParameter(lastParameterName, value)) {
				lastParameterName = null;
			} else {
				throw new SAXException(String.format(
						"Unable to parse paramter '%s'", lastParameterName));
			}
		}

		if (lastFieldName != null) {
			if (currentParser.parseAttribute(lastFieldName, value)) {
				lastFieldName = null;
			} else {
				throw new SAXException(String.format(
						"Unable to parse field '%s'", lastParameterName));
			}
		}
	}

	/**
	 * Finds parser
	 * 
	 * @param attributes
	 * @throws SAXException
	 */
	public void findDeviceParser(Attributes attributes) throws SAXException {

		String type = attributes.getValue("type");
		if (type != null) {
			// loop through parsers
			for (DeviceParser parser : parsers) {
				// if parser found
				if (parser.canHandle(type)) {
					currentParser = parser;
					parser.init();
					break;
				}
			}

			if (currentParser == null) {
				throw new SAXException(String.format(
						"Unable to parse device with type '%s'", type));
			}
		} else {
			throw new SAXException(String.format(
					"Attribute '%s' for device is required'", "type"));
		}
	}

	public void remeberParameterName(Attributes attributes) throws SAXException {
		String parameterName = attributes.getValue("name");
		if (parameterName != null) {
			lastParameterName = parameterName;
		} else {
			throw new SAXException(String.format(
					"Attribute '%s' for parameter is required'", "name"));
		}
	}

	public List<Device> getDevices() {
		return devices;
	}

}
