package com.example.nasko.englishexercises.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nasko.englishexercises.entries.EntryList;
import com.example.nasko.englishexercises.entries.MyEntity;
import com.example.nasko.englishexercises.R;

import java.util.ArrayList;

/**
 * Created by nasko on 27.3.2017 г..
 */

public class dataVisualizationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_visualization);

        TextView allText = (TextView) findViewById(R.id.textViewAllText);
        EntryList entryList = new EntryList();

        entryList.fillTheList(getApplication());

        String s = "";
        ArrayList<MyEntity>  litt = entryList.getList();


        for (MyEntity entity : litt) {
            s+= entity.toString();
            s+= "\n";
        }

        allText.setText(s);





    }
}
