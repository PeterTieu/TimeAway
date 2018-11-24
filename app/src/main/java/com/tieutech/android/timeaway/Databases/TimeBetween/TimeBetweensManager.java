package com.tieutech.android.timeaway.Databases.TimeBetween;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tieutech.android.timeaway.Models.TimeBetween;

import java.util.ArrayList;
import java.util.List;

public class TimeBetweensManager {

    private final String TAG = "TimeBetweensManager";
    private static TimeBetweensManager sTimeBetweensManager;
    private static SQLiteDatabase sSQLiteDatabase;
    private Context mContext;


    public static TimeBetweensManager get(Context context){

        if (sTimeBetweensManager == null){
            return new TimeBetweensManager(context);
        }

        return sTimeBetweensManager;
    }


    private TimeBetweensManager(Context context){

        try{
            mContext = context.getApplicationContext();

            sSQLiteDatabase = new TimeBetweenDatabaseHelper(mContext).getWritableDatabase();
        }
        catch (NullPointerException nullPointerException){
            Log.e(TAG, "NullPointerException called");
        }
    }



    public TimeBetween getTimeBetween(String ID){
        TimeBetweenDatabaseCursorWrapper timeBetweenDatabaseCursorWrapper = queryTimeBetweens(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ?", new String[]{ID.toString()});

        try{
            if (timeBetweenDatabaseCursorWrapper.getCount() == 0){
                return null;
            }

            timeBetweenDatabaseCursorWrapper.moveToFirst();

            TimeBetween timeBetween = timeBetweenDatabaseCursorWrapper.getTimeBetweenFromTimeBetweenDatabase();

            return timeBetween;

        }

        finally{
            timeBetweenDatabaseCursorWrapper.close();
        }

    }





    public List<TimeBetween> getTimeBetweens(){

        List<TimeBetween> timeBetweensList = new ArrayList<>();

        TimeBetweenDatabaseCursorWrapper timeBetweenDatabaseCursorWrapper = queryTimeBetweens(null, null);

        try{
            timeBetweenDatabaseCursorWrapper.moveToFirst();

            while (!timeBetweenDatabaseCursorWrapper.isAfterLast()){

                timeBetweensList.add(timeBetweenDatabaseCursorWrapper.getTimeBetweenFromTimeBetweenDatabase());

                timeBetweenDatabaseCursorWrapper.moveToNext();
            }

        }
        finally {
            timeBetweenDatabaseCursorWrapper.close();
        }

        return timeBetweensList;
    }


    public void addTimeBetween(TimeBetween timeBetween){
        ContentValues contentValues = getContentValues(timeBetween);
        sSQLiteDatabase.insert(TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME, null, contentValues);
    }






    private TimeBetweenDatabaseCursorWrapper queryTimeBetweens(String whereClause, String[] whereArgs){
        Cursor timeBetweensDatabaseCursor = sSQLiteDatabase.query(
                TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TimeBetweenDatabaseCursorWrapper(timeBetweensDatabaseCursor);
    }




    private static ContentValues getContentValues(TimeBetween timeBetween){
        ContentValues contentValues = new ContentValues();

        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID, timeBetween.getID().toString());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.TITLE, timeBetween.getTitle());

        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_FIRST, timeBetween.getDateFirst().getTime()); //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_FIRST, timeBetween.getHourFirst());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_FIRST, timeBetween.getMinuteFirst());

        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_SECOND, timeBetween.getDateSecond().getTime()); //Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_SECOND, timeBetween.getHourSecond());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_SECOND, timeBetween.getMinuteSecond());

        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.YEAR_DIFFERENCE, timeBetween.getYearDifference());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MONTH_DIFFERENCE, timeBetween.getMonthDifference());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.WEEK_DIFFERENCE, timeBetween.getWeekDifference());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DAY_DIFFERENCE, timeBetween.getDayDifference());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_DIFFERENCE, timeBetween.getHourDifference());
        contentValues.put(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_DIFFERENCE, timeBetween.getMinuteDifference());

        return contentValues;
    }





}
