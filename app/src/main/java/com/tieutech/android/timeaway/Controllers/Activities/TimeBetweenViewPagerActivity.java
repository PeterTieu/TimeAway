package com.tieutech.android.timeaway.Controllers.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tieutech.android.timeaway.Controllers.Fragments.TimeBetweenDetailFragment;
import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

import java.util.List;
import java.util.UUID;


//Activity hosting and paging between TimeBetweenDetailActivity(s)
public class TimeBetweenViewPagerActivity extends AppCompatActivity{

    //========================== INSTNACE VARIABLES ==================================================================================================

    private static final String TAG = "TimeBetweenVPActivity"; //Log for Logcat
    private static final String EXTRA_TIME_BETWEEN_ID = "EXTRA_TIME_BETWEEN_ID"; //Extra key for sending TimeBetweenID from TimeBetweenListFragment
    private ViewPager mViewPager; //ViewPager
    private final int OFF_SCREEN_PAGE_LIMIT = 5; //Total number of fragments to pre-load outside of the fragment on screen
    private List<TimeBetween> mTimeBetweensList; //List of all TimeBetween objects in the database of TimeBetween objects



    //========================== METHODS ==================================================================================================

    //Encapsulating 'constructor' - called by a list item of TimeBetweenListFragment
    public static Intent newIntent(Context context, UUID timeBetweenID){

        Log.i(TAG, "newIntent(..) called"); //Log to Logcat
        Intent intent = new Intent(context, TimeBetweenViewPagerActivity.class); //Create Intent to begin TimeBetweenViewPagerActivity
        intent.putExtra(EXTRA_TIME_BETWEEN_ID, timeBetweenID); //Pass Extra to Intent

        return intent; //Return Intent
    }




    //Override onCreate(..) activity lifecycle callback method
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate(..) called"); //Log to Logcat

        setContentView(R.layout.activity_time_between_view_pager); //Set the activity content from the ViewPager layout resource

        mViewPager = (ViewPager) findViewById(R.id.time_between_view_pager); //Assign the ViewPager to its associated resource ID
        mViewPager.setOffscreenPageLimit(OFF_SCREEN_PAGE_LIMIT); //Set total number of detail fragments to pre-load outside of the current fragment on screen
        mTimeBetweensList = TimeBetweensManager.get(this).getTimeBetweens();  //Assign the Pix instance reference variable to the PixManager singleton

        FragmentManager fragmentManager = getSupportFragmentManager(); //Create a FragmentManager

        Log.i(TAG, "mTimeBetweenList.size() = " + mTimeBetweensList.size()); //Log to Logcat

        //Set the Adapter to the ViewPager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            //Override method from the FragmentStatePagerAdapter
            @Override
            public Fragment getItem(int position) {
                TimeBetween timeBetween = mTimeBetweensList.get(position); //Get a specific Pix from the List of TimeBetween objects
                return TimeBetweenDetailFragment.newInstance(timeBetween.getID()); //Create and return a new TimeBetweenDetailFragment fragment
            }

            //Override method from the FragmentStatePagerAdapter
            @Override
            public int getCount() {
                return mTimeBetweensList.size(); //Get the size of the List of TimeBetween objects
            }
        });


        UUID timeBetweenID = (UUID) getIntent().getSerializableExtra(EXTRA_TIME_BETWEEN_ID); //Get the 'value' associated with the 'key' from the Intent that started this activity

        Log.i(TAG, "timeBewteenID: " + timeBetweenID); //Log to Logcat

        //Set the current number of the TimeBetween so that the ViewPager knows which TimeBetween number is being displayed.
        //Ultimately, there would be smooth transition between TimeBetween objects
        for (int i=0; i<mTimeBetweensList.size(); i++){

            //If the current TimeBetween object from the List has the same UUID as the one clicked on in TimeBetweenListFragment
            if (mTimeBetweensList.get(i).getID().equals(timeBetweenID)){
                mViewPager.setCurrentItem(i);  //Set detail view to display this TimeBetween
                break;
            }
        }

    }




    //Override onResume() fragment lifecycle callback method
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume() called"); //Log to Logcat
    }




    //Override onPause() fragment lifecycle callback method
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause() called"); //Log to Logcat
    }




    //Override onStop() fragment lifecycle callback method
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop() called"); //Log to Logcat
    }




    //Override onDestroy() fragment lifecycle callback method
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy() called"); //Log to Logcat
    }

}
