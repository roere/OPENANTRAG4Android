package org.tc.openantrag4android;

import java.util.Locale;

public abstract class Constants {
	
	public static String version = "0.970000";

	public static String REPRESENTATION_KEY = "representationKey";
	public static String SELECTED_ITEM = "selectedItem";
	public static String EMPTY_FIELD = "-";
	public static Integer PAGE_COUNT = 20;
	public static String ALL_IDENTIFIER = "...";
	public static String FORCE_RELOAD = "forceReload";
	public static Long RELOAD_MAIN = new Long(600); //reload every xxx seconds;
	
	public static String VAR_COMMITTEE = "%COMMITTEE%";
	public static String VAR_REPRESENTATIVE = "%REPRESENTATIVE%";

	public static Locale LOCALE = Locale.GERMAN;
	
	public Constants() {
	}

}
