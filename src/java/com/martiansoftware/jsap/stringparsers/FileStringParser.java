/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */
 
package com.martiansoftware.jsap.stringparsers;

import com.martiansoftware.jsap.PropertyStringParser;
import com.martiansoftware.jsap.ParseException;
import java.io.File;

/**
 * A StringParser for parsing java.io.File objects.  The parse() method 
 * delegates the actual
 * parsing to <code>new File(String)</code>.  If <code>new File(String)</code>
 * throws a NullPointerException, it is encapsulated in a ParseException and 
 * re-thrown.
 * 
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @author  Edward Glen (edward@glencomm.com) (modified URLStringParser)
 * @since 1.4
 * @see com.martiansoftware.jsap.StringParser
 * @see java.net.URL
 */
public class FileStringParser extends PropertyStringParser {

	public static final String MUSTBEFILE = "mustBeFile";
	public static final String MUSTBEDIRECTORY = "mustBeDirectory";
	public static final String MUSTEXIST = "mustExist";
	private BooleanStringParser bool = new BooleanStringParser();
	
	/**
	 * Creates a new FileStringParser.
	 */
	public FileStringParser() {
		super();
	}

	public void setUp() throws ParseException {
		
	}
	
	public FileStringParser setMustBeDirectory(boolean mustBeDirectory) {
		setProperty(MUSTBEDIRECTORY, mustBeDirectory + "");
		return (this);
	}
	
	public FileStringParser setMustBeFile(boolean mustBeFile) {
		setProperty(MUSTBEFILE, mustBeFile + "");
		return (this);
	}
	
	public FileStringParser setMustExist(boolean mustExist) {
		setProperty(MUSTEXIST, mustExist + "");
		return (this);
	}
	
	public boolean mustBeDirectory() {
		boolean result = false;
		try {
			result = ((Boolean) bool.parse(getProperty(MUSTBEDIRECTORY, "f"))).booleanValue();
		} catch (Throwable t) {}
		return (result);
	}

	public boolean mustBeFile() {
		boolean result = false;
		try {
			result = ((Boolean) bool.parse(getProperty(MUSTBEFILE, "f"))).booleanValue();
		} catch (Throwable t) {}
		return (result);
	}
	
	public boolean mustExist() {
		boolean result = false;
		try {
			result = ((Boolean) bool.parse(getProperty(MUSTEXIST, "f"))).booleanValue();
		} catch (Throwable t) {}
		return (result);
	}
	
	public void tearDown() {
		
	}
	
	/**
	 * Parses the specified argument into a File.  This method delegates the 
	 * actual
	 * parsing to <code>new File(arg)</code>.  If <code>new File(arg)</code>
	 * throws a NullPointerException, it is encapsulated in a ParseException 
	 * and re-thrown.
	 * 
	 * @param arg the argument to parse
	 * @return a File as specified by arg.
	 * @throws ParseException if <code>new File(arg)</code> throws a 
	 * NullPointerException.
	 * @see java.io File
	 * @see com.martiansoftware.jsap.StringParser#parse(String)
	 */
	public Object parse(String arg) throws ParseException {
		File result = null;
		try {
			result = new File(arg);
		} catch (NullPointerException e) {
			throw (
				new ParseException(
					"No File given to parse",
					e));
		}
		return (result);
	}
}