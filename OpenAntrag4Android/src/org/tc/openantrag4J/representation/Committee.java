/**
 * 
 */
package org.tc.openantrag4J.representation;

import java.net.URL;

/**
 * @author roere
 *
 */
public class Committee {

	private String representationKey = "";
	private String key = "";
	private String name = "";
	private String caption = "";
	private URL url = null;
	
	/**
	 * 
	 */
	public Committee () {
	}
	
	/**
	 * 
	 * @param representationKey
	 * @param key
	 * @param name
	 * @param caption
	 * @param url
	 */
	public Committee(String representationKey,
						String key,
						String name,
						String caption,
						URL url) {
		this.representationKey = representationKey;
		this.key = key;
		this.name = name;
		this.caption = caption;
		this.url = url;
	}

	/**
	 * @return the representationKey
	 */
	public String getRepresentationKey() {
		return representationKey;
	}

	/**
	 * @param representationKey the representationKey to set
	 */
	public void setRepresentationKey(String representationKey) {
		this.representationKey = representationKey;
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
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

}
