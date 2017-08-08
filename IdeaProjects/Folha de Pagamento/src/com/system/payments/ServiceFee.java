package com.system.payments;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class ServiceFee extends Payment {

  public ServiceFee(Date date, double feeValue) {

    super(date, feeValue);
  }

  public ServiceFee(ServiceFee serviceFee) {
    super(serviceFee);
  }

  public double getFeeValue() {
    return getValue();
  }

  @Override
  public String toString() {
    return "ServiceFee{" +
      "date=" + getDate() +
      ", feeValue=" + getFeeValue() +
      '}';
  }
}
