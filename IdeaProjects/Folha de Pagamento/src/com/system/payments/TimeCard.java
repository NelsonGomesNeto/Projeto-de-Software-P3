package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class TimeCard extends Payment {

    public TimeCard(Date date, double workedHours) {

        super(date, workedHours);
    }

    public double getWorkedHours() {
        return super.getValue();
    }

    @Override
    public String toString() {
        return "TimeCard{" +
                "date=" + getDate() +
                ", workedHours=" + getWorkedHours() +
                '}';
    }
}
