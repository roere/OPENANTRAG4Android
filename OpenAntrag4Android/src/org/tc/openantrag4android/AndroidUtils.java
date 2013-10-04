package org.tc.openantrag4android;

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
			str.replace(Constants.VAR_REPRESENTATIVE, representative);
		if (committee!=null) 
			str.replace(Constants.VAR_COMMITTEE, committee);
		return str;
	}

}
