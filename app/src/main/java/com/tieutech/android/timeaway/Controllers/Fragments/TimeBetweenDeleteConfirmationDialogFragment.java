package com.tieutech.android.timeaway.Controllers.Fragments;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AlertDialogLayout;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tieutech.android.timeaway.R;

//Dialog Fragment to confirm 'TimeBetween' delete
    //Called by the 'delete' menu item in TimeBetweenDetailFragment
public class TimeBetweenDeleteConfirmationDialogFragment extends DialogFragment {

    private static final String KEY_TIME_BETWEEN_ID = "keyTimeBetweenID";

    AlertDialogLayout mAlertDialogLayout;

    public static final String EXTRA_TIME_BETWEEN_DELETE_CONFIRMATION = "extraTimeBetweenDeleteConfirmation";


    public static TimeBetweenDeleteConfirmationDialogFragment newInstance(String timeBetweenID){
        Bundle argumenBundle = new Bundle();
        argumenBundle.putString(KEY_TIME_BETWEEN_ID, timeBetweenID);
        TimeBetweenDeleteConfirmationDialogFragment timeBetweenDeleteConfirmationDialogFragment = new TimeBetweenDeleteConfirmationDialogFragment();
        timeBetweenDeleteConfirmationDialogFragment.setArguments(argumenBundle);
        return timeBetweenDeleteConfirmationDialogFragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        String timeBetweenID = (String) getArguments().getString(KEY_TIME_BETWEEN_ID);

                //Set-up custom title to display in the dialog
        TextView dialogTitle = new TextView(getActivity()); //Create TextView object
        dialogTitle.setText("\nDelete this TimeBetween\n"); //Set curentDescriptionEditTextString on TextView
        dialogTitle.setTextSize(22); //Set size of curentDescriptionEditTextString
        dialogTitle.setGravity(Gravity.CENTER); //Set  position of curentDescriptionEditTextString in the title box of the dialog
        dialogTitle.setTypeface(null, Typeface.BOLD); //Set the curentDescriptionEditTextString to be bold
        dialogTitle.setTextColor(getResources().getColor(R.color.dialogFragmentText)); //Set curentDescriptionEditTextString color
        dialogTitle.setBackgroundColor(getResources().getColor(R.color.dialogFragmentBackground)); //Set curentDescriptionEditTextString background color




        //Create View object for dialog
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_time_between_delete_confirmation, null);

        //Assign AlertDialogLayout reference variable to associated resource ID in layout file
        mAlertDialogLayout = (AlertDialogLayout) view.findViewById(R.id.dialog_fragment_time_between_delete_confirmation);

        //Build the AlertDialog
        return new AlertDialog
                .Builder(getActivity())
                .setView(view)  //Set the custom view to be the conents of the AlertDialog
                .setCustomTitle(dialogTitle) //Set title of dialog to the TextView object (above)
                .setMessage(Html.fromHtml( //Set message of dialog
                        "<br>" + "</br>" + //New line
                                "<br>" + "</br>" + //New line
                                "<b>" + "Title:" + "</b>" + " " + timeBetweenID + //Title line (where "Title:" is bolded)

                                "<br>" + "</br>" + //New line
                                "<br>" + "</br>" + //New line
                                "<br>" + "</br>" + //New line
                                "<br>" + "</br>" + //New line
                                "<b>" + "Do you wish to delete this TimeBetween?" + "</b>")) //Prompt line (bolded)
                .setNegativeButton(android.R.string.cancel, null) //Set negative button
                .setPositiveButton(R.string.confirm_time_between_delete_button, //Set positive button
                        //Set listener for positive button
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Create boolean to indicate positive button is pressed
                                boolean confirmDelete = true;

                                //Send result to the target fragment (TimeBetweenFragment)
                                sendResult(Activity.RESULT_OK, confirmDelete);
                            }
                        })
                .create(); //Create the dialog
    }


    //Send boolean result for positive button press back to target fragment (PixDetailFragment)
    private void sendResult(int resultCode, boolean confirmDelete) {

        //If target fragment (PixDetailFragment) does NOT exist
        if (getTargetFragment() == null) {
            return;
        }

        //Create Intent object
        Intent intent = new Intent();

        //Add key-value pair to the Intent (for positive button press)
        intent.putExtra(EXTRA_TIME_BETWEEN_DELETE_CONFIRMATION, confirmDelete);

        //Call onActivityResult(..) of target fragment (PixDetailFragment)
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
