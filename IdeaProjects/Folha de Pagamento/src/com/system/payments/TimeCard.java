package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class TimeCard {

    private Date date;
    private double workedHours;

    public TimeCard(Date date, double workedHours) {

        this.date = date;
        this.workedHours = workedHours;
    }

    public Date getDate() {
        return this.date;
    }

    public double getWorkedHours() {
        return this.workedHours;
    }

    @Override
    public String toString() {
        return "TimeCard{" +
                "date=" + date +
                ", workedHours=" + workedHours +
                '}';
    }
}
