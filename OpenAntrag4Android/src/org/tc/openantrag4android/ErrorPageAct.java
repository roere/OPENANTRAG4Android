package org.tc.openantrag4android;

import org.tc.openantrag4J.OpenAntragException;

import com.tc.openantrag4android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

		/*
		 * Present a specific Error Message:
		 * track down openantragexception to a general network problem, a problem reaching openantrag-server
		 * or a problem with a specific api call.
		 * Present the general exception information, if exception is not of type openantrag
		 * Put all network calls into a asynctask
		 */
		if (ex.getClass().equals(OpenAntragException.class)) {
			LinearLayout errLayout = (LinearLayout)findViewById(R.id.exception);
			errLayout.setVisibility(LinearLayout.INVISIBLE);
			new RemotePreDataTask().execute(ex);
		} else {
			LinearLayout errLayout = (LinearLayout)findViewById(R.id.oaException);
			errLayout.setVisibility(LinearLayout.INVISIBLE);
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
		}
		LinearLayout lLayout = (LinearLayout)findViewById(R.id.showProposalBack);
		lLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent showProposalList = new Intent(ErrorPageAct.this, MainActivity.class);
				startActivity(showProposalList);
			}
		});

	}
	
	/**
	 * AsyncTask tests access to the internet and the openantrag-Server 
	 * @author roere
	 *
	 */
    private class RemotePreDataTask extends AsyncTask<Exception, Void, Void> {
    	
    	String errorMessage = "";
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
 
        @Override
        protected Void doInBackground(Exception... params) {
        	Exception ex = params[0];
        	if (!AndroidUtils.hasInternetAccess()) {
				errorMessage = "Keine Verbindung zum Internet. Bitte Netzwerkeinstellungen prüfen.";
			} else if (!AndroidUtils.hasServerAccess()) {
				errorMessage = "Keine Verbindung zum OPENANTRAG-Server. Versuchen Sie es später nochmal.";
			} else {
				errorMessage = "Fehler beim Zugriff auf den OPENANTRAG-Server:"+ex.getMessage();
			}
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
			TextView errMessage = (TextView)findViewById(R.id.errMessage);
        	errMessage.setText(errorMessage);
        }
    }


}
