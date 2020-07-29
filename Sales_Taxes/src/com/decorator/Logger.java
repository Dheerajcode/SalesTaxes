package com.decorator;

public class Logger {
	
	public static final Logger instance = new Logger();
	
	private Logger(){}
	
	public void log(String message){
		System.out.println(message);
	}
	
}
