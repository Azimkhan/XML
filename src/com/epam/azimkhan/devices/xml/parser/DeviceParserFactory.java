package com.epam.azimkhan.devices.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Device parser factory
 * @version 1.0
 */
public class DeviceParserFactory {
	
	public static final Logger logger = Logger.getRootLogger();
	
	
	/**
	 * Private constuctor
	 */
	private DeviceParserFactory() {
		super();
	}

	/**
	 * Retrieve a set of parsers
	 */
	public static List<DeviceParser> getParsers() {
		List<DeviceParser> parsers = new LinkedList<>();
		
		boolean loaded = loadFromFile(parsers);
		
		if (!loaded){
			parsers = getDefaultSet();
		}
		
		return parsers;

	}

	/**
	 * Try to load from .properties file
	 * The "parser.properties" must be located in class path
	 * Each line must contain a full class name of a parser
	 * For example: "cpu = org.developer.xml.parser.RAMParser"
	 * @param parsers
	 * @return
	 */
	private static boolean loadFromFile(List<DeviceParser> parsers) {
		String classpath = System.getProperty("java.class.path");
		String filename = classpath + "/parsers.properties";
		File file = new File(filename);
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);

			if (file.exists()) {
				Properties properties = new Properties();
				properties.load(fis);
				
				Enumeration<Object> e = properties.elements();
				
				while(e.hasMoreElements()){
					String className = e.nextElement().toString();
					DeviceParser deviceParser = (DeviceParser) Class.forName(className).newInstance();
					parsers.add(deviceParser);
				}
				
				return true;
				

			}
		} catch (FileNotFoundException e) {
			// TODO logging
			e.printStackTrace();
		} catch (IOException e) {
			// TODO logging
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			if (null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO logging
					e.printStackTrace();
				}
			}
		}

		return false;
	}
	
	/**
	 * Get parsed objects
	 * @return list of devices
	 */
	private static List<DeviceParser> getDefaultSet(){
		LinkedList<DeviceParser> parsers = new LinkedList<DeviceParser>();
		parsers.add(new CPUParser());
		parsers.add(new RAMParser());
		
		return parsers;
	}
}