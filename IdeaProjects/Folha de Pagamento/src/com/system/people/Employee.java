package com.system.people;

import com.system.payments.PaymentSchedule;
import com.system.payments.ServiceFee;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Employee {

  public int id;
  public String name;
  private String address;

  private String paymentMethod = "Bank Deposit";

  public int idInSyndicate;
  public boolean isPartOfSyndicate;
  private ArrayList<ServiceFee> fees = new ArrayList<>();

  public PaymentSchedule paymentSchedule = null;

  public Employee() {
  }

  public Employee(int id, String name, String address) {

    this.id = id;
    this.name = name;
    this.address = address;
  }

  public Employee(int id, String name, String address, String paymentMethod, int idInSyndicate, boolean isPartOfSyndicate, ArrayList<ServiceFee> fees) {

    this.id = id;
    this.name = name;
    this.address = address;
    this.paymentMethod = paymentMethod;
    this.idInSyndicate = idInSyndicate;
    this.isPartOfSyndicate = isPartOfSyndicate;
    this.fees = fees;
  }

  public int getId() {
    return id;
  }

  public ArrayList<ServiceFee> getFees() {
    return fees;
  }

  public void setPartOfSyndicate(boolean partOfSyndicate) {
    this.isPartOfSyndicate = partOfSyndicate;
  }

  public void setIdInSyndicate(int idInSyndicate) {

    this.idInSyndicate = idInSyndicate;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
    this.paymentSchedule = paymentSchedule;
  }

  public boolean launchServiceFee(Date date, double feeValue) {

    if (this.isPartOfSyndicate) {

      ServiceFee fee = new ServiceFee(date, feeValue);
      this.fees.add(fee);
      System.out.println("Service Fee successfully launched!\n");
      return(true);
    } else {

      System.out.println("This employee is not part of the syndicate!\n");
      return(false);
    }
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "id=" + id +
      ", name='" + name + '\'' +
      ", address='" + address + '\'';
  }
}
