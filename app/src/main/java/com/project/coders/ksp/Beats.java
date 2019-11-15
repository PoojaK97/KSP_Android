package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

public class Beats extends Activity {
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private String[] myDataset;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_beats);
            recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewBeat);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            myDataset =new String[]{"Beat 1: HKT layout","Beat 2: PalaceGrounds"};
            // specify an adapter (see also next example)
            mAdapter = new BeatAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);
        }
}
