package com.epam.azimkhan.devices.xml.sax;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.parser.DeviceParser;
import com.epam.azimkhan.devices.xml.parser.DeviceParserFactory;

/**
 * "Simple API for XML Processing" handler
 */
public class SAXDeviceHandler extends DefaultHandler {

	// TODO string external.

	public static final Logger logger = Logger.getRootLogger();
	/**
	 * List of parsed devices
	 */
	private LinkedList<Device> devices = new LinkedList<>();

	/**
	 * Device parser factory
	 */
	private DeviceParserFactory parserFactory = new DeviceParserFactory();

	/**
	 * Last field name, e.g. <name>, <origin> and etc.
	 */
	private String lastFieldName = null;

	/**
	 * Current device parser
	 */
	private DeviceParser currentParser = null;

	public SAXDeviceHandler() {
		super();
	}

	@Override
	public void startDocument() throws SAXException {
		logger.info("Document started");
	}

	@Override
	public void endDocument() throws SAXException {
		logger.info("Reached the end of the document");
	}

	/**
	 * On element start
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (!localName.equals("devices")) {
			if (currentParser == null) {
				findDeviceParser(localName);
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
		
		if (!localName.equals("devices")){
			if (parserFactory.hasDevice(localName)) {
				devices.add(currentParser.getDevice());
				currentParser = null;
			}
		}
		
	}

	/**
	 * Handle tag characters
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String value = new String(ch, start, length).trim();

		if (lastFieldName != null) {
			logger.info(String.format("Parsing field name='%s', value='%s'",
					lastFieldName, value));
			if (currentParser.parseField(lastFieldName, value)) {
				lastFieldName = null;
			} else {
				throw new SAXException(String.format(
						"Unable to parse field with name '%s'", lastFieldName));
			}
		}
	}

	/**
	 * Finds parser
	 * 
	 * @param attributes
	 * @throws SAXException
	 */
	private void findDeviceParser(String type) throws SAXException {

		if (type != null) {
			logger.info(String.format(
					"Device[%s] found. Searching for parser...", type));

			currentParser = parserFactory.getParser(type);

			if (currentParser != null) {
				logger.info(String.format("Parser found: %s", currentParser
						.getClass().getSimpleName()));
				currentParser.init();
			} else {
				throw new SAXException(String.format(
						"Unable to parse device with type '%s'", type));
			}
		} else {
			throw new SAXException(String.format(
					"Attribute '%s' for device is required'", "type"));
		}
	}

	/**
	 * return parsed devices
	 * 
	 * @return
	 */
	public List<Device> getDevices() {
		return devices;
	}

}
