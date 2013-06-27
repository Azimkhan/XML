package com.epam.azimkhan.devices.runner;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.azimkhan.devices.entity.core.Device;
import com.epam.azimkhan.devices.xml.dom.DOMAnalyzer;
import com.epam.azimkhan.devices.xml.parser.DeviceParserFactory;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

/**
 * DOM version
 */
public class DOMRunner {
	
	public static void main(String[] args){
		
		String filename = (args.length > 0) ? args[0] : "data.xml";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(filename);
			DeviceParserFactory.getParsers();
			DOMAnalyzer analyzer = DOMAnalyzer.getInstance();
			List<Device> devicesYoYo = analyzer.buildList(document.getDocumentElement());
			for(Device d :devicesYoYo ){
				System.out.println(d.getName());
				System.out.println(d.getOrigin());
				System.out.println(d.getManufacturer());
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO logging
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO logging
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO logging
			e.printStackTrace();
		}
	}

}
