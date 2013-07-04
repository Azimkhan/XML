package com.epam.azimkhan.devices.util;

public class EnumUtils {

	public static <E extends Enum<E>>E lookup (Class<E> e, String id ){
		try {
			E result = Enum.valueOf(e, id.toUpperCase());
			return result;
		} catch (IllegalArgumentException e2) {
			return null;
		}
		
	}
}
