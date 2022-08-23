package ht.codewithmarc.noteapp.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.adapters.NoteListAdapter;
import ht.codewithmarc.noteapp.config.Database;
import ht.codewithmarc.noteapp.dao.NoteDAO;
import ht.codewithmarc.noteapp.models.Note;


public class NoteListFragment extends Fragment {

    @SuppressLint("NewApi")
    ArrayList<Note> notes = new ArrayList<Note>() {
        {
            add(new Note(1L, "Note Title 1", "Note Contents 1", LocalDate.now()));
            add(new Note(2L, "Note Title 2", "Note Contents 2", LocalDate.now()));
            add(new Note(3L, "Note Title 3", "Note Contents 3", LocalDate.now()));
            add(new Note(4L, "Note Title 4", "Note Contents 4", LocalDate.now()));
            add(new Note(5L, "Note Title 5", "Note Contents 5", LocalDate.now()));
            add(new Note(6L, "Note Title 6", "Note Contents 6", LocalDate.now()));
            add(new Note(7L, "Note Title 7", "Note Contents 7", LocalDate.now()));
            add(new Note(8L, "Note Title 8", "Note Contents 8", LocalDate.now()));
            add(new Note(9L, "Note Title 9", "Note Contents 9", LocalDate.now()));
        }
    };
    RecyclerView notesList;
    TextView noteNumber;
    EditText searchBar;
    NoteDAO noteDAO;
    Database database;


    FloatingActionButton addNoteBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        database = new Database(view.getContext());
        noteDAO = new NoteDAO(database);

        searchBar = view.findViewById(R.id.searchBar);

        notes = (ArrayList<Note>) noteDAO.getNotes(null);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notes = (ArrayList<Note>) noteDAO.getNotes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        noteNumber = view.findViewById(R.id.noteNumber);
        noteNumber.setText((notes.size() > 1) ? notes.size() + " Notes" : notes.size() + " Note");

        addNoteBtn = view.findViewById(R.id.addNoteBtn);
        addNoteBtn.setOnClickListener(buttonView -> this.getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrameLayout, new NoteAddFragment())
                .addToBackStack(null).commit());

        notesList = view.findViewById(R.id.notes);
        notesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        notesList.setAdapter(new NoteListAdapter(view.getContext(), notes));

        return view;
    }
}