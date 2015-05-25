package ua.sanya5791.mysorttest.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import ua.sanya5791.mysorttest.FrList;
import ua.sanya5791.mysorttest.MainActivity;
import ua.sanya5791.mysorttest.R;

/**
 * Created by sanya on 20.05.2015.
 */
public class MainActivityPresenterImpl implements MainActivity.MainActivityInterface{

    Activity curActivity;

    public MainActivityPresenterImpl(Activity curActivity){
        this.curActivity = curActivity;
    }

    @Override
    public void frListFirstStart() {
        FragmentManager manager = curActivity.getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //TODO make a way to switch between  FrList.TYPE_LIST and FrList.TYPE_GRID
//            Fragment fragment = new FrList.newInstance(name, salary, FrList.TYPE_LIST);
        Fragment fragment = new FrList();
        ft.replace(R.id.container, fragment, "FrList");
        ft.commit();
    }
}
