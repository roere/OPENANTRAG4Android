package org.tc.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.tc.json.JSONArray;
import org.tc.json.JSONObject;
import org.tc.json.JSONTokener;
import org.tc.json.ParseUtils;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;
import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4j.proposal.Comment;

public abstract class GetComments {


	/**
	 * @throws OpenAntragException 
	 * 
	 */
	public static ArrayList<Comment> execute (String proposalID) throws OpenAntragException {
		ArrayList<Comment> tags = new ArrayList<Comment>();
		String u = Utils.getCommentsURL(proposalID);
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
		for (int i=0;i<ar.length();i++) {
			
			String a = (String)ParseUtils.getJSONField(Constants.FIELD_COMMENTED_AT_TIMESTAMP, (JSONObject)ar.get(i), true);
			String b = (String)ParseUtils.getJSONField(Constants.FIELD_COMMENTED_AT, (JSONObject)ar.get(i), true);

			Date b1 = null;
			try {
				b1 = new SimpleDateFormat(Constants.DATE_FORMAT).parse(b);
			} catch (ParseException e) {
				throw new OpenAntragException("Couldn't parse date '"+b+"' this comment.",e);
			}
			String c = (String)ParseUtils.getJSONField(Constants.FIELD_COMMENTED_BY, (JSONObject)ar.get(i), true);
			String d = (String)ParseUtils.getJSONField(Constants.FIELD_COMMENT_HTML, (JSONObject)ar.get(i), true);
			String e = (String)ParseUtils.getJSONField(Constants.FIELD_COMMENT_TEXT, (JSONObject)ar.get(i), true);

			tags.add(new Comment(a, b1, c, d, e));
		}
		return tags;
	}

}
