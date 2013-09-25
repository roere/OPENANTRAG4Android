package com.openantrag4j.proposal;

import java.util.Date;

public class Comment {

	private String commentedAtTimestamp = "";
	private Date commmentedAt = null;
	private String commentedBy = "";
	private String commentHTML = "";
	private String commentText = "";
	
	public Comment(String commentedAtTimestamp, 
					Date commentedAt,
					String commentedBy,
					String commentHTML,
					String commentText) {
		this.commentedAtTimestamp = commentedAtTimestamp;
		this.commmentedAt = commentedAt;
		this.commentedBy = commentedBy;
		this.commentHTML = commentHTML;
		this.commentText = commentText;
	}

	/**
	 * @return the commentedAtTimestamp
	 */
	public String getCommentedAtTimestamp() {
		return commentedAtTimestamp;
	}

	/**
	 * @param commentedAtTimestamp the commentedAtTimestamp to set
	 */
	public void setCommentedAtTimestamp(String commentedAtTimestamp) {
		this.commentedAtTimestamp = commentedAtTimestamp;
	}

	/**
	 * @return the commmentedAt
	 */
	public Date getCommmentedAt() {
		return commmentedAt;
	}

	/**
	 * @param commmentedAt the commmentedAt to set
	 */
	public void setCommmentedAt(Date commmentedAt) {
		this.commmentedAt = commmentedAt;
	}

	/**
	 * @return the commentedBy
	 */
	public String getCommentedBy() {
		return commentedBy;
	}

	/**
	 * @param commentedBy the commentedBy to set
	 */
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}

	/**
	 * @return the commentHTML
	 */
	public String getCommentHTML() {
		return commentHTML;
	}

	/**
	 * @param commentHTML the commentHTML to set
	 */
	public void setCommentHTML(String commentHTML) {
		this.commentHTML = commentHTML;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	

}
