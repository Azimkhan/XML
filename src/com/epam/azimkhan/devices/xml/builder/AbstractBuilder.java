package com.epam.azimkhan.devices.xml.builder;

import java.util.List;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public abstract class AbstractBuilder {
	
	public abstract List<Device> build(String filename) throws BuilderException;

}
