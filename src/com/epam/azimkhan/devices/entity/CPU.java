package com.epam.azimkhan.devices.entity;

import java.io.Serializable;

/**
 * Central processing unit, i.e. processor
 */
public class CPU extends Device implements Cloneable, Serializable{
	
	private static final long serialVersionUID = -2717461624092506689L;

	public enum CPUSocket{
		LGA1155, LGA2011, AM3PLUS, FM2	
	}
	
	/**
	 * Frequency in Mhz
	 */
	private int frequency;
	
	/**
	 * Number of cores
	 */
	private int numberOfCores;
	
	/**
	 * Number of threads
	 */
	private int numberOfThreads;
	
	/**
	 * Cache size in megabytes
	 */
	private int cacheSize;
	
	/**
	 * CPU socket
	 */
	private CPUSocket socket;

	
	public CPU() {
		super();
	}



	public CPU(String name, String manufacturer, String origin, int price,
			double powerConsumption, int frequency, int numberOfCores,
			int numberOfThreads, int cacheSize, CPUSocket socket) {
		super(name, manufacturer, origin, price, powerConsumption);
		this.frequency = frequency;
		this.numberOfCores = numberOfCores;
		this.numberOfThreads = numberOfThreads;
		this.cacheSize = cacheSize;
		this.socket = socket;
	}



	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the numberOfCores
	 */
	public int getNumberOfCores() {
		return numberOfCores;
	}

	/**
	 * @param numberOfCores the numberOfCores to set
	 */
	public void setNumberOfCores(int numberOfCores) {
		this.numberOfCores = numberOfCores;
	}

	/**
	 * @return the numberOfThreads
	 */
	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	/**
	 * @param numberOfThreads the numberOfThreads to set
	 */
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	/**
	 * @return the socket
	 */
	public CPUSocket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(CPUSocket socket) {
		this.socket = socket;
	}



	public int getCacheSize() {
		return cacheSize;
	}



	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cacheSize;
		result = prime * result + frequency;
		result = prime * result + numberOfCores;
		result = prime * result + numberOfThreads;
		result = prime * result + ((socket == null) ? 0 : socket.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPU other = (CPU) obj;
		if (cacheSize != other.cacheSize)
			return false;
		if (frequency != other.frequency)
			return false;
		if (numberOfCores != other.numberOfCores)
			return false;
		if (numberOfThreads != other.numberOfThreads)
			return false;
		if (socket != other.socket)
			return false;
		return true;
	}

	
	

}
