package com.tieutech.android.timeaway.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

import java.util.UUID;


//Fragment for the DETAIL VIEW of a TimeBetween - invoked by TimeBetweenListFragment -> TimeBetweenViewPagerActivity
public class TimeBetweenDetailFragment extends Fragment{

    //==================================== INSTANCE VARIABLES ============================================================================

    private final String TAG = "TBDetailFragment"; //Tag for Logcat
    private final static String ARGUMENT_TIME_BETWEEN_ID = "ARGUMENT_TIME_BETWEEN_ID"; //Argument for retrieving the ID of the TimeBetween

    TimeBetween mTimeBetween; //TimeBetween retrieved
    TextView mTimeBetweenIDTextView; //TextView to display ID of the TimeBetween



    //'Constructor' - invoked by TimeBetweenViewPagerActivity
    public static TimeBetweenDetailFragment newInstance(UUID timeBetweenID){

        Bundle argumentBundle = new Bundle(); //Argument-bundle to store information passed from hosting activity

        argumentBundle.putSerializable(ARGUMENT_TIME_BETWEEN_ID, timeBetweenID); //Add data (TimeBetween ID) to the argument-bundle

        TimeBetweenDetailFragment timeBetweenDetailFragment = new TimeBetweenDetailFragment(); //Instantiate the TimeBetweenDetailFragment fragment

        timeBetweenDetailFragment.setArguments(argumentBundle); //Set the argument-bundle to the fragment

        return timeBetweenDetailFragment; //Return the fragment
    }



    //Override onAttach() fragment lifecycle callback method
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Log in Logcat
        Log.i(TAG, "onAttach() called");
    }



    //Override onCreate(..) fragment lifecycle callback method
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate(..) called"); //Log to Logcat

        UUID timeBetweenID = (UUID) getArguments().getSerializable(ARGUMENT_TIME_BETWEEN_ID); //Obtain TimeBetween ID from TimeBetweenViewPagerActivity

        mTimeBetween = new TimeBetween(); //Create TimeBetween object

        mTimeBetween = TimeBetweensManager.get(getActivity()).getTimeBetween(timeBetweenID); //Populate the newly created TimeBetween object with variables from the SQLiteDatabase, based on the fed TimeBetween ID


        setHasOptionsMenu(true); //Declare that the fragment has an options menu

    }



    //Override onCreateView(..) fragment lifecycle callback method
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){


        Log.i(TAG, "onCreateView(..) called"); //Log to Logcat

        View view = layoutInflater.inflate(R.layout.fragment_time_between_detail, viewGroup, false); //Obtain layout View of the fragment



        mTimeBetweenIDTextView = (TextView) view.findViewById(R.id.time_between_detail_id); //Link mTimeBetweenID to the associated View element

        mTimeBetweenIDTextView.setText(mTimeBetween.getID().toString()); //Set the TimeBetween ID to mTimeBetweenID TextView

        return view; //Return the View

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);

        Log.i(TAG, "onCreateOptionsMenu(..) called");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        Log.i(TAG, "onOptionsItemSelected(..) called");

        switch(menuItem.getItemId()){

            //
            case (R.id.delete_time_between):
                TimeBetweensManager.get(getActivity()).deleteTimeBetween(mTimeBetween);

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }







}
