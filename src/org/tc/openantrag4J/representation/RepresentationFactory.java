package org.tc.openantrag4J.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4j.commands.GetKeyValueList;


public abstract class RepresentationFactory {

	/**
	 * 
	 * @return
	 */
	public static Representation getInstance() {
		Representation result = new Representation();
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws OpenAntragException 
	 */
	public static ArrayList<Representation> getAll() throws OpenAntragException {
		/*
		ArrayList<Representation> result = new ArrayList<Representation>();
		
		HashMap<String, String> map = Representation.getKeyValueList();
		Iterator<String> it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String n = it.next();
			result.add(new Representation(n, map.get(n)));
		}
		
		return result;
		*/
		return GetKeyValueList.execute();
	}
	
}
