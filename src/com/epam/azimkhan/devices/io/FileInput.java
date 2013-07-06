package com.epam.azimkhan.devices.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.apache.log4j.Logger;

public class FileInput {

	public static final Logger logger = Logger.getRootLogger();
	
	public static String readContent(String filename) throws IOException, FileNotFoundException{
		FileChannel fileChannel = null;
		RandomAccessFile file = null;
		FileLock lock = null;
		
		try {
			file = new RandomAccessFile(filename, "rw");
			fileChannel = file.getChannel();
			StringBuilder b=  new StringBuilder();
			lock = fileChannel.lock();
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int bytes = fileChannel.read(byteBuffer);
			
            while(bytes!=-1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                   b.append((char)byteBuffer.get());
                }
                byteBuffer.clear();
                bytes = fileChannel.read(byteBuffer);
            }
			
			return b.toString();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally{
			try {
				if (null != lock){
					lock.release();
				}
				if (null != fileChannel){
					fileChannel.close();
				}
				if (file != null){
					file.close();
				}
				
				
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			
		}
	}

}
