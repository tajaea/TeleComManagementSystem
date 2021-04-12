package com.microstar.cablevision.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
	
	public static String setDate() {
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	public static String setTime() {
		DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
