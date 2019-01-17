package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tieutech.android.timeaway.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerDialogFragment extends DialogFragment {


    //Declare instance variables
    private Date mDate;
    private static final String ARG_TIME_BETWEEN_TIME = "timeBetweenTime";
    private TimePicker mTimePicker;

    public static final String EXTRA_TIME = "com.tieutech.android.timeaway";



    public static TimePickerDialogFragment newInstance(Date date){

        Bundle argumentBundle = new Bundle();
        argumentBundle.putSerializable(ARG_TIME_BETWEEN_TIME, date);
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setArguments(argumentBundle); //Link the argument bundle (ARG_TIME, date) to the TimePickerFragment object
        return timePickerDialogFragment;
    }






    //Override the onCreateDialog(Bundle) Dialog lifecycle callback method from DialogFragment
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){


        mDate = (Date) getArguments().getSerializable(ARG_TIME_BETWEEN_TIME); //Retrieve data from the argument bundle, and store it in the reference variable, date, of type Date


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_between_time_picker, null); //Inflate the dialog layout

        //mTimePicker is the clock part of the TimePickerFragment dialog
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_between_time_picker);




        //Instantiate a Calendar object via its static method, getInstance()
        //then assign the Calendar object to instance variable, calendar (of type Calendar)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        //Initialise the clock part of the TimePickerFragment,
        // so that later when the TimePicker dialog is opened, the time is pre-set to these values.
        //NOTE: These values all originate from the mCrime.getDate() code from CrimeFragment!
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);




        //Call the setOnTimeChangedListener() method of TimePicker, to save the selected time - when the "ok" button is pressed
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                //minute = calendar.get(Calendar.MINUTE);


                mDate = new GregorianCalendar(year, month, day, hour, minute).getTime();
                getArguments().putSerializable(EXTRA_TIME, mDate);
            }
        });




        TextView dialogTitle = new TextView(getActivity());
        dialogTitle.setText(R.string.time_picker_title);
        dialogTitle.setTextColor(getResources().getColor(R.color.datePickerDialogButton));
        dialogTitle.setTextSize(25);
        dialogTitle.setTypeface(null, Typeface.BOLD);


        //"Wrap" the AlertDialog in the DatePickerFragment
        //i.e. in the DatePickerFragment class, we instantiate the AlertDialog class
        // and use it to call the dialog_time layout (now inflated) to show up on the screen
        //NOTE: AlertDialog is a subclass of Dialog. Therefore, it IS-A Dialog
        //AlertDialog is the entire widget, which includes the title, positive button, and the calendar part
        return new AlertDialog.Builder(getActivity()).
                setView(v)
                .setCustomTitle(dialogTitle) //i.e. "Time of crime"
                .setNegativeButton(android.R.string.cancel, null) //null: no listeners
                .setPositiveButton(android.R.string.ok, //we use the android default string, ok, to set the positive button to "ok"
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {




                                //NOTE (DURING REVISION):
                                //MAYBE WHY WE ARE NOT GETTING THE TIME SAVED INTO THE CRIME OBJECT IS BECAUSE...
                                // WE HAVE NOT PUT ANY CODE IN *THIS SPACE* (SIMILAR TO DatePickerFragment)..
                                //....AND...
                                // HAVE NOT PASSED ANY VALUES INTO sendResult(..).



                                //Send the changed date result to the target fragment (CrimeFragment)
                                sendResult(Activity.RESULT_OK); //Activity.RESULT_OK: resultCode

                            }
                        })
                .create();
    }




}
