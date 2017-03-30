package com.example.nasko.englishexercises.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nasko.englishexercises.R;
import com.example.nasko.englishexercises.databases.FeedReaderDbHelper;
import com.example.nasko.englishexercises.entries.EntryList;
import com.example.nasko.englishexercises.entries.MyEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreExersciseActivity extends AppCompatActivity {
    Button startBtn;
    Spinner spinnerTypes;
    EditText howManyExercises;
    Intent intent;
    List<MyEntity> myEntityList;
    Map<String, Integer> mapOfTypesCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_exerscise);
        startBtn = (Button) findViewById(R.id.startBtn);
        spinnerTypes = (Spinner) findViewById(R.id.spinnerTypes);
        howManyExercises = (EditText) findViewById(R.id.howManyExercises);
        intent = new Intent(this, ExerciseActivity.class);

        myEntityList = new ArrayList<>();
        mapOfTypesCounter = new HashMap<>();

        fillTheMap();

        spinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setHint();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String count = howManyExercises.getText().toString();
                String type = spinnerTypes.getSelectedItem().toString();
                ArrayList<MyEntity> list = EntryList.getAllDB(getApplication());
                int dbSize = list.size();
                Bundle bundle = new Bundle();
                if (!count.matches("")){
                    bundle.putInt("count", Integer.valueOf(count));
                    bundle.putString("type", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    bundle.putInt("count", dbSize);
                    bundle.putString("type", type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, NewWordsActivity.class);
                startActivity(intent);
                return true;
            case R.id.see:
                Intent intent2 = new Intent(this, dataVisualizationActivity.class);
                startActivity(intent2);
                return true;
            case R.id.settings:
                Intent intent4 = new Intent(this, PreferenceActivity.class);
                startActivity(intent4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillTheMap();
        setHint();
    }

    private void fillTheMap(){
        this.myEntityList = EntryList.getAllDB(getApplication());
        mapOfTypesCounter.clear();
        for (MyEntity entity : myEntityList) {
            if (!mapOfTypesCounter.containsKey(entity.getType())){
                mapOfTypesCounter.put(entity.getType(), 0);
            }else {
                int count = mapOfTypesCounter.get(entity.getType());
                count++;
                mapOfTypesCounter.put(entity.getType(), count);
            }
        }
    }

    private void setHint(){
        String selectedType = spinnerTypes.getSelectedItem().toString();
        if (selectedType.equals("All")){
            howManyExercises.setHint(String.valueOf(myEntityList.size()));
        } else {
            if (mapOfTypesCounter.containsKey(selectedType)){
                int count = mapOfTypesCounter.get(selectedType);
                howManyExercises.setHint(String.valueOf(count));
            }else {
                howManyExercises.setHint("0");
            }
        }
    }
}
