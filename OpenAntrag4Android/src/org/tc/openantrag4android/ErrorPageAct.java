package org.tc.openantrag4android;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity to present Error Messages.
 * @author roere
 *
 */
public class ErrorPageAct extends Activity {

	public ErrorPageAct() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.error_page);
		Class sender = (Class)getIntent().getExtras().get("class");
		Exception ex = (Exception)getIntent().getExtras().get("exception");
		
		TextView exception = (TextView)findViewById(R.id.errException);
		TextView cause = (TextView)findViewById(R.id.errCause);
		TextView text = (TextView)findViewById(R.id.errText);
		TextView section = (TextView)findViewById(R.id.errSection);
		TextView stackTrace = (TextView)findViewById(R.id.errStackTrace);

		
		StackTraceElement[] ste = ex.getStackTrace();
		StringBuilder sb = new StringBuilder();
		if (ste!=null)
			for (int i=0;i<ste.length;i++) {
				sb.append(ste[i].getClassName()+" in "+ste[i].getMethodName()+" at "+ste[i].getLineNumber());
			}
		
		exception.setText(ex.getClass()+"");
		cause.setText(AndroidUtils.null2Empty(""+ex.getCause()));
		text.setText(ex.getMessage());
		section.setText(sender.getName());
		stackTrace.setText(sb.toString());
		
		LinearLayout lLayout = (LinearLayout)findViewById(R.id.showProposalBack);
		lLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent showProposalList = new Intent(ErrorPageAct.this, MainActivity.class);
				startActivity(showProposalList);
			}
		});
	}

}
