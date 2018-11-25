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
                        //Call the overriden onCreate(SQLiteDatabase) from TimeBetweensDatabaseHelper to create the SQLiteDatabase
                    //IF: An SQLiteDatabase EXISTS...
                        //Check the version number of the database and call the overriden onUpgrade(..) from TimeBetweensDatabaseHelper to upgrade if necessary
            sSQLiteDatabase = new TimeBetweenDatabaseHelper(mContext).getWritableDatabase();
        }
        catch (NullPointerException nullPointerException){
            Log.e(TAG, "NullPointerException called");
        }
    }



    //===================== All the following methods are accessed like so: TimeBetweensManager.get(context).*method* ================================
    //===================== Their purposes are to QUERY, WRITE and REMOVE (favorite) TimeBetween(s) to/from the SQLiteDatabase database ===============================

    //Obtain the TimeBetween via its ID - this method is called to check if a TimeBetween EXISTS in the TimeBetween SQLiteDatabase
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




    ////OBTAIN List of ALL the TimeBetween objects in the TimeBetween database
    public List<TimeBetween> getTimeBetweens(){

        List<TimeBetween> timeBetweensList = new ArrayList<>(); //Create List object

        TimeBetweenDatabaseCursorWrapper timeBetweenDatabaseCursorWrapper = queryTimeBetweens(null, null); //Obtain the CursorWrapper, which could point to ANY column/row in the database

        //Try risky task - timeBetweenDatabaseCursorWrapper.moveToFirst() could return RuntimeException IF the TimeBetween database doesn't exist
        try{
            timeBetweenDatabaseCursorWrapper.moveToFirst(); //Point the CursorWrapper to FIRST ROW (whch contains the FIRST TimeBetween in the database)

            //While the Cursor DOES NOT point to the position after the last row (IOW, while the cursor is still pointing to an EXISTING row)
            while (!timeBetweenDatabaseCursorWrapper.isAfterLast()){
                timeBetweensList.add(timeBetweenDatabaseCursorWrapper.getTimeBetweenFromTimeBetweenDatabase()); //Obtain the TimeBetween pointed to by the Cursor, then add it to the List of TimeBetweens
                timeBetweenDatabaseCursorWrapper.moveToNext(); //Move the Cursor to the next row in the database
            }
        }
        finally {
            timeBetweenDatabaseCursorWrapper.close(); //Close the Cursor
        }

        return timeBetweensList; //Return the List of all TimeBetween objects in the database
    }




    ///ADD a TimeBetween to the TimeBetweens database
    public void addTimeBetween(TimeBetween timeBetween){
        ContentValues contentValues = getContentValues(timeBetween); //Create a ContentValues object, storing the TimeBetween in it
        sSQLiteDatabase.insert(TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME, null, contentValues); //Insert the ContentValues object (containing the TimeBetween) into the database
    }




    //DELETE a TimeBetween from the TimeBetweens database
    public void deleteTimeBetween(TimeBetween timeBetween){

        String timeBetweenID = timeBetween.getID().toString(); //Obtain the unique ID of the Quote

        //Delete the TimeBetween from the database
        sSQLiteDatabase.delete(TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME, //Database name
                TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ? ", //Column name (ID)
                new String[]{timeBetweenID}); //Row value (identified by the String value of the ID)
    }




    //UPDATE the TimeBetweens database - to accomodate the newly CHANGED TimeBetween object
    public void updateTimeBetweensDatabase(TimeBetween timeBetween){
        String timeBetweenID = timeBetween.getID().toString(); //Obtain the unique ID from the TimeBetween object

        ContentValues contentValues = getContentValues(timeBetween); //Create a ContentValues object, storing the TimeBetween in it

        //Delete the Quote from the database
        sSQLiteDatabase.update(
                TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME, //Database name
                contentValues, //ContentValuea object (containing the TimeBetween)
                TimeBetweenDatabaseSchema.TimeBetweensTable.Columns.ID + " = ? ", //Column name (ID)
                new String[]{timeBetweenID} //Row value (identified by the String value of the ID)
        );
    }





    //============= HELPER METHODS ==============================================================================================

    //Helper method - OBTAIN the CursorWrapper (if the Favorite TimeBetween EXIST in the database), and point it to the column (whereClause) and row (whereArgs)
    private TimeBetweenDatabaseCursorWrapper queryTimeBetweens(String whereClause, String[] whereArgs){

        //Obtain the Cursor
        Cursor timeBetweensDatabaseCursor = sSQLiteDatabase.query(
                TimeBetweenDatabaseSchema.TimeBetweensTable.TIME_BETWEENS_TABLE_NAME, //Database name
                null, //Which columns to return (String[])
                whereClause, //Column (String)
                whereArgs, //Row (String[])
                null, //How to group the rows (String). null means rows are not grouped
                null, //Which rows to group (String). null means all row groups are included
                null //How to order rows (String). null means use default order (i.e. unsorted)
        );

        return new TimeBetweenDatabaseCursorWrapper(timeBetweensDatabaseCursor); //Return the CursorWrapper (Cursor)
    }




    //Helper method - Create a ContentValues object and store a TimeBetween in it.
        //NOTE: A ContentValues object is necessary for the SQLiteDatabase to access
    private static ContentValues getContentValues(TimeBetween timeBetween){

        ContentValues contentValues = new ContentValues(); //Create the ContentValues object


        //Add member variables of the Quote to the ContentValues object (using key-value pairs)
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
