package org.tc.openantrag4android;

public abstract class AndroidUtils {

	public static String null2Empty (String a) {
		if ((a==null)||(a.equals("null")))
			return Constants.EMPTY_FIELD;
		else
			return a;
	}

}
