package org.tc.openantrag4j.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.tc.openantrag4J.Constants;
import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.Utils;

/**
 * 
 * @author roere
 *
 */
public abstract class GetCount {

	/**
	 * 
	 * @param key
	 * @return
	 * @throws OpenAntragException
	 */
	public static Integer execute (String key) throws OpenAntragException {
		Integer result = 0;
		StringBuffer resultString = new StringBuffer();
		String u = Utils.getProposalURL(Constants.COMMAND_GET_COUNT, key);
		URL url; 
		try {
			url = new URL(u);
		} catch (MalformedURLException e) {
			throw new OpenAntragException("Wrong URL:"+u,e);
		}
		
		try {	
			BufferedReader buf = new BufferedReader(new InputStreamReader(url.openStream()));
			String str = buf.readLine();
			while (str!=null) {
				resultString.append(str);
				str = buf.readLine();
			}
			result = Integer.parseInt(resultString.toString());
		} catch (IOException e) {
			throw new OpenAntragException("Error opening URL:"+u,e);
		} catch (NumberFormatException e) {
			throw new OpenAntragException("Invalid return value: '"+resultString.toString()+
											"'. Integer expected. URL:"+u,e);
		}

		return result;
	}

}
