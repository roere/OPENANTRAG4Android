package org.tc.openantrag4android;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.tc.openantrag4J.OpenAntragException;
import org.tc.openantrag4J.representation.RepresentationSet;
import org.tc.openantrag4android.adapter.ProcessStepEntry;
import org.tc.openantrag4android.adapter.ProcessStepEntryAdapter;
import org.tc.openantrag4android.adapter.ProposalEntry;
import org.tc.openantrag4android.adapter.ProposalEntryAdapter;
import org.tc.openantrag4j.commands.GetComments;
import org.tc.openantrag4j.proposal.Comment;
import org.tc.openantrag4j.proposal.Proposal;
import org.tc.openantrag4j.proposal.ProcessStep;

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
import android.widget.TextView;

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

		/*
		ArrayList<ProcessStep> pSteps = Storage.proposal.getProposalSteps();
		ArrayList<String> pStepsString = new ArrayList<String>();
		StringBuffer buf = null;
		for (int i=0;i<pSteps.size();i++) {
			ProcessStep step = pSteps.get(i);
			buf = new StringBuffer();
			buf.append(DateFormat.getDateInstance(DateFormat.FULL, Constants.LOCALE).format(step.getCreatedAt())+"\r\n");
			buf.append(step.getCaption()+"\r\n");
			buf.append(step.getInfoText()+"\r\n");
			pStepsString.add(buf.toString());
		}
				
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.proposal_comments_item, pStepsString);
		ListView lView = (ListView)findViewById(R.id.listProposalProcess);
		lView.setAdapter(dataAdapter);
		*/
		
		TextView headLine = (TextView)findViewById(R.id.proposalText);
		headLine.setText(Storage.proposal.getTitleText());
		
		ListView lView = (ListView)findViewById(R.id.listProposalProcess);
		// build comment list output		
		ArrayList<ProcessStepEntry> cElements = new ArrayList<ProcessStepEntry>();
		ArrayList<ProcessStep> pSteps = Storage.proposal.getProposalSteps();
		for (int i=0;i<pSteps.size();i++) {
			ProcessStep p = pSteps.get(i);
			cElements.add(new ProcessStepEntry(p.getCaption(),
												p.getInfoText(),
												p.getCreatedAt(),
												p.getColor(),
												(Math.abs(i/2)==((double)i/2))));
		}
		
		ProcessStepEntryAdapter pAdapter = new ProcessStepEntryAdapter(this, R.layout.show_processstep_list_item, cElements);
	    lView.setAdapter(pAdapter);
		
	}
	
	/**
	 * Build List of Comment Entries
	 */
	private void buildProcessList() {
		final ListView lView = (ListView)findViewById(R.id.listProposalProcess);
		// build comment list output		
		ArrayList<ProcessStepEntry> cElements = new ArrayList<ProcessStepEntry>();
		ArrayList<ProcessStep> pSteps = Storage.proposal.getProposalSteps();
		for (int i=0;i<pSteps.size();i++) {
			ProcessStep p = pSteps.get(i);
			cElements.add(new ProcessStepEntry(p.getCaption(),
												p.getInfoText(),
												p.getCreatedAt(),
												p.getColor(),
												(Math.abs(i/2)==((double)i/2))));
		}
		
		ProcessStepEntryAdapter pAdapter = new ProcessStepEntryAdapter(this, R.layout.show_processstep_list_item, cElements);
	    lView.setAdapter(pAdapter);		
	}

	
	@Override
	protected void onStop() {
		super.onStop();
		//Intent mainActivity = new Intent(ShowProposalProcessAct.this, MainActivity.class);
		//startActivity(mainActivity);
		
	}


}
