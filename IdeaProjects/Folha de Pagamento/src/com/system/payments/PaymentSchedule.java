package com.system.payments;

import java.util.Calendar;
import java.util.Date;

public class PaymentSchedule {

  public String period;
  public int day; // 1 - Segunda, ..., 7 - Domingo, se for mensal, será interpretado como o day do mês

  public PaymentSchedule() {
  }

  public PaymentSchedule(String period, int day) {

    this.period = period;
    this.day = day;
  }

  public Date newPaymentDay(Date date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    if (this.period.equalsIgnoreCase("monthly")) {

      //calendar.add(Calendar.MONTH, 1);
      if (this.day == 32) {

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      } else {

        calendar.set(Calendar.DAY_OF_MONTH, this.day);
      }

      return(calendar.getTime());

    } else if (this.period.equalsIgnoreCase("weekly")) {

      calendar.add(Calendar.WEEK_OF_MONTH, 1);
      calendar.set(Calendar.DAY_OF_WEEK, this.day);

      return(calendar.getTime());

    } else {// bi-semanalmente

      calendar.add(Calendar.WEEK_OF_MONTH, 2);
      calendar.set(Calendar.DAY_OF_WEEK, this.day);

      return(calendar.getTime());

    }
  }
}
