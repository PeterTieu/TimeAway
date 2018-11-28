package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tieutech.android.timeaway.Controllers.Activities.TimeBetweenViewPagerActivity;
import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

import java.util.List;

public class TimeBetweenListFragment extends Fragment{

    private final String TAG = "TimeBetweenListFragment";

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mTimeBetweensRecyclerView;
    private TimeBetweenAdapter mTimeBetweenAdapter;
//    private TimeBetweenViewHolder mTimeBetweenViewHolder;

    private LinearLayout noTimeBetweenView;
    private Button mAddNewTimeBetween;



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

        mTimeBetweensRecyclerView = (RecyclerView) view.findViewById(R.id.time_between_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mTimeBetweensRecyclerView.setLayoutManager(mLinearLayoutManager);


        noTimeBetweenView = (LinearLayout) view.findViewById(R.id.no_time_between_view);

        mAddNewTimeBetween = (Button) view.findViewById(R.id.add_new_time_between);

        mAddNewTimeBetween.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TimeBetween timeBetween = new TimeBetween();

                TimeBetweensManager.get(getActivity()).addTimeBetween(timeBetween);

                Toast.makeText(getActivity(), R.string.new_time_between_added, Toast.LENGTH_LONG).show();
            }


        });


        updateUI();


        return view;
    }



    private void updateUI(){

        final List<TimeBetween> timeBetweensList  = TimeBetweensManager.get(getActivity()).getTimeBetweens();

        if (timeBetweensList.size() == 0){
            noTimeBetweenView.setVisibility(View.VISIBLE);
        }
        else{
            noTimeBetweenView.setVisibility(View.GONE);
        }


        if (mTimeBetweenAdapter == null){
            mTimeBetweenAdapter = new TimeBetweenAdapter(timeBetweensList);

            mTimeBetweensRecyclerView.setAdapter(mTimeBetweenAdapter);

        }

        else{
            mTimeBetweenAdapter.setTimeBetweens(timeBetweensList);

            mTimeBetweenAdapter.notifyDataSetChanged();
        }

    }












    private class TimeBetweenAdapter extends RecyclerView.Adapter<TimeBetweenViewHolder>{

        private List<TimeBetween> mTimeBetweensList;


        public TimeBetweenAdapter(List<TimeBetween> timeBetweensList){
            mTimeBetweensList = timeBetweensList;
        }


        @Override
        public int getItemCount(){
            return mTimeBetweensList.size();
        }


        @Override
        public TimeBetweenViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_time_between, viewGroup, false);


            TimeBetweenViewHolder timeBetweenViewHolder = new TimeBetweenViewHolder(view);


            return timeBetweenViewHolder;
        }


        @Override
        public void onBindViewHolder(TimeBetweenViewHolder timeBetweenViewHolder, int position){

            TimeBetween timeBetween = mTimeBetweensList.get(position);

            timeBetweenViewHolder.bind(timeBetween);
        }



        public void setTimeBetweens(List<TimeBetween> timeBetweensList){
            mTimeBetweensList = timeBetweensList;
        }



    }




    private class TimeBetweenViewHolder extends RecyclerView.ViewHolder{


        private TimeBetween mTimeBetween;

        private TextView mListItemTimeBetweenID;


        public TimeBetweenViewHolder(View view){
            super(view);

            mListItemTimeBetweenID = view.findViewById(R.id.list_item_time_between_id);

        }



        private void updateTimeBetweenInDatabase(){

            TimeBetweensManager.get(getActivity()).updateTimeBetweensDatabase(mTimeBetween);

            updateUI();

        }



        public void bind(TimeBetween timeBetween){
            mTimeBetween = timeBetween;

            mListItemTimeBetweenID.setText(mTimeBetween.getID().toString());
        }




    }




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

            case (R.id.menu_item_add_time_between):

                TimeBetween timeBetween = new TimeBetween();

                TimeBetweensManager.get(getActivity()).addTimeBetween(timeBetween);

                updateUI();

                Intent intent =  TimeBetweenViewPagerActivity.newIntent(getActivity(), timeBetween.getID());


                //Display toast to notify user a new pix has been added
                Toast toast = Toast.makeText(getActivity(), R.string.new_time_between_added, Toast.LENGTH_LONG); //Create Toast
                toast.setGravity(Gravity.CENTER, 0,150); //Set positoin of Toast
                toast.show(); //Show Toast


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
