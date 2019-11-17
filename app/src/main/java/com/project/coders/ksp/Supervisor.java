package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Supervisor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        Button beatbutton = findViewById(R.id.beatwise);
        Button beatbutton2 = findViewById(R.id.beatwise2);
        Button constablebutton = findViewById(R.id.constablewise);
        beatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Supervisor.this,Beats.class));
            }
        });
        beatbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Supervisor.this,MapsActivity2.class));
            }
        });
        constablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Supervisor.this,ShowConstableWise.class));
            }
        });
    }
}
