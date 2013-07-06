package com.epam.azimkhan.devices.io;

import java.io.PrintStream;
import java.util.List;

import com.epam.azimkhan.devices.entity.Device;

public class DevicePrinter {

	public static void print(List<Device> devices){
		
		print(devices, System.out);
	}
	
	public static void print(List<Device> devices, PrintStream out){
		
		int i = 1;
		for(Device d : devices){
			out.print(i + ". ");
			print (d);
			i++;
		}
	}
	
	public static void print(Device d){
		print(d, System.out);
	}
	
	public static void print(Device d, PrintStream out){
		out.println(d.getManufacturer() + " " + d.getName());
		out.println("   " +d.getOrigin());
		out.println("   " + d.getPrice());
	}

}
