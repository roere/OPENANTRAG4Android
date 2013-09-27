package org.tc.openantrag4android;

import java.util.*;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.representation.Representation;
import org.tc.openantrag4J.representation.RepresentationFactory;
import org.tc.openantrag4j.commands.GetCount;
import org.tc.openantrag4j.commands.GetTags;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.proposal.ProposalFile;

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

/**
 * 
 * @author roere
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);	
			//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			//StrictMode.setThreadPolicy(policy); 
			
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
	 * 
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
		ArrayList<String> tList = null;
		List<String> list = new ArrayList<String>();		
    	
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
            //mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
        	list = new ArrayList<String>();		
    		ArrayList<Representation> rList = null;
    		tList = null;
    		try {
    			rList = RepresentationFactory.getAll();
    			for (int i=0;i<rList.size();i++) {
    				list.add(rList.get(i).getName());
    			}
    			Storage.representationList = rList; //persist representation list
    			
    			tList = GetTags.execute();
    			tList.add(0, "...");
    		} catch (OpenAntragException e) {
    			e.printStackTrace();
    			AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
    			alert.setTitle("Fehler!");
    			alert.setMessage(e.getMessage());
    			alert.setCanceledOnTouchOutside(true);
    			alert.show();
    		}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
    		Spinner lView = (Spinner)findViewById(R.id.representationList);		
    		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
    			android.R.layout.simple_spinner_item, list);	
    		dataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    		lView.setAdapter(dataAdapter);
    		
    		Spinner tView = (Spinner)findViewById(R.id.tagsList);		
    		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(MainActivity.this,
    			android.R.layout.simple_spinner_item, tList);	
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
        	ArrayList<Representation> rList = Storage.representationList;
        	proposalCount = new ArrayList<Integer>();
        	for (int i=0;i<rList.size();i++) {
            	try {
					proposalCount.add(GetCount.execute(rList.get(i).getKey()));
				} catch (OpenAntragException e) {
					//do absolutely nothing
				}        		
        	}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
    		Spinner lView = (Spinner)findViewById(R.id.representationList);
    		ArrayList<String> elementList = new ArrayList<String>();
    		ArrayAdapter<String> adapter = (ArrayAdapter<String>)lView.getAdapter();
    		for (int i=0;i<adapter.getCount();i++) {
    			elementList.add(adapter.getItem(i)+" ("+proposalCount.get(i)+")");
    		}
    		adapter = new ArrayAdapter<String>(MainActivity.this,
        											R.layout.multiline_spinner_item, 
        											elementList);
    		adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    		lView.setAdapter(adapter);
        }
    }

	
    /**
     * AsyncTask loads proposallist for next Activity
     * @author roere
     *
     */
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
		ProposalFile file = null;
    	
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
    		ProposalFile file = null;
    		try {
    			file = GetTop.execute(20,
    									key);
    			Storage.proposalFile = file;
    			Storage.representationKey = key;    			
    		} catch (OpenAntragException e) {
    			e.printStackTrace();
    			AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
    			alert.setTitle("Fehler!");
    			alert.setMessage(e.getMessage());
    			alert.setCanceledOnTouchOutside(true);
    			alert.show();
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
