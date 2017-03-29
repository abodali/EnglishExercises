package com.example.nasko.englishexercises.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nasko.englishexercises.R;
import com.example.nasko.englishexercises.entries.EntryListOld;
import com.example.nasko.englishexercises.entries.MyEntity;
import com.example.nasko.englishexercises.entries.UpdateTrueIntroductedTimes;

import java.util.ArrayList;

/**
 * Created by Nasko on 27.3.2017 г..
 */

public class ExerciseActivity extends Activity {
    int counter;
    TextView textViewEnglish;
    TextView textViewResult;
    TextView textViewType;
    Button buttonNext;
    EditText editTextBulgarian;
    ArrayList<MyEntity> wrongWords;
    ArrayList<MyEntity> list;
    ArrayList<MyEntity> currentWords;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        wrongWords = new ArrayList<>();
        currentWords = new ArrayList<>();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String intrSett = prefs.getString("introduced_times", "10");
        int introducedТimes = Integer.valueOf(intrSett);

        EntryListOld entryListOld = new EntryListOld();
        entryListOld.readDb(getApplication(), introducedТimes);
        list = entryListOld.getList();


        counter = list.size() -1;

        textViewType = (TextView) findViewById(R.id.textViewType);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewEnglish = (TextView) findViewById(R.id.textViewEnglish);
        editTextBulgarian = (EditText) findViewById(R.id.editTextBulgarian);
        buttonNext = (Button) findViewById(R.id.nextBtn);

        if (counter > 0) {
            textViewType.setText(list.get(counter).getType());
            textViewEnglish.setText(list.get(counter).getBgWord());

            buttonNext.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String entredWord = String.valueOf(editTextBulgarian.getText());
                    if (!(entredWord.equals(list.get(counter).getEngWord()))) {
                        wrongWords.add(list.get(counter));
                    } else {
                        currentWords.add(list.get(counter));
                    }

                    next();
                    editTextBulgarian.setText(null);
                }
            });

            //test
        }else {
            Context context = getApplicationContext();
            CharSequence text = "no words to learn";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            onBackPressed();
        }


    }

    final private void next(){
        counter--;
        if (counter == -1){
            end();
        }else {
            textViewType.setText(list.get(counter).getType());
            textViewEnglish.setText(list.get(counter).getBgWord());
            if (counter == 0){
                buttonNext.setText("finish");
            }}
    }

    private void printResult(){
        String result = "";
        if (wrongWords.size() == 0){
            result = "congratulations";
        }else {
            result += "wrong answers: " + wrongWords.size() + "\n";
            result += "incorrect words: \n";
            for (int i = 0; i < wrongWords.size(); i++) {
                result += wrongWords.get(i).getEngWord() + "\n";
            }
        }
        textViewResult.setText(result);
    }
    private void end(){
        buttonNext.setVisibility(View.GONE);
        editTextBulgarian.setVisibility(View.GONE);
        textViewType.setVisibility(View.GONE);
        textViewEnglish.setVisibility(View.GONE);

        if (wrongWords.size() > 0){
            UpdateTrueIntroductedTimes.wrongWords(wrongWords, getApplication());
        }

        if (currentWords.size() > 0){
            UpdateTrueIntroductedTimes.currentWords(currentWords, getApplication());
        }

        printResult();

    }
}
