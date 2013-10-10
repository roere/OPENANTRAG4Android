package org.tc.openantrag4android;

import java.util.Locale;

public abstract class Constants {
	
	public static String VERSION = "0.9701";
	public static String LICENCE = "GPL Version 3";
	
	public static String APP_NAME = "OPENANTRAG4Android";
	public static String REPOSITORY = "github.com/roere/OPENANTRAG4Android";
	
	public static String INFO_TEXT = "Version:"+
										Constants.VERSION+"/n/r"+
										"Lizenz:"+Constants.LICENCE+"/n/r"+
										"GitHub:"+Constants.REPOSITORY;

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
