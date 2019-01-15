package com.tieutech.android.timeaway.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Date;

public class TimePickerDialogFragment extends DialogFragment {


    //Declare instance variables
    private Date mDate;
    private static final String ARG_TIME_BETWEEN_TIME = "timeBetweenTime";
    private TimePicker mTimePicker;

    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.time";



    public static TimePickerDialogFragment newInstance(Date date){

        Bundle argumentBundle = new Bundle();
        argumentBundle.putSerializable(ARG_TIME_BETWEEN_TIME, date);
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setArguments(argumentBundle); //Link the argument bundle (ARG_TIME, date) to the TimePickerFragment object
        return timePickerDialogFragment;
    }



}
