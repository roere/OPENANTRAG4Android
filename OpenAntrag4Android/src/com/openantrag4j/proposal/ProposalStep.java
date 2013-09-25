package com.openantrag4j.proposal;

import java.util.ArrayList;

public class ProposalStep {
	
	private ArrayList<String> idNextSteps = new ArrayList<String>();
	private String caption = "";
	private String shortCaption = "";
	private String color = "";

	public ProposalStep() {
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
}
