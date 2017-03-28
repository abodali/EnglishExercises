package com.example.nasko.englishexercises.databases;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_ENGLISH = "english";
        public static final String COLUMN_NAME_BULGARIAN = "bulgarian";
        public static final String IS_LEARNED = "is_learned";
        public static final String TRUE_INTRODUCTED_TIMES = "true_introducted_times";
    }

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TYPE + " TEXT," +
                    FeedEntry.COLUMN_NAME_ENGLISH + " TEXT," +
                    FeedEntry.COLUMN_NAME_BULGARIAN + " TEXT)";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}