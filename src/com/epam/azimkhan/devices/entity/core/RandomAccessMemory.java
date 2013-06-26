package com.epam.azimkhan.devices.entity.core;

import java.io.Serializable;

/**
 * RAM
 */
public class RandomAccessMemory extends Device implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 5165872341166142516L;

	/**
	 * RAM type
	 */
	public enum RAMType{
		DDR, DDR2, DDR3
	}
	
	/**
	 * Memory in megabytes
	 */
	private int memorySize;
	
	/**
	 * Frequency in Mhz
	 */
	private int frequency;
	
	/**
	 * Ram type
	 */
	private  RAMType type;

	public RandomAccessMemory(){
		super();
	}
	
	

	public RandomAccessMemory(String name, String manufacturer, String origin,
			int price, double powerConsumption, int memorySize, int frequency,
			RAMType type, boolean critical) {
		super(name, manufacturer, origin, price, powerConsumption, critical);
		this.memorySize = memorySize;
		this.frequency = frequency;
		this.type = type;
	}



	/**
	 * @return the memorySize
	 */
	public int getMemorySize() {
		return memorySize;
	}

	/**
	 * @param memorySize the memorySize to set
	 */
	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
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
	 * @return the type
	 */
	public RAMType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(RAMType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + memorySize;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		RandomAccessMemory other = (RandomAccessMemory) obj;
		if (frequency != other.frequency)
			return false;
		if (memorySize != other.memorySize)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	
}
