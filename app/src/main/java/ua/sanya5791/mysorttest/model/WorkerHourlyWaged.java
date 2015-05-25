package ua.sanya5791.mysorttest.model;

/**
 * Created by sanya on 20.05.2015.
 */
public class WorkerHourlyWaged extends Worker {
    double mPricePerHour = 20.8;
    int mHoursPerDay = 8;
    double mHourlyRate;

    public WorkerHourlyWaged(String mName, double hourlyRate) {
        super(mName);
        mHourlyRate = hourlyRate;
        averageMonthlySalary();
    }

    void averageMonthlySalary() {
        setAverageMonthSalary(mPricePerHour * mHoursPerDay * mHourlyRate);
    }
}
