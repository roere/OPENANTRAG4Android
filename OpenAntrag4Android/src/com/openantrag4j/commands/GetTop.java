package com.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import com.openantrag4J.Constants;
import com.openantrag4J.OpenAntragException;
import com.openantrag4J.Utils;
import com.openantrag4J.representation.Representation;
import com.openantrag4j.json.JSONArray;
import com.openantrag4j.json.JSONObject;
import com.openantrag4j.json.JSONTokener;
import com.openantrag4j.proposal.Proposal;
import com.openantrag4j.proposal.ProposalFactory;
import com.openantrag4j.proposal.ProposalFile;

public abstract class GetTop {


	public static ProposalFile execute (int count) throws OpenAntragException {
		return GetTop.execute(count, Constants.COMMAND_KEY_ALL_REPRESENTATION);
	}
	
	/**
	 * 
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ProposalFile execute(int count, String representationID) throws OpenAntragException {
		
		String u = Utils.getTop(count, representationID);
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
