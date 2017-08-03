package com.system.people;

import com.system.payments.PaymentSchedule;
import com.system.payments.Sale;
import com.system.payments.ServiceFee;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Commissioned extends Employee {

  private double commissionFee;
  private ArrayList<Sale> sales = new ArrayList<>();

  public Commissioned() {
    super();
  }

  public Commissioned(int id, String name, String address) {
    super(id, name, address);
  }

  public Commissioned(int id, String name, String address, double commissionFee) {
    super(id, name, address);

    this.commissionFee = commissionFee;
    this.paymentSchedule = new PaymentSchedule("bi-weekly", 5);
  }

  public Commissioned(int id, String name, String address, String paymentMethod, int idInUnion, boolean isPartOfUnion, ArrayList<ServiceFee> fees) {
    super(id, name, address, paymentMethod, idInUnion, isPartOfUnion, fees);

    this.paymentSchedule = new PaymentSchedule("bi-weekly", 5);
  }

  public void launchSaleResult(Date date, double saleValue) {

    Sale sale = new Sale(date, saleValue);
    this.sales.add(sale);
  }

  public double getCommissionFee() {
    return this.commissionFee;
  }

  public int getNumberOfSales() {

    return this.sales.size();
  }

  public ArrayList<Sale> getSales() {
    return this.sales;
  }

  @Override
  public String toString() {
    return "Commissioned{" +
      super.toString() +
      ", commissionFee=" + commissionFee +
      ", sales=" + sales +
      '}';
  }
}
