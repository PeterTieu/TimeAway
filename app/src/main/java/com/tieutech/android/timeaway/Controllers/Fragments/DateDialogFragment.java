package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tieutech.android.timeaway.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//DialogFragment fgor DatePicker dialog
public class DateDialogFragment extends DialogFragment {

    private static final String ARG_TIME_BETWEEN_DATE = "argTimeBetweenDate"; //Declare 'key'
    public static final String EXTRA_DATE = "com.tieutech.android.timeaway"; //Define identifier for dialog fragment extra
    DatePicker mDatePicker; //Declare layout View of the dialog



    //Build encapsulating 'constructor'
    public static DateDialogFragment newInstance(Date timeBetweenDate){

        Bundle argumentBundle = new Bundle(); //Create Bundle object (i.e. argument-bundle)
        argumentBundle.putSerializable(ARG_TIME_BETWEEN_DATE, timeBetweenDate); //Set key-value pairs for argument-bundle
        DateDialogFragment dateDialogFragment = new DateDialogFragment(); //Create DateDialogFragment
        dateDialogFragment.setArguments(argumentBundle); //Set argument-bundle for the PixDetailFragment
        return dateDialogFragment; //Return DateDialogFragment object
    }



    //Override onCreateDialog lifecycle callback method from DialogFragment
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_between_date_picker, null); //Inflate DatePicker dialog layout

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_time_between_date_picker); //Assign DatePicker reference variable to associated resource ID

        //Create Calendar object, which could take Date objects and convert them into constituent properties (e.g. year, month, day of month, hour, etc.)
        Calendar calendar = Calendar.getInstance(); //Create instance of Calendar
        final Date timeBetweenDate = (Date) getArguments().getSerializable(ARG_TIME_BETWEEN_DATE); //Get 'value' from argument-bundle
        calendar.setTime(timeBetweenDate); //Set time in Calendar to time stored in the TimeBetween object

        //Get year/month/dayOfMonth from Calendar - i.e. saved from the TimeBetween
        int year = calendar.get(Calendar.YEAR);         //Get the Year
        int month = calendar.get(Calendar.MONTH);       //Get the Month
        int dayOfMonth = calendar.get(Calendar.DATE);   //Get the Day of the Month


        //Initialise DatePicker object
        mDatePicker.init(year, month, dayOfMonth, null);


        TextView dialogTitle = new TextView(getActivity());
        dialogTitle.setText(R.string.date_picker_dialog_fragment_title);
        dialogTitle.setTextColor(getResources().getColor(R.color.datePickerDialogButton));
        dialogTitle.setTextSize(25);
        dialogTitle.setTypeface(null, Typeface.BOLD);

        //Return AlertDialog (subclass of Dialog), which sets the dialog properties
        return new AlertDialog
                .Builder(getActivity()) //Create Builder
                .setView(view) //Set View of dialog
                .setCustomTitle(dialogTitle) //Set TITLE of dialog
                .setNeutralButton(android.R.string.cancel, null) //Set NEGATIVE BUTTON of the dialog. null: no listener for the cancel button
                .setNegativeButton(R.string.set_date_to_today, new DialogInterface.OnClickListener() { //Set NEUTRAL BUTTON of the dialog, and a listener to set Date to CURRENT date
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK, new Date());
                    }
                })
                .setPositiveButton(R.string.set_date_to_selected, new DialogInterface.OnClickListener() {
                            //Override listener of DialogInterface.OnClickListener.OnClickListener interface
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int year = mDatePicker.getYear(); //Get 'year' selected from DatePicker view
                                int month = mDatePicker.getMonth(); //Get 'month' selected from DatePicker view
                                int dayOfMonth = mDatePicker.getDayOfMonth(); //Get 'dayOfMonth' selected from DatePicker view

                                Date newSetDate = new GregorianCalendar(year, month, dayOfMonth).getTime(); //Save set year/month/dayOfMonth to Date object

                                sendResult(Activity.RESULT_OK, newSetDate); //Send new Date data back to hosting activity (TimeBetweenViewPagerActivity)
                            }
                        })
                .create();
    }



    //Send result to the hosting activity
    private void sendResult(int resultCode, Date newSetDate){

        //If hosting fragment (TimeBetweenDetailFragment) DOES NOT exist
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent(); //Create Intent
        intent.putExtra(EXTRA_DATE, newSetDate); //Add Date data as 'extra'

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent); //Send resultCode and Intent to hosting fragment (TimeBetweenDetailFragment)
    }

}
