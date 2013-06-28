package com.epam.azimkhan.devices.entity;

/**
 * Internal storage
 */
public class InternalStorage extends Device{
	
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
			int price, double powerConsumption, int capacity,
			int dataTransferRate, DiskType type, boolean critical) {
		super(name, manufacturer, origin, price, powerConsumption, critical);
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
	
	
	
	

}
