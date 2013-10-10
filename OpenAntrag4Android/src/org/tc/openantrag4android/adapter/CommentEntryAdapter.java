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

public class CommentEntryAdapter extends ArrayAdapter<CommentEntry>{

    Context context;
    int layoutResourceId;   
    ArrayList<CommentEntry> data = null;
   
    public CommentEntryAdapter(Context context, int layoutResourceId, ArrayList<CommentEntry> data) {
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
            
            holder.commentListBG = (LinearLayout)row.findViewById(R.id.commentListBG);
            holder.commentCountTxt = (TextView)row.findViewById(R.id.commentCount);
            holder.commentState = (TextView)row.findViewById(R.id.commentState);
            holder.createdAtTxt = (TextView)row.findViewById(R.id.commentCreatedAt);
            holder.commentedBy = (TextView)row.findViewById(R.id.commentedBy);
            holder.commentText = (TextView)row.findViewById(R.id.commentText);
           
            row.setTag(holder);
        }
        else
        {
            holder = (ContentHolder)row.getTag();
        }
       
        CommentEntry entry = data.get(position);
        holder.commentCountTxt.setText((position+1)+".");
        holder.createdAtTxt.setText(DateFormat.getDateInstance(DateFormat.LONG, Constants.LOCALE).format(entry.createdAt));
        holder.commentText.setText(entry.commentText);
        holder.commentedBy.setText(entry.commentedBy);
          
        if (entry.isEvenRow) {
        	holder.commentListBG.setBackgroundColor(lightGrey);
        	holder.commentCountTxt.setBackgroundColor(lightGrey);
        	holder.commentState.setBackgroundColor(lightGrey);
            holder.createdAtTxt.setBackgroundColor(lightGrey);
            holder.commentText.setBackgroundColor(lightGrey);
            holder.commentedBy.setBackgroundColor(lightGrey);
        } else {
        	holder.commentListBG.setBackgroundColor(lightGrey2);
        	holder.commentCountTxt.setBackgroundColor(lightGrey2);
        	holder.commentState.setBackgroundColor(lightGrey2);
            holder.createdAtTxt.setBackgroundColor(lightGrey2);
            holder.commentText.setBackgroundColor(lightGrey2);
            holder.commentedBy.setBackgroundColor(lightGrey2);
        }
        
        return row;
    }
   
    static class ContentHolder
    {
        
    	LinearLayout commentListBG;
    	TextView commentCountTxt;
    	TextView commentState;
    	TextView createdAtTxt;
    	TextView commentedBy;
    	TextView commentText;
    }
}