package com.example.nasko.englishexercises.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nasko.englishexercises.R;
import com.example.nasko.englishexercises.entries.EntryList;
import com.example.nasko.englishexercises.entries.MyEntity;

import java.util.ArrayList;

public class PreExersciseActivity extends AppCompatActivity {
    Button startBtn;
    Spinner spinnerTypes;
    EditText howManyExercises;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_exerscise);
        startBtn = (Button) findViewById(R.id.startBtn);
        spinnerTypes = (Spinner) findViewById(R.id.spinnerTypes);
        howManyExercises = (EditText) findViewById(R.id.howManyExercises);
        intent = new Intent(this, ExerciseActivity.class);


        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String count = howManyExercises.getText().toString();
                String type = spinnerTypes.getSelectedItem().toString();
                ArrayList<MyEntity> list = EntryList.getAllDB(getApplication());
                int dbSize = list.size();
                Bundle bundle = new Bundle();
                if (!count.matches("")){
                    bundle.putInt("count", dbSize);
                    bundle.putString("type", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
