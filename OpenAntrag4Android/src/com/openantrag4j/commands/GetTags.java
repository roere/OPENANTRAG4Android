package com.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


import com.openantrag4J.Constants;
import com.openantrag4J.OpenAntragException;
import com.openantrag4J.Utils;
import com.openantrag4j.json.JSONArray;
import com.openantrag4j.json.JSONTokener;

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
