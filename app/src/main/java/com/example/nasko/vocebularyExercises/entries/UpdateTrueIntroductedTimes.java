package com.example.nasko.vocebularyExercises.entries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.nasko.vocebularyExercises.databases.FeedReaderContract;
import com.example.nasko.vocebularyExercises.databases.FeedReaderDbHelper;

import java.util.List;


public class UpdateTrueIntroductedTimes {

    public static void currentWords(List<MyEntity> list, Context context){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

        String table = FeedReaderContract.FeedEntry.TABLE_NAME;
        String column = FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES;
        String columndId = FeedReaderContract.FeedEntry._ID;

        for (int i = 0; i < list.size(); i++) {
            int currentValue = list.get(i).getTrueIntroducedТimes() + 1;
            int id = list.get(i).getId();

            String strSQL = "UPDATE " + table + " SET "+ column + " = " + currentValue + " WHERE "+ columndId +" = "+ id;
            db1.execSQL(strSQL);
        }
    }

    public static void wrongWords(List<MyEntity> list, Context context){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

        String table = FeedReaderContract.FeedEntry.TABLE_NAME;
        String column = FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES;
        String columndId = FeedReaderContract.FeedEntry._ID;

        for (int i = 0; i < list.size(); i++) {
            int currentValue = list.get(i).getTrueIntroducedТimes() - 1;
            int id = list.get(i).getId();

            String strSQL = "UPDATE " + table + " SET "+ column + " = " + currentValue + " WHERE "+ columndId +" = "+ id;
            db1.execSQL(strSQL);
        }
    }
}
