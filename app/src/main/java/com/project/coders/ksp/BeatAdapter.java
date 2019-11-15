package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


public class BeatAdapter extends RecyclerView.Adapter<com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder> {
        private String[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;
            public MyViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public BeatAdapter(String[] myDataset) {
            mDataset = myDataset;
            //mDataset = new String[]{"rose", "violet"};
        }

        // Create new views (invoked by the layout manager)
        @Override
        public com.project.coders.ksp.CheckAssignmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                             int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_beat_adapter, parent, false);
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
