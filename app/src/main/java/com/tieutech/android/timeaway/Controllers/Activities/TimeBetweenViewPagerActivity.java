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

    private List<TimeBetween> mTimeBetweensList;




    //========================== METHODS ==================================================================================================

    //Encapsulating 'constructor' - called by a list item of TimeBetweenListFragment
    public static Intent newIntent(Context context, UUID timeBetweenID){

        Log.i(TAG, "newIntent(..) called"); //Log to Logcat

        Intent intent = new Intent(context, TimeBetweenViewPagerActivity.class); //Create Intent to begin TimeBetweenViewPagerActivity

        intent.putExtra(EXTRA_TIME_BETWEEN_ID, timeBetweenID); //Pass Extra to Intent

        return intent; //Return Intent
    }





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate(..) called");

        setContentView(R.layout.activity_time_between_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.time_between_view_pager);

        mViewPager.setOffscreenPageLimit(OFF_SCREEN_PAGE_LIMIT);


        mTimeBetweensList = TimeBetweensManager.get(this).getTimeBetweens();

        FragmentManager fragmentManager = getSupportFragmentManager();




        Log.i(TAG, "mTimeBetweenList.size() = " + mTimeBetweensList.size()); //Log to Logcat




        //Set the Adapter to the ViewPager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            //Override method from the FragmentStatePagerAdapter
            @Override
            public Fragment getItem(int position) {

                //Get a specific Pix from the List of Pix objects
                TimeBetween timeBetween = mTimeBetweensList.get(position);

                //Create and return a new PixDetailFragment fragment
                return TimeBetweenDetailFragment.newInstance(timeBetween.getID());
            }

            //Override method from the FragmentStatePagerAdapter
            @Override
            public int getCount() {
                //Get the size of the List of Pix objects
                return mTimeBetweensList.size();
            }
        });





        UUID timeBetweenID = (UUID) getIntent().getSerializableExtra(EXTRA_TIME_BETWEEN_ID);

        Log.i(TAG, "timeBewteenID: " + timeBetweenID);


        for (int i=0; i<mTimeBetweensList.size(); i++){

            if (mTimeBetweensList.get(i).getID().equals(timeBetweenID)){
                mViewPager.setCurrentItem(i);
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
