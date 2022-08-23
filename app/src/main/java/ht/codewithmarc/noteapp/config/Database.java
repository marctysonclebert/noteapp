package ht.codewithmarc.noteapp.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public String NOTES_TABLE = "notes";
    public String COLUMN_NOTE_ID = "ID";
    public String COLUMN_NOTE_TITLE = "title";
    public String COLUMN_NOTE_CONTENTS = "contents";
    public String COLUMN_NOTE_DATE = "date";

    public Database(@Nullable Context context) {
        super(context, "note_app.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE " +
                NOTES_TABLE + "(" +
                COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE_TITLE + " TEXT, " +
                COLUMN_NOTE_CONTENTS + " TEXT, " +
                COLUMN_NOTE_DATE + " DATE " +
                ")";

        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
