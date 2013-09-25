package com.openantrag4j.proposal;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class Proposal {
	
	private ArrayList<ProposalStep> proposalSteps = null;
	private String externalUrl = "";
	private String isAbuse = "";
	private String abuseMessage = "";
	private String abuseMessageText = "";
	private String abuseMessageHTML = "";
	private String shortUrl = "";
	private String externalShortUrl = "";
	private String textHTML = "";
	private String iDCurrentProposalStep = "";
	private Date createdAt = null;
	private String titleURL = "";
	private String iD = "";
	private String keyRepresentation = "";
	private String keyRepresentative = "";
	private String timeStamp = "";
	private String textMarkDown = "";
	private String keyCommittee = "";
	private String textRaw = "";
	private String fullURL = "";
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<Comment> proposalComments = new ArrayList<Comment>();
	private String titleText = "";
//	private JSONObject feedItem = null;
	
	protected Proposal() {
	}

	/**
	 * @return the externalShortUrl
	 */
	public String getExternalShortUrl() {
		return externalShortUrl;
	}

	/**
	 * @param externalShortUrl the externalShortUrl to set
	 */
	public void setExternalShortUrl(String externalShortUrl) {
		this.externalShortUrl = externalShortUrl;
	}

	/**
	 * @return the textHTML
	 */
	public String getTextHTML() {
		return textHTML;
	}

	/**
	 * @param textHTML the textHTML to set
	 */
	public void setTextHTML(String textHTML) {
		this.textHTML = textHTML;
	}

	/**
	 * @return the externalUrl
	 */
	public String getExternalUrl() {
		return externalUrl;
	}

	/**
	 * @param externalUrl the externalUrl to set
	 */
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	/**
	 * @return the shortUrl
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * @param shortUrl the shortUrl to set
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * @return the iDCurrentProposalStep
	 */
	public String getiDCurrentProposalStep() {
		return iDCurrentProposalStep;
	}

	/**
	 * @param iDCurrentProposalStep the iDCurrentProposalStep to set
	 */
	public void setiDCurrentProposalStep(String iDCurrentProposalStep) {
		this.iDCurrentProposalStep = iDCurrentProposalStep;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the titleURL
	 */
	public String getTitleURL() {
		return titleURL;
	}

	/**
	 * @param titleURL the titleURL to set
	 */
	public void setTitleURL(String titleURL) {
		this.titleURL = titleURL;
	}

	/**
	 * @return the iD
	 */
	public String getiD() {
		return iD;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(String iD) {
		this.iD = iD;
	}
	
	/**
	 * Returns the tags of the current proposal.
	 * @return the tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(ArrayList<String> tagsList) {
		this.tags = tagsList;
	}

	/**
	 * @return the keyRepresentation
	 */
	public String getKeyRepresentation() {
		return keyRepresentation;
	}

	/**
	 * @param keyRepresentation the keyRepresentation to set
	 */
	public void setKeyRepresentation(String keyRepresentation) {
		this.keyRepresentation = keyRepresentation;
	}

	/**
	 * @return the keyRepresentative
	 */
	public String getKeyRepresentative() {
		return keyRepresentative;
	}

	/**
	 * @param keyRepresentative the keyRepresentative to set
	 */
	public void setKeyRepresentative(String keyRepresentative) {
		this.keyRepresentative = keyRepresentative;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the textMarkDown
	 */
	public String getTextMarkDown() {
		return textMarkDown;
	}

	/**
	 * @param textMarkDown the textMarkDown to set
	 */
	public void setTextMarkDown(String textMarkDown) {
		this.textMarkDown = textMarkDown;
	}

	/**
	 * @return the keyCommittee
	 */
	public String getKeyCommittee() {
		return keyCommittee;
	}

	/**
	 * @param keyCommittee the keyCommittee to set
	 */
	public void setKeyCommittee(String keyCommittee) {
		this.keyCommittee = keyCommittee;
	}

	/**
	 * @return the textRaw
	 */
	public String getTextRaw() {
		return textRaw;
	}

	/**
	 * @param textRaw the textRaw to set
	 */
	public void setTextRaw(String textRaw) {
		this.textRaw = textRaw;
	}

	/**
	 * @return the fullURL
	 */
	public String getFullURL() {
		return fullURL;
	}

	/**
	 * @param fullURL the fullURL to set
	 */
	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}

	/**
	 * @return the isAbuse
	 */
	public String setIsAbuse() {
		return isAbuse;
	}

	/**
	 * @param isAbuse the isAbuse to set
	 */
	public void setIsAbuse(String isAbuse) {
		this.isAbuse = isAbuse;
	}

	/**
	 * @return the abuseMessage
	 */
	public String getAbuseMessage() {
		return abuseMessage;
	}

	/**
	 * @param abuseMessage the abuseMessage to set
	 */
	public void setAbuseMessage(String abuseMessage) {
		this.abuseMessage = abuseMessage;
	}

	/**
	 * @return the abuseMessageText
	 */
	public String getAbuseMessageText() {
		return abuseMessageText;
	}

	/**
	 * @param abuseMessageText the abuseMessageText to set
	 */
	public void setAbuseMessageText(String abuseMessageText) {
		this.abuseMessageText = abuseMessageText;
	}

	/**
	 * @return the abuseMessageHTML
	 */
	public String getAbuseMessageHTML() {
		return abuseMessageHTML;
	}

	/**
	 * @param abuseMessageHTML the abuseMessageHTML to set
	 */
	public void setAbuseMessageHTML(String abuseMessageHTML) {
		this.abuseMessageHTML = abuseMessageHTML;
	}

	/**
	 * @return the proposalSteps
	 */
	public ArrayList<ProposalStep> getProposalSteps() {
		return proposalSteps;
	}

	/**
	 * @param proposalSteps the proposalSteps to set
	 */
	public void setProposalSteps(ArrayList<ProposalStep> proposalSteps) {
		this.proposalSteps = proposalSteps;
	}

	/**
	 * @return the proposalComments
	 */
	public ArrayList<Comment> getProposalComments() {
		return proposalComments;
	}

	/**
	 * @param proposalComments the proposalComments to set
	 */
	public void setProposalComments(ArrayList<Comment> proposalComments) {
		this.proposalComments = proposalComments;
	}

	/**
	 * @return the titleText
	 */
	public String getTitleText() {
		return titleText;
	}

	/**
	 * @param titleText the titleText to set
	 */
	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	
	
	
}
