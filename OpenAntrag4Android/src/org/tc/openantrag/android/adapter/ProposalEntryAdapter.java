package org.tc.openantrag.android.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.tc.openantrag.android.Constants;

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

import com.tc.openantrag.android.R;
import com.tc.openantrag.android.R.id;

public class ProposalEntryAdapter extends ArrayAdapter<ProposalEntry>{

    Context context;
    int layoutResourceId;   
    ArrayList<ProposalEntry> data = null;
   
    public ProposalEntryAdapter(Context context, int layoutResourceId, ArrayList<ProposalEntry> data) {
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
            
            holder.proposalListBG = (LinearLayout)row.findViewById(R.id.proposalListBG);
            holder.proposalCountTxt = (TextView)row.findViewById(R.id.proposalCount);
            holder.proposalState = (TextView)row.findViewById(R.id.proposalState);
            holder.createdAtTxt = (TextView)row.findViewById(R.id.proposalCreatedAt);
            holder.headerTxt = (TextView)row.findViewById(R.id.proposalHeadline);
            holder.representationTxt = (TextView)row.findViewById(R.id.proposalRepresentation);
            holder.proposalStepTxt = (TextView)row.findViewById(R.id.proposalTeaser);
           
            row.setTag(holder);
        }
        else
        {
            holder = (ContentHolder)row.getTag();
        }
       
        ProposalEntry entry = data.get(position);
        holder.proposalCountTxt.setText((position+1)+".");
        holder.createdAtTxt.setText(DateFormat.getDateInstance(DateFormat.LONG, Constants.LOCALE).format(entry.createdAt));
        holder.representationTxt.setText(entry.representation);
        holder.headerTxt.setText(entry.header);
        holder.proposalStepTxt.setText(entry.proposalStep);
       	holder.proposalState.setBackgroundColor(Color.parseColor(entry.proposalStepColor));
          
        if (entry.isEvenRow) {
        	holder.proposalListBG.setBackgroundColor(lightGrey);
        	holder.proposalCountTxt.setBackgroundColor(lightGrey);
            holder.createdAtTxt.setBackgroundColor(lightGrey);
            holder.representationTxt.setBackgroundColor(lightGrey);
            holder.headerTxt.setBackgroundColor(lightGrey);
            holder.proposalStepTxt.setBackgroundColor(lightGrey);
        } else {
        	holder.proposalListBG.setBackgroundColor(lightGrey2);
        	holder.proposalCountTxt.setBackgroundColor(lightGrey2);
            holder.createdAtTxt.setBackgroundColor(lightGrey2);
            holder.representationTxt.setBackgroundColor(lightGrey2);
            holder.headerTxt.setBackgroundColor(lightGrey2);
            holder.proposalStepTxt.setBackgroundColor(lightGrey2);
        }
        
        return row;
    }
   
    static class ContentHolder
    {
        
    	LinearLayout proposalListBG;
    	TextView proposalCountTxt;
    	TextView proposalState;
    	TextView createdAtTxt;
    	TextView headerTxt;
    	TextView representationTxt;
        TextView proposalStepTxt;
    }
}