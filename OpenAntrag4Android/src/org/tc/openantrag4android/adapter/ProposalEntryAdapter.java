package org.tc.openantrag4android.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tc.openantrag4android.R;
import com.tc.openantrag4android.R.id;

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
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new ContentHolder();
            
            holder.proposalCountTxt = (TextView)row.findViewById(R.id.proposalCount);
            holder.createdAtTxt = (TextView)row.findViewById(R.id.proposalCreatedAt);
            holder.headerTxt = (TextView)row.findViewById(R.id.proposalHeadline);
            holder.proposalStepTxt = (TextView)row.findViewById(R.id.proposalTeaser);
           
            row.setTag(holder);
        }
        else
        {
            holder = (ContentHolder)row.getTag();
        }
       
        ProposalEntry entry = data.get(position);
        holder.proposalCountTxt.setText((position+1)+".");
        holder.createdAtTxt.setText(entry.createdAt+"");
        holder.headerTxt.setText(entry.header);
        holder.proposalStepTxt.setText(entry.proposalStep);
        holder.proposalStepTxt.setBackgroundColor(Color.parseColor(entry.proposalStepColor));
       
        return row;
    }
   
    static class ContentHolder
    {
        
    	TextView proposalCountTxt;
    	TextView createdAtTxt;
    	TextView headerTxt;
        TextView proposalStepTxt;
    }
}