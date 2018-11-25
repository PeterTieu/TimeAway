package com.tieutech.android.timeaway.Models;

import java.util.Date;
import java.util.UUID;

public class TimeBetween {

    //========= INSTANCE VARIABLES =========================
    private UUID mID;   //Unique (randomly generated) ID of the TimeBetween object

    private String mTitle;      //Title of the TimeBetween

    private Date mDateFirst;    //DATE of the FIRST time to calculate from
    private int mHourFirst;     //HOUR of the FIRST time to calculate from
    private int mMinuteFirst;   //MINUTE of the FIRST time to calculate from

    private Date mDateSecond;   //DATE of the SECOND tie to calculate from
    private int mHourSecond;    //HOUR of the SECOND time to calculate from
    private int mMinuteSecond;  //MINUTE of the SECOND time to calculate from

    private int mYearDifference;    //Difference in the YEAR between the two times
    private int mMonthDifference;   //Difference in the MONTH between the two times
    private int mWeekDifference;    //Difference in the WEEK between the two times
    private int mDayDifference;     //Difference in the DAY between the two times
    private int mHourDifference;    //Difference in the HOUR between the two times
    private int mMinuteDifference;  //Difference in the MINUTE between the two times



    //========= CONSTRUCTORS =========================

    //Constructor #1
    public TimeBetween(){
        this (UUID.randomUUID());
    }

    //Constructor #2 - called by constructor #1
    public TimeBetween(UUID id){
        mID = id;
    }



    //========= GETTERS/SETTERS =========================

    //Getter for ID - NOTE: No setter for the ID is made, as it should only be defined (randomly) via the constructor
    public UUID getID() {
        return mID;
    }


    //Getter for the Title
    public String getTitle(){
        return mTitle;
    }


    //Setter for the Title
    public void setTitle(String title){
        mTitle = title;
    }


    //Getter for Date First
    public Date getDateFirst() {
        return mDateFirst;
    }

    //Setter for Date First
    public void setDateFirst(Date dateFirst) {
        mDateFirst = dateFirst;
    }


    //Getter for Hour First
    public int getHourFirst() {
        return mHourFirst;
    }

    //Setter for Hour First
    public void setHourFirst(int hourFirst) {
        mHourFirst = hourFirst;
    }


    //Getter for Minute First
    public int getMinuteFirst() {
        return mMinuteFirst;
    }

    //Stter for Minute First
    public void setMinuteFirst(int minuteFirst) {
        mMinuteFirst = minuteFirst;
    }


    //Getter for Date Second
    public Date getDateSecond() {
        return mDateSecond;
    }

    //Setter for Date Second
    public void setDateSecond(Date dateSecond) {
        mDateSecond = dateSecond;
    }


    //Getter for Hour Second
    public int getHourSecond() {
        return mHourSecond;
    }

    //Setter for Hour Second
    public void setHourSecond(int hourSecond) {
        mHourSecond = hourSecond;
    }


    //Getter for Minute Second
    public int getMinuteSecond() {
        return mMinuteSecond;
    }

    //Getter for Minute Second
    public void setMinuteSecond(int minuteSecond) {
        mMinuteSecond = minuteSecond;
    }


    //Getter for Year Difference
    public int getYearDifference() {
        return mYearDifference;
    }

    //Setter for Year Difference
    public void setYearDifference(int yearDifference) {
        mYearDifference = yearDifference;
    }


    //Getter for Month Difference
    public int getMonthDifference() {
        return mMonthDifference;
    }

    //Stter for Month Difference
    public void setMonthDifference(int monthDifference) {
        mMonthDifference = monthDifference;
    }


    //Getter for Week Difference
    public int getWeekDifference() {
        return mWeekDifference;
    }


    //Setter for Week Difference
    public void setWeekDifference(int weekDifference) {
        mWeekDifference = weekDifference;
    }


    //Getter for Day Difference
    public int getDayDifference() {
        return mDayDifference;
    }

    //Setter for Day Difference
    public void setDayDifference(int dayDifference) {
        mDayDifference = dayDifference;
    }


    //Getter for Hour Difference
    public int getHourDifference() {
        return mHourDifference;
    }

    //Setter for Hour Difference
    public void setHourDifference(int hourDifference) {
        mHourDifference = hourDifference;
    }


    //Getter for Minute Difference
    public int getMinuteDifference() {
        return mMinuteDifference;
    }

    //Setter for Minute Difference
    public void setMinuteDifference(int minuteDifference) {
        mMinuteDifference = minuteDifference;
    }

}