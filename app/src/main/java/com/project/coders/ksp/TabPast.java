package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabPast extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,@NonNull Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_tab_past, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.pastRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Assignment[] myListData = new Assignment[] {
                new Assignment("Start_Time - 10:00am", "End_Time - 1:00pm", "Indoor Stadium"),
                new Assignment("Start_Time - 4:00pm", "End_Time - 7:00pm", "Canteen")
        };

        AssignmentAdapter adapter = new AssignmentAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
}
