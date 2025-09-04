package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";// changed
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	
	public static final String ACC_PAGE_TITLE = "Account Login";
	public static final String ACC_PAGE_FRACTION_URL = "route=account/login";
	
	public static final int DEFAULT_SHORT_WAIT = 5;
	public static final int DEFAULT_MEDIUM_WAIT = 10;
	public static final int DEFAULT_LONG_WAIT = 15;
	
	public static final int ACC_PAGE_HEADER_COUNT = 4;
	
	public static final String USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	
	public static List<String> expected_ACC_Page_Header_List = List.of("My Account",
																		"My Orders",
																		"My Affiliate Account",
																		"Newsletter");
	
	
}
