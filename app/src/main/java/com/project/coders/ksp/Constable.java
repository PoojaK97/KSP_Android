package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Constable extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constable);
        Button reportbutton = findViewById(R.id.report);
        Button checkassignbutton = findViewById(R.id.beatwise);
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
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,Camera.class));
            }
        });
        writ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,Report.class));
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this,QR.class));
            }
        });

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            //Check if there is any live assignment if yes then start TrackerService
            startService(new Intent(this, TrackerService.class));
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }
    }
}
