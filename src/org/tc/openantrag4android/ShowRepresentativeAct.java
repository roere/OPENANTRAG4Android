package org.tc.openantrag4android;

import org.tc.openantrag4J.representation.android.Representative;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowRepresentativeAct extends Activity {

	public ShowRepresentativeAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.show_representative);
			this.buildView();
			
			LinearLayout lLayout = (LinearLayout)findViewById(R.id.showProposalBack);
			lLayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent showProposal = new Intent(ShowRepresentativeAct.this, ShowProposalAct.class);
					startActivity(showProposal);
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
	
	/**
	 * 
	 */
	private void buildView () {
		Representative rep = Storage.representative;
		TextView name = (TextView)findViewById(R.id.repName);
		TextView party = (TextView)findViewById(R.id.repParty);
		TextView mail = (TextView)findViewById(R.id.repMail);
		TextView twitter = (TextView)findViewById(R.id.repTwitter);
		ImageView portrait = (ImageView)findViewById(R.id.portraitImage);
		
		name.setText(rep.getName());
		party.setText(rep.getParty());
		mail.setText(rep.getMail());
		twitter.setText(rep.getTwitter());
		portrait.setImageBitmap(rep.getPortraitImage());
	}

}
