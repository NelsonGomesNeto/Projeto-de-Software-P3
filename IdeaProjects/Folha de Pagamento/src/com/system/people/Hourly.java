package com.system.people;

import com.system.payments.PaymentSchedule;
import com.system.payments.TimeCard;
import com.system.payments.ServiceFee;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Hourly extends Employee {

  private double wage;
  private ArrayList<TimeCard> cards = new ArrayList<>();

  public Hourly() {
  }

  public Hourly(int ID, String name, String address, double wage) {
    super(ID, name, address);

    this.wage = wage;
    this.paymentSchedule = new PaymentSchedule("weekly", 5);
  }

  public Hourly(int ID, String name, String address, String paymentMethod, int idInUnion, boolean isPartOfUnion, ArrayList<ServiceFee> fees) {
    super(ID, name, address, paymentMethod, idInUnion, isPartOfUnion, fees);

    this.paymentSchedule = new PaymentSchedule("weekly", 5);
  }

  public void lançarCartãoDePonto(Date date, double workedHours) {

    TimeCard timeCard = new TimeCard(date, workedHours);
    this.cards.add(timeCard);
  }

  public int getQuantidadeDeCartoões() {

    return this.cards.size();
  }

  public ArrayList<TimeCard> getCards() {

    return this.cards;
  }

  public double getWage() {
    return this.wage;
  }

  @Override
  public String toString() {
    return "Hourly{" +
      super.toString() +
      ", wage=" + wage +
      ", cards=" + cards +
      '}';
  }
}
