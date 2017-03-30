package com.example.nasko.englishexercises.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nasko.englishexercises.R;
import com.example.nasko.englishexercises.databases.FeedReaderContract;
import com.example.nasko.englishexercises.databases.FeedReaderDbHelper;


public class NewWordsActivity extends Activity {
    TextView result;
    Spinner spinnerType;
    Button buttonAdd;
    EditText editTextEnglish;
    EditText editTextBulgarian;
    TextView responseTextView;
    FeedReaderDbHelper mDbHelper;
    private String type;
    private String english;
    private String bulgarian;
    private static final String TAG = "NewWordsActivity";


    private void setType(String type) {
        this.type = type;
    }

    private void setEnglish(String english) {
        this.english = english;
    }

    private void setBulgarian(String bulgarian) {
        this.bulgarian = bulgarian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_words_activity);
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());

        result = (TextView) findViewById(R.id.textView11);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        editTextEnglish = (EditText) findViewById(R.id.editTextEnglish);
        editTextBulgarian = (EditText) findViewById(R.id.editTextBulgarian);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        responseTextView = (TextView) findViewById(R.id.responseTextWiew);


        // Gets the data repository in write mode
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys

        final Button button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String englishWord = editTextEnglish.getText().toString().toLowerCase();
                String bulgarianWord = editTextBulgarian.getText().toString().toLowerCase();

                if (!englishWord.equals("") && !bulgarianWord.equals("")) {
                    setType((String) spinnerType.getSelectedItem());
                    setEnglish(englishWord);
                    setBulgarian(bulgarianWord);

                    final ContentValues values = new ContentValues();
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE, type);
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENGLISH, english);
                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BULGARIAN, bulgarian);

                    // Insert the new row, returning the primary key value of the new row
                    long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                    editTextEnglish.setText("");
                    editTextBulgarian.setText("");
                    responseTextView.setText("Saved in Id: " + String.valueOf(newRowId));

                }else {
                    Context context = getApplicationContext();
                    CharSequence text = "wrong input!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });







    }
}



