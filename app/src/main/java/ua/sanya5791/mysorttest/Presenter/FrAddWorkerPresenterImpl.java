package ua.sanya5791.mysorttest.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ua.sanya5791.mysorttest.FrAddWorker;
import ua.sanya5791.mysorttest.FrList;
import ua.sanya5791.mysorttest.R;
import ua.sanya5791.mysorttest.model.Singleton;
import ua.sanya5791.mysorttest.model.Worker;
import ua.sanya5791.mysorttest.model.WorkerFixedPayed;
import ua.sanya5791.mysorttest.model.WorkerHourlyWaged;

/**
 * Created by sanya on 22.05.2015.
 */
public class FrAddWorkerPresenterImpl implements FrAddWorker.OnFragmentInteractionListener{

    Activity activity;
    public FrAddWorkerPresenterImpl(Activity activity){
        this.activity=activity;
    }

    @Override
    public void addFixedSalaryWorker(String name, double salary) {
//        WorkerFixedPayed workerFixedPayed = new WorkerFixedPayed(name, salary);

        Worker worker = new WorkerFixedPayed(name, salary);
        Singleton.getInstance().add(worker);
        showFrList();

    }

    @Override
    public void addHourRateWorker(String name, double salary) {
        Worker worker = new WorkerHourlyWaged(name, salary);
        Singleton.getInstance().add(worker);
        showFrList();

    }

    @Override
    public void onButtonCancel() {

    }

    private void showFrList() {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //TODO make a way to switch between  FrList.TYPE_LIST and FrList.TYPE_GRID
//        Fragment fragment = FrList.newInstance(name, salary, FrList.TYPE_LIST);
        Fragment fragment = new FrList();
        ft.replace(R.id.container, fragment, FrList.TAG);
        ft.commit();

    }

}
