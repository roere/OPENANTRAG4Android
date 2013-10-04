package org.tc.openantrag4j.proposal;

import java.util.ArrayList;
import java.util.HashMap;

public class ProposalSet extends ArrayList<Proposal>{

	static final long serialVersionUID = 0;
	
	//private ArrayList<Proposal> proposals = new ArrayList<Proposal>();
	private HashMap<String, ArrayList<Proposal>> proposalTags = new HashMap<String, ArrayList<Proposal>>(); 
	
	/**
	 * 
	 */
	public ProposalSet() {
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean contains(Proposal p) {
		for (int i=0;i<this.size();i++) {
			if (p.getiD().equals(this.get(i).getiD()))
					return true;
		}
		return false;
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
