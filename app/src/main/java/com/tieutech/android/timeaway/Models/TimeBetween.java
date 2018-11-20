package com.tieutech.android.timeaway.Models;

import java.util.Date;
import java.util.UUID;

public class TimeBetween {

    //========= INSTANCE VARIABLES =========================
    private UUID mID;

    private Date mDateFirst;
    private int mHourFirst;
    private int mMinuteFirst;


    private Date mDateSecond;
    private int mHourSecond;
    private int mMinuteSecond;


    private int mYearDifference;
    private int mMonthDifference;
    private int mWeekDifference;
    private int mDayDifference;
    private int mHourDifference;
    private int mMinuteDifference;


    //========= CONSTRUCTORS =========================
    public TimeBetween(){

        this (UUID.randomUUID());
    }


    public TimeBetween(UUID id){
        mID = id;
    }








    //========= GETTERS/SETTERS =========================

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public Date getDateFirst() {
        return mDateFirst;
    }

    public void setDateFirst(Date dateFirst) {
        mDateFirst = dateFirst;
    }

    public int getHourFirst() {
        return mHourFirst;
    }

    public void setHourFirst(int hourFirst) {
        mHourFirst = hourFirst;
    }

    public int getMinuteFirst() {
        return mMinuteFirst;
    }

    public void setMinuteFirst(int minuteFirst) {
        mMinuteFirst = minuteFirst;
    }

    public Date getDateSecond() {
        return mDateSecond;
    }

    public void setDateSecond(Date dateSecond) {
        mDateSecond = dateSecond;
    }

    public int getHourSecond() {
        return mHourSecond;
    }

    public void setHourSecond(int hourSecond) {
        mHourSecond = hourSecond;
    }

    public int getMinuteSecond() {
        return mMinuteSecond;
    }

    public void setMinuteSecond(int minuteSecond) {
        mMinuteSecond = minuteSecond;
    }

    public int getYearDifference() {
        return mYearDifference;
    }

    public void setYearDifference(int yearDifference) {
        mYearDifference = yearDifference;
    }

    public int getMonthDifference() {
        return mMonthDifference;
    }

    public void setMonthDifference(int monthDifference) {
        mMonthDifference = monthDifference;
    }

    public int getWeekDifference() {
        return mWeekDifference;
    }

    public void setWeekDifference(int weekDifference) {
        mWeekDifference = weekDifference;
    }

    public int getDayDifference() {
        return mDayDifference;
    }

    public void setDayDifference(int dayDifference) {
        mDayDifference = dayDifference;
    }

    public int getHourDifference() {
        return mHourDifference;
    }

    public void setHourDifference(int hourDifference) {
        mHourDifference = hourDifference;
    }

    public int getMinuteDifference() {
        return mMinuteDifference;
    }

    public void setMinuteDifference(int minuteDifference) {
        mMinuteDifference = minuteDifference;
    }

}
