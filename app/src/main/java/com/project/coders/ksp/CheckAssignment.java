package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

public class CheckAssignment extends Activity {
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private String[] myDataset;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_check_assignment);
            recyclerView = (RecyclerView) findViewById(R.id.assignRecycle);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            myDataset =new String[]{"ROSE: 12th Jan\nTime: 11:00 AM\nBeat: HKT layout","POOJA: 1st Jan\nTime: 7:00 AM\nBeat: PalaceGrounds"};
            // specify an adapter (see also next example)
            mAdapter = new CheckAssignmentAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);
        }
}
