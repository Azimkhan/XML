package com.epam.azimkhan.devices.xml.stax;

import java.io.InputStream;
import java.util.List;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractXMLBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;

public class StAXBuilder extends AbstractXMLBuilder {

	@Override
	public List<Device> build(InputStream instream) throws BuilderException {
		// TODO сделать
		return null;
	}

}