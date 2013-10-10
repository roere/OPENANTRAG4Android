package org.tc.openantrag4android.adapter;

import java.util.Date;

public class ProcessStepEntry {

	protected Date createdAt = null;
	protected String caption = null;
	protected String infoText = "";
	protected String processStepColor = "";
	protected Boolean isEvenRow = true;
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public ProcessStepEntry(String a, String b, Date c, String e, boolean f) {
		
		this.caption = a;
		this.infoText = b;
		this.createdAt = c;
		this.processStepColor = e;
		this.isEvenRow = f;
	}

}
