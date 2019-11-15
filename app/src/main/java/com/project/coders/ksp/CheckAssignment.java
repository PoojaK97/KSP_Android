package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAssignment extends Activity {
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private String[] myDataset;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_check_assignment);
            recyclerView = findViewById(R.id.assignRecycle);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final String uid = user.getUid();
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            final ArrayList<Assignment> asgn= new ArrayList<>();

            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Assignments");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.child(uid).getChildren()) {
                        String key = ds.getKey();
                        Assignment a = ds.getValue(Assignment.class);
                        asgn.add(a);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            myDataset =new String[]{"ROSE: 12th Jan\nTime: 11:00 AM\nBeat: HKT layout","POOJA: 1st Jan\nTime: 7:00 AM\nBeat: PalaceGrounds"};
            // specify an adapter (see also next example)
            mAdapter = new CheckAssignmentAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);
        }
}
