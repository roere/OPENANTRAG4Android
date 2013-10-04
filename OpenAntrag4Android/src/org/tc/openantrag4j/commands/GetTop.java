package org.tc.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.tc.json.JSONArray;
import org.tc.json.JSONObject;
import org.tc.json.JSONTokener;
import org.tc.json.ParseUtils;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;
import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalFactory;
import org.tc.openantrag4j.proposal.ProposalSet;

public abstract class GetTop {


	public static ProposalSet execute (int count) throws OpenAntragException {
		return GetTop.execute(count, Constants.COMMAND_KEY_ALL_REPRESENTATION);
	}
	
	/**
	 * 
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ProposalSet execute(int count, String representationID) throws OpenAntragException {
		
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
		return ParseUtils.parseProposolFileJSON(ar);		
		
	}
	
}
