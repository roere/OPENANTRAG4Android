package org.tc.json;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalSet;
import org.tc.openantrag4j.proposal.ProcessStep;

public abstract class ParseUtils {

	private ParseUtils() {
	}
	
	public static Object getJSONField (String fieldName, JSONObject jObj, boolean returnStringValue) {
		if (jObj.has(fieldName)) {
			if (returnStringValue)
				return jObj.get(fieldName).toString();
			else
				return jObj.get(fieldName);
		}
		if (returnStringValue)
			return "";
		else
			return null;
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
	
			p.setTextHTML((String)ParseUtils.getJSONField(Constants.FIELD_TEXT_HTML, (JSONObject)ar.get(i), true));
			p.setTitleText((String)ParseUtils.getJSONField(Constants.FIELD_TITLE, (JSONObject)ar.get(i), true));
			p.setExternalShortUrl((String)ParseUtils.getJSONField(Constants.FIELD_EXTERNAL_SHORT_URL, (JSONObject)ar.get(i), true));
			p.setShortUrl((String)ParseUtils.getJSONField(Constants.FIELD_SHORT_URL, (JSONObject)ar.get(i), true));
			p.setExternalUrl((String)ParseUtils.getJSONField(Constants.FIELD_EXTERNAL_URL, (JSONObject)ar.get(i), true));
			p.setiDCurrentProposalStep((String)ParseUtils.getJSONField(Constants.FIELD_ID_CURRENT_PROPOSAL_STEP, (JSONObject)ar.get(i), true));
			
			p.setTitleURL((String)ParseUtils.getJSONField(Constants.FIELD_TITLE_URL, (JSONObject)ar.get(i), true));
			p.setiD((String)ParseUtils.getJSONField(Constants.FIELD_ID, (JSONObject)ar.get(i), true));
			p.setKeyRepresentation((String)ParseUtils.getJSONField(Constants.FIELD_KEY_REPRESENTATION, (JSONObject)ar.get(i), true));
			p.setKeyRepresentative((String)ParseUtils.getJSONField(Constants.FIELD_KEY_REPRESENTATIVE, (JSONObject)ar.get(i), true));
			p.setTimeStamp((String)ParseUtils.getJSONField(Constants.FIELD_TIMESTAMP, (JSONObject)ar.get(i), true));
			p.setTextMarkDown((String)ParseUtils.getJSONField(Constants.FIELD_TEXTMARKDOWN, (JSONObject)ar.get(i), true));
			
			p.setKeyCommittee((String)ParseUtils.getJSONField(Constants.FIELD_KEY_COMMITTEE, (JSONObject)ar.get(i), true));
			p.setTextRaw((String)ParseUtils.getJSONField(Constants.FIELD_TEXT_RAW, (JSONObject)ar.get(i), true));
			p.setFullURL((String)ParseUtils.getJSONField(Constants.FIELD_FULL_URL, (JSONObject)ar.get(i), true));
					
			p.setAbuseMessage((String)ParseUtils.getJSONField(Constants.FIELD_ABUSE_MESSAGE, (JSONObject)ar.get(i), true));
			p.setAbuseMessageHTML((String)ParseUtils.getJSONField(Constants.FIELD_ABUSE_MESSAGE_HTML, (JSONObject)ar.get(i), true));
			p.setAbuseMessageText((String)ParseUtils.getJSONField(Constants.FIELD_ABUSE_MESSAGE_TEXT, (JSONObject)ar.get(i), true));
			p.setIsAbuse((String)ParseUtils.getJSONField(Constants.FIELD_IS_ABUSE, (JSONObject)ar.get(i), true));
					
			//parse and add Elements from feed...
			//30.09.13: API seems to be changed. Field 'FeedItem' is no longer available.
			//JSONObject jObj = (JSONObject)((JSONObject)ar.get(i)).get(Constants.FIELD_FEEDITEM);
			//p.setTitleText(((JSONObject)jObj.get(Constants.FIELD_FEED_TITLE)).get(Constants.FIELD_FEED_TEXT)+"");
			
			//parse and set ProposalSteps...
			//JSONArray ar2 = (JSONArray)((JSONObject)ar.get(i)).get(Constants.FIELD_PROPOSAL_STEPS);
			JSONArray ar2 = (JSONArray)ParseUtils.getJSONField(Constants.FIELD_PROPOSAL_STEPS, (JSONObject)ar.get(i), false);
			
			ArrayList<ProcessStep> pSteps = new ArrayList<ProcessStep>();
			for (int j=0;j<ar2.length();j++) {
				ProcessStep step = new ProcessStep();
				//JSONObject jObject = (JSONObject)((JSONObject)ar2.get(j)).get(Constants.FIELD_PROCESS_STEP);
				JSONObject jObject = (JSONObject)ParseUtils.getJSONField(Constants.FIELD_PROCESS_STEP, (JSONObject)ar2.get(j), false);

				step.setCaption((String)ParseUtils.getJSONField(Constants.FIELD_CAPTION, jObject, true));
				step.setShortCaption((String)ParseUtils.getJSONField(Constants.FIELD_SHORT_CAPTION, jObject, true));
				step.setColor((String)ParseUtils.getJSONField(Constants.FIELD_COLOR, jObject, true));
				step.setIdProcessStep((Integer)ParseUtils.getJSONField(Constants.FIELD_ID_PROCESS_STEP, (JSONObject)ar2.get(j), false));
				step.setId((String)ParseUtils.getJSONField(Constants.FIELD_ID, (JSONObject)ar2.get(j), true));
				step.setTimestamp((Integer)ParseUtils.getJSONField(Constants.FIELD_TIMESTAMP, (JSONObject)ar2.get(j), false));
				step.setInfoHtml((String)ParseUtils.getJSONField(Constants.FIELD_INFO_HTML, (JSONObject)ar2.get(j), true));
				step.setInfoText((String)ParseUtils.getJSONField(Constants.FIELD_INFO_TEXT, (JSONObject)ar2.get(j), true));

				
//				step.setCaption(jObject.get(Constants.FIELD_CAPTION)+"");
//				step.setShortCaption(jObject.get(Constants.FIELD_SHORT_CAPTION)+"");
//				step.setColor(jObject.get(Constants.FIELD_COLOR)+"");
//				step.setIdProcessStep(((Integer)((JSONObject)ar2.get(j)).get(Constants.FIELD_ID_PROCESS_STEP)));
//				step.setId(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_ID)));
//				step.setTimestamp(((Integer)((JSONObject)ar2.get(j)).get(Constants.FIELD_TIMESTAMP)));
//				step.setInfoHtml(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_INFO_HTML)));
//				step.setInfoText(((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_INFO_TEXT)));
				
				//String c = ((String)((JSONObject)ar2.get(j)).get(Constants.FIELD_CREATED_AT));
				String c = (String)ParseUtils.getJSONField(Constants.FIELD_CREATED_AT, (JSONObject)ar2.get(j), true);
				try {
					step.setCreatedAt(new SimpleDateFormat(Constants.DATE_FORMAT).parse(c));
				} catch (ParseException e) {
					throw new OpenAntragException("Couldn't parse date '"+c+"' for Proposal with ID '"+p.getiD()+"'.",e);
				}
				
				//parse and add nextSteps...
				ArrayList<String> nSteps = new ArrayList<String>();
 				//StringTokenizer st = new StringTokenizer(jObject.get(Constants.FIELD_ID_NEXT_STEPS)+"",",");
 				StringTokenizer st = new StringTokenizer((String)ParseUtils.getJSONField(Constants.FIELD_ID_NEXT_STEPS, jObject, true),",");
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
	
	/**
	 * Experimental Method, don't use.
	 * @param containerClass
	 * @param jObj
	 * @param caseSensitive
	 * @param allowStringAlternative
	 * @return
	 * @todo
	 */
	public static Object buildObject (Class containerClass, 
										JSONObject jObj, 
										boolean caseSensitive,
										boolean allowStringAlternative) {
		try {
			Method[] methods = containerClass.getMethods();
			Constructor con = containerClass.getConstructor();
			Object instance = con.newInstance();
			
			ArrayList<KeyValue> kvList = new ArrayList<KeyValue>();
			
			Iterator<String> it = jObj.keys();
			while (it.hasNext()) {
				try {
					String key = it.next();
					Object obj = jObj.get(key);
					
					kvList.add(new KeyValue(key, obj));
					
					Class objClass = obj.getClass();
					boolean isString = false;
					
					Method m = null;
					if (caseSensitive)
						try {
							m = containerClass.getMethod("set"+key, objClass);
						} catch (NoSuchMethodException e) {
							m = containerClass.getMethod("set"+key, String.class);
							isString = true;
						}
					else				
						for (int i=0;i<methods.length;i++) {
							Class[] param = methods[i].getParameterTypes();
							if (((methods[i].getName().toLowerCase().equals("set"+key.toLowerCase()))&&
									(param!=null)&&
									(param.length==1))) {
								
								if (param[0].equals(objClass))						
									m = methods[i];
								else if (param[0].equals(String.class)) {
									m = methods[i];
									isString = true;
								}
							}
						}
					if (m!=null)
						if (isString) {
							if (allowStringAlternative)
								m.invoke(instance, obj.toString());
						} else
							m.invoke(instance, obj);
				} catch (NoSuchMethodException e) {
					//Setter method is not available. Value can't be set. Do nothing.
					e.printStackTrace();
				}
			}
			return instance;
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		return null;
	}


}
