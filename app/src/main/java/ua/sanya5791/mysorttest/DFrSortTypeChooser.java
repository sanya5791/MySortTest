package ua.sanya5791.mysorttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import ua.sanya5791.mysorttest.Presenter.DFrChooseTypeWorkerPresenterImpl;
import ua.sanya5791.mysorttest.Presenter.DFrSortTypeChooserPresenterImpl;

/**
 * Created by sanya on 20.05.2015.
 */
public class DFrSortTypeChooser extends DialogFragment{
    public final static String TAG = DFrSortTypeChooser.class.getSimpleName();

    private int chosenType;
    ChosenSortingType presenter;

    public interface ChosenSortingType {
        void onSortByName();
        void onSortBySalary();
    }

//    ChosenTypeWorker choice;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        choice = (ChosenTypeWorker) activity;

        presenter = (ChosenSortingType) new DFrSortTypeChooserPresenterImpl(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_sort_type)
                .setSingleChoiceItems(R.array.sort_type, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "You have chosen which: " + which, Toast.LENGTH_LONG).show();
                                chosenType = which;
                            }
                        })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        switch (chosenType){
                            case 0:                  //By Name
                                presenter.onSortByName();
                                break;
                            case 1:                  //By Salary
                                presenter.onSortBySalary();
                                break;
                        }
                        }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
//                        presenter.onHourlyPayed();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
