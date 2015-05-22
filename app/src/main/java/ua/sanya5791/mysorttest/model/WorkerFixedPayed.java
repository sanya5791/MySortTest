package ua.sanya5791.mysorttest.model;

/**
 * Created by sanya on 20.05.2015.
 */
public class WorkerFixedPayed extends Worker{

    double fixedPayment;

    public WorkerFixedPayed(String name, double fixedPayment) {
        super(name);
        this.fixedPayment = fixedPayment;
        averageMonthlySalary();
    }

    void averageMonthlySalary() {
        setAverageMonthSalary(fixedPayment);
//        return 0;
    }

//    @Override
//    public String toString() {
//        return getName();
//    }
}
