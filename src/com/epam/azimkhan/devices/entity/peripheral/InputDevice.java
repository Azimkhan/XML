package com.epam.azimkhan.devices.entity.peripheral;

/**
 * Input device
 */
public class InputDevice extends PeripheralDevice{
	
	private Port inputPort;

	public InputDevice() {
		super();
	}

	public InputDevice(String name, String manufacturer, String origin,
			int price, double powerConsumption, boolean critical,
			DeviceGroup deviceGroup, Port inputPort) {
		super(name, manufacturer, origin, price, powerConsumption, critical,
				deviceGroup);
		this.inputPort = inputPort;
	}

	/**
	 * @return the inputPort
	 */
	public Port getInputPort() {
		return inputPort;
	}

	/**
	 * @param inputPort the inputPort to set
	 */
	public void setInputPort(Port inputPort) {
		this.inputPort = inputPort;
	}
	

}
