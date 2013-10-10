package org.tc.openantrag4J;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public abstract class Utils {

	public static String getBaseURL() {		
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							null,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}		
	}	
	
	/**
	 * 
	 * @param command
	 * @return
	 */
	public static String getURL(String command) {
		return Utils.getURL(command, "");
	}	
	
	/**
	 * 
	 * @param command
	 * @param key
	 * @return
	 */
	public static String getURL(String command, String key) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+command+"/"+key,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param command
	 * @param key
	 * @return
	 */
	public static String getProposalURL (String command, String key) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.PROPOSAL_URL+"/"+key+"/"+command,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	
	
	/**
	 * 
	 * @param command
	 * @param representationKey
	 * @param tag
	 * @return
	 */
	public static String getURL(String command, String representationKey, String tag) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.PROPOSAL_URL+"/"+representationKey+"/"+command+"/"+tag,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
		
	/**
	 * 
	 * @param key
	 * @return
	 */
	private static String getKeyURL (String key) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.PROPOSAL_URL+"/"+key,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param page
	 * @param pageCount
	 * @param key
	 * @return
	 */
	public static String getPageURL(int page, int pageCount, String key) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.PROPOSAL_URL+"/"+key+"/"+Constants.COMMAND_GET_PAGE+"/"+page+"/"+pageCount,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param count
	 * @param representationID
	 * @return
	 */
	public static String getTop (int count, String representationID) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.PROPOSAL_URL+"/"+representationID+"/"+Constants.COMMAND_GET_TOP+"/"+count,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
		
	/**
	 * 
	 * @param proposalID
	 * @return
	 */
	public static String getCommentsURL (String proposalID) {
		try {
			URI uri = new URI(Constants.PROTOCOL,
							Constants.BASE_URL,
							"/"+Constants.API_URL+"/"+Constants.COMMAND_GET_COMMENTS+"/"+proposalID,
							null);
			return uri.toString();
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param page
	 * @param pageCount
	 * @return
	 */
	public static String getPageURL(int page, int pageCount) {
		return Utils.getPageURL(page, pageCount, Constants.COMMAND_KEY_ALL_REPRESENTATION);
	}
	
}
