package org.tc.openantrag4J;

public abstract class Utils {

	public static String getBaseURL() {
		return Constants.PROTOCOL+"://"+Constants.BASE_URL;
	}	
	
	/**
	 * 
	 * @param command
	 * @return
	 */
	public static String getURL(String command) {
		//return Constants.PROTOCOL+"://"+Constants.BASE_URL+"/"+Constants.API_URL+"/"+command;
		return Utils.getURL(command, "");
	}	
	
	/**
	 * 
	 * @param command
	 * @param key
	 * @return
	 */
	public static String getURL(String command, String key) {
		return Utils.getBaseURL()+"/"+Constants.API_URL+"/"+command+"/"+key;
	}
	
	/**
	 * 
	 * @param command
	 * @param key
	 * @return
	 */
	public static String getProposalURL (String command, String key) {
		return Utils.getBaseURL()+"/"+
				Constants.API_URL+"/"+
				Constants.PROPOSAL_URL+"/"+
				key+"/"+
				command;		
	}
	
	
	
	/**
	 * 
	 * @param command
	 * @param representationKey
	 * @param tag
	 * @return
	 */
	public static String getURL(String command, String representationKey, String tag) {
		return Utils.getBaseURL()+"/"+
					Constants.API_URL+"/"+
					Constants.PROPOSAL_URL+"/"+
					representationKey+"/"+
					command+"/"+
					tag;
	}
		
	/**
	 * 
	 * @param key
	 * @return
	 */
	private static String getKeyURL (String key) {
		return Utils.getBaseURL()+"/"+
				Constants.API_URL+"/"+
				Constants.PROPOSAL_URL+
				"/"+key;
	}
	
	/**
	 * 
	 * @param page
	 * @param PAGE_COUNT
	 * @param key
	 * @return
	 */
	public static String getPageURL(int page, int pageCount, String key) {
		return Utils.getKeyURL(key)+
				"/"+
				Constants.COMMAND_GET_PAGE+"/"+
				page+"/"+pageCount;
	}
	
	/**
	 * 
	 * @param count
	 * @param representationID
	 * @return
	 */
	public static String getTop (int count, String representationID) {
		return Utils.getKeyURL(representationID)+
				"/"+
				Constants.COMMAND_GET_TOP+"/"+
				count;
	}
		
	/**
	 * 
	 * @param proposalID
	 * @return
	 */
	public static String getCommentsURL (String proposalID) {
		return Utils.getBaseURL()+"/"+
				Constants.API_URL+"/"+
				Constants.COMMAND_GET_COMMENTS+
				"/"+proposalID;
	}
	
	/**
	 * 
	 * @param page
	 * @param PAGE_COUNT
	 * @return
	 */
	public static String getPageURL(int page, int pageCount) {
		return Utils.getPageURL(page, pageCount, Constants.COMMAND_KEY_ALL_REPRESENTATION);
	}
	
}
