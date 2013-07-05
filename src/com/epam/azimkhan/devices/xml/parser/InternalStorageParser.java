/**
 * 
 */
package com.epam.azimkhan.devices.xml.parser;

import com.epam.azimkhan.devices.entity.Device;
import com.epam.azimkhan.devices.entity.InternalStorage;
import com.epam.azimkhan.devices.entity.InternalStorage.DiskType;
import com.epam.azimkhan.devices.util.EnumUtils;
import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

/**
 * Internal storage parser
 *
 */
public class InternalStorageParser extends DeviceParser {
	
	/**
	 * Storage field handlers
	 *
	 */
	enum StorageFieldHandler{
		CAPACITY {
			@Override
			public boolean handle(InternalStorage device, String value) {
				try {
					device.setCapacity(FieldParser.parseSize(value));
					return true;
				} catch (ParseException e) {
					return false;
				}
				
			}
		}, TYPE {
			@Override
			public boolean handle(InternalStorage device, String value) {
				DiskType type = EnumUtils.lookup(DiskType.class, value);
				
				if (type != null){
					device.setType(type);
					return true;
				} else {
					return false;
				}
				
			}
		}, TRANSFER_RATE {
			@Override
			public boolean handle(InternalStorage device, String value) {
				try {
					device.setCapacity(FieldParser.parseDataTransferRate(value));
					return true;
				} catch (ParseException e) {
					return false;
				}
				
			}
		};
		
		public abstract boolean handle(InternalStorage device, String value); 
	}

	/**
	 * storage
	 */
	private InternalStorage device;
	
	/**
	 * parse storage field
	 */
	@Override
	public boolean parseField(String name, String value){
		
		if (super.parseField(name, value)){
			return true;
		}
		if (name != null && value != null){
			StorageFieldHandler handler =  EnumUtils.lookup(StorageFieldHandler.class, name);
			
			if (handler != null){
				return handler.handle(device, value);
			} else{ // нужен else или нет??
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public Device getDevice() {
		return device;
	}

	
	@Override
	public void init() {
		device = new InternalStorage();

	}

}
