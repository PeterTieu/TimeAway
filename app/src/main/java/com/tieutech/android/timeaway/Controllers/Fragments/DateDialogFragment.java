package com.tieutech.android.timeaway.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Date;

//DialogFragment fgor DatePicker dialog
public class DateDialogFragment extends DialogFragment {


    //Declare 'key'
    private static final String ARG_PIX_DATE = "pixDate";

    //Define identifier for dialog fragment extra
    public static final String EXTRA_DATE = "com.petertieu.android.mepix";

    //Declare layout View of the dialog
    DatePicker mDatePicker;


    //Build encapsulating 'constructor'
    public static DateDialogFragment newInstance(Date pixDate){

        Bundle argumentBundle = new Bundle(); //Create Bundle object (i.e. argument-bundle)

        argumentBundle.putSerializable(ARG_PIX_DATE, pixDate); //Set key-value pairs for argument-bundle

        DateDialogFragment dateDialogFragment = new DateDialogFragment(); //Create DateDialogFragment

        dateDialogFragment.setArguments(argumentBundle); //Set argument-bundle for the PixDetailFragment

        return dateDialogFragment; //Return DateDialogFragment object
    }

}
