package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button loginbutton = findViewById(R.id.button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //for now this is going to constable page
                Intent intent = new Intent(Login.this,Constable.class);
                startActivity(intent);
            }
        });
    }
}
