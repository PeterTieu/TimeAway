package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

public class TimeBetweenListFragment extends Fragment{

    private final String TAG = "TimeBetweenListFragment";

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
//    private TimeBetweenAdapter mTimeBetweenAdapter;
//    private TimeBetweenViewHolder mTimeBetweenViewHolder;



    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        Log.i(TAG, "onAttach(..) called");
    }


    //Override onCreate(..) fragment lifecycle callback method
    @Override
    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        Log.i(TAG, "onCreate(..) caled"); //Log to Logcat

        setHasOptionsMenu(true); //Declaqre that this fragment has an options menu
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreateView(layoutInflater, viewGroup, savedInstanceState);

        Log.i(TAG, "onCreateView(..) called");

        View view = layoutInflater.inflate(R.layout.fragment_time_between_list, viewGroup, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.time_between_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        return view;
    }





//    @Override
//    private class TimeBetweenAdapter extends RecyclerView.Adapter<TimeBetweenViewHolder>{
//    }



//    @Override
//    private class TimeBetweenViewHolder extends RecyclerView.ViewHolder{
//    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);

        menuInflater.inflate(R.menu.fragment_time_between_list, menu);

        MenuItem addTimeBetweenItem = menu.findItem(R.id.menu_item_add_time_between);

        int actualSizeOfTimeBetweenList = 0;

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch(menuItem.getItemId()){

            case (R.menu.fragment_time_between_list):

                TimeBetween timeBetween = new TimeBetween();

                //TODO: Add TimeBetween object to the SQLiteDatabase of TimeBetween

                //TODO: updateUI

                Intent intent = new TimeBetweenViewPagerActivity.newIntent(this, timeBetween.getID());


            default:
                return super.onOptionsItemSelected(menuItem);

        }




    }








    //Override onActivityCreated(..) fragment lifecycle callback method
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated called"); //Log to Logcat
    }




    //Override onStart(..) fragment lifecycle callback method
    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart called"); //Log to Logcat
    }




    //Override onResume(..) fragment lifecycle callback method
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume called"); //Log to Logcat
    }




    //Override onPause(..) fragment lifecycle callback method
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause() called"); //Log to Logcat
    }




    //Override onStop(..) fragment lifecycle callback method
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop() called"); //Log to Logcat
    }




    //Override onDestroyView(..) fragment lifecycle callback method
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i(TAG, "onDestroyView() called"); //Log to Logcat
    }




    //Override onDestroy(..) fragment lifecycle callback method
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy() called"); //Log to Logcat
    }




    //Override onDetach(..) fragment lifecycle callback method
    @Override
    public void onDetach(){
        super.onDetach();
        Log.i(TAG, "onDeatch() caled"); //Log to Logcat
    }





}
