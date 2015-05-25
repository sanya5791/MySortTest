package ua.sanya5791.mysorttest.Presenter;

import android.app.Activity;
import android.app.DialogFragment;

import ua.sanya5791.mysorttest.DFrChooseTypeWorker;
import ua.sanya5791.mysorttest.DFrSortTypeChooser;
import ua.sanya5791.mysorttest.FrList;

/**
 * Created by sanya on 22.05.2015.
 */
public class FrListPresenterImpl implements FrList.OnFragmentInteractionListener{

    Activity curActivity;

    public FrListPresenterImpl(Activity curActivity){
        this.curActivity = curActivity;
    }

    @Override
    public void dFrChooseTypeWorkerStart() {
        showChooseTypeWorker();
    }

    @Override
    public void dFrSortTypeChooser() {
        DialogFragment dialogFragment = new DFrSortTypeChooser();
        dialogFragment.show(curActivity.getFragmentManager(), DFrSortTypeChooser.TAG);
    }

    private void showChooseTypeWorker() {
        DialogFragment dialog = new DFrChooseTypeWorker();
        dialog.show(curActivity.getFragmentManager(), DFrChooseTypeWorker.TAG);
    }


}
