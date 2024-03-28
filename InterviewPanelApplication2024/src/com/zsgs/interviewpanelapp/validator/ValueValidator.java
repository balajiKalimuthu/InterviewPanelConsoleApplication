package com.zsgs.interviewpanelapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueValidator {
	private static Pattern pattern;
	private static Matcher matcher;

	public static boolean validateName(String adminName) {
		String regex = "^[A-Za-z\\s.]{1,15}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(adminName);
		return matcher.matches();
	}

	public static boolean validateId(String id) {
		String regex = "^[0-9]{1,7}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(id);
		return matcher.matches();
	}

	public static boolean validateRole(String role) {
		String regex = "^[A-Za-z\\s-]{1,15}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(role);
		return matcher.matches();
	}

	public static boolean validateDate(String date) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(date);
		return matcher.matches();
	}

	public static boolean validatePhoneNo(String phoneNo) {
		String regex = "^[0-9]{10}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(phoneNo);
		return matcher.matches();
	}

	public static boolean validateLocation(String location) {
		String regex = "^[A-Za-z\\s]{1,15}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(location);
		return matcher.matches();
	}

	public static boolean validateEmailId(String emailId) {
		String regex = "^[a-z0-9.-]{1,10}+@gmail\\.com$$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}
}