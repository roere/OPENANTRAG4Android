package org.tc.openantrag4j.commands;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4j.proposal.ProposalFactory;
import org.tc.openantrag4j.proposal.ProposalSet;

public abstract class GetPage {

	public GetPage() {
	}
	
	public static ProposalSet execute(String representationID, int page, int pageCount) throws OpenAntragException {
		return ProposalFactory.getByRepresentationID(representationID, page, pageCount);		
	}

}
