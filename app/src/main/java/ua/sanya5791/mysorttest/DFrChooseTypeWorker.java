package ua.sanya5791.mysorttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import ua.sanya5791.mysorttest.Presenter.DFrChooseTypeWorkerPresenterImpl;

/**
 * Created by sanya on 20.05.2015.
 */
public class DFrChooseTypeWorker extends DialogFragment{
    public final static String TAG = DFrChooseTypeWorker.class.getSimpleName();

    DFrChooseTypeWorkerPresenterImpl presenter;

    public interface ChosenTypeWorker {
        void onFixedPayed();
        void onHourlyPayed();
    }

//    ChosenTypeWorker choice;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        choice = (ChosenTypeWorker) activity;

        presenter = new DFrChooseTypeWorkerPresenterImpl(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_fire_missiles)
                .setNegativeButton(R.string.fixed_payed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        presenter.onFixedPayed();
                    }
                })
                .setPositiveButton(R.string.hourly_payed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        presenter.onHourlyPayed();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
