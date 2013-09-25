package org.tc.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.tc.json.JSONArray;
import org.tc.json.JSONObject;
import org.tc.json.JSONTokener;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;
import org.tc.openantrag4J.representation.Representation;

public abstract class GetKeyValueList {

	/**
	 * Returns the tags of all proposals.
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ArrayList<Representation> execute() throws OpenAntragException {
		ArrayList<Representation> tags = new ArrayList<Representation>();
		String u = Utils.getURL(Constants.COMMAND_GET_KEY_VALUE_LIST);
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
			tags.add(new Representation(((JSONObject)ar.get(i)).get(Constants.FIELD_KEY).toString(), 
											((JSONObject)ar.get(i)).get(Constants.FIELD_VALUE).toString()));
		}
		return tags;
	}


}
