package ua.sanya5791.mysorttest.model;

/**
 * Created by sanya on 20.05.2015.
 */
public class Worker {
    private String mName;
    private double mAverageMonthSalary;

    protected Worker(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }

    public void setAverageMonthSalary(double averageMonthSalary){
        mAverageMonthSalary = averageMonthSalary;
    }

    public double getAverageMonthSalary(){
        return mAverageMonthSalary;
    }

//    public String getAverageMonthSalary(){
//
//        return String.valueOf(mAverageMonthSalary);
//    }

    @Override
    public String toString() {
        return getName();
    }

//    abstract int averageMonthlySalary();
}
