package com.example.rickmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycleView);
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 1));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 3));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 4));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 5));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 6));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 7));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 8));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 9));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 10));
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 11));
        NoteAdapter noteAdapter = new NoteAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(noteAdapter);
    }
}