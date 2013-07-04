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

	public enum Parser{
		CPU {
			@Override
			public DeviceParser getParser() {
				return new CPUParser();
			}
		}, RAM {
			@Override
			public DeviceParser getParser() {
				return new RAMParser();
			}
		}, INTERNAL_STORAGE {
			@Override
			public DeviceParser getParser() {
				// TODO Auto-generated method stub
				return null;
			}
		}, PERIPHERAL {
			@Override
			public DeviceParser getParser() {
				// TODO
				return null;
			}
		};
		
		public abstract DeviceParser getParser();
		
	}
	
	
	public boolean hasDevice(String name){
		
		if (name != null){
			for(Parser p : Parser.values()){
				if (p.name().equals(name.toUpperCase()))
					return true;
			}
		}
		return false;
	}
	
	public DeviceParser getParser(String name){
		if (hasDevice(name)){
			return Parser.valueOf(name.toUpperCase()).getParser();
		}
		
		return null;
	}

	
}
