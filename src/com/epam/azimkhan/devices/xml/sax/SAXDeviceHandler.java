package com.epam.azimkhan.devices.xml.sax;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.BuilderProperties;
import com.epam.azimkhan.devices.xml.parser.DeviceParser;
import com.epam.azimkhan.devices.xml.parser.DeviceParserFactory;
import com.epam.azimkhan.devices.xml.parser.Messages;

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
	/**
	 * Error handling
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}
	
	/**
	 * Document start
	 */
	@Override
	public void startDocument() throws SAXException {
		logger.info(Messages.getString("document_start"));  //$NON-NLS-1$
	}
	
	/**
	 * Document end
	 */
	@Override
	public void endDocument() throws SAXException {
		logger.info(Messages.getString("document_end"));  //$NON-NLS-1$
	}

	/**
	 * On element start
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (!localName.equals(BuilderProperties.getString("root_element"))) { //$NON-NLS-1$
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
		
		if (!localName.equals(BuilderProperties.getString("root_element"))) { //$NON-NLS-1$
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
			logger.info(String.format(Messages.getString("parsing_field"),  //$NON-NLS-1$
					lastFieldName, value));
			if (currentParser.parseField(lastFieldName, value)) {
				lastFieldName = null;
			} else {
				throw new SAXException(String.format(
						Messages.getString("unable_to_parse_field"), lastFieldName));  //$NON-NLS-1$
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
					Messages.getString("device_found"), type));  //$NON-NLS-1$

			currentParser = parserFactory.getParser(type);

			if (currentParser != null) {
				logger.info(String.format(Messages.getString("parser_found"), currentParser  //$NON-NLS-1$
						.getClass().getSimpleName()));
				currentParser.init();
			} else {
				throw new SAXException(String.format(
						Messages.getString("unable_to_parse_device"), type));  //$NON-NLS-1$
			}
		} else {
			throw new SAXException(String.format(
					Messages.getString("empty_device_name"))); //$NON-NLS-1$
		}
	}

	/**
	 * return parsed devices
	 * @return
	 */
	public List<Device> getDevices() {
		return devices;
	}

}
