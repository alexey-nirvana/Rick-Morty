package com.example.rickmorty;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.hide();

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("Сообщение", "Первый интент");
                startActivity(intent);
            }
        });


        DownloadJson task = new DownloadJson();
        task.execute("https://rickandmortyapi.com/api/character");
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

/*
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
 */