package org.tc.openantrag4android.adapter;

import java.util.Date;

public class CommentEntry {

	protected Date createdAt = null;
	protected String timestamp = "";
	protected String commentHTML = "";
	protected String commentText = "";
	protected String commentedBy = "";
	protected boolean isEvenRow = true;
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public CommentEntry(String a, String b, String c, String d, Date e, boolean f) {
		
		this.commentedBy = a;
		this.commentText = b;
		this.commentHTML = c;
		this.timestamp = d;
		this.createdAt = e;
		this.isEvenRow = f;
	}

}
