package com.openantrag4J.representation.android;

import android.graphics.Bitmap;

public class Representative {
	
	private String key = "";
	private String name = "";
	private String party = "";
	private String mail = "";
	private String twitter = "";
	private Bitmap portraitImage = null;
	private String infoHTML = "";
	    	
	public Representative() {
		
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * @param party the party to set
	 */
	public void setParty(String party) {
		this.party = party;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * @param twitter the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * @return the portraitImage
	 */
	public Bitmap getPortraitImage() {
		return portraitImage;
	}

	/**
	 * @param portraitImage the portraitImage to set
	 */
	public void setPortraitImage(Bitmap portraitImage) {
		this.portraitImage = portraitImage;
	}
	
	/**
	 * @return the infoHTML
	 */
	public String getInfoHTML() {
		return infoHTML;
	}

	/**
	 * @param infoHTML the infoHTML to set
	 */
	public void setInfoHTML(String infoHTML) {
		this.infoHTML = infoHTML;
	}

}
