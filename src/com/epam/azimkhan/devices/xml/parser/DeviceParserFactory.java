package com.epam.azimkhan.devices.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * Device parser factory
 * 
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

		if (!loaded) {
			parsers = getDefaultSet();
		}

		return parsers;

	}

	/**
	 * Try to load from .properties file The "parser.properties" must be located
	 * in class path Each line must contain a full class name of a parser For
	 * example: "cpu = org.developer.xml.parser.RAMParser"
	 * 
	 * @param parsers
	 * @return
	 */
	private static boolean loadFromFile(List<DeviceParser> parsers) {

		FileInputStream fis = null;
		try {

			File file = findPropertiesFile();

			if (file.exists()) {

				fis = new FileInputStream(file);
				Properties properties = new Properties();
				properties.load(fis);

				Enumeration<Object> e = properties.elements();

				while (e.hasMoreElements()) {
					String className = e.nextElement().toString();
					DeviceParser deviceParser = (DeviceParser) Class.forName(
							className).newInstance();
					parsers.add(deviceParser);
				}

				return true;

			}

		} catch (FileNotFoundException e) {
			logger.error(String.format("File error: %s", e.getMessage()));
		} catch (IOException e) {
			logger.error(String.format("Input/output error: %s", e.getMessage()));
		} catch (InstantiationException e) {
			logger.error(String.format("Unable to instantiate class: %s",
					e.getMessage()));
		} catch (IllegalAccessException e) {
			logger.error(String.format("Class access error: %s", e.getMessage()));
		} catch (ClassNotFoundException e) {
			logger.error(String.format("Class not found: %s", e.getMessage()));
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(String
							.format("Failed to close properties file: %s"));
				}
			}
		}

		return false;
	}

	/**
	 * Find properties file in class path
	 * @return properties file
	 */
	private static File findPropertiesFile() {
		String classpath = System.getProperty("java.class.path");

		StringTokenizer tok = new StringTokenizer(classpath, ":");
		while (tok.hasMoreElements()) {

			String path = tok.nextToken().trim();
			String filename = path + "/parsers.properties";
			File file = new File(filename);
			if (file.exists()) {
				return file;
			}
		}
		return null;
	}

	/**
	 * Get parsed objects
	 * 
	 * @return list of devices
	 */
	private static List<DeviceParser> getDefaultSet() {
		LinkedList<DeviceParser> parsers = new LinkedList<DeviceParser>();
		parsers.add(new CPUParser());
		parsers.add(new RAMParser());

		return parsers;
	}

}
