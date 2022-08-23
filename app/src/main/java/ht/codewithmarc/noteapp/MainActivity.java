package ht.codewithmarc.noteapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import ht.codewithmarc.noteapp.fragments.NoteListFragment;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrameLayout, new NoteListFragment())
                .addToBackStack(null).commit();
    }
}