package com.tieutech.android.timeaway.Databases.TimeBetween;

//Database class #1:
    //Blueprint for the SQLite Database of TimeBetweens
public class TimeBetweenDatabaseSchema {

    public static final class TimeBetweensTable{
        public static final String TIME_BETWEENS_TABLE_NAME = "TIME_BETWEENS_TABLE_NAME";

        public static final class Columns{
            public static final String ID = "ID";
            public static final String TITLE = "TITLE";

            public static final String DATE_FIRST = "DATE_FIRST";
            public static final String HOUR_FIRST = "HOUR_FIRST";
            public static final String MINUTE_FIRST = "MINUTE_FIRST";
            public static final String DATE_SECOND = "DATE_SECOND";
            public static final String HOUR_SECOND = "HOUR_SECOND";
            public static final String MINUTE_SECOND = "MINUTE_SECOND";

            public static final String YEAR_DIFFERENCE = "YEAR_DIFFERENCE";
            public static final String MONTH_DIFFERENCE = "MONTH_DIFFERENCE";
            public static final String WEEK_DIFFERENCE = "WEEK_DIFFERENCE";
            public static final String DAY_DIFFERENCE = "DAY_DIFFERENCE";
            public static final String HOUR_DIFFERENCE = "HOUR_DIFFERENCE";
            public static final String MINUTE_DIFFERENCE = "MINUTE_DIFFERENCE";
        }
    }

}
