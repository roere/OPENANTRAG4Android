package org.tc.openantrag4android;

import java.util.ArrayList;
import java.util.Calendar;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4android.adapter.ProposalEntry;
import org.tc.openantrag4android.adapter.ProposalEntryAdapter;
import org.tc.openantrag4j.commands.GetPage;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalFile;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProposalListAct extends Activity {
	
	protected ScrollView sView = null;
	//protected ListView lView = null;
	
	public ProposalListAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.show_proposal_list);
		
			//String repKey = getIntent().getStringExtra(Constants.REPRESENTATION_KEY);
			//Storage.representationKey = repKey;
			
			this.buildView();
			this.buildProposalList();
		} catch (Exception e) {
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler (debug)!");
			alert.setMessage(e.getClass()+" - "+e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
		}
	}	
	
	/**
	 * 
	 */
	public void buildView () {
		final ListView lView = (ListView)findViewById(R.id.listViewProposal);
		LinearLayout bButton = (LinearLayout)findViewById(R.id.showProposalListBack);
	
		bButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainActivity = new Intent(ProposalListAct.this, MainActivity.class);
				startActivity(mainActivity);
			}
		});
		
		lView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				Intent showProposal = new Intent(ProposalListAct.this, ShowProposalAct.class);
				showProposal.putExtra(Constants.SELECTED_ITEM, (int)arg3);
				Storage.proposal = Storage.proposalFile.get((int)arg3);
				startActivity(showProposal);
				
			}
			
		});
		
        lView.setOnScrollListener(new OnScrollListener() {
        	 
            @Override
            public void onScrollStateChanged(AbsListView view,
                    int scrollState) { // TODO Auto-generated method stub
                int threshold = 1;
                int count = lView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (lView.getLastVisiblePosition() >= count
                            - threshold) {
                        // Execute Task to retrieve more Data, at max every 2 seconds
                    	Integer pages = (int)Math.floor(count/Constants.pageCount);
                   		Integer rest = (int)Math.abs(count - (pages)*Constants.pageCount);
                    	if ((System.currentTimeMillis()-Storage.lastReload)>5000)
                    		new RemoteDataTask().execute(pages, rest, count);

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub

            }

        });				
	}
	
	public void buildProposalList() {
		final ListView lView = (ListView)findViewById(R.id.listViewProposal);
		// build proposal list output		
		ArrayList<ProposalEntry> pElements = new ArrayList<ProposalEntry>();
		for (int i=0;i<Storage.proposalFile.size();i++) {
			Proposal p = Storage.proposalFile.get(i);
			pElements.add(new ProposalEntry(p.getTitleText(), 
												p.getProposalSteps().get(p.getProposalSteps().size()-1).getCaption(),
												p.getProposalSteps().get(p.getProposalSteps().size()-1).getColor(),
												p.getCreatedAt()));
		}
		
		ProposalEntryAdapter pAdapter = new ProposalEntryAdapter(this, R.layout.list_item, pElements);
	    lView.setAdapter(pAdapter);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.proposal_menu, menu);
		return true;
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
    private class RemoteDataTask extends AsyncTask<Integer, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ProposalListAct.this);
            // Set progressdialog title
            //mProgressDialog.setTitle("OPENANTRAG");
            // Set progressdialog message
            mProgressDialog.setMessage("Lade Daten...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Integer... params) {
        	Integer pages = params[0];
        	Integer rest = params[1];
        	Integer count = params[2];
        	try {
				ProposalFile file = null;				
				if (rest==0) {
					file = GetPage.execute(Storage.representationKey, 
											pages+1, 
											Constants.pageCount);
					Storage.proposalFile.addAll(file);
				} else {
					for (int i=0;i<rest;i++) {
						file = GetPage.execute(Storage.representationKey, 
												count+1+i, 
												1);
						Storage.proposalFile.addAll(file);
					}
				}
				if (file.size()>0) {
					
				}
				Storage.lastReload = System.currentTimeMillis();
			} catch (OpenAntragException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
			ProposalListAct.this.buildProposalList();
            mProgressDialog.dismiss();
	
        }
    }

}
