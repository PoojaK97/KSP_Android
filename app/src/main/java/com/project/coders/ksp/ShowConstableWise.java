package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
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

public class ShowConstableWise extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        setContentView(R.layout.activity_show_constable_wise);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewConstable);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<String> idsArrayList= new ArrayList<>();
        final ArrayList<String> namesArrayList= new ArrayList<>();

        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Users");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    String role = ds.child("Role").getValue(String.class);
                    String supervisor = ds.child("Supervisor").getValue(String.class);
                    if (role.equals("Constable") && supervisor.equals(uid)) {
                        String name = ds.child("Name").getValue(String.class);
                        Log.i("NAME", name);
                        Log.i("ROLE", role);
                        Log.i("KEY", key);
                        idsArrayList.add(key);
                        namesArrayList.add(name);
                    }
                }
                for (String member : namesArrayList){
                    Log.i("NAMES LIST: ", member);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myDataset =new String[]{"KSP171: Kumaran LP","KSP202: Hema Lata"};
        // specify an adapter (see also next example)
        mAdapter = new CheckAssignmentAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
