package com.example.nasko.englishexercises.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nasko.englishexercises.R;
import com.example.nasko.englishexercises.entries.EntryList;
import com.example.nasko.englishexercises.entries.MyEntity;
import com.example.nasko.englishexercises.entries.UpdateTrueIntroductedTimes;

import java.util.ArrayList;

/**
 * Created by Nasko on 27.3.2017 г..
 */

public class ExerciseActivity extends Activity {
    TextView textViewEnglish;
    TextView textViewResult;
    TextView textViewType;
    Button buttonNext;
    EditText editTextBulgarian;
    int counter;
    ArrayList<MyEntity> wrongWords;
    ArrayList<MyEntity> list;
    ArrayList<MyEntity> currentWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        wrongWords = new ArrayList<>();
        currentWords = new ArrayList<>();

        EntryList entryList = new EntryList();
        entryList.readDb(getApplication(), 10);
        list = entryList.getList();


        counter = list.size() -1;





        textViewType = (TextView) findViewById(R.id.textViewType);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewEnglish = (TextView) findViewById(R.id.textViewEnglish);
        editTextBulgarian = (EditText) findViewById(R.id.editTextBulgarian);
        buttonNext = (Button) findViewById(R.id.nextBtn);

        textViewType.setText(list.get(counter).getType());
        textViewEnglish.setText(list.get(counter).getBgWord());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String entredWord = String.valueOf(editTextBulgarian.getText());
                if (!(entredWord.equals(list.get(counter).getEngWord()))){
                    wrongWords.add(list.get(counter));
                }else {
                    currentWords.add(list.get(counter));
                }

                next();
                editTextBulgarian.setText(null);
            }
        });


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
