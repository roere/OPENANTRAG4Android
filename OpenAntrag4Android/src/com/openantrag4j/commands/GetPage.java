package com.openantrag4j.commands;

import com.openantrag4J.OpenAntragException;
import com.openantrag4j.proposal.ProposalFactory;
import com.openantrag4j.proposal.ProposalFile;

public abstract class GetPage {

	public GetPage() {
	}
	
	public static ProposalFile execute(String representationID, int page, int pageCount) throws OpenAntragException {
		return ProposalFactory.getByRepresentationID(representationID, page, pageCount);		
	}

}
