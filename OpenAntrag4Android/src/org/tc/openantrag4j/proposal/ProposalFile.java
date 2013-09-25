package org.tc.openantrag4j.proposal;

import java.util.ArrayList;
import java.util.HashMap;

public class ProposalFile extends ArrayList<Proposal>{

	static final long serialVersionUID = 0;
	
	//private ArrayList<Proposal> proposals = new ArrayList<Proposal>();
	private HashMap<String, ArrayList<Proposal>> proposalTags = new HashMap<String, ArrayList<Proposal>>(); 
	
	/**
	 * 
	 */
	public ProposalFile() {
	}
		
	/**
	 * 
	 * @param tag
	 * @param proposal
	 */
	public void setTag (String tag, Proposal proposal) {
		ArrayList<Proposal> l = this.proposalTags.get(tag);
		if (l==null) {
			l = new ArrayList<Proposal>();
		}
		l.add(proposal);
		this.proposalTags.put(tag, l);
	}
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	public ArrayList<Proposal> getByTag (String tag) {
		return this.proposalTags.get(tag);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getTags () {		
		return new ArrayList<String>(this.proposalTags.keySet());
	}
	
}
