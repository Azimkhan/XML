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

/**
 * DOM version
 */
public class DOMDeviceAnalyzer {
	
	//TODO string external.
	
	
	/**
	 * Singleton instance
	 */
	private static DOMDeviceAnalyzer instance = null;
	
	/**
	 * List of parsers
	 */
	private DeviceParserFactory parserFactory = new DeviceParserFactory();
	
	/**
	 * Current parser
	 */
	private DeviceParser currentParser;
	
	/**
	 * Singleton constructor
	 */
	
	public static final Logger logger = Logger.getRootLogger();
	
	private DOMDeviceAnalyzer(){
		super();
	}
	
	/**
	 * Retrieve instance
	 * @return DOM analyzer
	 */
	public static DOMDeviceAnalyzer getInstance(){
		if (null == instance){
			instance = new DOMDeviceAnalyzer();
		}
		
		return instance;
	}
	
	/**
	 * Build list of devices from Document Model
	 * @param rootElement
	 * @return list of device objects
	 * @throws ParseException
	 */
	public List<Device> buildList(Element rootElement) throws ParseException{
	
		LinkedList<Device> devices = new LinkedList<>();
		
		NodeList childern = rootElement.getChildNodes();
		
		logger.info("Document started");
		for (int i = 0; i < childern.getLength(); i++){
			Node node = childern.item(i);
			if (node.getNodeName().equals("device")){
				currentParser = null;
				Device device = parseDevice(node);
				devices.add(device);
			}
		}
		logger.info("Reached the end of the document");
		return devices;
		
	}
	
	/**
	 * Parse particular device
	 * @param deviceNode
	 * @throws ParseException
	 */
	private Device parseDevice(Node deviceNode) throws ParseException{
		
		NodeList children = deviceNode.getChildNodes();
		String type = nodeAttribute(deviceNode, "type");
		
		findDeviceParser(type);
		
		for (int i = 0; i < children.getLength(); i++){
			Node element = children.item(i);
			String name = element.getNodeName();
			
			if (element.getNodeType() == Node.ELEMENT_NODE){
				if (name != "parameters"){
					
					String value = nodeText(element);
					parseField(name, value);
					
				} else{
					parseParameters(element.getChildNodes());
				}
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
			logger.info(String.format("Device[%s] found. Searching for parser...", type));
			// loop through parsers
			currentParser = parserFactory.getParser(type);
			if (currentParser != null) {
				logger.info(String.format("Parser found: %s", currentParser.getClass().getSimpleName()));
			} else{
				throw new ParseException(String.format(
						"Unable to parse device with type '%s'", type));
			}
		} else {
			throw new ParseException(String.format(
					"Attribute '%s' for device is required'", "type"));
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
		logger.info(String.format("Parsing field name='%s', value='%s'", name, value ));
		
		if (!result){
			throw new ParseException(String.format("Unable to parse field with name '%s'", name));
		}
	}
	/**
	 * Parse device parameter
	 * @param parameters list of parameters to be parsed
	 * @throws ParseException
	 */
	private void parseParameters(NodeList parameters) throws ParseException{
		
		for(int i = 0; i < parameters.getLength(); i++){
			Node parameter = parameters.item(i);
			
			if (parameter.getNodeType() == Node.ELEMENT_NODE){
			String name = nodeAttribute(parameter, "name");
			String value = nodeText(parameter);
			
			logger.info(String.format("Parsing parameter name='%s', value='%s'", name, value ));
			
			/*
				boolean result = currentParser.parseParameter(name, value);
				
				if (!result){
					throw new ParseException(String.format("Unable to parse paramter with name'%s'", name));
				}
				*/
			} 
			
		}
			
	}
	
	/**
	 * Get attribute value if exists
	 * @param node
	 * @param attributeName
	 * @return attribute value
	 */
	private String nodeAttribute(Node node, String attributeName){
		
		Node attribute = node.getAttributes().getNamedItem(attributeName);
		
		if (attribute != null){
			return attribute.getNodeValue();
		}
		
		return null;
		
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
