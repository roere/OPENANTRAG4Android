package org.tc.openantrag4J.representation;

import java.util.ArrayList;
import java.util.Collection;

public class CommitteeSet extends ArrayList<Committee> {

	public CommitteeSet() {
	}

	public CommitteeSet(int initialCapacity) {
		super(initialCapacity);
	}

	public CommitteeSet(Collection<? extends Committee> c) {
		super(c);
	}
	
	/**
	 * 
	 * @param committeID
	 * @return
	 */
	public String getNameByID (String committeID) {
		for (int i=0;i<this.size();i++) {
			if (this.get(i).getKey().equals(committeID)) {
				return this.get(i).getName();
			}
		}
		return null;
	}

}
