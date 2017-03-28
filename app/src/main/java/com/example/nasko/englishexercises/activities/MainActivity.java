package com.example.nasko.englishexercises.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nasko.englishexercises.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        final Button button = (Button) findViewById(R.id.buttonAdd);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });
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
            case R.id.exercises:
                Intent intent3 = new Intent(this, ExerciseActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
