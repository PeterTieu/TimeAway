package com.tieutech.android.timeaway.Controllers.Fragments;


import android.app.DialogFragment;
import android.os.Bundle;

//Dialog Fragment to confirm 'TimeBetween' delete
    //Called by the 'delete' menu item in TimeBetweenDetailFragment
public class TimeBetweenDeleteConfirmationDialogFragment extends DialogFragment{

    private static final String KEY_TIME_BETWEEN_ID = "keyTimeBetweenID";


    public static TimeBetweenDeleteConfirmationDialogFragment newInstance(String timeBetweenID){

        Bundle argumenBundle = new Bundle();

        argumenBundle.putString(KEY_TIME_BETWEEN_ID, timeBetweenID);

        TimeBetweenDeleteConfirmationDialogFragment timeBetweenDeleteConfirmationDialogFragment = new TimeBetweenDeleteConfirmationDialogFragment();

        timeBetweenDeleteConfirmationDialogFragment.setArguments(argumenBundle);

        return timeBetweenDeleteConfirmationDialogFragment;
    }

}
