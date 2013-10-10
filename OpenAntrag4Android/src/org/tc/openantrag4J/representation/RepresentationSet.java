package org.tc.openantrag4J.representation;

import java.util.ArrayList;
import java.util.Collection;

public class RepresentationSet extends ArrayList<Representation> {

	/**
	 * 
	 */
	public RepresentationSet() {
	}
	
	/**
	 * 
	 * @param list
	 */
	public RepresentationSet(ArrayList<Representation> list) {
		for (int i=0;i<list.size();i++) {
			this.add(list.get(i));
		}
	}

	public RepresentationSet(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public RepresentationSet(Collection<? extends Representation> collection) {
		super(collection);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param representationKey
	 * @return
	 */
	public Representation getByID (String representationKey) {
		for (int i=0;i<this.size();i++) {
			if (this.get(i).getKey().equals(representationKey))
				return this.get(i);
		}
		return null;
	}

}
