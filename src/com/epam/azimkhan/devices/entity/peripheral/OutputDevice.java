package com.epam.azimkhan.devices.entity.peripheral;

/**
 * Output device
 */
public class OutputDevice extends PeripheralDevice{
	private Port outputPort;
	
	public OutputDevice() {
		super();
	}

	public OutputDevice(String name, String manufacturer, String origin,
			int price, double powerConsumption, boolean critical,
			DeviceGroup deviceGroup, Port outputPort) {
		super(name, manufacturer, origin, price, powerConsumption, critical,
				deviceGroup);
		this.outputPort = outputPort;
	}

	/**
	 * @return the outputPort
	 */
	public Port getOutputPort() {
		return outputPort;
	}

	/**
	 * @param outputPort the outputPort to set
	 */
	public void setOutputPort(Port outputPort) {
		this.outputPort = outputPort;
	}
	
	
}
