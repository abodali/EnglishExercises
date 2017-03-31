package com.example.nasko.vocebularyExercises.entries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nasko.vocebularyExercises.databases.FeedReaderContract;
import com.example.nasko.vocebularyExercises.databases.FeedReaderDbHelper;

import java.util.ArrayList;

/**
 * Created by nasko on 28.3.2017 г..
 */

public class EntryListOld extends ArrayList {
    ArrayList<MyEntity> list;

    public EntryListOld(){
        list = new ArrayList<>();
    }

    public void fillTheList(Context context){
        readAllDb(context);
    }

    public ArrayList<MyEntity> getList() {
        return list;
    }


    private void Db(Context context){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ENGLISH,
                FeedReaderContract.FeedEntry.COLUMN_NAME_BULGARIAN
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES + " = ?";
        String[] selectionArgs = {"0"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_ENGLISH + " DESC";

        Cursor cursor = db1.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );




        while (cursor.moveToNext()) {

            //get value from DB
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE));
            String englishWord = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ENGLISH));
            String bulgarianWord = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_BULGARIAN));

             this.list.add(new MyEntity(itemId, type, englishWord, bulgarianWord));
        }
        cursor.close();
    }

    public void readAllDb(Context context){
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

            this.list.add(new MyEntity(itemId, type, englishWord, bulgarianWord, is_learned, trueIntroducedТimes));
        }
        cursor.close();
    }


    public ArrayList<MyEntity> readDb(Context context, int IntroducedТimes){
        ArrayList<MyEntity> list = new ArrayList<>();
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();

        Cursor cursor = db1.rawQuery(String.format("SELECT * FROM %s WHERE %s < %d",
                FeedReaderContract.FeedEntry.TABLE_NAME,
                FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES,
                IntroducedТimes), null);

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
    public ArrayList<MyEntity> readDb(Context context, int IntroducedТimes, String wantedType){
        ArrayList<MyEntity> list = new ArrayList<>();
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s < %d AND %s = '%s'",
                FeedReaderContract.FeedEntry.TABLE_NAME,
                FeedReaderContract.FeedEntry.TRUE_INTRODUCTED_TIMES,
                IntroducedТimes,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE,
                wantedType);
        Cursor cursor = db1.rawQuery(query, null);

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
