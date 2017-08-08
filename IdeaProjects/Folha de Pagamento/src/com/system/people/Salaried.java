package com.system.people;

import com.system.payments.PaymentSchedule;
import com.system.payments.ServiceFee;

import java.util.ArrayList;

/**
 * Created by alunoic on 28/07/17.
 */
public class Salaried extends Employee {

  double salary;

  public Salaried() {
  }

  public Salaried(Salaried salaried) {
    super(salaried);

    this.salary = salaried.salary;
  }

  public Salaried(int ID, String name, String address, double salary) {
    super(ID, name, address);

    this.salary = salary;
    this.paymentSchedule = new PaymentSchedule("monthly", 32);
  }

  public Salaried(int ID, String name, String address, String paymentMethod, int idInUnion, boolean isPartOfUnion, ArrayList<ServiceFee> fees) {
    super(ID, name, address, paymentMethod, idInUnion, isPartOfUnion, fees);

    this.paymentSchedule = new PaymentSchedule("monthly", 32);
  }

  public double getSalary() {
    return this.salary;
  }

  @Override
  public String toString() {
    return "Salaried{" +
      super.toString() +
      ", salary=" + salary +
      '}';
  }
}
