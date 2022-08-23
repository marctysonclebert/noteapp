package ht.codewithmarc.noteapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ht.codewithmarc.noteapp.config.Database;
import ht.codewithmarc.noteapp.models.Note;

public class NoteDAO {

    private final Database database;

    public NoteDAO(@NonNull Database database) {
        this.database = database;
    }


    public boolean addNote(@NonNull Note note) {

        SQLiteDatabase sqLiteDatabase = this.database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.database.COLUMN_NOTE_TITLE, note.getTitle());
        contentValues.put(this.database.COLUMN_NOTE_CONTENTS, note.getContents());
        contentValues.put(this.database.COLUMN_NOTE_DATE, note.getDate().toString());

        return sqLiteDatabase.insert(this.database.NOTES_TABLE, null, contentValues) > 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Note> getNotes() {

        List<Note> notes = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + this.database.NOTES_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                Long id = (long) cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                LocalDate date = LocalDate.parse(cursor.getString(3));

                Note note = new Note(id, title, content, date);

                notes.add(note);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return notes;
    }
}
