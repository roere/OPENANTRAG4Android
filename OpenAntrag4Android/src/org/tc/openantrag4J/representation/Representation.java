package org.tc.openantrag4J.representation;

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
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.commands.GetKeyValueList;
import org.tc.openantrag4j.commands.GetTags;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.proposal.ProposalFactory;
import org.tc.openantrag4j.proposal.ProposalSet;

public class Representation {

	public String key = "";
	public String name = "";
	
	/**
	 * 
	 */
	protected Representation() {
	}
	
	/**
	 * 
	 * @param key
	 * @param name
	 */
	public Representation(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 * @throws OpenAntragException 
	 * @Deprecated
	 */
	public static HashMap<String, String> getKeyValueList() throws OpenAntragException {
		HashMap<String, String> result = new HashMap<String, String>();
		URL url = null;
		String u = Utils.getURL(Constants.COMMAND_GET_KEY_VALUE_LIST);
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
		
		int i = ar.length();
		for (int j=0;j<i;j++) {
			if (ar.get(j).getClass().equals(JSONObject.class)) {
				JSONObject o = (JSONObject)ar.get(j);
				String key = JSONObject.getNames(o)[1];
				String value = JSONObject.getNames(o)[0];
				result.put(o.getString(key), o.getString(value));
			}
		}

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ArrayList<Representation> rList = GetKeyValueList.execute();
			//ProposalSet file = ProposalFactory.getAll(0,20);
			ProposalSet file = GetTop.execute(20, "wiesbaden");
			ProposalFactory.getComments(file);
			GetComments.execute(file.get(0).getiD());
			ArrayList<String> tags = file.getTags();
			for (int i=0;i<tags.size();i++)
				System.out.println(tags.get(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
