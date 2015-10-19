package org.tc.openantrag.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AndroidUtils {

	public static String null2Empty (String a) {
		if ((a==null)||(a.equals("null")))
			return Constants.EMPTY_FIELD;
		else
			return a;
	}
	
	/**
	 * Replace variables like %REPRESENTATIVE% with real values.
	 * @param str
	 * @return
	 */
	public static String setVariables (String str, 
										String representative,
										String committee) {
		if (representative!=null) 
			str = str.replace(Constants.VAR_REPRESENTATIVE, representative);
		if (committee!=null) 
			str = str.replace(Constants.VAR_COMMITTEE, committee);
		return str;
	}
	
	/**
	 * Tries to open an inputStream using TEST_URL.
	 * Assumes that Internet is available in case that works and returns true hence.
	 * @return
	 */
	public static boolean hasInternetAccess() {
		return hasAccess(Constants.TEST_URL);
	}
	
	/**
	 * Tries to open an inputStream using BASE_URL.
	 * Assumes that Server is available in case that works and returns true hence.
	 * @return
	 */
	public static boolean hasServerAccess() {
		return hasAccess(org.tc.openantrag4J.Constants.BASE_URL);
	}
	
	/**
	 * 
	 * @param host
	 * @return
	 */
	private static boolean hasAccess(String host) {
		try {
			URI testURI = new URI ("http",host,null,null);
			testURI.toURL().openStream();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}

}
