package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowConstableWiseAdapter extends RecyclerView.Adapter<com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder> {
    private String[] mDataset;
    private Context mcontext;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView textView;
        public LinearLayoutCompat parentLayout;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView3);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ShowConstableWiseAdapter(Context c, String[] myDataset) {
        mDataset = myDataset;
        //mDataset = new String[]{"rose", "violet"};
    }

    // Create new views (invoked by the layout manager)
    @Override
    public com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                         int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_show_constable_wise_adapter, parent, false);
        com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder vh = new com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

