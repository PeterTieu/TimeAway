package com.tieutech.android.timeaway.Databases.TimeBetween;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tieutech.android.timeaway.Models.TimeBetween;

import java.util.Date;
import java.util.UUID;


//Database class #3:
    //Wrapper class for the SQLiteDatabase cursor
public class TimeBetweenDatabaseCursorWrapper extends CursorWrapper{

    //Constructor - called by TimeBetweensManager to obtain a CursorWrapper so as to point the cursor to the TimeBewtween in the TimeBetweens SQLiteDatabase
    public TimeBetweenDatabaseCursorWrapper(Cursor cursor){
        super(cursor);
    }

    //Called by TimeBetweensManager to obtain the TimeBetween that is pointed to by the CursorWrapper (TimeBetweenDatabaseCursorWrapper), in the TimeBetweens SQLiteDatabase
    public TimeBetween getTimeBetweenFromTimeBetweenDatabase(){

        //Obtain the values of all the columns in the same row (corresponding to the TimeBetween instance variables)
        String ID = getString(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID));
        String TITLE = getString(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.TITLE));

        long DATE_FIRST = getLong(getColumnIndex( TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_FIRST));
        int HOUR_FIRST = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_FIRST ));
        int MINUTE_FIRST = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_FIRST ));
        long DATE_SECOND = getLong(getColumnIndex( TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DATE_SECOND));
        int HOUR_SECOND = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_SECOND ));
        int MINUTE_SECOND = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_SECOND ));

        int YEAR_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.YEAR_DIFFERENCE));
        int MONTH_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MONTH_DIFFERENCE));
        int WEEK_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.WEEK_DIFFERENCE));
        int DAY_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.DAY_DIFFERENCE));
        int HOUR_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.HOUR_DIFFERENCE));
        int MINUTE_DIFFERENCE = getInt(getColumnIndex(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.MINUTE_DIFFERENCE));



        //Assign the obtained values to a TimeBetween object
        TimeBetween timeBetween = new TimeBetween(UUID.fromString(ID)); //Create a TimeBetween object with the obtained UUID

        timeBetween.setTitle(TITLE); //Assign Title

        timeBetween.setDateFirst(new Date(DATE_FIRST));     //Assign First Date
        timeBetween.setHourFirst(HOUR_FIRST);               //Assign First Hour
        timeBetween.setMinuteFirst(MINUTE_FIRST);           //Assign First Minute
        timeBetween.setDateSecond(new Date(DATE_SECOND));   //Assign Second Date
        timeBetween.setHourSecond(HOUR_SECOND);             //Assign Second Hour
        timeBetween.setMinuteSecond(MINUTE_SECOND);         //Assign Second Minute

        timeBetween.setYearDifference(YEAR_DIFFERENCE);     //Assign Year Difference
        timeBetween.setMonthDifference(MONTH_DIFFERENCE);   //Assign Month Difference
        timeBetween.setWeekDifference(WEEK_DIFFERENCE);     //Assign Week Difference
        timeBetween.setDayDifference(DAY_DIFFERENCE);       //Assign Day Difference
        timeBetween.setHourDifference(HOUR_DIFFERENCE);     //Assign Hour Difference
        timeBetween.setMinuteDifference(MINUTE_DIFFERENCE); //Assign Minute Difference

        return timeBetween; //Return fully constructed TimeBetween object
    }

}
