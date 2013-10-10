package org.tc.openantrag4android.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.tc.openantrag4android.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.openantrag4android.R;
import com.tc.openantrag4android.R.id;

public class ProcessStepEntryAdapter extends ArrayAdapter<ProcessStepEntry>{

    Context context;
    int layoutResourceId;   
    ArrayList<ProcessStepEntry> data = null;
   
    public ProcessStepEntryAdapter(Context context, int layoutResourceId, ArrayList<ProcessStepEntry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContentHolder holder = null;
        Resources res = ((Activity)context).getResources();
        Integer lightGrey = res.getColor(R.color.lightGrey);
        Integer lightGrey2 = res.getColor(R.color.lightGrey2);
        Integer darkGrey = res.getColor(R.color.darkGrey);
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
                        
            holder = new ContentHolder();
            
            holder.commentListBG = (LinearLayout)row.findViewById(R.id.processListBG);
            holder.processCountTxt = (TextView)row.findViewById(R.id.processCount);
            holder.processState = (TextView)row.findViewById(R.id.processState);
            holder.createdAtTxt = (TextView)row.findViewById(R.id.processCreatedAt);
            holder.captionTxt = (TextView)row.findViewById(R.id.processCaption);
            holder.infoTxt = (TextView)row.findViewById(R.id.processInfoText);
           
            row.setTag(holder);
        }
        else
        {
            holder = (ContentHolder)row.getTag();
        }
       
        ProcessStepEntry entry = data.get(position);
        holder.processCountTxt.setText((position+1)+".");
        holder.createdAtTxt.setText(DateFormat.getDateInstance(DateFormat.LONG, Constants.LOCALE).format(entry.createdAt));
        holder.captionTxt.setText(entry.caption);
        holder.infoTxt.setText(entry.infoText);
       	holder.processState.setBackgroundColor(Color.parseColor(entry.processStepColor));
          
        if (entry.isEvenRow) {
        	holder.processCountTxt.setBackgroundColor(lightGrey);
        	holder.commentListBG.setBackgroundColor(lightGrey);
        	holder.createdAtTxt.setBackgroundColor(lightGrey);
            holder.infoTxt.setBackgroundColor(lightGrey);
            holder.captionTxt.setBackgroundColor(lightGrey);
        } else {
        	holder.processCountTxt.setBackgroundColor(lightGrey2);
        	holder.commentListBG.setBackgroundColor(lightGrey2);
        	holder.createdAtTxt.setBackgroundColor(lightGrey2);
            holder.infoTxt.setBackgroundColor(lightGrey2);
            holder.captionTxt.setBackgroundColor(lightGrey2);
        }
        
        return row;
    }
   
    static class ContentHolder
    {        
    	LinearLayout commentListBG;
    	TextView processCountTxt;
    	TextView processState;
    	TextView createdAtTxt;
    	TextView infoTxt;
    	TextView captionTxt;
    }
}