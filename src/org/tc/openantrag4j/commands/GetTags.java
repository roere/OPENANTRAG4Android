package org.tc.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.tc.json.JSONArray;
import org.tc.json.JSONTokener;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;

public abstract class GetTags {

	/**
	 * Returns the tags of all proposals.
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ArrayList<String> execute() throws OpenAntragException {
		ArrayList<String> tags = new ArrayList<String>();
		String u = Utils.getURL(Constants.COMMAND_GET_TAGS);
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
			tags.add(ar.get(i).toString());
		}
		return tags;
	}

}
