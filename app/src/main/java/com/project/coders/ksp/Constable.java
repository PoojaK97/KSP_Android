package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

public class Constable extends AppCompatActivity {

    private static final String TAG = Constable.class.getName();
    private static final int PERMISSIONS_REQUEST = 1;

    private String mToBeSignedMessage;
    private Context context;

    // Unique identifier of a key pair
    private static final String KEY_NAME = UUID.randomUUID().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constable);
        context = getApplicationContext();
        Button reportbutton = findViewById(R.id.report);
        Button checkassignbutton = findViewById(R.id.beatwise);
        Button sheeterbutton = findViewById(R.id.sheeters);
        final ImageView cam = findViewById(R.id.imageButton);
        final ImageView writ = findViewById(R.id.imageButton2);
        reportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cam.setVisibility(View.VISIBLE);
                writ.setVisibility(View.VISIBLE);
            }
        });
        checkassignbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, MapsActivity.class));
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, Camera.class));
            }
        });
        sheeterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, Sheeter.class));
            }
        });
        writ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, Report.class));
            }
        });
        Button  emerg = findViewById(R.id.buttonEmeg);
        emerg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("9980950485", null, "NEED URGENT HELP", null, null);
                    smsManager.sendTextMessage("7975525797", null, "NEED URGENT HELP", null, null);
                    smsManager.sendTextMessage("9705235640", null, "NEED URGENT HELP", null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                String eNo = "7022414226";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + eNo));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(intent);
                Snackbar.make(view, "Calling", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
