package ua.sanya5791.mysorttest;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ua.sanya5791.mysorttest.Presenter.FrAddWorkerPresenterImpl;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrAddWorker.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrAddWorker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrAddWorker extends Fragment {

    public static final int FIXED_SALARY = 0;
    public static final int HOUR_RATE = 1;

    private static final String TAG = FrAddWorker.class.getSimpleName();
    private static final boolean isDebug = true;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TYPE_SALARY = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mSalaryType;
    private String mParam2;

    private EditText etName;
    private EditText etSalary;

    private Button buttonOk;
    private Button buttonCancel;

    private OnFragmentInteractionListener presenter;
//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param salaryType you must choose between FIXED_SALARY and HOUR_RATE constants.
     * @return A new instance of fragment FrAddWorker.
     */
    public static FrAddWorker newInstance(int salaryType) {
//    public static FrAddWorker newInstance(String param1, String param2) {
        FrAddWorker fragment = new FrAddWorker();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE_SALARY, salaryType);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FrAddWorker() {
        // Required empty public constructor
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
        presenter = (OnFragmentInteractionListener) new FrAddWorkerPresenterImpl(getActivity());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSalaryType = getArguments().getInt(ARG_TYPE_SALARY);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fr_add_worker, container, false);

        etName = (EditText) v.findViewById(R.id.et_name);
        //select all text in if tv in focus
        etName.setSelectAllOnFocus(true);

        etSalary = (EditText) v.findViewById(R.id.et_salary);
        etSalary.setText("25000");
        etSalary.setSelectAllOnFocus(true);

        TextView tvSalary = (TextView) v.findViewById(R.id.tv_salary);
        if(mSalaryType == FIXED_SALARY){
            tvSalary.setText(R.string.month_salary);
        }else if(mSalaryType == HOUR_RATE) {
            tvSalary.setText(R.string.hour_rate);
        }else{
            throw new IllegalArgumentException("Wrong type of salary!!!");
        }


        buttonOk = (Button) v.findViewById(R.id.bt_ok);
        buttonCancel = (Button) v.findViewById(R.id.bt_cancel);


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();

                double salary = 0;
                try {
                    salary = Double.valueOf(etSalary.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    return;
                }

                Toast.makeText(getActivity(), "Name - " + name
                        + "; Salary - " + salary, Toast.LENGTH_LONG).show();
                switch (mSalaryType){
                    case FIXED_SALARY:
                        presenter.addFixedSalaryWorker(name, salary);

                        break;
                    case HOUR_RATE:
                        presenter.addHourRateWorker(name, salary);
                        break;
                };
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCancel();
            }
        });


        return v;
    }

    private void myLogger(String message) {
        if(isDebug){
            Log.i(TAG, message);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        presenter = null;
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
        public void addFixedSalaryWorker(String name, double salary);
        public void addHourRateWorker(String name, double salary);

        public void onButtonCancel();
    }

}
