package com.epam.azimkhan.devices.runner;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.dom.DOMAnalyzer;
import com.epam.azimkhan.devices.xml.parser.DeviceParserFactory;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

/**
 * DOM version
 */
public class DOMRunner {
	
	public static final Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args){
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(filename);
			DeviceParserFactory.getParsers();
			DOMAnalyzer analyzer = DOMAnalyzer.getInstance();
			List<Device> devices = analyzer.buildList(document.getDocumentElement());
			
			
			
		} catch (ParserConfigurationException e) {
			logger.error(String.format("Parser configuration error: %s", e.getMessage()));
		} catch (SAXException e) {
			logger.error(String.format("XML processing error: %s", e.getMessage()));
		} catch (IOException e) {
			logger.error(String.format("Input/output error: %s", e.getMessage()));
		} catch (ParseException e) {
			logger.error(String.format("Parse error: %s", e.getMessage()));
		}
	}

}
