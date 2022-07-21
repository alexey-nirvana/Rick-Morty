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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private Button button;
    private ImageView imageView;

    private DownloadJson.DownloadJsonCompleteListener downloadCompleteListener = models -> {
        for (CharacterModel model: models){
            Log.wtf("вот и моделька", model.name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        executeJsonDownload();
        executeImageDownload();
    }

    private void setupViews() {
        actionBar = getSupportActionBar();
        actionBar.hide();

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            intent.putExtra("Сообщение", "Первый интент");
            startActivity(intent);
        });
    }

    private void executeJsonDownload() {
        DownloadJson task = new DownloadJson(downloadCompleteListener);
        task.execute("https://rickandmortyapi.com/api/character");
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
}