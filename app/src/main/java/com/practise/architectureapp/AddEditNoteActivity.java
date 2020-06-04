package com.practise.architectureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";

    public static final String EXTRA_ID = "com.practise.architectureapp.ID";
    public static final String EXTRA_TITLE = "com.practise.architectureapp.TITLE";
    public static final String EXTRA_DESCRIPTION = "com.practise.architectureapp.DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.practise.architectureapp.PRIORITY";

    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24dp);

        mEditTextTitle = (EditText) findViewById(R.id.edit_text_title);
        mEditTextDescription = (EditText) findViewById(R.id.edit_text_description);
        mNumberPicker = (NumberPicker)  findViewById(R.id.number_picker);
        mNumberPicker.setMaxValue(10);
        mNumberPicker.setMinValue(1);

        if(getIntent().hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            Intent intent = getIntent();
            mEditTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            mEditTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            mNumberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            //it is a new note
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                saveNote();
                Log.d(TAG, "onOptionsItemSelected: ");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = mEditTextTitle.getText().toString();
        String description = mEditTextDescription.getText().toString();
        int priority = mNumberPicker.getValue();
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please add a title and description", Toast.LENGTH_LONG).show();
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESCRIPTION, description);
            data.putExtra(EXTRA_PRIORITY, priority);
            data.putExtra(EXTRA_ID, id);

            setResult(RESULT_OK, data);
            finish();
        }
    }
}
