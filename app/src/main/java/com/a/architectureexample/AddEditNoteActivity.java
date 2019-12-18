package com.a.architectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.a.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.a.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.a.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.a.architectureexample.EXTRA_PRIORITY";
    private EditText tittle, description;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        tittle = findViewById(R.id.title);
        description = findViewById(R.id.description);
        numberPicker = findViewById(R.id.numberpicker);

        numberPicker.setMaxValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            tittle.setText(intent.getStringExtra(EXTRA_TITLE));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
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
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String mTitle = tittle.getText().toString();
        String mDesc = description.getText().toString();
        int p = numberPicker.getValue();
        if (mTitle.trim().isEmpty() || mDesc.trim().isEmpty()){
            Toast.makeText(this, "Fill data", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,mTitle);
        data.putExtra(EXTRA_DESCRIPTION,mDesc);
        data.putExtra(EXTRA_PRIORITY,p);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }
}
