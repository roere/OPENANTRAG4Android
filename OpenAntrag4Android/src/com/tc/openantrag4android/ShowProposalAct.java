package com.tc.openantrag4android;

import java.util.ArrayList;

import com.openantrag4J.OpenAntragException;
import com.openantrag4J.representation.android.Representative;
import com.openantrag4j.commands.GetComments;
import com.openantrag4j.commands.GetTop;
import com.openantrag4j.commands.android.GetRepresentatives;
import com.openantrag4j.proposal.Comment;
import com.openantrag4j.proposal.Proposal;
import com.openantrag4j.proposal.ProposalFile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 
 * @author roere
 *
 * @todo: remove options menu	
 */
public class ShowProposalAct extends Activity {
	
	public ShowProposalAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.show_proposal);
			
			buildView(Storage.proposal);
			new RemoteDataTask().execute();
			
			LinearLayout lLayout = (LinearLayout)findViewById(R.id.showProposalBack);
			lLayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent showProposalList = new Intent(ShowProposalAct.this, ProposalListAct.class);
					showProposalList.putExtra(Constants.REPRESENTATION_KEY, Storage.representationKey);
					startActivity(showProposalList);
				}
			});
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
		
		//getMenuInflater().inflate(R.menu.proposal_menu, menu);
		
		return true;
	}
	
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
	
	/**
	 * 
	 */
	private void buildView (Proposal proposal) {
		TextView headline = (TextView)findViewById(R.id.showProposalHeadline);
		TextView body = (TextView)findViewById(R.id.showProposalBody);
		TextView tags = (TextView)findViewById(R.id.showProposalTags);
		LinearLayout pView = (LinearLayout)findViewById(R.id.showProposalSteps);
		
		pView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowProposalAct.this, ShowProposalProcessAct.class);
				startActivity(intent);
			}
		});
		
		TextView proposalSteps = (TextView)findViewById(R.id.proposalSteps);
		
		headline.setText(proposal.getTitleText());
		body.setText(proposal.getTextMarkDown());
		
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<proposal.getTags().size();i++) {
			sb.append(proposal.getTags().get(i));
			if ((i+1)<proposal.getTags().size()) 
				sb.append(", ");
		}
		if (proposal.getTags().size()>0) {
			tags.setText(sb);
		} else tags.setText("");
		
		//print proposal steps...
		if (proposal.getProposalSteps().size()>0) {
			proposalSteps.setText(proposal.getProposalSteps().get(proposal.getProposalSteps().size()-1).getShortCaption());
			proposalSteps.setBackgroundColor(Color.parseColor(proposal.getProposalSteps().get(proposal.getProposalSteps().size()-1).getColor()));
		} else proposalSteps.setText(Constants.EMPTY_FIELD);
	
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    	
    	ProgressDialog mProgressDialog = null;
    	Representative rep = null;
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ShowProposalAct.this);
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
    		try {
    			ArrayList<Comment> cList = GetComments.execute(Storage.proposal.getiD());
    			Storage.comments = cList;
    		} catch (OpenAntragException e) {
    			e.printStackTrace();
    		}
       		try {
    			ArrayList<Representative> repList = GetRepresentatives.execute(Storage.proposal.getKeyRepresentation());
    			for (int i=0; i<repList.size();i++) {
    				if (repList.get(i).getKey().equals(Storage.proposal.getKeyRepresentative())) {
    					rep = repList.get(i);
    					break;
    				}
    			}    			
    		} catch (OpenAntragException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
        	TextView comments = (TextView)findViewById(R.id.showCommentCount);
        	LinearLayout cView = (LinearLayout)findViewById(R.id.showComments);
    		TextView representative = (TextView)findViewById(R.id.representative);
    		LinearLayout rView = (LinearLayout)findViewById(R.id.repLine);

        	comments.setText("  "+Storage.comments.size()+"  ");
			
			//make clickable if there are any comments
			if (Storage.comments.size()>0) {
				cView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ShowProposalAct.this, ShowProposalCommentsAct.class);
						startActivity(intent);
					}
				});
			}
			if (rep==null) { //no representative data found...
				String s = Storage.proposal.getKeyRepresentative();
				representative.setText(AndroidUtils.null2Empty(s));
			} else { //further representative data available...
				String s = rep.getName();
				representative.setText(AndroidUtils.null2Empty(s));
				Storage.representative = rep;
				
				//make clickable to show more information about the representative...
				rView.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ShowProposalAct.this, ShowRepresentativeAct.class);
						startActivity(intent);
					}
				});
			}
            mProgressDialog.dismiss();
        }
    }	
}
