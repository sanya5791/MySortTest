package ua.sanya5791.mysorttest.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ua.sanya5791.mysorttest.DFrChooseTypeWorker;
import ua.sanya5791.mysorttest.FrAddWorker;
import ua.sanya5791.mysorttest.R;

/**
 * Created by sanya on 22.05.2015.
 */
public class DFrChooseTypeWorkerPresenterImpl implements DFrChooseTypeWorker.ChosenTypeWorker{
    Activity activity;

    public DFrChooseTypeWorkerPresenterImpl(Activity activity){
        this.activity  = activity;
    }

    @Override
    public void onFixedPayed() {
        startFrAddWorker(FrAddWorker.FIXED_SALARY);
    }

    @Override
    public void onHourlyPayed() {
        startFrAddWorker(FrAddWorker.HOUR_RATE);
    }

    /**
     *replaces current fragment with FrAddWorker.
     * @param typeSalary must be chosen ether FrAddWorker.FIXED_SALARY or FrAddWorker.HOUR_RATE
     */
    private void startFrAddWorker(int typeSalary) {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = FrAddWorker.newInstance(typeSalary);
        ft.replace(R.id.container, fragment, "FrAddWorker");
        ft.commit();
    }
}
