package ht.codewithmarc.noteapp.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ht.codewithmarc.noteapp.MainActivity;
import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.adapters.NoteListAdapter;
import ht.codewithmarc.noteapp.config.Database;
import ht.codewithmarc.noteapp.dao.NoteDAO;
import ht.codewithmarc.noteapp.models.Note;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NoteListFragment extends Fragment {

    ArrayList<Note> notes;
    RecyclerView notesList;
    TextView noteNumber;
    EditText searchBar;
    NoteDAO noteDAO;
    FloatingActionButton addNoteBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create View
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        notesList = view.findViewById(R.id.notesList);
        noteNumber = view.findViewById(R.id.numberOfNote);


        retrieveNotes(view, null);

        // Searching Notes Event
        searchBar = view.findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                retrieveNotes(view, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Changing Page Button Click Event
        addNoteBtn = view.findViewById(R.id.addNoteBtn);
        addNoteBtn.setOnClickListener(button -> this.getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrameLayout, new NoteAddFragment())
                .addToBackStack(null).
                commit()
        );

        notesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }


    private void retrieveNotes(@NonNull View view, String searchValue) {
        noteDAO = new NoteDAO(new Database(view.getContext()));
        notes = (ArrayList<Note>) noteDAO.getNotes(searchValue);
        notesList.setAdapter(new NoteListAdapter(notes, view.getContext()));

        noteNumber.setText((notes.size() > 1) ? notes.size() + " Notes" : notes.size() + " Note");
    }
}

