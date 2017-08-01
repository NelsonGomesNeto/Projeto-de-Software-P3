package com.system.pagamentos;

import java.util.Calendar;
import java.util.Date;

public class AgendaDePagamento {

  public String período;
  public int dia; // 1 - Segunda, ..., 7 - Domingo, se for mensal, será interpretado como o dia do mês

  public AgendaDePagamento() {
  }

  public AgendaDePagamento(String período, int dia) {

    this.período = período;
    this.dia = dia;
  }

  public Date novaDataDePagamento(Date data) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(data);

    if (this.período.equalsIgnoreCase("mensalmente")) {

      //calendar.add(Calendar.MONTH, 1);
      if (this.dia == 32) {

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      } else {

        calendar.set(Calendar.DAY_OF_MONTH, this.dia);
      }

      return(calendar.getTime());

    } else if (this.período.equalsIgnoreCase("semanalmente")) {

      calendar.add(Calendar.WEEK_OF_MONTH, 1);
      calendar.set(Calendar.DAY_OF_WEEK, this.dia);

      return(calendar.getTime());

    } else {// bi-semanalmente

      calendar.add(Calendar.WEEK_OF_MONTH, 2);
      calendar.set(Calendar.DAY_OF_WEEK, this.dia);

      return(calendar.getTime());

    }
  }

}
