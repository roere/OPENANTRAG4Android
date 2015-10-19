package org.tc.openantrag4android;

import java.text.DateFormat;
import java.util.ArrayList;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4android.adapter.CommentEntry;
import org.tc.openantrag4android.adapter.CommentEntryAdapter;
import org.tc.openantrag4android.adapter.ProcessStepEntry;
import org.tc.openantrag4android.adapter.ProcessStepEntryAdapter;
import org.tc.openantrag4android.adapter.ProposalEntry;
import org.tc.openantrag4android.adapter.ProposalEntryAdapter;
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.proposal.Comment;
import org.tc.openantrag4j.proposal.ProcessStep;
import org.tc.openantrag4j.proposal.Proposal;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ShowProposalCommentsAct extends Activity {

	public ShowProposalCommentsAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.proposal_comments);
			
			LinearLayout bButton = (LinearLayout)findViewById(R.id.showProposalListBack);
			bButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent showProposalActivity = new Intent(ShowProposalCommentsAct.this, ShowProposalAct.class);
					startActivity(showProposalActivity);
				}
			});
			ArrayList<Comment> comments = Storage.comments;
			
			/*
			ArrayList<String> pElements = new ArrayList<String>();
			for (int i=0;i<comments.size();i++) {
				Comment c = comments.get(i);
				
				pElements.add(c.getCommentText()+" ("+
								c.getCommentedBy()+", "+
								DateFormat.getInstance().format(c.getCommmentedAt())+")");
			}
			*/
			
			//ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			//		R.layout.proposal_comments_item, pElements);
			//ListView lView = (ListView)findViewById(R.id.listViewProposal);
			//lView.setAdapter(dataAdapter);
			
			ListView lView = (ListView)findViewById(R.id.listViewProposal);
			// build comment list output		
			ArrayList<CommentEntry> cElements = new ArrayList<CommentEntry>();
			for (int i=0;i<comments.size();i++) {
				Comment c = comments.get(i);
				cElements.add(new CommentEntry(c.getCommentedBy(),
													c.getCommentRaw(),
													c.getCommentHTML(),
													c.getCommentedAtTimestamp(),
													c.getCommmentedAt(),
													(Math.abs(i/2)==((double)i/2))));
			}		
			CommentEntryAdapter pAdapter = new CommentEntryAdapter(this, R.layout.show_comment_list_item, cElements);
		    lView.setAdapter(pAdapter);
					
		} catch (Exception e) {
			//cancel AsyncTask and call Error Page Activity
			Intent intent = new Intent(ShowProposalCommentsAct.this, ErrorPageAct.class);
			intent.putExtra("class", this.getClass());
			intent.putExtra("exception", e);
			startActivity(intent);

		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//Intent mainActivity = new Intent(ShowProposalCommentsAct.this, MainActivity.class);
		//startActivity(mainActivity);
	}

}
