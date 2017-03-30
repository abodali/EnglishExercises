package com.example.nasko.englishexercises.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nasko.englishexercises.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this, PreExersciseActivity.class);

        Button testbtn = (Button) findViewById(R.id.testButton);
        testbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
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
}
