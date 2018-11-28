package com.tieutech.android.timeaway.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;

import java.util.UUID;

public class TimeBetweenDetailFragment extends Fragment{

    private final String TAG = "TBDetailFragment";
    private final static String ARGUMENT_TIME_BETWEEN_ID = "ARGUMENT_TIME_BETWEEN_ID";

    TimeBetween mTimeBetween;




    public static TimeBetweenDetailFragment newInstance(UUID timeBetweenID){

        Bundle argumentBundle = new Bundle();

        argumentBundle.putSerializable(ARGUMENT_TIME_BETWEEN_ID, timeBetweenID);

        TimeBetweenDetailFragment timeBetweenDetailFragment = new TimeBetweenDetailFragment();

        timeBetweenDetailFragment.setArguments(argumentBundle);

        return timeBetweenDetailFragment;
    }



    //Override onAttach() fragment lifecycle callback method
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Log in Logcat
        Log.i(TAG, "onAttach() called");
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate(..) called");

        UUID timeBetweenID = (UUID) getArguments().getSerializable(ARGUMENT_TIME_BETWEEN_ID);

        mTimeBetween = new TimeBetween();

        mTimeBetween = TimeBetweensManager.get(getActivity()).getTimeBetween(timeBetweenID);


        setHasOptionsMenu(true);

    }




}
