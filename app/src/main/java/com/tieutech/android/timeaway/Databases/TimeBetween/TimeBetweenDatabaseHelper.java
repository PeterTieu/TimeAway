package com.tieutech.android.timeaway.Databases.TimeBetween;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeBetweenDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timeBetweenDatabase.db";

    public TimeBetweenDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL(
                "create table" +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME +
                        "(" +
                        " _id integer primary key autoincrement, " +

                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.TITLE + ", " +

                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_FIRST + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_FIRST + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_FIRST + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_SECOND + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_SECOND + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_SECOND + ", " +

                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.YEAR_DIFFERENCE + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MONTH_DIFFERENCE + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.WEEK_DIFFERENCE + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DAY_DIFFERENCE + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_DIFFERENCE + ", " +
                        TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_DIFFERENCE + ", " +
                        ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        //Do nothing
    }



}
