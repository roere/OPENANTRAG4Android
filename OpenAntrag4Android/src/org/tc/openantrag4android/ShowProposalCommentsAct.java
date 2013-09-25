package org.tc.openantrag4android;

import java.text.DateFormat;
import java.util.ArrayList;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4android.adapter.ProposalEntry;
import org.tc.openantrag4android.adapter.ProposalEntryAdapter;
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.proposal.Comment;
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
			ArrayList<Comment> comments = GetComments.execute(Storage.proposal.getiD());
			
			ArrayList<String> pElements = new ArrayList<String>();
			for (int i=0;i<comments.size();i++) {
				Comment c = comments.get(i);
				
				pElements.add(c.getCommentText()+" ("+
								c.getCommentedBy()+", "+
								DateFormat.getInstance().format(c.getCommmentedAt())+")");
			}
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					R.layout.proposal_comments_item, pElements);
			ListView lView = (ListView)findViewById(R.id.listViewProposal);
			lView.setAdapter(dataAdapter);
					
		} catch (OpenAntragException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("Fehler (debug)!");
			alert.setMessage(e.getMessage());
			alert.setCanceledOnTouchOutside(true);
			alert.show();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//Intent mainActivity = new Intent(ShowProposalCommentsAct.this, MainActivity.class);
		//startActivity(mainActivity);
	}

}
