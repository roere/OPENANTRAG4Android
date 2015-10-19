package org.tc.openantrag.android;

import com.tc.openantrag.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author roere
 *
 */
public class ShowInfoAct extends Activity {

	public ShowInfoAct() {
	}

	/**
	 * 
	 */
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_info);
		
		final Class sender = (Class)getIntent().getExtras().get("class");
		
		TextView version = (TextView)findViewById(R.id.appVersion);
		TextView licence = (TextView)findViewById(R.id.appLicense);
		TextView github = (TextView)findViewById(R.id.appGitHub);
		
		version.setText(Constants.VERSION);
		licence.setText(Constants.LICENCE);
		github.setText(Constants.REPOSITORY);
		
		LinearLayout lLayout = (LinearLayout)findViewById(R.id.showProposalBack);
		lLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowInfoAct.this, sender);
				startActivity(intent);
			}
		});
		/*
		Uri uri = Uri.parse("http://www.adresse.de");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		 */
	}
	
}
