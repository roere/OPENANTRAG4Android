package org.tc.openantrag4android.adapter;

import java.util.Date;

public class ProposalEntry {

	protected Date createdAt = null;
	protected String representation = null;
	protected String header = "";
	protected String proposalStep = "";
	protected String proposalStepColor = "";
	protected boolean isEvenRow = true;
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public ProposalEntry(String a, String b, String c, String d, Date e, boolean f) {
		
		this.header = a;
		this.proposalStep = b;
		this.proposalStepColor = c;
		this.representation = d;
		this.createdAt = e;
		this.isEvenRow = f;
	}

}
