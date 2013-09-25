package com.openantrag4j.commands.android;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.openantrag4j.json.*;
import com.openantrag4J.Constants;
import com.openantrag4J.OpenAntragException;
import com.openantrag4J.Utils;
import com.openantrag4J.representation.android.Representative;

public abstract class GetRepresentatives {
	
	public static ArrayList<Representative> execute (String representationID) throws OpenAntragException {
		ArrayList<Representative> representatives = new ArrayList<Representative>();
		String u = Utils.getURL(Constants.COMMAND_GET_REPRESENTATIVES, representationID);
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
			Representative rep = new Representative();
			
			
			
			String key = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_KEY)+"";
			String name = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_NAME)+"";
			String party = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_PARTY)+"";
			String mail = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_MAIL)+"";
			String twitter = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_TWITTER)+"";
			String image = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_PORTRAIT_IMAGE)+"";
			String infoHTML = ((JSONObject)ar.get(i)).get(Constants.FIELD_REPRESENTATIVE_INFO_HTML)+"";
			
			rep.setKey(key);
			rep.setName(name);
			rep.setParty(party);
			rep.setMail(mail);
			rep.setTwitter(twitter);
			rep.setInfoHTML(infoHTML);
			
			
			
			if (image!=null&&(!image.equals(""))) {
				try {
					URL imageUrl = new URL (Utils.getBaseURL()+image);
					rep.setPortraitImage(BitmapFactory.decodeStream(imageUrl.openStream()));
				} catch (MalformedURLException e) {
					throw new OpenAntragException("Wrong URL for portrait image:"+image,e);
				} catch (IOException e) {
					throw new OpenAntragException("Error opening URL for portrait image:"+image,e);
				}
			}
			representatives.add(rep);
		}
		return representatives;
	}

}
