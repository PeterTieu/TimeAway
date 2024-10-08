package com.tieutech.android.timeaway.Databases.TimeBetween;

//Database class #1:
    //Blueprint for the SQLite Database of TimeBetweens
public class TimeBetweenDatabaseSchema {

    //STATIC inner class for setting the database
    public static final class TimeBetweensTable{

        //Name of the table
        public static final String TIME_BETWEENS_TABLE_NAME = "TIME_BETWEENS_TABLE_NAME";

        //Inner-inner class that sets names of the columns of the table
        public static final class Columns{
            public static final String ID = "ID"; //Unique UUID of the TimeBetween
            public static final String TITLE = "TITLE"; //Title of the TimeBetween

            public static final String DATE_FIRST = "DATE_FIRST"; //Date of the FIRST date
            public static final String HOUR_FIRST = "HOUR_FIRST"; //Hour of the FIRST date
            public static final String MINUTE_FIRST = "MINUTE_FIRST"; //Minute of the FIRST date

            public static final String DATE_SECOND = "DATE_SECOND"; //Date of the SECOND date
            public static final String HOUR_SECOND = "HOUR_SECOND"; //Hour of the SECOND date
            public static final String MINUTE_SECOND = "MINUTE_SECOND"; //Minute of the SECOND date

            public static final String YEAR_DIFFERENCE = "YEAR_DIFFERENCE"; //DIFFERENCE in Year
            public static final String MONTH_DIFFERENCE = "MONTH_DIFFERENCE"; //DIFFERENCE in Month
            public static final String WEEK_DIFFERENCE = "WEEK_DIFFERENCE"; //DIFFERENCE in Week
            public static final String DAY_DIFFERENCE = "DAY_DIFFERENCE"; //DIFFERENCE in Day
            public static final String HOUR_DIFFERENCE = "HOUR_DIFFERENCE"; //DIFFERENCE in Hour
            public static final String MINUTE_DIFFERENCE = "MINUTE_DIFFERENCE"; //DIFFERENCE in Minute
        }
    }

}
