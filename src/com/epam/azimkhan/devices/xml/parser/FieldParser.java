package com.epam.azimkhan.devices.xml.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.azimkhan.devices.xml.parser.exception.ParseException;

public class FieldParser {

	public static int parseFrequency(String value) throws ParseException{
		Pattern p = Pattern.compile("\\s*(((?<mhz>[1-9][0-9]+)\\s*(Mhz)?)|((?<ghz>[1-9](\\.[0-9]+))\\s*Ghz))\\s*");
		Matcher m = p.matcher(value);
		
		if (m.matches()){
			String mhz = m.group("mhz");
			String ghz = m.group("ghz");
			
			if (null != mhz){
				return Integer.parseInt(mhz);
			} else {
				return (int) (Double.parseDouble(ghz) * 1000);
			}
		}
		
		throw new ParseException("Frequency doesn't match pattern");
	}
	
	/**
	public static void main(String[] args) throws ParseException{
		System.out.println(parseFrequency("100Mhz"));
		System.out.println(parseFrequency("3.5 Ghz"));
		System.out.println(parseFrequency("1300"));
		System.out.println(parseFrequency(" 100 Mhz "));
		System.out.println(parseFrequency("3.2Ghz"));
		System.out.println(parseSize("100"));
		System.out.println(parseSize("100 Mb"));
		System.out.println(parseSize("100Mb"));
		System.out.println(parseSize("100 Gb"));
	}
	*/
	
	public static int parseSize(String value) throws ParseException{
		Pattern p = Pattern.compile("\\s*(((?<mb>[1-9][0-9]+)\\s*(Mb)?)|((?<gb>[1-9][0-9]+)\\s*Gb))\\s*");
		Matcher m = p.matcher(value);
		
		if (m.matches()){
			String mb = m.group("mb");
			String gb = m.group("gb");
			
			if (null != mb){
				return Integer.parseInt(mb);
			} else {
				return Integer.parseInt(gb) * 1024;
			}
		}
		throw new ParseException("Size doesn't match pattern");
		
	}
}
