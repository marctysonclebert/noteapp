package ht.codewithmarc.noteapp.adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.fragments.NoteAddFragment;
import ht.codewithmarc.noteapp.models.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListHolder> {

    private final ArrayList<Note> notes;
    private final Context context;

    public NoteListAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public NoteListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new NoteListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListHolder holder, int position) {
        Note note = notes.get(position);

        holder.noteId = note.getId();
        holder.titleTextView.setText(note.getTitle());
        holder.dateTextView.setText(note.getDate().toString());
        holder.contentsTextView.setText(note.getContents());
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public static class NoteListHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView contentsTextView;
        CardView noteComponent;
        Long noteId = null;

        @RequiresApi(api = Build.VERSION_CODES.O)
        public NoteListHolder(@NonNull View itemView) {
            super(itemView);

            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.dateTextView = itemView.findViewById(R.id.dateTextView);
            this.contentsTextView = itemView.findViewById(R.id.contentsTextView);
            this.noteComponent = itemView.findViewById(R.id.noteComponent);

            noteComponent.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(this.noteId));

                NoteAddFragment noteAddFragment = new NoteAddFragment();
                noteAddFragment.setArguments(bundle);
            });

            // TODO: Work Todo To Delete
            noteComponent.setOnLongClickListener(view -> true);
        }
    }
}
