package com.tieutech.android.timeaway.Databases.TimeBetween;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tieutech.android.timeaway.Models.TimeBetween;

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







}
