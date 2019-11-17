package com.project.coders.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Beats extends Activity {
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;
        private String[] myDataset;
        HttpURLConnection conn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_beats);
            recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewBeat);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            myDataset =new String[]{"Beat 1: HKT layout\nExpected theft: 22\nExpected Eveteasing: 5\nExpected murder: 0\n--------------------\n","Beat 2: PalaceGrounds\nExpected theft: 12\nExpected Eveteasing: 10\nExpected murder: 0\n--------------------  \n"};
            // specify an adapter (see also next example)
            mAdapter = new BeatAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);

            try{
                String url = "http://www.example.com/test.php";
                URL urlObj = new URL(url);
                conn = (HttpURLConnection) urlObj.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                //String paramsString = sbParams.toString();

                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes("");
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("test", "result from server: " + result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
}
