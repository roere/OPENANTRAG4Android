package org.tc.openantrag4android;

import java.util.ArrayList;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.representation.RepresentationSet;
import org.tc.openantrag4android.adapter.ProposalEntry;
import org.tc.openantrag4android.adapter.ProposalEntryAdapter;
import org.tc.openantrag4j.commands.GetByTag;
import org.tc.openantrag4j.commands.GetCount;
import org.tc.openantrag4j.commands.GetPage;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalSet;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
			/*
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler (debug)!");
			alert.setMessage(e.getClass()+" - "+e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
			*/
			Intent intent = new Intent(ProposalListAct.this, ErrorPageAct.class);
			intent.putExtra("class", this.getClass());
			intent.putExtra("exception", e);
			startActivity(intent);
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
				mainActivity.putExtra(Constants.FORCE_RELOAD, false);
				startActivity(mainActivity);
			}
		});
		
		lView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				Intent showProposal = new Intent(ProposalListAct.this, ShowProposalAct.class);
				showProposal.putExtra(Constants.SELECTED_ITEM, (int)arg3);
				showProposal.putExtra(Constants.FORCE_RELOAD, true);
				Storage.proposal = Storage.proposalFile.get((int)arg3);
				startActivity(showProposal);
				
			}
			
		});
		
        lView.setOnScrollListener(new OnScrollListener() {
        	 
            @Override
            public void onScrollStateChanged(AbsListView view,
                    int scrollState) {
                int threshold = 1;
                int count = lView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (lView.getLastVisiblePosition() >= count
                            - threshold) {
                        // Execute Task to retrieve more Data, at max every 5 seconds
                    	Integer pages = (int)Math.floor(count/Constants.PAGE_COUNT);
                    	if ((System.currentTimeMillis()-Storage.lastReloadProposalPage)>5000)
                    		new RemoteDataTask().execute(pages);
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
	
	/**
	 * Build List of Proposal Entries
	 */
	private void buildProposalList() {
		final ListView lView = (ListView)findViewById(R.id.listViewProposal);
		// build proposal list output		
		ArrayList<ProposalEntry> pElements = new ArrayList<ProposalEntry>();
		RepresentationSet rFile = new RepresentationSet(Storage.representationList);
		for (int i=0;i<Storage.proposalFile.size();i++) {
			Proposal p = Storage.proposalFile.get(i);
			pElements.add(new ProposalEntry(p.getTitleText(),
												AndroidUtils.setVariables(p.getProposalSteps().get(p.getProposalSteps().size()-1).getCaption(),
																			null,
																			null),
												p.getProposalSteps().get(p.getProposalSteps().size()-1).getColor(),
												rFile.getByID(p.getKeyRepresentation()).getName(),
												p.getCreatedAt(),
												(Math.abs(i/2)==((double)i/2))));
		}
		
		ProposalEntryAdapter pAdapter = new ProposalEntryAdapter(this, R.layout.show_proposal_list_item, pElements);
	    lView.setAdapter(pAdapter);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.proposal_menu, menu);
		return true;
	}
	
	/**
	 * 
	 * @author roere
	 *
	 */
    private class RemoteDataTask extends AsyncTask<Integer, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ProposalListAct.this);
            // Set progressdialog message
            mProgressDialog.setMessage("Lade Daten...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
 

        @Override
        protected Void doInBackground(Integer... params) {
        	Integer pages = params[0];
        	try {
        		Proposal p = GetPage.execute(Storage.representationKey, 1, 1).get(0);
				
        		/*
        		 * Refresh proposal list:
        		 * if tag is specified, reload complete list (OA API enables no paging for that)
        		 * if no tag is specified and proposal list on server has no new entry, load new page
        		 * if no tag is specified and proposal list on server has new entries reload list up to the current page
        		 */
				if (Storage.tag!=null) {
					Storage.proposalFile = GetByTag.execute(Storage.representationKey, 
												Storage.tag);
				} else if (Storage.proposalFile.get(0).equals(p)) {
					int upperBound = Storage.proposalFile.size();
					for (int i=upperBound-1;i>=pages*Constants.PAGE_COUNT;i--) {
						Storage.proposalFile.remove(i);
					}
					Storage.proposalFile.addAll(GetPage.execute(Storage.representationKey, 
																	pages+1, 
																	Constants.PAGE_COUNT));
				} else {
					Storage.proposalFile = GetTop.execute(pages*Constants.PAGE_COUNT, 
															Storage.representationKey);
				}
				Storage.lastReloadProposalPage = System.currentTimeMillis();
			} catch (Exception e) {
    			//cancel AsyncTask and call Error Page Activity
				mProgressDialog.dismiss();
    			this.cancel(true);
    			Intent intent = new Intent(ProposalListAct.this, ErrorPageAct.class);
    			intent.putExtra("class", this.getClass());
    			intent.putExtra("exception", e);
    			startActivity(intent);
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
