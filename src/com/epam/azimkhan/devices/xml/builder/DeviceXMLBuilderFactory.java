package com.epam.azimkhan.devices.xml.builder;

import com.epam.azimkhan.devices.xml.dom.DOMBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;
import com.epam.azimkhan.devices.xml.sax.SAXBuilder;
import com.epam.azimkhan.devices.xml.stax.StAXBuilder;

public class DeviceXMLBuilderFactory {
	
	private static BuilderEnum preferedBuilder = BuilderEnum.SAX;
	
	
	enum BuilderEnum{
		DOM {
			@Override
			public AbstractXMLBuilder createBuilder() {
				return new DOMBuilder();
			}
		}, 
		SAX {
			@Override
			public AbstractXMLBuilder createBuilder() {
				return new SAXBuilder();
			}
		}, 
		STAX {
			@Override
			public AbstractXMLBuilder createBuilder() {
				return new StAXBuilder();
			}
		};
		
		public abstract AbstractXMLBuilder createBuilder();	
		
	}
	
	/**
	 * Get builder from name
	 * @param name
	 * @return builder
	 * @throws BuilderException
	 */
	public AbstractXMLBuilder createBuilder(String name) throws BuilderException{
		if (name != null){
			for (BuilderEnum b :BuilderEnum.values()){
				if (b.name().equals(name.toUpperCase())){
					return b.createBuilder();
				}
			}
		}
		
		throw new BuilderException("Builder not found");
		
	}
	
	/**
	 * Get default builder
	 * @return default builder
	 */
	public AbstractXMLBuilder createBuilder(){
		return preferedBuilder.createBuilder();
	}
	
	
	
	

}
