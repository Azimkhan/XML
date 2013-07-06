package com.epam.azimkhan.devices.xml.dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.xml.builder.AbstractXMLBuilder;
import com.epam.azimkhan.devices.xml.exception.BuilderException;
import com.epam.azimkhan.devices.xml.exception.ParseException;

public class DOMBuilder extends AbstractXMLBuilder {

	@Override
	protected List<Device> build(InputStream instream) throws BuilderException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new DOMErrorHandler());

			Document document = builder.parse(instream);

			DOMDeviceAnalyzer analyzer = DOMDeviceAnalyzer.INSTANCE;

			return analyzer.buildList(document.getDocumentElement());

		} catch (ParseException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (ParserConfigurationException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (SAXException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
	}

}
