package org.tc.openantrag4android;

import java.util.ArrayList;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.representation.android.Representative;
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.commands.GetCommittees;
import org.tc.openantrag4j.commands.GetTop;
import org.tc.openantrag4j.commands.android.GetRepresentatives;
import org.tc.openantrag4j.proposal.Comment;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProposalSet;

import com.tc.openantrag4android.R;

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
			//call Error Page Activity
			Intent intent = new Intent(ShowProposalAct.this, ErrorPageAct.class);
			intent.putExtra("class", this.getClass());
			intent.putExtra("exception", e);
			startActivity(intent);

		}
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
		TextView proposalStepText = (TextView)findViewById(R.id.proposalStepText);
		
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
			proposalStepText.setText(proposal.getProposalSteps().get(proposal.getProposalSteps().size()-1).getShortCaption());
			proposalSteps.setBackgroundColor(Color.parseColor(proposal.getProposalSteps().get(proposal.getProposalSteps().size()-1).getColor()));
		} else proposalSteps.setText(Constants.EMPTY_FIELD);
	
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	/**
	 * Loads Representative-Information and Comments.
	 * @author roere
	 *
	 */
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    	
		Representative rep = Storage.representative;
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
			//Reload Data only if Force_Reload is set.
			if (getIntent().getBooleanExtra(Constants.FORCE_RELOAD, false)) {
	        	try {
	    			Storage.comments = GetComments.execute(Storage.proposal.getiD());
	    			Storage.committees = GetCommittees.execute(Storage.proposal.getKeyRepresentation());
	    			ArrayList<Representative> repList = GetRepresentatives.execute(Storage.proposal.getKeyRepresentation());
	    			for (int i=0; i<repList.size();i++) {
	    				if (repList.get(i).getKey().equals(Storage.proposal.getKeyRepresentative())) {
	    					rep = repList.get(i);
	    					break;
	    				}
	    			}    			
	    		} catch (Exception e1) {
	    			//e1.printStackTrace();
	    			//cancel AsyncTask and call Error Page Activity
					this.cancel(true);
	    			Intent intent = new Intent(ShowProposalAct.this, ErrorPageAct.class);
	    			intent.putExtra("class", this.getClass());
	    			intent.putExtra("exception", e1);
	    			startActivity(intent);
	    		} 
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
			
			//make clickable if comments are available
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
            //mProgressDialog.dismiss();
        }
    }	
}
