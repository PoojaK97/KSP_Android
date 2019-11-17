package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Starttrack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttrack);
        final Button bt = findViewById(R.id.buttonstart);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strst1 = bt.getText().toString();
                if(strst1.equalsIgnoreCase("Start")){
                    startService(new Intent(getApplicationContext(), TrackerService.class));
                    bt.setBackgroundColor(Color.BLUE);
                    bt.setText("STOP");
                }
                if(strst1.equalsIgnoreCase("Stop")){
                    stopService(new Intent(getApplicationContext(), TrackerService.class));
                    bt.setBackgroundColor(Color.RED);
                    bt.setText("START");
                }}});
    }

}
