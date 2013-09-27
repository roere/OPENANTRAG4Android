/**
 * 
 */
package org.tc.openantrag4j.commands;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.tc.json.JSONArray;
import org.tc.json.JSONObject;
import org.tc.json.JSONTokener;
import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;
import org.tc.openantrag4J.representation.Committee;

/**
 * @author roere
 *
 */
public abstract class GetCommittees {

	/**
	 * Gets a complete List of the Committee-Objects of the specified representation.
	 * With ignoreMalformedURLs = true the method ignores Malformed URLs inside a Committee-Object and sets the field to null.
	 * 
	 * @param representationKey
	 * @return
	 * @throws OpenAntragException
	 */
	public static ArrayList<Committee> execute(String representationKey) throws OpenAntragException {
		return GetCommittees.execute(representationKey, true);
	}
	
	/**
	 * 
	 * @param representationKey
	 * @param ignoreMalFormedURLs
	 * @return
	 * @throws OpenAntragException
	 */
	public static ArrayList<Committee> execute(String representationKey,
												boolean ignoreMalFormedURLs) throws OpenAntragException {
		ArrayList<Committee> result = new ArrayList<Committee>();
		String u = Utils.getURL(Constants.COMMAND_GET_COMMITTEES, representationKey);
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
		/**
		 * @todo
		 */
		for (int i=0;i<ar.length();i++) {
			JSONObject o = (JSONObject)ar.get(i);
			String a = o.getString(Constants.FIELD_KEY);
			String b = o.getString(Constants.FIELD_NAME);
			String c = o.getString(Constants.FIELD_CAPTION);
			String d = o.getString(Constants.FIELD_URL);
			URL e = null;
			try {
				if ((d!=null)&&(!d.equals("")))
						new URL(d);
			} catch (MalformedURLException mfue) {
				if (!ignoreMalFormedURLs)
					throw new OpenAntragException("Committee-Object with key '"+a+"' contains malformed URL:"+d, mfue);
			}
			result.add(new Committee(representationKey, a, b, c, e));
		}
		
		return result;
	}
		
}
