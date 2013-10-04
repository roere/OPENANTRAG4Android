package org.tc.openantrag4j.proposal;

import java.util.ArrayList;
import java.util.Date;

public class ProcessStep {
	
	private ArrayList<String> idNextSteps = new ArrayList<String>();
	private String caption = "";
	private String shortCaption = "";
	private String color = "";
	private Integer idProcessStep = null;
	private String infoHtml = "";
	private String infoText = "";
	private String id = null;
	private Date createdAt = null;
	private Integer timestamp = null;

	public ProcessStep() {
	}

	/**
	 * @return the idNextSteps
	 */
	public ArrayList<String> getIdNextSteps() {
		return idNextSteps;
	}

	/**
	 * @param idNextSteps the idNextSteps to set
	 */
	public void setIdNextSteps(ArrayList<String> idNextSteps) {
		this.idNextSteps = idNextSteps;
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
	 * @return the shortCaption
	 */
	public String getShortCaption() {
		return shortCaption;
	}

	/**
	 * @param shortCaption the shortCaption to set
	 */
	public void setShortCaption(String shortCaption) {
		this.shortCaption = shortCaption;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the idProcessStep
	 */
	public Integer getIdProcessStep() {
		return idProcessStep;
	}

	/**
	 * @param idProcessStep the idProcessStep to set
	 */
	public void setIdProcessStep(Integer idProcessStep) {
		this.idProcessStep = idProcessStep;
	}

	/**
	 * @return the infoHtml
	 */
	public String getInfoHtml() {
		return infoHtml;
	}

	/**
	 * @param infoHtml the infoHtml to set
	 */
	public void setInfoHtml(String infoHtml) {
		this.infoHtml = infoHtml;
	}

	/**
	 * @return the infoText
	 */
	public String getInfoText() {
		return infoText;
	}

	/**
	 * @param infoText the infoText to set
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the timestamp
	 */
	public Integer getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}
	
}
