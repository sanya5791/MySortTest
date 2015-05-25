package ua.sanya5791.mysorttest.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ua.sanya5791.mysorttest.DFrSortTypeChooser;
import ua.sanya5791.mysorttest.FrList;
import ua.sanya5791.mysorttest.model.IFrList;
import ua.sanya5791.mysorttest.model.Singleton;
import ua.sanya5791.mysorttest.model.Worker;

/**
 * Created by sanya on 25.05.2015.
 */
public class DFrSortTypeChooserPresenterImpl implements DFrSortTypeChooser.ChosenSortingType {

    IFrList iFrList;

    Activity activity;
    public DFrSortTypeChooserPresenterImpl(Activity activity){
        this.activity = activity;
        FragmentManager fm = activity.getFragmentManager();
        Fragment fragment = fm.findFragmentByTag(FrList.TAG);
        if(fragment != null){
            try {
                iFrList = (IFrList)fragment;
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSortByName() {
        ArrayList<Worker> list = Singleton.getInstance();

        Collections.sort(list, new Comparator<Worker>() {
            @Override
            public int compare(Worker lhs, Worker rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        iFrList.notifyDataSetChanged();
    }

    @Override
    public void onSortBySalary() {
        ArrayList<Worker> list = Singleton.getInstance();

        Collections.sort(list, new Comparator<Worker>() {
            @Override
            public int compare(Worker lhs, Worker rhs) {
                return Double.compare(rhs.getAverageMonthSalary(), lhs.getAverageMonthSalary());
            }
        });
        iFrList.notifyDataSetChanged();
    }
}
