package com.epam.azimkhan.devices.entity;

import java.io.Serializable;

/**
 * Internal storage
 */
public class InternalStorage extends Device implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4781164774814994043L;

	public enum DiskType{
		HDD, SSD
	}
	
	/**
	 * Capacity in megabytes;
	 */
	private int capacity;
	
	/**
	 * Megabits per second
	 */
	private int dataTransferRate;
	
	/**
	 * Disk type
	 */
	private DiskType type;

	public InternalStorage() {
		super();
	}

	public InternalStorage(String name, String manufacturer, String origin,
			int price, int capacity,
			int dataTransferRate, DiskType type, boolean critical) {
		super(name, manufacturer, origin, price, critical);
		this.capacity = capacity;
		this.dataTransferRate = dataTransferRate;
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDataTransferRate() {
		return dataTransferRate;
	}

	public void setDataTransferRate(int dataTransferRate) {
		this.dataTransferRate = dataTransferRate;
	}

	public DiskType getType() {
		return type;
	}

	public void setType(DiskType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + capacity;
		result = prime * result + dataTransferRate;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternalStorage other = (InternalStorage) obj;
		if (capacity != other.capacity)
			return false;
		if (dataTransferRate != other.dataTransferRate)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	

}
