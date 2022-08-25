package ht.codewithmarc.noteapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

import ht.codewithmarc.noteapp.R;
import ht.codewithmarc.noteapp.config.Database;
import ht.codewithmarc.noteapp.dao.NoteDAO;
import ht.codewithmarc.noteapp.models.Note;


public class NoteAddFragment extends Fragment {

    FloatingActionButton saveNoteBtn;
    EditText textNote;
    Note note;
    NoteDAO noteDAO;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_add, container, false);

        Database database = new Database(view.getContext());

        saveNoteBtn = view.findViewById(R.id.saveNoteBtn);
        textNote = view.findViewById(R.id.textNote);

        saveNoteBtn.setOnClickListener(button -> {

            Long id = null;
            String title = textNote.getText().toString().length() > 13 ?
                    textNote.getText().toString().substring(0, 13) + "..." :
                    textNote.getText().toString();
            String text = textNote.getText().toString();
            LocalDate date = LocalDate.now();

            note = new Note(id, title, text, date);

            noteDAO = new NoteDAO(database);
            noteDAO.addNote(note);
        });

        return view;
    }
}