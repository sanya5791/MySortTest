 package ua.sanya5791.mysorttest;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ua.sanya5791.mysorttest.Presenter.FrListPresenterImpl;
import ua.sanya5791.mysorttest.model.IFrList;
import ua.sanya5791.mysorttest.model.Singleton;
import ua.sanya5791.mysorttest.model.Worker;

 /**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FrList extends Fragment implements View.OnClickListener,
                                                AbsListView.OnItemClickListener,
                                                IFrList {

     public static final int TYPE_LIST = R.layout.fr_list1;
     public static final int TYPE_GRID = R.layout.fr_list_grid;
     public static final String TAG = FrList.class.getSimpleName();

     //     private static ArrayList<Worker> list;
     private ArrayList<Worker> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORKER_NAME = "param1";
    private static final String ARG_SALARY = "param2";
    private static final String ARG_LIST_TYPE = "param3";

    // TODO: Rename and change types of parameters
    private String newWorkerName;
    private double newWorkersSalary;
    private int typeLayout;

//    private OnFragmentInteractionListener mListener;
     private FrListPresenterImpl presenter;

     private Button mAddWorker;
     private Button mSortList;

     /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter mAdapter;
//    private ListAdapter mAdapter;

     /**
      * to get instance of FrList with parameters
      * @param workerName new worker to add to list
      * @param Salary average salary of the new worker
      * @param typeOfList choose ether regular list TYPE_LIST, or grid TYPE_GRID
      * @return new instance of FrList with specified parameters
      */
    public static FrList newInstance(String workerName, Double Salary, int typeOfList) {
        FrList fragment = new FrList();
        Bundle args = new Bundle();
        args.putString(ARG_WORKER_NAME, workerName);
        args.putDouble(ARG_SALARY, Salary);
        args.putInt(ARG_LIST_TYPE, typeOfList);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FrList() {
    }

     @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

         presenter = new FrListPresenterImpl(getActivity());
     }


     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            newWorkerName = getArguments().getString(ARG_WORKER_NAME);
            newWorkersSalary = getArguments().getDouble(ARG_SALARY);
            int type = getArguments().getInt(ARG_LIST_TYPE);
            if(type == TYPE_LIST){
                typeLayout = TYPE_LIST;
            }else if(type == TYPE_GRID){
                typeLayout = TYPE_GRID;
            }else {
                throw new IllegalArgumentException("TYPE_LIST must be either TYPE_LIST or TYPE_GRID");
            }
        }

        // Adapter to display your content
        if(list == null) {
            list = Singleton.getInstance();
        }

        mAdapter = new MyAdapter1(getActivity());
//        mAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                Log.i(TAG, "registerDataSetObserver onChanged!!!!!");
//                notifyDataSetChanged();
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //if there was not set a perticular layout - use regular list
        if (typeLayout == 0) {
            typeLayout = TYPE_LIST;
        }
        View view = inflater.inflate(typeLayout, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        mAddWorker = (Button) view.findViewById(R.id.bt_add_worker);
        mSortList = (Button) view.findViewById(R.id.bt_sort_list1);

        mAddWorker.setOnClickListener(this);
        mSortList.setOnClickListener(this);



//        fillWorkersList();

        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

//        mAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//            }
//        });

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }


     @Override
     public void onResume() {
         super.onResume();
         Log.i(TAG, "onResume():");
         mAdapter.notifyDataSetChanged();
     }

     @Override
     public void onPause() {
         super.onPause();
         Log.i(TAG, "onPause():");
     }

     @Override
     public void onClick(View v) {
         int id = v.getId();

         switch (id) {
             case R.id.bt_add_worker:
                 Toast.makeText(getActivity(), "Выберите сотрудника", Toast.LENGTH_SHORT).show();
                 presenter.dFrChooseTypeWorkerStart();
                 break;

             case R.id.bt_sort_list1:
                 Toast.makeText(getActivity(), "Выберите вид сортировки", Toast.LENGTH_SHORT).show();
                 presenter.dFrSortTypeChooser();

                 break;
         }
     }


     @Override
     public void onDetach() {
         super.onDetach();
         presenter = null;
     }


     @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != presenter) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Toast.makeText(getActivity(), "You pressed position: " + position +
                                            ", with id: " + id, Toast.LENGTH_SHORT).show();
//            presenter.addWorker(DummyContent.ITEMS.get(position).id);
        }
    }

     private void fillWorkersList() {

//         if(newWorkerName != null) {
//             Worker worker = new WorkerFixedPayed(newWorkerName, newWorkersSalary);
//             list.add(worker);
//         }

         Collections.sort(list, new Comparator<Worker>() {
             @Override
             public int compare(Worker lhs, Worker rhs) {
                 return lhs.getName().compareTo(rhs.getName());
             }
         });

     }


    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

     @Override
     public void notifyDataSetChanged() {
         mAdapter.notifyDataSetChanged();
     }

     /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void addWorker(String id);
        void dFrChooseTypeWorkerStart();
        void dFrSortTypeChooser();
    }



     private class MyAdapter1 extends ArrayAdapter <Worker> {

         public MyAdapter1(Context context) {
             super(context, android.R.layout.simple_list_item_2, list);
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {

             Worker worker = getItem(position);

             if(convertView == null){
                 convertView = LayoutInflater.from(getContext())
                         .inflate(android.R.layout.simple_list_item_2, null);
             }

             ((TextView) convertView.findViewById(android.R.id.text1))
                     .setText(worker.getName());
             ((TextView) convertView.findViewById(android.R.id.text2))
                     .setText(String.valueOf(worker.getAverageMonthSalary()));

             return convertView;
//             return super.getView(position, convertView, parent);
         }

     }

}
