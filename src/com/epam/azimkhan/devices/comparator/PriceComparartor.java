package com.epam.azimkhan.devices.comparator;
import java.util.Comparator;

import com.epam.azimkhan.devices.entity.Device;

public class PriceComparartor implements Comparator<Device> {

	@Override
	public int compare(Device arg0, Device arg1) {
		if (arg1.getPrice() > arg0.getPrice()){
			return 1;
		} else if(arg0.getPrice() > arg1.getPrice()){
			return -1;
		}
		
		return 0;
	}

	

}
