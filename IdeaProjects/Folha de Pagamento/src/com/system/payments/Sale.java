package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class Sale {

    private Date date;
    private double saleValue;

    public Sale() {
    }

    public Sale(Date date, double saleValue) {

        this.date = date;
        this.saleValue = saleValue;
    }

    public Date getDate() {
        return this.date;
    }

    public double getSaleValue() {
        return this.saleValue;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "date=" + date +
                ", saleValue=" + saleValue +
                '}';
    }
}
