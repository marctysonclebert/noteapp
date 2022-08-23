package ht.codewithmarc.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.models.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListHolder> {

    private final Context context;
    private final ArrayList<Note> notes;

    public NoteListAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new NoteListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListHolder holder, int position) {
        Note note = notes.get(position);

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

        public NoteListHolder(@NonNull View itemView) {
            super(itemView);

            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.dateTextView = itemView.findViewById(R.id.dateTextView);
            this.contentsTextView = itemView.findViewById(R.id.contentsTextView);
        }
    }
}
