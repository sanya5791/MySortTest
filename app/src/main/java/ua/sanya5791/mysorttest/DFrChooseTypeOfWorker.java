package ua.sanya5791.mysorttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by sanya on 20.05.2015.
 */
public class DFrChooseTypeOfWorker extends DialogFragment{
    public final static String TAG = DFrChooseTypeOfWorker.class.getSimpleName();

    interface ChosenTypeWorker {
        void fixedPayed();
        void hourlyPayed();
    }

    ChosenTypeWorker choice;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        choice = (ChosenTypeWorker) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_fire_missiles)
                .setNegativeButton(R.string.fixed_payed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        choice.fixedPayed();
                    }
                })
                .setPositiveButton(R.string.hourly_payed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        choice.hourlyPayed();
                    }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
