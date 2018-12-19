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


//Fragment to display the LIST VIEW of a TimeBetween list items - hosted by TimeBetweenListActivity
public class TimeBetweenListFragment extends Fragment{

    //==================================== INSTANCE VARIABLES ============================================================================
    private final String TAG = "TimeBetweenListFragment"; //Log to Logcat


    //RecyclerView variables
    private LinearLayoutManager mLinearLayoutManager;   //LinearLayoutManager
    private RecyclerView mTimeBetweensRecyclerView;     //RecyclerView
    private TimeBetweenAdapter mTimeBetweenAdapter;     //Adapter for RecyclerView

    //Other View variables
    private LinearLayout noTimeBetweenView; //Indicate that there is no TimeBetween item
    private Button mAddNewTimeBetween;      //Menu item to create new TimeBetween



    //==================================== METHODS ============================================================================

    //Override onAttach(..) fragment lifecycle callback method
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        Log.i(TAG, "onAttach(..) called"); //Log to Logcat
    }


    //Override onCreate(..) fragment lifecycle callback method
    @Override
    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        Log.i(TAG, "onCreate(..) caled"); //Log to Logcat

        setHasOptionsMenu(true); //Declaqre that this fragment has an options menu
    }




    //Override onCreateView(..) fragment lifecycle callback method
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreateView(layoutInflater, viewGroup, savedInstanceState);

        Log.i(TAG, "onCreateView(..) called"); //Log to Logcat

        View view = layoutInflater.inflate(R.layout.fragment_time_between_list, viewGroup, false); //Create View object

        mTimeBetweensRecyclerView = (RecyclerView) view.findViewById(R.id.time_between_recycler_view); //Create RecyclerView for the LIST VIEW
        mLinearLayoutManager = new LinearLayoutManager(getActivity()); //Create LinearLayoutManager
        mTimeBetweensRecyclerView.setLayoutManager(mLinearLayoutManager); //Assign RecyclerView to LinearLayoutManager


        noTimeBetweenView = (LinearLayout) view.findViewById(R.id.no_time_between_view); //View to indicate no TimeBetween is available

        mAddNewTimeBetween = (Button) view.findViewById(R.id.add_new_time_between); //Button to create first TimeBetween

        //Set listener for button to create first TimeBetween
        mAddNewTimeBetween.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TimeBetween timeBetween = new TimeBetween(); //Create new TimeBetween object

                TimeBetweensManager.get(getActivity()).addTimeBetween(timeBetween); //Add new TimeBetween object to TimeBetween objects database

                Toast.makeText(getActivity(), R.string.new_time_between_added, Toast.LENGTH_LONG).show(); //Display Toast
            }
        });


        updateUI(); //Update the UI/Show the list items


        return view;
    }



    //Helper method - Update teh UI/Show the list items
    private void updateUI(){

        final List<TimeBetween> timeBetweensList  = TimeBetweensManager.get(getActivity()).getTimeBetweens(); //Refer to TimeBetween List object

        //If the TimeBetween List is empty
        if (timeBetweensList.size() == 0){
            noTimeBetweenView.setVisibility(View.VISIBLE); //Display indicator that the TimeBetween database is empty
        }
        else{
            noTimeBetweenView.setVisibility(View.GONE); //Remove indicator that the TimeBetween database is empty
        }


        //If the RecyclerView-Adapter DOES NOT exist
        if (mTimeBetweenAdapter == null){
            mTimeBetweenAdapter = new TimeBetweenAdapter(timeBetweensList); //Create RecyclerView-Adapter and set it to the List
            mTimeBetweensRecyclerView.setAdapter(mTimeBetweenAdapter); //Set RecyclerView to the RecyclerViea-Adapter
        }
        //If the RecyclerView-Adapter EXISTS
        else{
            mTimeBetweenAdapter.setTimeBetweens(timeBetweensList); //Set RecyclerView-Adapter to the List
            mTimeBetweenAdapter.notifyDataSetChanged(); //Update the RecyclerView-Adapter to the new List
        }

    }




    //RecyclerView-Adapter inner class
    private class TimeBetweenAdapter extends RecyclerView.Adapter<TimeBetweenViewHolder>{

        private List<TimeBetween> mTimeBetweensList; //List of TimeBetween


        //Constructor
        public TimeBetweenAdapter(List<TimeBetween> timeBetweensList){
            mTimeBetweensList = timeBetweensList; //Assign TimeBetween List item to member variable
        }


        //Override getItemCount() method
        @Override
        public int getItemCount(){
            return mTimeBetweensList.size(); //Return size of the List
        }


        //Override onCreateViewHolder(..) method
        @Override
        public TimeBetweenViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //Obtain LayoutInflater to inflate layout of list item (ViewHolder)
            View view = layoutInflater.inflate(R.layout.list_item_time_between, viewGroup, false); //Inflate list item View
            TimeBetweenViewHolder timeBetweenViewHolder = new TimeBetweenViewHolder(view); //Create ViewHolder
            return timeBetweenViewHolder; //Return ViewHolder
        }


        //Override onBindViewHolder(..) method
        @Override
        public void onBindViewHolder(TimeBetweenViewHolder timeBetweenViewHolder, int position){
            TimeBetween timeBetween = mTimeBetweensList.get(position); //Obtain position of ViewHolder
            timeBetweenViewHolder.bind(timeBetween); //Bind data to ViewHolder
        }


        //Set RecyclerView-Adapter to the new and updated List of TimeBetween objects
        public void setTimeBetweens(List<TimeBetween> timeBetweensList){
            mTimeBetweensList = timeBetweensList;
        }

    }




    //RecyclerView-ViewHolder inner class
    private class TimeBetweenViewHolder extends RecyclerView.ViewHolder{

        private TimeBetween mTimeBetween;

        private TextView mListItemTimeBetweenIDTextView;


        //Constructor
        public TimeBetweenViewHolder(View view){
            super(view);

            //Set listener for list item - to start TimeBetweenViewPagerActivity and open the TimeBetween DETAIL VIEW
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent timeBetweenViewPagerActivityIntent =  TimeBetweenViewPagerActivity.newIntent(getActivity(), mTimeBetween.getID()); //Create Intent to begin TimeBetweenViewPagerActivity
                    startActivity(timeBetweenViewPagerActivityIntent); //Start TimeBetweenViewPagerActivity Intent

                }
            });


            mListItemTimeBetweenIDTextView = view.findViewById(R.id.list_item_time_between_id); //Assign View of the TimeBetween ID to associated resource ID
        }


        //
        private void updateTimeBetweenInDatabase(){

            TimeBetweensManager.get(getActivity()).updateTimeBetweensDatabase(mTimeBetween);

            updateUI();

        }



        //Bind the ViewHolder to the associated TimeBetween data - called by onBindViewHolder in TimeBetweenAdapter
        public void bind(TimeBetween timeBetween){

            mTimeBetween = timeBetween; //Stash TimeBetween parameter to member variable

            mListItemTimeBetweenIDTextView.setText(mTimeBetween.getID().toString()); //Set TextView of TimeBetween ID to its ID

            //If the TimeBetween ID EXISTS
            if (mTimeBetween.getID() != null || !mTimeBetween.getID().toString().isEmpty()){
                mListItemTimeBetweenIDTextView.setText(mTimeBetween.getID().toString()); //Set TextView of TimeBetween ID to its ID

            }
        }

    }




    //Override onCreateOptionsMenu(..) method
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);

        //If there are one or more TimeBetween objects in the database
        if (TimeBetweensManager.get(getActivity()).getTimeBetweens().size() > 0){
            menuInflater.inflate(R.menu.fragment_time_between_list, menu); //Inflate the Menu layout
        }
    }




    //Override onOptionsItemSelected(..) method
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        //Check through all MenuItem objects
        switch(menuItem.getItemId()){

            //Check the "Add TimeBetween" MenuItem
            case (R.id.menu_item_add_time_between):

                TimeBetween timeBetween = new TimeBetween(); //Create new TimeBetween object
                TimeBetweensManager.get(getActivity()).addTimeBetween(timeBetween); //Add the TimeBetween object to the TimeBetween database
                updateUI(); //Update the UI to include the new TimeBetween in the RecyclerView

                Intent timeBetweenViewPagerActivityIntent =  TimeBetweenViewPagerActivity.newIntent(getActivity(), timeBetween.getID()); //Create Inten to the TimeBetweenViewPagerActivity
                startActivity(timeBetweenViewPagerActivityIntent); //Start the TimeBetweenViewPagerActivity Intent

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

        Log.i(TAG, "onResume() called"); //Log lifecycle callback

        updateUI(); //Called to update the list after a TimeBetween is deleted in TimeBetweenDetailFragment
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
