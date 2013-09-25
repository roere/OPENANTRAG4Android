package com.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.openantrag4j.json.*;

import com.openantrag4J.Constants;
import com.openantrag4J.OpenAntragException;
import com.openantrag4J.Utils;
import com.openantrag4j.proposal.ProposalFactory;
import com.openantrag4j.proposal.ProposalFile;

public abstract class GetByID {

	public static ProposalFile execute(String representationID) throws OpenAntragException {
		String u = Utils.getURL(Constants.COMMAND_GET_BY_ID)+"/"+representationID;
		URL url; 
		try {
			url = new URL(u);

		} catch (MalformedURLException e) {
			throw new OpenAntragException("Wrong URL:"+u,e);
		}
		JSONTokener tok;
		try {
			tok = null;
			tok = new JSONTokener(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			throw new OpenAntragException("Error opening URL:"+u,e);
		}
		JSONArray ar = new JSONArray(tok);
		return ProposalFactory.parseProposolFileJSON(ar);
	}

}
