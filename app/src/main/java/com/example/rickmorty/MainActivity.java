package com.example.rickmorty;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button button;
    ImageView imageView;
    String URL = "https://rickandmortyapi.com/api/character";
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);


        setupViews();
        executeJsonDownload(URL);
        executeImageDownload();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("Сообщение", "Первый интент");
                startActivity(intent);
            }
        });
    }

    private void setupViews() {
        actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void executeJsonDownload(String url) {
        DownloadJson task = new DownloadJson();
        try {
            String s = task.execute(url).get();
            getExecuteName(s);
            getExecuteImage(s);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeImageDownload() {
        DownloadImage downloadImage = new DownloadImage();
        try {
            Bitmap bitmap = downloadImage.execute("https://rickandmortyapi.com/api/character/avatar/2.jpeg").get();
            imageView.setImageBitmap(bitmap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getExecuteName(String task) {
        try {
            JSONObject jsonObject = new JSONObject(task);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < 15; i++) {
                JSONObject one = jsonArray.getJSONObject(i);
                String name = one.getString("name");
                // String image = one.getString("image");
                names.add(name);
                Log.wtf("Name", name);
                //  Log.wtf("Image", image);
            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        } finally {

        }
        return names;
    }

    private ArrayList<String> getExecuteImage(String task) {
        try {
            JSONObject jsonObject = new JSONObject(task);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < 18; i++) {
                JSONObject one = jsonArray.getJSONObject(i);
              //  String name = one.getString("name");
                String image = one.getString("image");
                images.add(image);
              //  Log.wtf("Name", name);
                Log.wtf("Image", image);
            }

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        } finally {

        }
        return images;
    }
}