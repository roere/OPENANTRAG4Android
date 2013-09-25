package com.tc.openantrag4android;

import java.util.*;

import com.openantrag4J.OpenAntragException;
import com.openantrag4J.representation.Representation;
import com.openantrag4J.representation.RepresentationFactory;
import com.openantrag4j.commands.GetTags;
import com.openantrag4j.commands.GetTop;
import com.openantrag4j.proposal.ProposalFile;

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

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);	
			//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			//StrictMode.setThreadPolicy(policy); 
			
			buildFilterView();
		} catch (Exception e) {
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler (debug)!");
			alert.setMessage(e.getClass()+" - "+e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
		}
	}
	
	private void buildFilterView () {
		List<String> list = new ArrayList<String>();		
		ArrayList<Representation> rList = null;
		ArrayList<String> tList = null;
		try {
			rList = RepresentationFactory.getAll();
			for (int i=0;i<rList.size();i++) {
				list.add(rList.get(i).getName());
			}
			Storage.representationList = rList; //persist representation list
			//list.add(0, "...");
			
			tList = GetTags.execute();
			tList.add(0, "...");
		} catch (OpenAntragException e) {
			list.add("list 1");
			list.add("list 2");
			list.add("list 3");
		}
		
		Spinner lView = (Spinner)findViewById(R.id.representationList);		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);	
		dataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
		lView.setAdapter(dataAdapter);
		
		Spinner tView = (Spinner)findViewById(R.id.tagsList);		
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, tList);	
		dataAdapter2.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
		tView.setAdapter(dataAdapter2);
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
		/*
		Spinner rSpinner = (Spinner)findViewById(R.id.representationList);	
		Spinner tSpinner = (Spinner)findViewById(R.id.tagsList);
		
		String repName = (String)rSpinner.getSelectedItem();
		int itemNo = rSpinner.getSelectedItemPosition();
		String key = Storage.representationList.get(itemNo).getKey();
		ProposalFile file = null;
		try {
			file = GetTop.execute(20,
									key);
			Storage.proposalFile = file;
			Storage.representationKey = key;
			Intent showProposalList = new Intent(this, ProposalListAct.class);
			//showProposalList.putExtra(Constants.REPRESENTATION_KEY, 
			//							key);
			startActivity(showProposalList);
			
		} catch (OpenAntragException e) {
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler!");
			alert.setMessage(e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
		}
		*/
	}
	
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
		ProposalFile file = null;
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("OPENANTRAG");
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
