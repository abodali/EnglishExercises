package com.example.nasko.vocebularyExercises.entries;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nasko.vocebularyExercises.databases.FeedReaderContract;
import com.example.nasko.vocebularyExercises.databases.FeedReaderDbHelper;

import java.util.ArrayList;
import java.util.List;

public class EntryList {
    private List<MyEntity> myEntityList;

    public static ArrayList<MyEntity> getAllDB(Context context){
        ArrayList<MyEntity> list = new ArrayList<>();

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

        Cursor cursor = db1.rawQuery(String.format("SELECT * FROM %s", FeedReaderContract.FeedEntry.TABLE_NAME), null);

        while (cursor.moveToNext()) {

            //get value from DB
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE));
            String englishWord = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ENGLISH));
            String bulgarianWord = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BULGARIAN));
            int is_learned = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.IS_LEARNED));
            int trueIntroducedТimes = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES));

            list.add(new MyEntity(itemId, type, englishWord, bulgarianWord, is_learned, trueIntroducedТimes));
        }
        cursor.close();

        return list;
    }
}
