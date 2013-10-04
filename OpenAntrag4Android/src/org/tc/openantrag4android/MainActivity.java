package org.tc.openantrag4android;

import java.util.*;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4J.representation.RepresentationFactory;
import org.tc.openantrag4j.commands.GetByTag;
import org.tc.openantrag4j.commands.GetCount;
import org.tc.openantrag4j.commands.GetTags;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.proposal.ProposalSet;

import com.tc.openantrag4android.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 
 * @author roere
 *
 */
public class MainActivity extends Activity {
	
	Boolean forceReload = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		try {
			super.onCreate(savedInstanceState);
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			
			//reload on first call or after a defined period of time.
			forceReload = getIntent().getBooleanExtra(Constants.FORCE_RELOAD, true)||
					((System.currentTimeMillis()-Storage.lastReloadMainPage)>(Constants.RELOAD_MAIN*1000));
			
			setContentView(R.layout.activity_main);	
			
    		Spinner lView = (Spinner)findViewById(R.id.representationList);		
    		Spinner tView = (Spinner)findViewById(R.id.tagsList);		
    		
    		//set onItemSelectListener for REPRESENTATION Spinner
    		lView.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
    		
    		//set onItemSelectListener for TAG Spinner
    		tView.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String tag = (String)((Spinner)arg0).getItemAtPosition(arg2);
					Spinner lView = (Spinner)findViewById(R.id.representationList);
		    		String rep = (String)lView.getItemAtPosition(0);
					if (tag.equals(Constants.ALL_IDENTIFIER)) {
			    		if (rep.equals(Constants.ALL_IDENTIFIER)) {
			    			ArrayAdapter<String> adapter = (ArrayAdapter<String>)lView.getAdapter();
			    			adapter.remove(Constants.ALL_IDENTIFIER);
			    			Storage.representationList.remove(0);
			    		}
					} else {
						if (!rep.equals(Constants.ALL_IDENTIFIER)) {
			    			ArrayAdapter<String> adapter = (ArrayAdapter<String>)lView.getAdapter();
			    			adapter.insert(Constants.ALL_IDENTIFIER, 0);
			    			Storage.representationList.add(0, 
			    					new Representation(org.tc.openantrag4J.Constants.COMMAND_KEY_ALL_REPRESENTATION,
			    										org.tc.openantrag4J.Constants.COMMAND_KEY_ALL_REPRESENTATION));
			    		}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
			
			new RemotePreDataTask().execute();
		} catch (Exception e) {
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler (debug)!");
			alert.setMessage(e.getClass()+" - "+e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
		}
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
		
	/**
	 * Starts if search button was clicked.
	 * Executes an AsyncTask to load proposal lists for the next view.
	 */
	public void onClickFind(View view) {		
		new RemoteDataTask().execute();
	}

	/**
	 * AsyncTask retrieves data to fill Spinner: Representations, Tags 
	 * @author roere
	 *
	 */
    private class RemotePreDataTask extends AsyncTask<Void, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;		
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog message
            mProgressDialog.setMessage("Lade Daten...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
        	if (forceReload) {
	    		ArrayList<Representation> rList = null;
	    		ArrayList<String> tList = null;
	    		try {
	    			rList = RepresentationFactory.getAll();
	    			Storage.representationList = rList; //persist representation list
	    			
	    			tList = GetTags.execute();
	    			tList.add(0, Constants.ALL_IDENTIFIER);
	    			Storage.tagList = tList;
	    		} catch (OpenAntragException e) {
	    			e.printStackTrace();
	    		}
        	}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
        	ArrayList<String> list = new ArrayList<String>();		
			for (int i=0;i<Storage.representationList.size();i++) {
				list.add(Storage.representationList.get(i).getName());
			}
    		Spinner lView = (Spinner)findViewById(R.id.representationList);		
    		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
    			android.R.layout.simple_spinner_item, list);	
    		dataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    		lView.setAdapter(dataAdapter);
    		
    		Spinner tView = (Spinner)findViewById(R.id.tagsList);		
    		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(MainActivity.this,
    			android.R.layout.simple_spinner_item, Storage.tagList);	
    		dataAdapter2.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    		tView.setAdapter(dataAdapter2);
            mProgressDialog.dismiss();
            new ProposalCountRemoteDataTask().execute();
        }
    }
    
    /**
     * Loads the number of proposals for each representation and adds that information to the spinner entries.
     * @author roere
     *
     */
    private class ProposalCountRemoteDataTask extends AsyncTask<Void, Void, Void> {
    	    	
    	ArrayList<Integer> proposalCount = null;
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
        	if (forceReload) {
	        	ArrayList<Representation> rList = Storage.representationList;
	        	proposalCount = new ArrayList<Integer>();
	        	for (int i=0;i<rList.size();i++) {
	            	try {
						proposalCount.add(GetCount.execute(rList.get(i).getKey()));
					} catch (OpenAntragException e) {
						//do absolutely nothing
					}        		
	        	}
	        	Storage.proposalCount = proposalCount;
        	}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
    		Spinner lView = (Spinner)findViewById(R.id.representationList);
    		
    		//check, if All_Representations is selectable (Item 0) and start updating entries starting
    		//with position 1 in that case
    		Integer increment = 0;
    		if (((String)lView.getItemAtPosition(0)).equals(Constants.ALL_IDENTIFIER))
    			increment = 1;
    			
    		//load spinner entries (representations) and add number of proposals 
    		ArrayList<String> elementList = new ArrayList<String>();
    		ArrayAdapter<String> adapter = (ArrayAdapter<String>)lView.getAdapter();
    		for (int i=increment;i<adapter.getCount();i++) {
    			elementList.add(adapter.getItem(i)+" ("+Storage.proposalCount.get(i-increment)+")");
    		}
    		adapter = new ArrayAdapter<String>(MainActivity.this,
        											R.layout.multiline_spinner_item, 
        											elementList);
    		adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    		lView.setAdapter(adapter);
    		
    		//set forceReload to false. Reload will start after defined time (s. Constants).
    		forceReload = false;
        }
    }

	
    /**
     * AsyncTask loads proposallist for next Activity
     * @author roere
     *
     */
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
		ProposalSet file = null;
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            //mProgressDialog.setTitle("OPENANTRAG");
            // Set progressdialog message
            mProgressDialog.setMessage("Lade Daten...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
    		Spinner rSpinner = (Spinner)findViewById(R.id.representationList);	
    		Spinner tSpinner = (Spinner)findViewById(R.id.tagsList);
    		
    		int itemNo = rSpinner.getSelectedItemPosition();
    		String key = Storage.representationList.get(itemNo).getKey();
    		String tag = (String)tSpinner.getSelectedItem();
    		ProposalSet file = null;
    		try {
    			/*
    			 * Load Top 20 Proposal of the given Representation if no tag is specified or all proposal of that representation with the given tag. 
    			 */
    			if (tag.equals(Constants.ALL_IDENTIFIER)) {
    				file = GetTop.execute(Constants.PAGE_COUNT, key);
    				Storage.tag = null;
    			} else {
    				file = GetByTag.execute(key, tag);
    				Storage.tag = tag;
    			}
    			Storage.proposalFile = file;
    			Storage.representationKey = key;    			
    		} catch (OpenAntragException e) {
    			e.printStackTrace();
    		}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
			Intent showProposalList = new Intent(MainActivity.this, ProposalListAct.class);
			startActivity(showProposalList);

        }
    }

}
