package com.tieutech.android.timeaway.Databases.TimeBetween;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tieutech.android.timeaway.Models.TimeBetween;

import java.util.Date;
import java.util.UUID;

public class TimeBetweenDatabaseCursorWrapper extends CursorWrapper{

    public TimeBetweenDatabaseCursorWrapper(Cursor cursor){
        super(cursor);
    }


    public TimeBetween getTimeBetweenFromTimeBetweenDatabase(){

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


        TimeBetween timeBetween = new TimeBetween(UUID.fromString(ID));
        timeBetween.setTitle(TITLE);

        timeBetween.setDateFirst(new Date(DATE_FIRST));
        timeBetween.setHourFirst(HOUR_FIRST);
        timeBetween.setMinuteFirst(MINUTE_FIRST);
        timeBetween.setDateSecond(new Date(DATE_SECOND));
        timeBetween.setHourSecond(HOUR_SECOND);
        timeBetween.setMinuteSecond(MINUTE_SECOND);

        timeBetween.setYearDifference(YEAR_DIFFERENCE);
        timeBetween.setMonthDifference(MONTH_DIFFERENCE);
        timeBetween.setWeekDifference(WEEK_DIFFERENCE);
        timeBetween.setDayDifference(DAY_DIFFERENCE);
        timeBetween.setHourDifference(HOUR_DIFFERENCE);
        timeBetween.setMinuteDifference(MINUTE_DIFFERENCE);

        return timeBetween;
    }


}
