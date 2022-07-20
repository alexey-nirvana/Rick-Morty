package com.example.rickmorty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {

    private ArrayList<Note> notes;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textView01.setText(note.getTitle());
        holder.textView02.setText(note.getDescription());
        holder.textView03.setText(note.getDayOfWeek());
        holder.textView04.setText(String.format("%s",note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textView01;
        private TextView textView02;
        private TextView textView03;
        private TextView textView04;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            textView01 = itemView.findViewById(R.id.textView01);
            textView02 = itemView.findViewById(R.id.textView02);
            textView03 = itemView.findViewById(R.id.textView03);
            textView04 = itemView.findViewById(R.id.textView04);
        }
    }
}