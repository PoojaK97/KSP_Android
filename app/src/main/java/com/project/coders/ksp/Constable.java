package com.project.coders.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.UUID;
import java.util.concurrent.Executor;

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
                startActivity(new Intent(Constable.this, MapsActivity.class));
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, Camera.class));
            }
        });
        writ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Constable.this, Report.class));
            }
        });

    }
}
