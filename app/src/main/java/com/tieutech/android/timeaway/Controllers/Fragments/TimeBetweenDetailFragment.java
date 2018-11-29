package com.tieutech.android.timeaway.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class TimeBetweenDetailFragment extends Fragment{

    private final static String ARGUMENT_TIME_BETWEEN_ID = "ARGUMENT_TIME_BETWEEN_ID";



    public static TimeBetweenDetailFragment newInstance(UUID timeBetweenID){

        Bundle argumentBundle = new Bundle();

        argumentBundle.putSerializable(ARGUMENT_TIME_BETWEEN_ID, timeBetweenID);

        TimeBetweenDetailFragment timeBetweenDetailFragment = new TimeBetweenDetailFragment();

        timeBetweenDetailFragment.setArguments(argumentBundle);

        return timeBetweenDetailFragment;
    }





}
