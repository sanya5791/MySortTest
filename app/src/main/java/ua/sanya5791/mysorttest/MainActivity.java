package ua.sanya5791.mysorttest;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ua.sanya5791.mysorttest.model.WorkerFixedPayed;


public class MainActivity extends ActionBarActivity
        implements  DFrChooseTypeOfWorker.ChosenTypeWorker,
                    FrAddWorker.OnFragmentInteractionListener,
                    FrList.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on first start activity show empty FrList
        if(savedInstanceState == null){
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            //TODO make a way to switch between  FrList.TYPE_LIST and FrList.TYPE_GRID
//            Fragment fragment = new FrList.newInstance(name, salary, FrList.TYPE_LIST);
            Fragment fragment = new FrList();
            ft.replace(R.id.container, fragment, "FrList");
            ft.commit();
        }

//        mAddWorker = (Button) findViewById(R.id.bt_add_worker);
//        mSortList = (Button) findViewById(R.id.bt_sort_list1);
//
//        mAddWorker.setOnClickListener(this);
//        mSortList.setOnClickListener(this);
//
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//
//        switch (id) {
//            case R.id.bt_add_worker:
//                Toast.makeText(this, "Выберите сотрудника", Toast.LENGTH_SHORT).show();
//                showChooseTypeWorker();
//                break;
//
//            case R.id.bt_sort_list1:
//                Toast.makeText(this, "Выберите вид сортировки", Toast.LENGTH_SHORT).show();
//
//            break;
//        }
//    }
//
//    private void showChooseTypeWorker() {
//        DialogFragment dialog = new DFrChooseTypeOfWorker();
//        dialog.show(getFragmentManager(), DFrChooseTypeOfWorker.TAG);
//    }
//

    private void showChooseTypeWorker() {
        DialogFragment dialog = new DFrChooseTypeOfWorker();
        dialog.show(getFragmentManager(), DFrChooseTypeOfWorker.TAG);
    }

    @Override
    public void fixedPayed() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = new FrAddWorker();
        ft.replace(R.id.container, fragment, "FrAddWorker");
        ft.commit();

//        D:\Develop\AndroidStudioProjects\MySortTest\app\src\main\res\layout\fr_list.xml:
//        D:\Develop\AndroidStudioProjects\MySortTest\app\src\main\res\layout\fr_list.xml:layout/fr_list,
//        D:\Develop\AndroidStudioProjects\MySortTest\app\src\main\res\values\refs.xml:layout/fr_list
    }

    @Override
    public void hourlyPayed() {

    }

    @Override
    public void onButtonOk(String name, double salary) {
        WorkerFixedPayed workerFixedPayed = new WorkerFixedPayed(name, salary);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //TODO make a way to switch between  FrList.TYPE_LIST and FrList.TYPE_GRID
        Fragment fragment = FrList.newInstance(name, salary, FrList.TYPE_LIST);
//        Fragment fragment = new FrList();
        ft.replace(R.id.container, fragment, "FrList");
        ft.commit();

    }

    @Override
    public void onButtonCancel() {

    }

    @Override
    public void onFragmentInteraction(String id) {
        showChooseTypeWorker();

    }
}
