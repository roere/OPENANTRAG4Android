package com.tc.openantrag4android.adapter;

import java.util.Date;

public class ProposalEntry {

	protected Date createdAt = null;
	protected String header = "";
	protected String proposalStep = "";
	protected String proposalStepColor = "";
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public ProposalEntry(String a, String b, String c, Date createdAt) {
		
		this.header = a;
		this.proposalStep = b;
		this.proposalStepColor = c;
		this.createdAt = createdAt;
	}

}
