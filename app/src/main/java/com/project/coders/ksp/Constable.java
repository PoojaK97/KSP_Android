package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Constable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constable);
        Button reportbutton = findViewById(R.id.report);
        Button checkassignbutton = findViewById(R.id.checkAssignment);
        final ImageView cam = findViewById(R.id.imageButton);
        final ImageView writ = findViewById(R.id.imageButton2);
        final ImageView qr = findViewById(R.id.imageButton4);
        reportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cam.setVisibility(View.VISIBLE);
                writ.setVisibility(View.VISIBLE);
                qr.setVisibility(View.VISIBLE);
            }
        });
        checkassignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,CheckAssignment.class));
                finish();
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,Camera.class));
                finish();
            }
        });
        writ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,Report.class));
                finish();
            }
        });
    }
}
