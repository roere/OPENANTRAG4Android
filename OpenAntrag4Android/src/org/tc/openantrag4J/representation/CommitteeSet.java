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

}
