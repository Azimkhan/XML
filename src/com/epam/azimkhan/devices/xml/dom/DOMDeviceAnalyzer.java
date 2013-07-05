package com.epam.azimkhan.devices.xml.dom;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.exception.ParseException;
import com.epam.azimkhan.devices.xml.parser.DeviceParser;
import com.epam.azimkhan.devices.xml.parser.DeviceParserFactory;
import com.epam.azimkhan.devices.xml.parser.Messages;

/**
 * DOM version
 */
public enum DOMDeviceAnalyzer {
	
	INSTANCE;
	
	//TODO string external.
	
	
	
	/**
	 * List of parsers
	 */
	private DeviceParserFactory parserFactory = new DeviceParserFactory();
	
	/**
	 * Current parser
	 */
	private DeviceParser currentParser;
	
	
	public static final Logger logger = Logger.getRootLogger();
	
	
	/**
	 * Build list of devices from Document Model
	 * @param rootElement
	 * @return list of device objects
	 * @throws ParseException
	 */
	public List<Device> buildList(Element rootElement) throws ParseException{
	
		LinkedList<Device> devices = new LinkedList<>();
		
		NodeList childern = rootElement.getChildNodes();
		
		logger.info(Messages.getString("document_start"));
		for (int i = 0; i < childern.getLength(); i++){
			Node node = childern.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				currentParser = null;
				Device device = parseDevice(node);
				devices.add(device);
			}
			
		}
		logger.info(Messages.getString("document_end"));
		return devices;
		
	}
	
	/**
	 * Parse particular device
	 * @param deviceNode
	 * @throws ParseException
	 */
	private Device parseDevice(Node deviceNode) throws ParseException{
		
		NodeList children = deviceNode.getChildNodes();
		
		findDeviceParser(deviceNode.getNodeName());
		
		for (int i = 0; i < children.getLength(); i++){
			Node element = children.item(i);
			String name = element.getNodeName();
			
			if (element.getNodeType() == Node.ELEMENT_NODE){
				
					
					String value = nodeText(element);
					parseField(name, value);
			}
		}
		
		return currentParser.getDevice();
		
	}
	
	/**
	 * Find parser
	 * @param type node containing type value
	 * @throws SAXException
	 */
	private void findDeviceParser(String type) throws ParseException {

		if (type != null) {
			logger.info(String.format(
					Messages.getString("device_found"), type));  //$NON-NLS-1$

			currentParser = parserFactory.getParser(type);

			if (currentParser != null) {
				logger.info(String.format(Messages.getString("parser_found"), currentParser  //$NON-NLS-1$
						.getClass().getSimpleName()));
				currentParser.init();
			} else {
				throw new ParseException(String.format(
						Messages.getString("unable_to_parse_device"), type));  //$NON-NLS-1$
			}
		} else {
			throw new ParseException(String.format(
					Messages.getString("empty_device_name"))); //$NON-NLS-1$
		}
	}
	
	/**
	 * Parse single field
	 * @param name
	 * @param value
	 * @throws ParseException
	 */
	private void parseField(String name, String value) throws ParseException{
		boolean result = currentParser.parseField(name, value);
		logger.info(String.format(Messages.getString("parsing_field"), name, value ));
		
		if (!result){
			throw new ParseException(String.format(Messages.getString("unable_to_parse_field"), name));
		}
	}
	
	/**
	 * Extract text from node
	 * @param node
	 * @return node text
	 */
	private String nodeText(Node node){
		Node valueNode = node.getChildNodes().item(0);
		
		if (valueNode != null && valueNode.getNodeType() == Node.TEXT_NODE){
			return valueNode.getNodeValue();
		}
		
		return null;
	}

}
