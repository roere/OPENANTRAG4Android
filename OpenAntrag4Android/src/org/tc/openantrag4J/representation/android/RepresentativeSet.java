package org.tc.openantrag4J.representation.android;

import java.util.ArrayList;

public class RepresentativeSet extends ArrayList<Representative> {
	
	/**
	 * 
	 * @param representativeID
	 * @return
	 */
	public Representative getByID (String representativeID) {
		for (int i=0; i<this.size();i++) {
			if (this.get(i).getKey().equals(representativeID))
				return this.get(i);
		}
		return null;
	}

}
