package org.tc.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalSet;
import org.tc.openantrag4j.proposal.ProcessStep;

public abstract class ParseUtils {

	private ParseUtils() {
	}

	/**
	 * 
	 * @param ar
	 * @return
	 * @throws OpenAntragException
	 */
	public static ProposalSet parseProposolFileJSON (JSONArray ar) throws OpenAntragException {
		ProposalSet result = new ProposalSet();		
		for (int i=0;i<ar.length();i++) {
			Proposal p = new Proposal();
			p.setTextHTML(((JSONObject)ar.get(i)).get(Constants.FIELD_TEXT_HTML)+"");
			p.setTitleText(((JSONObject)ar.get(i)).get(Constants.FIELD_TITLE)+"");
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
			//30.09.13: API seems to be changed. Field 'FeedItem' is no longer available.
			//JSONObject jObj = (JSONObject)((JSONObject)ar.get(i)).get(Constants.FIELD_FEEDITEM);
			//p.setTitleText(((JSONObject)jObj.get(Constants.FIELD_FEED_TITLE)).get(Constants.FIELD_FEED_TEXT)+"");
			
			//parse and set ProposalSteps...
			JSONArray ar2 = (JSONArray)((JSONObject)ar.get(i)).get(Constants.FIELD_PROPOSAL_STEPS);
			ArrayList<ProcessStep> pSteps = new ArrayList<ProcessStep>();
			for (int j=0;j<ar2.length();j++) {
				ProcessStep step = new ProcessStep();
				JSONObject jObject = (JSONObject)((JSONObject)ar2.get(j)).get(Constants.FIELD_PROCESS_STEP);
				step.setCaption(jObject.get(Constants.FIELD_CAPTION)+"");
				step.setShortCaption(jObject.get(Constants.FIELD_SHORT_CAPTION)+"");
				step.setColor(jObject.get(Constants.FIELD_COLOR)+"");
				step.setIdProcessStep(((Integer)((JSONObject)ar2.get(j)).get(Constants.FIELD_ID_PROCESS_STEP)));
				step.setId(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_ID)));
				step.setTimestamp(((Integer)((JSONObject)ar2.get(j)).get(Constants.FIELD_TIMESTAMP)));
				step.setInfoHtml(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_INFO_HTML)));
				step.setInfoText(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_INFO_TEXT)));
				
				String c = ((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_CREATED_AT));
				try {
					step.setCreatedAt(new SimpleDateFormat(Constants.DATE_FORMAT).parse(c));
				} catch (ParseException e) {
					throw new OpenAntragException("Couldn't parse date '"+c+"' for Proposal with ID '"+p.getiD()+"'.",e);
				}
				
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
	}

}
