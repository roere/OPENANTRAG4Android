package org.tc.openantrag4j.proposal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.tc.json.*;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;
import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4j.commands.GetComments;

public abstract class ProposalFactory {

	private ProposalFactory() {
	}
	
	/**
	 * 
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ProposalSet getAll(int page, int pageCount) throws OpenAntragException {
		return ProposalFactory.getByRepresentationID(Constants.COMMAND_KEY_ALL_REPRESENTATION,page,pageCount);
	}
	
	/**
	 * 
	 * @param rep
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ProposalSet getByRepresentation(Representation rep,int page, int pageCount) throws OpenAntragException {
		return ProposalFactory.getByRepresentationID(rep.getKey(),page,pageCount);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ProposalSet getByRepresentationID(String id,int page, int pageCount) throws OpenAntragException {	
		String u = Utils.getPageURL(page, pageCount, id);
		URL url = null;
		
		try {
			url = new URL(u);
		} catch (MalformedURLException e) {
			throw new OpenAntragException("Wrong URL:"+u,e);
		}
		
		JSONTokener tok = null;
		try {
			tok = new JSONTokener(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			throw new OpenAntragException("Error opening URL:"+u,e);
		}		
		JSONArray ar = new JSONArray(tok);
		return ParseUtils.parseProposolFileJSON(ar);		
	}
	
	/**
	 * 
	 * @param ar
	 * @return
	 * @throws OpenAntragException
	 */
	public static ProposalSet parseProposolFileJSON (JSONArray ar) throws OpenAntragException {
		return ParseUtils.parseProposolFileJSON(ar);		
		/*
		ProposalSet result = new ProposalSet();		
		for (int i=0;i<ar.length();i++) {
			Proposal p = new Proposal();
			p.setTextHTML(((JSONObject)ar.get(i)).get(Constants.FIELD_TEXT_HTML)+"");
			p.setExternalShortUrl(((JSONObject)ar.get(i)).get(Constants.FIELD_EXTERNAL_SHORT_URL)+"");
			p.setShortUrl(((JSONObject)ar.get(i)).get(Constants.FIELD_SHORT_URL)+"");
			p.setExternalUrl(((JSONObject)ar.get(i)).get(Constants.FIELD_EXTERNAL_URL)+"");
			p.setiDCurrentProposalStep(((JSONObject)ar.get(i)).get(Constants.FIELD_ID_CURRENT_PROPOSAL_STEP)+"");
			
			p.setTitleURL(((JSONObject)ar.get(i)).get(Constants.FIELD_TITLE_URL)+"");
			p.setiD(((JSONObject)ar.get(i)).get(Constants.FIELD_ID)+"");
			p.setKeyRepresentation(((JSONObject)ar.get(i)).get(Constants.FIELD_KEY_REPRESENTATION)+"");
			p.setKeyRepresentative(((JSONObject)ar.get(i)).get(Constants.FIELD_KEY_REPRESENTATIVE)+"");
			p.setTimeStamp(((JSONObject)ar.get(i)).get(Constants.FIELD_TIMESTAMP)+"");	
			p.setTextMarkDown(((JSONObject)ar.get(i)).get(Constants.FIELD_TEXTMARKDOWN)+"");	
			p.setKeyCommittee(((JSONObject)ar.get(i)).get(Constants.FIELD_KEY_COMMITTEE)+"");	
			p.setTextRaw(((JSONObject)ar.get(i)).get(Constants.FIELD_TEXT_RAW)+"");	
			p.setFullURL(((JSONObject)ar.get(i)).get(Constants.FIELD_FULL_URL)+"");	
			
			p.setAbuseMessage(((JSONObject)ar.get(i)).get(Constants.FIELD_ABUSE_MESSAGE)+"");	
			p.setAbuseMessageHTML(((JSONObject)ar.get(i)).get(Constants.FIELD_ABUSE_MESSAGE_HTML)+"");	
			p.setAbuseMessageText(((JSONObject)ar.get(i)).get(Constants.FIELD_ABUSE_MESSAGE_TEXT)+"");	
			p.setIsAbuse(((JSONObject)ar.get(i)).get(Constants.FIELD_IS_ABUSE)+"");	
				
			//parse and add Elements from feed...
			JSONObject jObj = (JSONObject)((JSONObject)ar.get(i)).get(Constants.FIELD_FEEDITEM);
			p.setTitleText(((JSONObject)jObj.get(Constants.FIELD_FEED_TITLE)).get(Constants.FIELD_FEED_TEXT)+"");
			
			//parse and set ProposalSteps...
			JSONArray ar2 = (JSONArray)((JSONObject)ar.get(i)).get(Constants.FIELD_PROPOSAL_STEPS);
			ArrayList<ProcessStep> pSteps = new ArrayList<ProcessStep>();
			for (int j=0;j<ar2.length();j++) {
				ProcessStep step = new ProcessStep();
				JSONObject jObject = (JSONObject)((JSONObject)ar2.get(j)).get(Constants.FIELD_PROCESS_STEP);
				step.setCaption(jObject.get(Constants.FIELD_CAPTION)+"");
				step.setShortCaption(jObject.get(Constants.FIELD_SHORT_CAPTION)+"");
				step.setColor(jObject.get(Constants.FIELD_COLOR)+"");
				
				//parse and add nextSteps...
				ArrayList<String> nSteps = new ArrayList<String>();
 				StringTokenizer st = new StringTokenizer(jObject.get(Constants.FIELD_ID_NEXT_STEPS)+"",",");
				for (int k=0;k<st.countTokens();k++) {
					nSteps.add(st.nextToken());
				}
				step.setIdNextSteps(nSteps);
				
				pSteps.add(step);
			}
			p.setProposalSteps(pSteps);
			
			//parse and set DateField...
			String c = ((JSONObject)ar.get(i)).get(Constants.FIELD_CREATED_AT)+"";
			try {
				p.setCreatedAt(new SimpleDateFormat(Constants.DATE_FORMAT).parse(c));
			} catch (ParseException e) {
				throw new OpenAntragException("Couldn't parse date '"+c+"' for Proposal with ID '"+p.getiD()+"'.",e);
			}
			
			//parse and set TagList...
			StringTokenizer sTok = new StringTokenizer(((JSONObject)ar.get(i)).get(Constants.FIELD_TAGS_LIST)+"", ",");
			ArrayList<String> tags = new ArrayList<String>();
			while (sTok.hasMoreElements()) {
				String t = sTok.nextToken();
				tags.add(t);
				result.setTag(t, p);
			}
			p.setTags(tags);
						
			result.add(p);
		}
		return result;
		*/
	}
	
	/**
	 * 
	 * @param file
	 * @throws OpenAntragException 
	 */
	public static void getComments(ProposalSet file) throws OpenAntragException {
		for (int i=0;i<file.size();i++) {
			ProposalFactory.getComments(file.get(i));
		}
	}
	
	/**
	 * 
	 * @param proposal
	 * @throws OpenAntragException 
	 */
	public static void getComments (Proposal proposal) throws OpenAntragException {
		proposal.setProposalComments(GetComments.execute(proposal.getiD()));
	}

}
