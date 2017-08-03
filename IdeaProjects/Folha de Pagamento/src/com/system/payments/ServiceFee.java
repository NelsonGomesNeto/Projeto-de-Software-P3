package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class ServiceFee {

    private Date date;
    private double feeValue;

    public ServiceFee() {
    }

    public ServiceFee(Date date, double feeValue) {

        this.date = date;
        this.feeValue = feeValue;
    }

    public Date getDate() {
        return date;
    }

    public double getFeeValue() {
        return feeValue;
    }

    @Override
    public String toString() {
        return "ServiceFee{" +
                "date=" + date +
                ", feeValue=" + feeValue +
                '}';
    }
}
