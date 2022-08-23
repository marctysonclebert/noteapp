package ht.codewithmarc.noteapp.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.adapters.NoteListAdapter;
import ht.codewithmarc.noteapp.config.Database;
import ht.codewithmarc.noteapp.dao.NoteDAO;
import ht.codewithmarc.noteapp.models.Note;


public class NoteListFragment extends Fragment {

    @SuppressLint("NewApi")
    ArrayList<Note> notes;
    RecyclerView notesList;
    NoteDAO noteDAO;
    Database database;

    FloatingActionButton addNoteBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        addNoteBtn = view.findViewById(R.id.addNoteBtn);

        addNoteBtn.setOnClickListener(buttonView -> {
            this.getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFrameLayout, new NoteAddFragment())
                    .addToBackStack(null).commit();
        });

        database = new Database(view.getContext());

        noteDAO = new NoteDAO(database);

        notes = (ArrayList<Note>) noteDAO.getNotes();

        notesList = view.findViewById(R.id.notes);
        notesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        notesList.setAdapter(new NoteListAdapter(view.getContext(), notes));

        return view;
    }
}