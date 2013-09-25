package org.tc.openantrag4android;

import java.text.DateFormat;
import java.util.ArrayList;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.proposal.Comment;
import org.tc.openantrag4j.proposal.ProposalStep;

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

public class ShowProposalProcessAct extends Activity {

	public ShowProposalProcessAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.proposal_process);
			this.buildView();
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
		LinearLayout bButton = (LinearLayout)findViewById(R.id.showProposalListBack);
		bButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent showProposalActivity = new Intent(ShowProposalProcessAct.this, ShowProposalAct.class);
				startActivity(showProposalActivity);
			}
		});

		ArrayList<ProposalStep> pSteps = Storage.proposal.getProposalSteps();
		ArrayList<String> pStepsString = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<pSteps.size();i++) {
			ProposalStep step = pSteps.get(i);
			buf.append(step.getCaption()+"\r\n");
			pStepsString.add(buf.toString());
		}
				
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.proposal_comments_item, pStepsString);
		ListView lView = (ListView)findViewById(R.id.listProposalProcess);
		lView.setAdapter(dataAdapter);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//Intent mainActivity = new Intent(ShowProposalProcessAct.this, MainActivity.class);
		//startActivity(mainActivity);
		
	}


}
