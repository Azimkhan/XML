package com.epam.azimkhan.devices.entity;

/**
 * Basic device
 */
public abstract class Device {
	
	/**
	 * The name, e.g. "Intel Core i3"
	 */
	private String name;
	
	/**
	 * The manufacturer company, e.g. "Asus"
	 */
	private String manufacturer;
	
	/**
	 * Country of origin, e.g. "USA"
	 */
	private String origin;
	
	/**
	 * Power consumption in watts
	 */
	private double powerConsumption;
	/**
	 * The price of device
	 */
	private int price;
	
	public Device(){}
	
	public Device(String name, String manufacturer, String origin, int price, double powerConsumption) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
		this.origin = origin;
		this.price = price;
		this.powerConsumption = powerConsumption;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}


	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}


	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	public double getPowerConsumption() {
		return powerConsumption;
	}

	public void setPowerConsumption(double powerConsumption) {
		this.powerConsumption = powerConsumption;
	}

}