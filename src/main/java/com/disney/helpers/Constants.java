package com.disney.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang.RandomStringUtils;

public class Constants {
	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String character = "!@#$%^&*-_=+|;:,<.>/?";
	public static final String pwd = RandomStringUtils.random( 17, upper+character);
	public static final LocalDateTime now = LocalDateTime.now();
	public static final String d = now.format(DateTimeFormatter.ofPattern("HHmmss", Locale.ENGLISH));
	public static final String dob= now.format(DateTimeFormatter.ofPattern("MM/yy/dd", Locale.ENGLISH));

}
