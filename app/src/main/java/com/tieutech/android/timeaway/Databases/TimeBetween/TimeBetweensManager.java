package com.tieutech.android.timeaway.Databases.TimeBetween;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tieutech.android.timeaway.Models.TimeBetween;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


//Database class #4:
    //Class for managing the TimeBetween SQLiteDatabase
public class TimeBetweensManager {


    //================= INSTANCE VARIABLES ==============================================================

    private final String TAG = "TimeBetweensManager"; //Tag for Logcat
    private static TimeBetweensManager sTimeBetweensManager; //DB Manager object. Singleton - only created once and lasts the lifetime of the app, unless memory is cleared
    private static SQLiteDatabase sSQLiteDatabase; //SQLiteDatabase object
    private Context mContext; //Context


    //================= METHODS ==============================================================

    //'Main Constructor' method - creates the TimeBetweensManager singleton object if it doesn't exist. Otherwise, if the object exists, just returns it
    public static TimeBetweensManager get(Context context){

        //If the TimeBetweensManager singleton does NOT exist
        if (sTimeBetweensManager == null){
            return new TimeBetweensManager(context); //Create the TimeBetweensManager singleton
        }

        return sTimeBetweensManager; //Return the singleton TimeBetweensManager object IF it exists. NOTE: This object lasts the lifetime of the app, unless memory is cleared
    }


    //Contructor - helper method for 'Main Constructor' method
    private TimeBetweensManager(Context context){

        try{
            mContext = context.getApplicationContext(); //Context that is tied to the LIFECYCLE of the ENTIRE application (instead of activity) for the purpose of retaining the SQLiteDatabase

            //Create/Retrieve the SINGLETON database (of type SQLiteDatabase)
                //getWritableDatable will:
                    //IF: An SQLiteDatabase does NOT exist..
                        //Call the overriden onCreate(SQLiteDatabase) from FavoriteQuotesDatabaseHelper to create the SQLiteDatabase
                    //IF: An SQLiteDatabase EXISTS...
                        //Check the version number of the database and call the overriden onUpgrade(..) from FavoriteQuotesDatabaseHelper to upgrade if necessary
            sSQLiteDatabase = new TimeBetweenDatabaseHelper(mContext).getWritableDatabase();
        }
        catch (NullPointerException nullPointerException){
            Log.e(TAG, "NullPointerException called");
        }
    }



    //===================== All the following methods are accessed like so: TimeBetweensManager.get(context).*method* ================================
    //===================== Their purposes are to QUERY, WRITE and REMOVE (favorite) Quote(s) to/from the SQLiteDatabase database ===============================

    //Obtain the TimeBetween via its ID - this method is called to check if a Quote EXISTS in the TimeBetween SQLiteDatabase
    public TimeBetween getTimeBetween(String ID){

        //OBTAIN the CursorWrapper (if the TimeBetween EXISTS in the database)
        TimeBetweenDatabaseCursorWrapper timeBetweenDatabaseCursorWrapper = queryTimeBetweens(TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ?", new String[]{ID.toString()});

        //Try risky task - timeBetweenDatabaseCursorWrapper.getCount() could return RuntimeException IF the Favorite Quoes database doesn't exist
        try{
            if (timeBetweenDatabaseCursorWrapper.getCount() == 0){
                return null;
            }

            //At this point, the TimeBetween exists in the TimeBetween database
            timeBetweenDatabaseCursorWrapper.moveToFirst(); //Point the CursorWrapper to the row containing the TimeBetween (since this row contains the matching ID)

            TimeBetween timeBetween = timeBetweenDatabaseCursorWrapper.getTimeBetweenFromTimeBetweenDatabase(); //Obtain the TimeBetween from the database (which is pointed to by the Cursor)

            return timeBetween; //Return the TimeBetween

        }

        finally{
            timeBetweenDatabaseCursorWrapper.close(); //Close the Cursor
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




    public void deleteTimeBetween(TimeBetween timeBetween){
        String timeBetweenID = timeBetween.getID().toString();

        sSQLiteDatabase.delete(TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME,
                TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ? ",
                new String[]{timeBetweenID});
    }



    public void updateTimeBetweensDatabase(TimeBetween timeBetween){
        String timeBetweenID = timeBetween.getID().toString();
        ContentValues contentValues = getContentValues(timeBetween);

        sSQLiteDatabase.update(
                TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME,
                contentValues,
                TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ? ",
                new String[]{timeBetweenID}
        );
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
