package com.results.plexus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.textclassifier.ConversationActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    String jsonData = "";
    List<App> root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromInternet();
    }

    private void doInBackground() throws IOException {

        String url  = "https://raw.githubusercontent.com/parveshnarwal/Plexus-Demo/main/test.json";

        client = new OkHttpClient();

        jsonData = run(url);

    }

    private  String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    private void getDataFromInternet(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                doInBackground();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //UI Thread work here
            //Populate results in Text Views

            handler.post(() -> {

                try {
                    populateList();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            });
        });
    }

    public void populateList() throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        root = om.readValue(jsonData, new TypeReference<List<App>>(){});

    }

}