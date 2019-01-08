package com.tieutech.android.timeaway.Controllers.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tieutech.android.timeaway.Databases.TimeBetween.TimeBetweensManager;
import com.tieutech.android.timeaway.Models.TimeBetween;
import com.tieutech.android.timeaway.R;

import java.util.UUID;


//Fragment for the DETAIL VIEW of a TimeBetween - invoked by TimeBetweenListFragment -> TimeBetweenViewPagerActivity
public class TimeBetweenDetailFragment extends Fragment{

    //==================================== INSTANCE VARIABLES ============================================================================

    private final String TAG = "TBDetailFragment"; //Tag for Logcat
    private final static String ARGUMENT_TIME_BETWEEN_ID = "ARGUMENT_TIME_BETWEEN_ID"; //Argument for retrieving the ID of the TimeBetween

    TimeBetween mTimeBetween;               //TimeBetween retrieved
    TextView mTimeBetweenIDTextView;        //TextView to display ID of the TimeBetween
    EditText mTimeBetweenTitle;             //Title of TimeBetween
    Button mTimeBetweenFirstDateButton;     //Button to change FIRST DATE
    Button mTimeBetweenFirstTimeButton;     //Button to change FIRST TIME
    Button mTimeBetweenSecondDateButton;    //Button to change SECOND DATE
    Button mTimeBetweenSecondTimeButton;    //Button to change SECOND TIME



    private static final int REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT = 1;
    private static final String TAG_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT = "TiagTimeBetweenDeleteConfirmationDialogFragment";



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


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); //Disable the soft-keyboard from automatically showing, since that mTimeBetweenTitle is the first View that is focussed

        setHasOptionsMenu(true); //Declare that the fragment has an options menu

    }



    //Override onCreateView(..) fragment lifecycle callback method
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){


        Log.i(TAG, "onCreateView(..) called"); //Log to Logcat

        View view = layoutInflater.inflate(R.layout.fragment_time_between_detail, viewGroup, false); //Obtain layout View of the fragment



        //Link member variables to the associated View objects
        mTimeBetweenIDTextView = (TextView) view.findViewById(R.id.time_between_detail_id);
        mTimeBetweenTitle = (EditText) view.findViewById(R.id.time_between_detail_title);
        mTimeBetweenFirstDateButton = (Button) view.findViewById(R.id.time_between_first_date_button);
        mTimeBetweenFirstTimeButton = (Button) view.findViewById(R.id.time_between_first_time_button);
        mTimeBetweenSecondDateButton = (Button) view.findViewById(R.id.time_between_second_date_button);
        mTimeBetweenSecondTimeButton = (Button) view.findViewById(R.id.time_between_second_time_button);



        mTimeBetweenIDTextView.setText(mTimeBetween.getID().toString()); //Set the TimeBetween ID to mTimeBetweenID TextView

        mTimeBetweenTitle.setText(mTimeBetween.getTitle());
        mTimeBetweenTitle.setCursorVisible(false); //Make the cursor invisible, otherwise it would show up because mTimeBetweenTitle is automatically focussed since it is the first View





        mTimeBetweenTitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTimeBetween.setTitle(charSequence.toString());
                updateTimeBetween();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });






            mTimeBetweenTitle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    mTimeBetweenTitle.setCursorVisible(true); //Show the cursor
                    return false; //Don't hide the soft-keyboard
                }
            });






        return view; //Return the View

    }


    //Update the TimeBetween in the TimeBetweens SQLite Database
    private void updateTimeBetween(){

        TimeBetweensManager.get(getActivity()).updateTimeBetweensDatabase(mTimeBetween);
    }




    //Override onCreateOptionsMenu(..) fragment lifecycle callback method
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);

        Log.i(TAG, "onCreateOptionsMenu(..) called");

        menuInflater.inflate(R.menu.fragment_time_between_detail, menu);

    }


    //Override onOptionsItemSelected(..) fragment lifecycle callback method
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        Log.i(TAG, "onOptionsItemSelected(..) called");

        switch(menuItem.getItemId()){

            //
            case (R.id.delete_time_between):

                deleteTimeBetweenConfirmationDialog();
                TimeBetweensManager.get(getActivity()).deleteTimeBetween(mTimeBetween);

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }




    private void deleteTimeBetweenConfirmationDialog(){

        FragmentManager fragmentManager = getFragmentManager();

        TimeBetweenDeleteConfirmationDialogFragment timeBetweenDeleteConfirmationDialogFragment = TimeBetweenDeleteConfirmationDialogFragment.newInstance(mTimeBetween.getID().toString());

        timeBetweenDeleteConfirmationDialogFragment.setTargetFragment(TimeBetweenDetailFragment.this, REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT);

        timeBetweenDeleteConfirmationDialogFragment.show(fragmentManager, TAG_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT);
    }



    //Override onActivityResult(..) callback method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        Log.i(TAG, "onActivityResult(..) called");

        //If the resultCode DOES NOT equal Activity.RESULT_OK
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        //
        if (requestCode == REQUEST_CODE_TIME_BETWEEN_DELETE_CONFIRMATION_DIALOG_FRAGMENT){

            boolean confirmDelete = intent.getBooleanExtra(TimeBetweenDeleteConfirmationDialogFragment.EXTRA_TIME_BETWEEN_DELETE_CONFIRMATION, false);

            if (confirmDelete == true){
                TimeBetweensManager.get(getActivity()).deleteTimeBetween(mTimeBetween);
            }


            getActivity().finish();


            updateTimeBetween();

            //======= Hide soft keyboard ========
            //Get InputMethodManager object
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            //Request to hide soft keyboard. Argument 1 (IBinder): Any view visible on screen (e.g. mTitle)
            inputMethodManager.hideSoftInputFromWindow(mTimeBetweenIDTextView.getWindowToken(), 0);


            //======= Display Toast on Pix delete ========
            //If no Pix title exists or is empty
            if (mTimeBetween.getTitle() == null || mTimeBetween.getTitle().isEmpty()) {
                Toast.makeText(getContext(), Html.fromHtml("<i>" + "*Untitled*" + "</i>" + " Pix Deleted"), Toast.LENGTH_LONG).show();
            }
            //If Pix title exists or is not empty
            else {
                Toast.makeText(getContext(), Html.fromHtml("Pix Deleted: " + "<b>" + mTimeBetween.getTitle() + "</b>"), Toast.LENGTH_LONG).show();
            }









        }
    }





}
