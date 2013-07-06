package com.epam.azimkhan.devices.logic;

import java.util.Collections;
import java.util.List;

import com.epam.azimkhan.devices.comparator.PriceComparartor;
import com.epam.azimkhan.devices.entity.Device;

public enum DeviceSorter {
	INSTANCE;
	
	public void sortByPrice(List<Device> devices){
		Collections.sort(devices, new PriceComparartor());
	}
}
