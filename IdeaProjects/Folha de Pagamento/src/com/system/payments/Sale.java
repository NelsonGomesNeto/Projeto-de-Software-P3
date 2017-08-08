package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class Sale extends Payment {

  public Sale(Date date, double saleValue) {

    super(date, saleValue);
  }

  public Sale(Sale sale) {

    super(sale);
  }

  public double getSaleValue() {
        return getValue();
    }

    @Override
    public String toString() {
      return "Sale{" +
              "date=" + getDate() +
              ", saleValue=" + getSaleValue() +
              '}';
    }
}
